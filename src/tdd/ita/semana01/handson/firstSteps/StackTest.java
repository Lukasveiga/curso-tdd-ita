package tdd.ita.semana01.handson.firstSteps;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StackTest {

    @Test
    public void emptyStack() {
        var stack = new MyStack<String>();
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    public void stackAnElement() {
        var stack = new MyStack<String>();
        stack.add("first");
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());
        assertEquals("first", stack.peek());
    }

    @Test
    public void stackTwoElement() {
        var stack = new MyStack<String>();
        stack.add("first");
        stack.add("second");
        assertEquals(2, stack.size());
        assertEquals("second", stack.peek());
    }
}
