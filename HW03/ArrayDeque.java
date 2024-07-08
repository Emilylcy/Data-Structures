/**
 * A class that implements the Deque data structure.
 * 
 * @param <E> - supports generics
 * @author Emily Lu
 */
public class ArrayDeque<E> implements Deque<E> {
    private E[] data;
    private int front;
    private int size;

    /**
     * Constructs an empty queue with capacity to a size 100.
     */    

    public ArrayDeque() {
        this(100);
    }

    /**
     * Constructs an empty queue with max capacity.
     * @param capacity gives the array size
     */    
    public ArrayDeque(int capacity) {
        data = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E first() {
        if (isEmpty()) {
            return null;
        }
        return data[front];
    }

    @Override
    public E last() {
        if (isEmpty()) {
            return null;
        }
        int last = (front + size - 1) % data.length;
        return data[last];
    }

    @Override
    public void addFirst(E e) {
        if (size == data.length) {
            throw new RuntimeException("Deque is full");
        }
        front = (front - 1 + data.length) % data.length;
        data[front] = e;
        size++;
    }

    @Override
    public void addLast(E e) {
        if (size == data.length) {
            throw new RuntimeException("Deque is full");
        }
        int last = (front + size) % data.length;
        data[last] = e;
        size++;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        E removed = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        return removed;
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            return null;
        }
        int last = (front + size - 1) % data.length;
        E removed = data[last];
        data[last] = null;
        size--;
        return removed;
    }
}
