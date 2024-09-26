package tiei.aads.adt;

import java.util.*;
import java.math.*;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class StackQueueTest {

    private StackQueue<Integer> stackQueue;

    @Before
    public void setUp() {
        stackQueue = new StackQueue<>();
    }

    @Test
    public void poll_ShouldReturnElementAndThrowExceptionWhenQueueIsEmpty() {
        assertThrows(EmptyQueueException.class, () -> stackQueue.poll());
    }

    @Test
    public void poll_ShouldReturnAndRemoveTheFirstElementAdded() throws EmptyQueueException {
        stackQueue.offer(1);
        stackQueue.offer(2);
        stackQueue.offer(3);

        assertEquals(Integer.valueOf(1), stackQueue.poll());
        assertEquals(Integer.valueOf(2), stackQueue.poll());
        assertEquals(Integer.valueOf(3), stackQueue.poll());
    }

    @Test
    public void poll_ShouldShiftElementsBetweenStacks() throws EmptyQueueException {
        stackQueue.offer(1);
        stackQueue.offer(2);
        stackQueue.offer(3);

        stackQueue.poll(); // Should shift elements to stack2

        assertEquals(Integer.valueOf(2), stackQueue.poll());
        assertEquals(Integer.valueOf(3), stackQueue.poll());
    }

    @Test
    public void poll_ShouldThrowExceptionWhenBothStacksAreEmpty() throws EmptyQueueException {
        stackQueue.offer(1);
        stackQueue.poll(); // Remove the element

        assertThrows(EmptyQueueException.class, () -> stackQueue.poll());
    }
}
