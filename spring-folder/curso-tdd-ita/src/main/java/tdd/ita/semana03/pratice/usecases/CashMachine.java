package tdd.ita.semana03.pratice.usecases;

import tdd.ita.semana03.pratice.entity.CheckingAccount;
import tdd.ita.semana03.pratice.ports.RemoteService;
import tdd.ita.semana03.pratice.exceptions.CheckingAccountNotFoundException;
import tdd.ita.semana03.pratice.exceptions.UnauthenticatedUserException;

public class CashMachine {

    private CheckingAccount autenticatedUser;

    private final RemoteService remoteService;

    public CashMachine(RemoteService remoteService) {
        this.remoteService = remoteService;
    }

    public String log(int accountId, String password) {
        var checkingAccount = remoteService.recoveryAccountInfo(accountId)
                .orElseThrow(() -> new CheckingAccountNotFoundException("Checking Account with id: " + accountId + " was not found."));

        if(!checkingAccount.password().equals(password)) {
            throw new UnauthenticatedUserException("Unauthenticated User");
        }

        autenticatedUser = checkingAccount;

        return "Authenticated User";
    }

    public String balance() {
       return String.format("The balance is $%.2f", autenticatedUser.balance());
    }
}
