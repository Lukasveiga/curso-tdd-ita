package tdd.ita.semana03.pratice.usecases;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tdd.ita.semana03.pratice.exceptions.RemoteServiceException;
import tdd.ita.semana03.pratice.ports.RemoteService;
import tdd.ita.semana03.pratice.entity.CheckingAccount;
import tdd.ita.semana03.pratice.exceptions.CheckingAccountNotFoundException;
import tdd.ita.semana03.pratice.exceptions.UnauthenticatedUserException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CashMachineTest {

    @InjectMocks
    private CashMachine cashMachine;

    @Mock
    private RemoteService remoteService;

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
}
