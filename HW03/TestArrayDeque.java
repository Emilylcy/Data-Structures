import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * A class that contains JUnit tests for the ArrayDeque class.
 */
public class TestArrayDeque {

    /**
     * Tests the add and remove operations at the front of the deque.
     */
    @Test
    public void testAddRemoveFirst() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        assertNull(deque.removeFirst());
        deque.addFirst(1);
        deque.addFirst(2);
        assertEquals(Integer.valueOf(2), deque.removeFirst());
        assertEquals(Integer.valueOf(1), deque.removeFirst());
    }

    /**
     * Tests the add and remove operations at the end of the deque.
     */
    @Test
    public void testAddRemoveLast() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        assertNull(deque.removeLast());
        deque.addLast(1);
        deque.addLast(2);
        assertEquals(Integer.valueOf(2), deque.removeLast());
        assertEquals(Integer.valueOf(1), deque.removeLast());
    }

    /**
     * Tests getting the first element in the deque.
     */
    @Test
    public void testFirst() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        assertNull(deque.first());
        deque.addFirst(1);
        deque.addFirst(2);
        assertEquals(Integer.valueOf(2), deque.first());
        deque.removeFirst();
        assertEquals(Integer.valueOf(1), deque.first());
    }

    /**
     * Tests the retrieval of the last element in the deque.
     */
    @Test
    public void testLast() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        assertNull(deque.last());
        deque.addLast(1);
        deque.addLast(2);
        assertEquals(Integer.valueOf(2), deque.last());
        deque.removeLast();
        assertEquals(Integer.valueOf(1), deque.last());
    }

    /**
     * Tests the size method of the deque.
     */
    @Test
    public void testSize() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        assertEquals(0, deque.size());
        deque.addFirst(1);
        assertEquals(1, deque.size());
        deque.addLast(2);
        assertEquals(2, deque.size());
    }

    /**
     * Tests the isEmpty method of the deque.
     */
    @Test
    public void testIsEmpty() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        assertEquals(true, deque.isEmpty());
        deque.addFirst(1);
        assertEquals(false, deque.isEmpty());
        deque.removeFirst();
        assertEquals(true, deque.isEmpty());
    }
}
