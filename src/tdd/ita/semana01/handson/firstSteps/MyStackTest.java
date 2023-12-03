package tdd.ita.semana01.handson.firstSteps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyStackTest {

    private MyStack<String> stack;
    @BeforeEach
    public void initStack() {
        stack = new MyStack<>();
    }

    @Test
    public void emptyStack() {
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    public void stackAnElement() {
        stack.add("first");
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());
        assertEquals("first", stack.peek());
    }

    @Test
    public void stackAndUnstackTwoElement() {
        stack.add("first");
        stack.add("second");
        assertEquals(2, stack.size());
        assertEquals("second", stack.peek());
        String unstack = stack.pop();
        assertEquals(1, stack.size());
        assertEquals("first", stack.peek());
        assertEquals("second", unstack);
    }

    @Test
    public void popElementFromEmptyStack() {
        assertThrows(EmptyMyStackException.class, () -> stack.pop());
    }

    @Test
    public void peekElementFromEmptyStack() {
        assertThrows(EmptyMyStackException.class, () -> stack.peek());
    }
}
