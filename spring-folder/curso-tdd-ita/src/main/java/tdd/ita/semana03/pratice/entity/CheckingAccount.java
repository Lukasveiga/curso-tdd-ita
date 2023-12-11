package tdd.ita.semana03.pratice.entity;

import java.util.concurrent.atomic.AtomicInteger;

public class CheckingAccount {

    int accountId;
    String password;
    AtomicInteger balance;

    public CheckingAccount(int accountId, String password) {
        this.accountId = accountId;
        this.password = password;
        balance = new AtomicInteger(0);
    }

    public int getAccountId() {
        return accountId;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return (double) balance.get() / 100;
    }

    public void updateBalance(double newBalance) {
        var convertedBalance = (int) newBalance * 100;
        this.balance.set(convertedBalance);
    }
}
