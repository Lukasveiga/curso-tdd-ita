package tdd.ita.semana03.pratice.usecases;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tdd.ita.semana03.pratice.entity.CreditCard;
import tdd.ita.semana03.pratice.exceptions.*;
import tdd.ita.semana03.pratice.ports.Hardware;
import tdd.ita.semana03.pratice.ports.RemoteService;
import tdd.ita.semana03.pratice.entity.CheckingAccount;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CashMachineTest {

    @InjectMocks
    private CashMachine cashMachine;

    @Mock
    private RemoteService remoteService;

    @Mock
    private Hardware hardware;

    @Test
    public void log_ShouldReturnAutenticatedMessage_WhenValidValuesAreProvided() {
        var account = new CheckingAccount(1,"12345");

        when(remoteService.recoveryAccountInfo(anyInt()))
                .thenReturn(Optional.of(account));

        var message = cashMachine.log(account.getAccountId(), account.getPassword());
        assertEquals("Authenticated User", message);
    }

    @Test
    public void log_ShouldThrow_WhenCheckingAccountWasNotFound() {
        var account = new CheckingAccount(1,"12345");

        when(remoteService.recoveryAccountInfo(anyInt()))
                .thenReturn(Optional.empty());

        assertThrows(CheckingAccountNotFoundException.class,
                () -> cashMachine.log(account.getAccountId(), account.getPassword()));
    }

    @Test
    public void log_ShouldThrow_WhenInvalidPasswordIsProvided() {
        var account = new CheckingAccount(1,"12345");

        when(remoteService.recoveryAccountInfo(anyInt()))
                .thenReturn(Optional.of(account));

        assertThrows(UnauthenticatedUserException.class,
                () -> cashMachine.log(account.getAccountId(), "invalid_password"));
    }

    @Test
    public void balance_ShouldReturnBalanceMessage() {
        var account = new CheckingAccount(1,"12345");

        when(remoteService.recoveryAccountInfo(anyInt()))
                .thenReturn(Optional.of(account));

        cashMachine.log(account.getAccountId(), account.getPassword());
        var message = cashMachine.balance();
        assertEquals(String.format("The balance is $%.2f", account.getBalance()), message);
    }

    @Test
    public void deposit_ShouldReturnDepositMessage_WhenDepositMadeSuccessfully() {
        var account = new CheckingAccount(1,"12345");

        when(remoteService.recoveryAccountInfo(anyInt()))
                .thenReturn(Optional.of(account));

        cashMachine.log(account.getAccountId(), account.getPassword());
        var depositMessage = cashMachine.deposit(500);
        var balanceMessage = cashMachine.balance();

        assertEquals("Deposit received successfully", depositMessage);
        assertEquals(String.format("The balance is $%.2f", account.getBalance()), balanceMessage);
    }

    @Test
    public void deposit_ShouldThrow_WhenRemoteServiceThrows() {
        var account = new CheckingAccount(1,"12345");

        when(remoteService.recoveryAccountInfo(anyInt()))
                .thenReturn(Optional.of(account));

        cashMachine.log(account.getAccountId(), account.getPassword());

        doThrow(RemoteServiceException.class).when(remoteService).persistAccountChange(account);

        assertThrows(RemoteServiceException.class,
                () -> cashMachine.deposit(500));
    }

    @Test
    public void withdraw_ShouldReturnWithdrawMessage_WhenWithdrawMadeSuccessfully() {
        var account = new CheckingAccount(1,"12345");

        when(remoteService.recoveryAccountInfo(anyInt()))
                .thenReturn(Optional.of(account));

        cashMachine.log(account.getAccountId(), account.getPassword());
        cashMachine.deposit(500);
        var withdrawMessage = cashMachine.withdraw(500);

        assertEquals("Withdraw your money", withdrawMessage);
    }

    @Test
    public void withdraw_ShouldReturnInsufficientFundsMessage_WhenWCheckingAccountDontHaveEnoughMoney() {
        var account = new CheckingAccount(1,"12345");

        when(remoteService.recoveryAccountInfo(anyInt()))
                .thenReturn(Optional.of(account));

        cashMachine.log(account.getAccountId(), account.getPassword());
        var withdrawMessage = cashMachine.withdraw(500);

        assertEquals("Insufficient Funds", withdrawMessage);
    }

    @Test
    public void withdraw_ShouldThrow_WhenRemoteServiceThrows() {
        var account = new CheckingAccount(1,"12345");

        when(remoteService.recoveryAccountInfo(anyInt()))
                .thenReturn(Optional.of(account));

        cashMachine.log(account.getAccountId(), account.getPassword());
        cashMachine.deposit(500);

        doThrow(RemoteServiceException.class).when(remoteService).persistAccountChange(account);

        assertThrows(RemoteServiceException.class,
                () -> cashMachine.withdraw(500));
    }

    @Test
    void readCreditCard_ShouldReturnAccountId_WhenSuccess() {
        var creditCart = new CreditCard("12345",
                LocalDate.now().plusMonths(5));

        when(hardware.catchAccountIdFromCreditCard(any(CreditCard.class)))
                .thenReturn(creditCart.accountId());

        var result = this.cashMachine.readCreditCard(creditCart);

        assertEquals(result, creditCart.accountId());
    }

    @Test
    void readCreditCard_ShouldReturnThrow_WhenCreditCarWithExpiredValidityIsProvided() {
        var creditCart = new CreditCard("12345",
                LocalDate.now().minusMonths(1));

        doThrow(ExpiredCreditCardValidityException.class)
                .when(hardware).catchAccountIdFromCreditCard(creditCart);

        assertThrows(ExpiredCreditCardValidityException.class,
                () -> this.cashMachine.readCreditCard(creditCart));
    }

    @Test
    void withdrawCreditCard_ReturnSuccessMessage() {
        var creditCart = new CreditCard("12345",
                LocalDate.now().plusMonths(5));

        var amount = 100.0;

        when(hardware.catchAccountIdFromCreditCard(creditCart))
                .thenReturn(creditCart.accountId());

        doNothing().when(hardware)
                .withdrawFromCreditCard(creditCart.accountId(), amount);

        var result = this.cashMachine.withdrawCreditCard(creditCart, amount);

        assertEquals(result, "Withdraw with Credit Card Successfully");
    }

    @Test
    void withdrawCreditCard_ShouldReturnThrow_WhenCreditCarWithExpiredValidityIsProvided() {
        var creditCart = new CreditCard("12345",
                LocalDate.now().plusMonths(5));

        var amount = 100.0;

        doThrow(ExpiredCreditCardValidityException.class)
                .when(hardware).catchAccountIdFromCreditCard(creditCart);

        assertThrows(ExpiredCreditCardValidityException.class,
                () -> this.cashMachine.withdrawCreditCard(creditCart, amount));

        verify(hardware, times(0)).withdrawFromCreditCard(creditCart.accountId(), amount);
    }

    @Test
    void withdrawCreditCard_ShouldReturnThrow_AccountDontHaveInsufficientFunds() {
        var creditCart = new CreditCard("12345",
                LocalDate.now().plusMonths(5));

        var amount = 100.0;

        when(hardware.catchAccountIdFromCreditCard(creditCart))
                .thenReturn(creditCart.accountId());

        doThrow(InsuficientFundsException.class)
                .when(hardware).withdrawFromCreditCard(creditCart.accountId(), amount);

        assertThrows(InsuficientFundsException.class,
                () -> this.cashMachine.withdrawCreditCard(creditCart, amount));
    }
}
