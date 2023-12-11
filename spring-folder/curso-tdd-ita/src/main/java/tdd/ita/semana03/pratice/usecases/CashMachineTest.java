package tdd.ita.semana03.pratice.usecases;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tdd.ita.semana03.pratice.ports.RemoteService;
import tdd.ita.semana03.pratice.entity.CheckingAccount;
import tdd.ita.semana03.pratice.exceptions.CheckingAccountNotFoundException;
import tdd.ita.semana03.pratice.exceptions.UnauthenticatedUserException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CashMachineTest {

    @InjectMocks
    private CashMachine cashMachine;

    @Mock
    private RemoteService remoteService;

    @Test
    public void log_ShouldReturnAutenticatedMessage_WhenValidValuesAreProvided() {
        var account = new CheckingAccount(1,"12345", 0);

        when(remoteService.recoveryAccountInfo(anyInt()))
                .thenReturn(Optional.of(account));

        var message = cashMachine.log(account.accountId(), account.password());
        assertEquals(message, "Authenticated User");
    }

    @Test
    public void log_ShouldThrow_WhenCheckingAccountWasNotFound() {
        var account = new CheckingAccount(1,"12345", 0);

        when(remoteService.recoveryAccountInfo(anyInt()))
                .thenReturn(Optional.empty());

        assertThrows(CheckingAccountNotFoundException.class,
                () -> cashMachine.log(account.accountId(), account.password()));
    }

    @Test
    public void log_ShouldThrow_WhenInvalidPasswordIsProvided() {
        var account = new CheckingAccount(1,"12345", 0);

        when(remoteService.recoveryAccountInfo(anyInt()))
                .thenReturn(Optional.of(account));

        assertThrows(UnauthenticatedUserException.class,
                () -> cashMachine.log(account.accountId(), "invalid_password"));
    }

    @Test
    public void balance_ShouldReturnBalanceMessage() {
        var account = new CheckingAccount(1,"12345", 0);

        when(remoteService.recoveryAccountInfo(anyInt()))
                .thenReturn(Optional.of(account));

        cashMachine.log(account.accountId(), account.password());
        var message = cashMachine.balance();
        assertEquals(message, String.format("The balance is $%.2f", account.balance()));
    }
}
