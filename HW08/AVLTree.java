public class AVLTree<E extends Comparable<E>> extends LinkedBinaryTree<E> {

    public AVLTree() {
        super();
    }

    public AVLTree(E element) {
        super(element);
        insert(element);
        root.height = 0;
        size = 1;
    }

    public Node<E> getRoot() {
        return root;
    }

    public int height() {
        if (root == null) {
            return -1;  
        }
        return getHeight(root) - 1;
    }

    protected int getHeight(Node<E> node) {
        root = getRoot();
        if (node == null) {
            return 0;
        } else if (node == root && node.left == null && node.right == null){
            return 1;
        } else {
            return node.height;
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
        } else {
            node.element = element;
        }

        return rebalance(node);
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
        int cmp = element.compareTo(node.element);
        if (cmp < 0) {
            node.left = removeRecursively(node.left, element);
        } else if (cmp > 0) {
            node.right = removeRecursively(node.right, element);
        } else {
            if (node.left == null || node.right == null) {
                Node<E> temp = node.left != null ? node.left : node.right;
                node = temp;
            } else {
                Node<E> temp = findSmallestValue(node.right);
                node.element = temp.element;
                node.right = removeRecursively(node.right, temp.element);
            }
        }
        if (node == null) {
            return null;
        } else {
            return rebalance(node);
        }
        
    }

    protected Node<E> rotateLeft(Node<E> node) {
        Node<E> newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        newRoot.parent = node.parent;
        node.parent = newRoot;
        if (node.right != null) {
            node.right.parent = node;
        }
        updateHeight(node);
        updateHeight(newRoot);
        return newRoot;
    }

    protected Node<E> rotateRight(Node<E> node) {
        Node<E> newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        newRoot.parent = node.parent;
        node.parent = newRoot;
        if (node.left != null) {
            node.left.parent = node;
        }
        updateHeight(node);
        updateHeight(newRoot);
        return newRoot;
    }

    protected Node<E> rotateLeftRight(Node<E> node) {
        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }

    protected Node<E> rotateRightLeft(Node<E> node) {
        node.right = rotateRight(node.right);
        return rotateLeft(node);
    }

    protected Node<E> rebalance(Node<E> node) {
        updateHeight(node);
        int balance = getBalance(node);

        if (balance > 1) { 
            if (getBalance(node.left) < 0) { 
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node); 
        } else if (balance < -1) { 
            if (getBalance(node.right) > 0) { 
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node); 
        }
        return node;
    }

    protected void updateHeight(Node<E> node) {
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    protected int getBalance(Node<E> node) {
        if (node == null) {
            return 0;
        } else {
            int leftHeight = getHeight(node.left);
            int rightHeight = getHeight(node.right);
            return leftHeight - rightHeight;
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        inOrderTraversalWithHeight(root, sb);
        return sb.toString().trim();
    }
    
    protected void inOrderTraversalWithHeight(Node<E> node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        updateHeight(node);
        inOrderTraversalWithHeight(node.left, sb);
        sb.append(node.element.toString()).append("(").append(node.height).append(")").append(" ");
        inOrderTraversalWithHeight(node.right, sb);
    }

}

