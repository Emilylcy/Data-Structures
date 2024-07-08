/**
 * the class will implement Queue interface and store two ArrayStack objects.
 * @author Emily Lu
 * @param <E> - supports generics
 */
public class TwoStacksQueue<E> implements Queue<E> {
    private ArrayStack<E> stack1;
    private ArrayStack<E> stack2;

    /**
     * Constructs an empty queue with max capacity.
     */    
    public TwoStacksQueue() {
        this.stack1 = new ArrayStack<>();
        this.stack2 = new ArrayStack<>();
    }

    /**
     * Constructs an empty queue with capacity of 100.
     * @param capacity - sets the array’s capacity to a size 100
     */
    public TwoStacksQueue(int capacity) {
        this.stack1 = new ArrayStack<>(100);
        this.stack2 = new ArrayStack<>(100);
    }

    @Override
    public int size() {
        return stack1.size() + stack2.size();
    }

    @Override
    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        stack1.push(e);
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    @Override
    public E first() {
        if (isEmpty()) {
            return null;
        }
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.top();
    }

    /**
     * Provides a string representation of the queue.
     * 
     * @return string representation of the queue
     */
    public String toString() {
        ArrayStack<E> tempStack = new ArrayStack<>();
    
        while (!stack1.isEmpty()) {
            tempStack.push(stack1.pop());
        }

        String result = "(";
        while (!tempStack.isEmpty()) {
            E element = tempStack.pop();
            result += element.toString();
            if (!tempStack.isEmpty()) {
                result += ", ";
            }
            stack1.push(element); 
        }
        result += ")";

        if (!stack2.isEmpty()) {
            result += ", ";
            result += stack2.toString();
        }
    
        return result;
    }  
    
}
