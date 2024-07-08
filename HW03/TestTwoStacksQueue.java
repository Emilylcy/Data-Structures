import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Class that tests the TwoStacksQueue.
 * 
 * @author Emily Lu
 **/
public class TestTwoStacksQueue {
    
    /**
     * Tests the enqueue and dequeue operations of the TwoStacksQueue.
     */
    @Test
    public void testEnqueueDequeue() {
        TwoStacksQueue<Integer> queue = new TwoStacksQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals(Integer.valueOf(1), queue.dequeue());
        assertEquals(Integer.valueOf(2), queue.dequeue());
        assertEquals(Integer.valueOf(3), queue.dequeue());
        assertNull(queue.dequeue());
    }

    /**
     * Tests the first() method of the TwoStacksQueue.
     */
    @Test
    public void testFirst() {
        TwoStacksQueue<Integer> queue = new TwoStacksQueue<>();
        assertNull(queue.first());
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(Integer.valueOf(1), queue.first());
        queue.dequeue();
        assertEquals(Integer.valueOf(2), queue.first());
    }

    /**
     * Tests the size() method of the TwoStacksQueue.
     */
    @Test
    public void testSize() {
        TwoStacksQueue<Integer> queue = new TwoStacksQueue<>();
        assertEquals(0, queue.size());
        queue.enqueue(1);
        assertEquals(1, queue.size());
        queue.dequeue();
        assertEquals(0, queue.size());
    }

    /**
     * Tests the isEmpty() method of the TwoStacksQueue.
     */
    @Test
    public void testIsEmpty() {
        TwoStacksQueue<Integer> queue = new TwoStacksQueue<>();
        assertEquals(true, queue.isEmpty());
        queue.enqueue(1);
        assertEquals(false, queue.isEmpty());
        queue.dequeue();
        assertEquals(true, queue.isEmpty());
    }

    /**
     * Tests the toString() method of the TwoStacksQueue.
     */
    @Test
    public void testToString() {
        TwoStacksQueue<Integer> queue = new TwoStacksQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals("(1, 2, 3)", queue.toString());
    }
}
