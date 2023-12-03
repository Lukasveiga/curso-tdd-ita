package tdd.ita.semana01.handson.firstSteps;

import org.junit.jupiter.api.Test;;import static org.junit.jupiter.api.Assertions.*;

public class StackTest {

    @Test
    public void emptyStack() {
        MyStack stack = new MyStack();
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    public void stackAnElement() {
        MyStack stack = new MyStack();
        stack.add("first");
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());
        assertEquals("primeiro", stack.peek());
    }
}
