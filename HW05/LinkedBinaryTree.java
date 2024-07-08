/**
 * Represents a binary search tree that implements the Binary tree interface.
 * @param <E> generics
 */

public class LinkedBinaryTree<E extends Comparable<E>>
    implements BinaryTree<E> {
    private class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;

        Node(E element) {
            this.element = element;
            this.left = null;
            this.right = null;
        }
    }

    private Node<E> root;
    private int size;

    /**
     * Constructs an empty LinkedBinaryTree.
     */
    public LinkedBinaryTree() {
        root = null;
        size = 0;
    }

    /**
     * Constructs a LinkedBinaryTree with a single root element.
     *
     * @param element the root element
     */
    public LinkedBinaryTree(E element) {
        root = new Node<>(element);
        size = 1;
    }

    @Override
    public E getRootElement() {
        if (root == null) {
            return null;
        }
        return root.element;
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
    public void insert(E element) {
        root = insertRecursively(root, element);
    }

    private Node<E> insertRecursively(Node<E> node, E element) {
        if (node == null) {
            size++;
            return new Node<>(element);
        }
        int cmp = element.compareTo(node.element);
        if (cmp < 0) {
            node.left = insertRecursively(node.left, element);
        } else if (cmp > 0) {
            node.right = insertRecursively(node.right, element);
        } else {
            node.element = element; 
        }
        return node;
    }

    @Override
    public boolean contains(E element) {
        return containsRecursively(root, element);
    }

    private boolean containsRecursively(Node<E> node, E element) {
        if (node == null) {
            return false;
        }
        int cmp = element.compareTo(node.element);
        if (cmp < 0) { 
            return containsRecursively(node.left, element);
        } else if (cmp > 0) {
            return containsRecursively(node.right, element);
        } else {
            return true;
        }
    }

    @Override
    public String toStringInOrder() {
        StringBuilder sb = new StringBuilder();
        inOrderTraversal(root, sb);
        return sb.toString().trim();
    }

    private void inOrderTraversal(Node<E> node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        inOrderTraversal(node.left, sb);
        sb.append(node.element.toString()).append(" ");
        inOrderTraversal(node.right, sb);
    }

    @Override
    public String toString() {
        return "Tree:\nPre: " + toStringPreOrder() 
            + "\nIn: " + toStringInOrder() + "\nPost: " + toStringPostOrder();
    }

    @Override
    public boolean remove(E element) {
        int initialSize = size;
        root = removeRecursively(root, element);
        return size < initialSize;
    }
    
    private Node<E> removeRecursively(Node<E> node, E element) {
        if (node == null) {
            return null;
        }
        int compareResult = element.compareTo(node.element);
        if (compareResult < 0) {
            node.left = removeRecursively(node.left, element);
        } else if (compareResult > 0) {
            node.right = removeRecursively(node.right, element);
        } else { 
            if (node.left == null) {
                size--;
                return node.right;
            } else if (node.right == null) {
                size--;
                return node.left;
            }
            node.element = findSmallestValue(node.right);
            node.right = removeRecursively(node.right, node.element);
        }
        return node;
    }
    
    private E findSmallestValue(Node<E> node) {
        if (node.left == null) {
            return node.element;
        } else {
            return findSmallestValue(node.left);
        }
    }
        
    @Override
    public String toStringPreOrder() {
        StringBuilder sb = new StringBuilder();
        preOrderTraversal(root, sb);
        return sb.toString().trim();
    }
    
    private void preOrderTraversal(Node<E> node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        sb.append(node.element.toString()).append(" ");
        preOrderTraversal(node.left, sb);
        preOrderTraversal(node.right, sb);
    }
    
    @Override
    public String toStringPostOrder() {
        StringBuilder sb = new StringBuilder();
        postOrderTraversal(root, sb);
        return sb.toString().trim();
    }
    
    private void postOrderTraversal(Node<E> node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        postOrderTraversal(node.left, sb);
        postOrderTraversal(node.right, sb);
        sb.append(node.element.toString()).append(" ");
    }
    
    
}





