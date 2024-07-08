import java.util.Stack;

/**
 * Represents a stack data structure that supports 
 * finding the minimum element in the stack.
 * 
 * @param <E> the type of elements stored in the stack, must be comparable
 * @author Emily Lu
 */
public class NewStack<E extends Comparable<E>> {
    private Stack<E> stack;
    private Stack<E> minStack;

    /**
     * Constructs a new NewStack with an empty stack and a minStack.
     */
    public NewStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    /**
     * Returns the number of elements in the stack.
     * 
     * @return the number of elements in the stack
     */
    public int size() {
        return stack.size();
    }

    /**
     * Checks if the stack is empty.
     * 
     * @return true if the stack is empty
     */
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    /**
     * Pushes an element onto the stack.
     * 
     * @param e the element to be pushed onto the stack
     */
    public void push(E e) {
        stack.push(e);
        if (minStack.isEmpty() || e.compareTo(minStack.peek()) <= 0) {
            minStack.push(e);
        }
    }

    /**
     * Removes and returns the top element of the stack.
     * 
     * @return the top element of the stack, or null if the stack is empty
     */
    public E pop() {
        if (stack.isEmpty()) {
            return null;
        }
        E top = stack.pop();
        if (top.equals(minStack.peek())) {
            minStack.pop();
        }
        return top;
    }

    /**
     * Returns the top element of the stack without removing it.
     * 
     * @return the top element of the stack, or null if the stack is empty
     */
    public E top() {
        if (stack.isEmpty()) {
            return null;
        } else {
            return stack.peek();
        }
    }
    
    /**
     * Returns the minimum element currently in the stack.
     * 
     * @return the minimum element in the stack, or null if the stack is empty
     */
    public E minElement() {
        if (minStack.isEmpty()) {
            return null;
        } else {
            return minStack.peek();
        }
    }

    /**
     * Returns a string representation of the stack.
     * 
     * @return a string representation of the stack
     */
    public String toString() {
        return stack.toString();
    }
}
