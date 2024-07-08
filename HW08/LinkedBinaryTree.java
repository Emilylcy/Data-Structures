public class LinkedBinaryTree<E extends Comparable<E>> implements BinaryTree<E> {
    protected class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;
        int height;

        Node(E element, Node<E> parent) {
            this.element = element;
            this.left = null;
            this.right = null;
            this.parent = parent;
            this.height = 1;
        }

        public Node<E> getParent() {
            return parent;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public E getData() {
            return element;
        }

        public Node<E> getLeft() {
            return left;
        }

        public Node<E> getRight() {
            return right;
        }

        @Override
        public String toString() {
            return element.toString() + "(" + height + ")";
        }
    }

    protected Node<E> root;
    protected int size;

    public LinkedBinaryTree() {
        root = null;
        size = 0;
    }

    public LinkedBinaryTree(E element) {
        root = new Node<>(element, null);
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
    public E get(E element) {
        Node<E> resultNode = getElementRecursively(root, element);
        if (resultNode == null) {
            return null;
        } else {
            return resultNode.element;
        }
    }
    
    private Node<E> getElementRecursively(Node<E> node, E element) {
        if (node == null) {
            return null;  
        }
        int cmp = element.compareTo(node.element);
        if (cmp < 0) {
            return getElementRecursively(node.left, element);  
        } else if (cmp > 0) {
            return getElementRecursively(node.right, element);  
        } else {
            return node;  
        }
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
    public boolean contains(E element) {
        return containsRecursively(root, element);
    }

    private boolean containsRecursively(Node<E> node, E element) {
        if (node == null || element == null) {
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
    public void insert(E element) {
        root = insertRecursively(root, element, null);
    }

    private Node<E> insertRecursively(Node<E> node, E element, Node<E> parent) {
        if (node == null) {
            size++;
            return new Node<>(element, parent);
        }
        int cmp = element.compareTo(node.element);
        if (cmp < 0) {
            node.left = insertRecursively(node.left, element, node);
        } else if (cmp > 0) {
            node.right = insertRecursively(node.right, element, node);
        }
        return node;
    }

    @Override
    public boolean remove(E element) {
        Node<E> removedNode = removeRecursively(root, element);
        boolean wasRemoved = (removedNode != null);
        if (wasRemoved) {
            size--;
        }
        return wasRemoved;
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
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                Node<E> smallest = findSmallestValue(node.right);
                node.element = smallest.element;
                node.right = removeRecursively(node.right, smallest.element);
            }
        }
        return node;
    }

    protected Node<E> findSmallestValue(Node<E> node) {
        return node.left == null ? node : findSmallestValue(node.left);
    }

    @Override
    public String toStringInOrder() {
        StringBuilder sb = new StringBuilder();
        inOrderTraversal(root, sb);
        return sb.toString().trim();
    }

    private void inOrderTraversal(Node<E> node, StringBuilder sb) {
        if (node != null) {
            inOrderTraversal(node.left, sb);
            sb.append(node.toString()).append(" ");
            inOrderTraversal(node.right, sb);
        }
    }

    @Override
    public String toStringPreOrder() {
        StringBuilder sb = new StringBuilder();
        preOrderTraversal(root, sb);
        return sb.toString().trim();
    }

    private void preOrderTraversal(Node<E> node, StringBuilder sb) {
        if (node != null) {
            sb.append(node.toString()).append(" ");
            preOrderTraversal(node.left, sb);
            preOrderTraversal(node.right, sb);
        }
    }

    @Override
    public String toStringPostOrder() {
        StringBuilder sb = new StringBuilder();
        postOrderTraversal(root, sb);
        return sb.toString().trim();
    }

    private void postOrderTraversal(Node<E> node, StringBuilder sb) {
        if (node != null) {
            postOrderTraversal(node.left, sb);
            postOrderTraversal(node.right, sb);
            sb.append(node.toString()).append(" ");
        }
    }

    @Override
    public String toString() {
        return "Tree:\nPre: " + toStringPreOrder() 
            + "\nIn: " + toStringInOrder() + "\nPost: " + toStringPostOrder();
    }
}
