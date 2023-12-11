package tdd.ita.semana03.pratice;

public class CashMachine {

    private final RemoteService remoteService;

    public CashMachine(RemoteService remoteService) {
        this.remoteService = remoteService;
    }

    public String log(int accountId, String password) {
        var checkingAccount = remoteService.recoveryAccountInfo(accountId)
                .orElseThrow(() -> new CheckingAccountNotFoundException("Checking Account with id: " + accountId + " was not found."));

        return "Authenticated User";
    }
}
