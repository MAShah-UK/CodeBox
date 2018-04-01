package cbox.exercises;

public class ArrayBinarySearch {
    public static <T extends Comparable<T>> int exec(T[] array, T val) {
        if (array == null || array.length == 0) {
            return -1;
        }

        int low = 0, mid = 0, high = array.length-1;
        int comp = 0;
        while (low <= high) {
            mid = (low + high) / 2;
            comp = array[mid].compareTo(val);
            if (comp < 0) {
                low = mid+1;
            } else if (comp > 0) {
                high = mid;
            }
            if (comp == 0 || low == high) {
                break;
            }
        }
        return comp == 0 ? mid : -1;
    }
}
