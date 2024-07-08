import java.util.ArrayList;

/**
 * A generic Array-based heap (priority queue) implementation.
 * 
 * @param <E> the type of elements in this heap
 */
public class ArrayHeap<E extends Comparable<E>> 
    extends ArrayBinaryTree<E> implements PriorityQueue<E> {
    @Override
    public void insert(E element) {
        int index = findElementIndex(element);
        if (index != -1) {
            PollingData existing = (PollingData) tree[index];
            existing.setPercent(((PollingData) element).getPercent());
            heapifyUp(index);
            heapDown(index);
        } else {
            super.insert(element);
            heapifyUp(size - 1);  
        }
    }

    private void heapifyUp(int index) {
        while (index > 0 
            && tree[index].compareTo(tree[parentIndex(index)]) > 0) {
            swap(index, parentIndex(index));
            index = parentIndex(index);
        }
    }
    
    @Override
    public E peek() {
        return getRootElement();
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E result = tree[0];
        tree[0] = tree[size - 1];
        tree[size - 1] = null;
        size--;
        if (size > 0) {
            heapify(0);
        }
        return result;
    }
    

    @Override
    public boolean remove(E element) {
        int index = findElementIndex(element);  
        if (index == -1) {
            return false; 
        }
    
        tree[index] = tree[size - 1];
        tree[size - 1] = null;
        size--;
    
        if (size == 0 || index >= size) {
            return true;
        }
    
        heapDown(index);  
        return true;
    }
    
    
    private void heapDown(int index) {
        int largestChild;
        while (index < size) {
            int left = leftChild(index);
            int right = rightChild(index);
            largestChild = index;

            if (left < size && tree[left].compareTo(tree[largestChild]) > 0) {
                largestChild = left;
            }
            if (right < size && tree[right].compareTo(tree[largestChild]) > 0) {
                largestChild = right;
            }
            if (largestChild != index) {
                swap(index, largestChild);
                index = largestChild;
            } else {
                break;
            }
        }
    }
    
    
    private int leftChild(int index) {
        return 2 * index + 1;
    }
    
    private int rightChild(int index) {
        return 2 * index + 2;
    }
    
    /**
    * Retrieves the index of the element.
    * @param  element the element
    * @return int the integer index of that element
    */
    public int findElementIndex(E element) {
        if (element == null) {
            return -1;
        }
    
        for (int i = 0; i < size; i++) {
            if (element instanceof PollingData 
                && tree[i] instanceof PollingData) {
                PollingData elemData = (PollingData) element;
                PollingData treeData = (PollingData) tree[i];
                if (elemData.getLastName().equals(treeData.getLastName())) {
                    return i;
                }
            } else if (element.equals(tree[i])) {
                return i;
            }
        }
        return -1; 
    }
    
    private void heapify(int index) {
        int largest = index;
        int leftIndex = leftChild(index);
        int rightIndex = rightChild(index);
    
        if (leftIndex < size && tree[leftIndex].compareTo(tree[largest]) > 0) {
            largest = leftIndex;
        }
        if (rightIndex < size 
            && tree[rightIndex].compareTo(tree[largest]) > 0) {
            largest = rightIndex;
        }
        if (largest != index) {
            swap(index, largest);
            heapify(largest);
        }
    }
    
    /**
     * Sets the element at the specified index in the heap's array.
     *
     * @param index   The index at which the element is to be set.
     * @param element The new element to set at the specified index. 
     */
    public void set(int index, E element) {
        if (index >= 0 && index < size) {
            tree[index] = element;
            if (index > 0 && element.compareTo(tree[parentIndex(index)]) > 0) {
                // Sift up since new element might be greater than its parent
                while (index > 0 
                    && tree[index].compareTo(tree[parentIndex(index)]) > 0) {
                    swap(index, parentIndex(index));
                    index = parentIndex(index);
                }
            } else {
                // Sift down since new element might be less than its children
                heapify(index);
            }
        }
    }

    /**
     * Swap two elements in the heap.
     *
     * @param i1  element1
     * @param i2 element 2
     */
    public void swap(int i1, int i2) {
        E temp = tree[i1];
        tree[i1] = tree[i2];
        tree[i2] = temp;
    }
    
    private int parentIndex(int i) {
        return (i - 1) / 2;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return " ";
        }
    
        StringBuilder sb = new StringBuilder();
        int level = 0; 
        int itemsInLevel = 1; 
        int itemCounter = 0; 

        for (int i = 0; i < size; i++) {
            if (itemCounter == itemsInLevel) { 
                sb.append("\n"); 
                itemsInLevel *= 2; 
                itemCounter = 0; 
            }
            if (itemCounter > 0) {
                sb.append(" ");
            }
            sb.append(tree[i].toString()); 
            itemCounter++; 
        }
        
        return sb.toString();
    }
    

    @Override
    public boolean contains(E element) {
        return containsIdx(element) != -1;
    }

    /**
    * Retrieves the top n largest elements from the heap without removing them.
    * @param n The number of top elements to retrieve from the heap.
    * @return A list of n elements sorted from largest to smallest.
    */
    public ArrayList<E> peekTopN(int n) {
        ArrayList<E> topN = new ArrayList<>();
        if (n <= 0) {
            return topN; 
        } 
    
        for (int i = 0; i < size; i++) {
            E currentElement = tree[i];
            int insertIndex = 0;
            while (insertIndex < topN.size() 
                && currentElement.compareTo(topN.get(insertIndex)) < 0) {
                insertIndex++;
            }
            if (insertIndex < n) {
                topN.add(insertIndex, currentElement);
                if (topN.size() > n) {
                    topN.remove(topN.size() - 1);
                }
            }
        }
    
        return topN;  
    }
    
}
