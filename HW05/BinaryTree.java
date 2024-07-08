/**
 * A BinaryTree interface that defines the methods of a binary tree.
 *
 * @param <E> generics
 */
public interface BinaryTree<E extends Comparable<E>> {
    
    /**
     * Gets the root element of the tree.
     *
     * @return the root element of the tree
     */
    E getRootElement();
    
    /**
     * Returns the number of elements in the tree.
     *
     * @return the number of elements in the tree
     */
    int size();
    
    /**
     * Checks if the tree is empty.
     *
     * @return true if the tree has no elements
     */
    boolean isEmpty();
    
    /**
     * Determines whether an element exists within the tree.
     *
     * @param element the element to be checked
     * @return true if the tree contains the specified element
     */
    boolean contains(E element);
    
    /**
     * Inserts an element into the tree.
     *
     * @param element the element to be inserted
     */
    void insert(E element);
    
    /**
     * Removes an element from the tree.
     *
     * @param element the element to be removed 
     * @return true if the element was successfully removed
     */
    boolean remove(E element);
    
    /**
     * Returns a string representation of in-order traversal.
     *
     * @return string of in-order traversal
     */
    String toStringInOrder();
    
    /**
     * Returns a string representation of pre-order traversal.
     *
     * @return string of pre-order traversal
     */
    String toStringPreOrder();
    
    /**
     * Returns a string representation of post-order traversal.
     * @return string of post-order traversal
     */
    String toStringPostOrder();
}
