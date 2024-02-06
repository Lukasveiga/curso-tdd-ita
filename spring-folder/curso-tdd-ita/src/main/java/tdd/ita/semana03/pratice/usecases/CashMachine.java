package tdd.ita.semana03.pratice.usecases;

import tdd.ita.semana03.pratice.entity.CheckingAccount;
import tdd.ita.semana03.pratice.entity.CreditCard;
import tdd.ita.semana03.pratice.exceptions.*;
import tdd.ita.semana03.pratice.ports.Hardware;
import tdd.ita.semana03.pratice.ports.RemoteService;

public class CashMachine {

    private CheckingAccount authenticatedUser;

    private final RemoteService remoteService;

    private final Hardware hardware;

    public CashMachine(RemoteService remoteService, Hardware hardware) {
        this.remoteService = remoteService;
        this.hardware = hardware;
    }

    public String log(int accountId, String password) {
        var checkingAccount = remoteService.recoveryAccountInfo(accountId)
                .orElseThrow(() -> new CheckingAccountNotFoundException("Checking Account with id: " + accountId + " was not found."));

        if(!checkingAccount.getPassword().equals(password)) {
            throw new UnauthenticatedUserException("Unauthenticated User");
        }

        authenticatedUser = checkingAccount;

        return "Authenticated User";
    }

    public String balance() {
        return String.format("The balance is $%.2f", authenticatedUser.getBalance());
    }

    public String deposit(double depositValue) {
        try {
            authenticatedUser.updateBalance(authenticatedUser.getBalance() + depositValue);
            remoteService.persistAccountChange(authenticatedUser);
        } catch (RemoteServiceException e) {
            throw new RemoteServiceException("Internal Server Error");
        }

        return "Deposit received successfully";
    }

    public String withdraw(int withdrawValue) {
        var convertWithdrawValue = (double) withdrawValue / 100;

        if(convertWithdrawValue > authenticatedUser.getBalance()) {
            return "Insufficient Funds";
        }

        try {
            authenticatedUser.updateBalance(authenticatedUser.getBalance() - convertWithdrawValue);
            remoteService.persistAccountChange(authenticatedUser);
        } catch (RemoteServiceException e) {
            throw new RemoteServiceException("Internal Server Error");
        }

        return "Withdraw your money";
    }

    public String readCreditCard(CreditCard creditCard) {
        try {
            return this.hardware.catchAccountIdFromCreditCard(creditCard);
        } catch (ExpiredCreditCardValidityException ex) {
            throw new ExpiredCreditCardValidityException("Credit card has expired validity");
        }
    }

    public String withdrawCreditCard(CreditCard creditCard, Double amount) {
        var accountId = this.hardware.catchAccountIdFromCreditCard(creditCard);
        try {
            this.hardware.withdrawFromCreditCard(accountId, amount);
        } catch (InsuficientFundsException ex) {
            throw new InsuficientFundsException("Insuficient funds");
        }

        return "Withdraw with Credit Card Successfully";
    }
}
