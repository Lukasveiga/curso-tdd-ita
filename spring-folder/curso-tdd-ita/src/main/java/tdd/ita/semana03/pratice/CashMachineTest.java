package tdd.ita.semana03.pratice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CashMachineTest {

    private CashMachine cashMachine;

    @BeforeEach
    public void createCachMachineInstance() {
        cashMachine = new CashMachine();
    }

    @Test
    public void log_ShouldReturnAutenticatedMessage_WhenValidValuesAreProvided() {
        var account = new CheckingAccount(1,"12345", 0);
        var message = cashMachine.log(account);
        assertEquals(message, "Authenticated User");
    }
}
