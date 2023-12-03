package tdd.ita.semana01.handson.firstSteps;

import org.junit.jupiter.api.Test;;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StackTest {

    @Test
    public void emptyStack() {
        MyStack stack = new MyStack();
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }
}
