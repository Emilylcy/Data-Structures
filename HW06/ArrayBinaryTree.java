public class ArrayBinaryTree<E extends Comparable<E>> implements BinaryTree<E> {
    protected E[] tree;
    protected int size;

    public ArrayBinaryTree() {
        this.tree = (E[]) new Comparable[100]; 
        this.size = 0;
    }

    @Override
    public E getRootElement() {
        if (size == 0) {
            return null;
        } else {
            return tree[0];
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            if (tree[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void insert(E element) {
        if (size >= tree.length) {
            throw new IllegalStateException("Tree is full");
        }
        tree[size++] = element;
    }

    public int containsIdx(E element) {
        for (int i = 0; i < size; i++) {
            if (tree[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    public int left(int index) {
        int leftIndex = 2 * index + 1;
        if (leftIndex < size) {
            return leftIndex;
        } else {
            return -1;
        }
    }
    
    public int right(int index) {
        int rightIndex = 2 * index + 2;
        if (rightIndex < size) {
            return rightIndex;
        } else {
            return -1;
        }
    }
    
    public int parent(int index) {
        if (index > 0) {
            return (index - 1) / 2;
        } else {
            return -1;
        }
    }

    public void swap(int index1, int index2) {
        E temp = tree[index1];
        tree[index1] = tree[index2];
        tree[index2] = temp;
    }

    @Override
    public boolean remove(E element) {
        int index = containsIdx(element);
        if (index == -1) {
            return false;
        }
        tree[index] = tree[size - 1];
        tree[size - 1] = null;
        size--;
        return true;
    }
    
    @Override
    public String toStringInOrder() {
        StringBuilder sb = new StringBuilder();
        sb.append("In: ");
        inOrder(0, sb);
        return sb.toString().trim();
    }

    private void inOrder(int index, StringBuilder sb) {
        if (index >= size) {
            return;
        }
        int leftIndex = 2 * index + 1;
        int rightIndex = 2 * index + 2;
        if (leftIndex < size) {
            inOrder(leftIndex, sb);
        }
        sb.append(tree[index]).append(' ');
        if (rightIndex < size) {
            inOrder(rightIndex, sb);
        }
    }

    @Override
    public String toStringPreOrder() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pre: ");
        preOrder(0, sb);
        return sb.toString().trim();
    }

    private void preOrder(int index, StringBuilder sb) {
        if (index >= size) return;
        sb.append(tree[index]).append(' ');
        int leftIndex = 2 * index + 1;
        int rightIndex = 2 * index + 2;
        if (leftIndex < size) {
            preOrder(leftIndex, sb);
        } 
        if (rightIndex < size) {
            preOrder(rightIndex, sb);
        }
    }

    @Override
    public String toStringPostOrder() {
        StringBuilder sb = new StringBuilder();
        sb.append("Post: ");
        postOrder(0, sb);
        return sb.toString().trim();
    }

    private void postOrder(int index, StringBuilder sb) {
        if (index >= size) return;
        int leftIndex = 2 * index + 1;
        int rightIndex = 2 * index + 2;
        if (leftIndex < size) {
            postOrder(leftIndex, sb);
        }
        if (rightIndex < size) {
            postOrder(rightIndex, sb);
        }
        sb.append(tree[index]).append(' ');
    }

    @Override
    public String toString() {
        return "Tree:\n" + toStringPreOrder() + "\n" + toStringInOrder() + "\n" + toStringPostOrder();
    }

    public String toStringBreadthFirst() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(tree[i].toString()).append(" ");
        }
        return sb.toString().trim();
    }
}
