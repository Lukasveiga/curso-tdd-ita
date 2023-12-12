package tdd.ita.semana03.pratice.usecases;

import tdd.ita.semana03.pratice.entity.CheckingAccount;
import tdd.ita.semana03.pratice.exceptions.RemoteServiceException;
import tdd.ita.semana03.pratice.ports.RemoteService;
import tdd.ita.semana03.pratice.exceptions.CheckingAccountNotFoundException;
import tdd.ita.semana03.pratice.exceptions.UnauthenticatedUserException;

public class CashMachine {

    private CheckingAccount authenticatedUser;

    private final RemoteService remoteService;

    public CashMachine(RemoteService remoteService) {
        this.remoteService = remoteService;
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
}
