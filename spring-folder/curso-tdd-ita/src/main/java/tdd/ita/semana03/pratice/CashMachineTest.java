package tdd.ita.semana03.pratice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
}
