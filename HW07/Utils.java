import java.util.ArrayList;

public class Utils {

    /*
     * Returns the middle element, as specified by compareTo.
     */
    public static <E extends Comparable<E>> E median(E a, E b, E c) {
        if (a.compareTo(b) > 0) {
            if (b.compareTo(c) > 0) return b;
            else if (a.compareTo(c) > 0) return c;
            else return a;
        } else {
            if (a.compareTo(c) > 0) return a;
            else if (b.compareTo(c) > 0) return c;
            else return b;
        }
    }
    

    /*
     * Implements the median-of-tree pivot selection. 
     * @param arr   the array to select three elements from 
     * @param lo    the location of the first element
     * @param hi    the location of the second element 
     * @return      the index of the meidan element (lo, hi, or mid)
     */
    public static <E extends Comparable<E>> int medianOfThree(ArrayList<E> arr, int lo, int hi) {
        int mid = (lo + hi) / 2;
        E a = arr.get(lo);
        E b = arr.get(mid);
        E c = arr.get(hi);
        E m = median(a, b, c);
    
        if (m.equals(a)) return lo;
        else if (m.equals(b)) return mid;
        else return hi;
    }
    

    /*
     * Swaps the elements in locations i and j in arr
     */
    public static  <E extends Comparable<E>>void swap(ArrayList<E> arr, int i, int j) {
        E temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

    /*
     * Given an arr, find a pivot using the medianOfThree method.
     * Follow the quicksort algorithm using leftHigh and rightLow markers.
     */
    public static <E extends Comparable<E>> int partitionPivot(ArrayList<E> arr, int lo, int hi) {
        int pivotIndex = medianOfThree(arr, lo, hi);
        E pivotValue = arr.get(pivotIndex);
        swap(arr, pivotIndex, hi);  
        int left = lo;
        int right = hi - 1;

        while (left <= right) {
            while (left <= right && arr.get(left).compareTo(pivotValue) < 0) left++;
            while (left <= right && arr.get(right).compareTo(pivotValue) > 0) right--;
            if (left <= right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }
        swap(arr, left, hi);  
        return left;
    }

    public static <E extends Comparable<E>> void quickSort(ArrayList<E> arr, int left, int right) {
        if (left < right) {
            int pivot = partitionPivot(arr, left, right);
            quickSort(arr, left, pivot-1);
            quickSort(arr, pivot+1, right);
        }
    }

    public static <E extends Comparable<E>> void quickSort(ArrayList<E> arr) {
        quickSort(arr, 0, arr.size()-1);
    }
}