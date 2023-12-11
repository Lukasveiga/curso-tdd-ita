package tdd.ita.semana03.pratice.entity;

public class CheckingAccount {

    int accountId;
    String password;
    double balance;

    public CheckingAccount(int accountId, String password, double balance) {
        this.accountId = accountId;
        this.password = password;
        this.balance = balance;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public void updateBalance(double newBalance) {
        this.balance = newBalance;
    }
}
