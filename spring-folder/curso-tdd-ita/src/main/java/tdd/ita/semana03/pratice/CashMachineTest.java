package tdd.ita.semana03.pratice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CashMachineTest {

    @InjectMocks
    private CashMachine cashMachine;

    @Mock
    private UserRepository userRepository;

    @Test
    public void log_ShouldReturnAutenticatedMessage_WhenValidValuesAreProvided() {
        var account = new CheckingAccount(1,"12345", 0);
        var message = cashMachine.log(account.accountId(), account.password());
        assertEquals(message, "Authenticated User");
    }
}
