package cbox.sorting;
/*
Steps:
- Pick a pivot element.
  - Any element <= pivot element should be on the left.
  - Any element > pivot element should be on the right.
- Sort the sub-arrays on either side of the pivot.
  - Pick a pivot element again and repeat steps.
Properties:
- Recursive: a method that calls itself in the same manner.
- In place: Space complexity isn't proportional to array size - O(1).
- O(nlogn) average case time complexity.
- O(n^2) worst case time complexity. Can be mostly avoided with randomised quick sort.
 */
public class QuickSort {
    public static void exec(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        quickSort(array, 0, array.length-1);
    }

    private static void quickSort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }

        int pIdx = partition(array, start, end);
        quickSort(array, start, pIdx-1);
        quickSort(array, pIdx+1, end);
    }

    private static int partition(int[] array, int start, int end) {
        int pivot = array[end];
        int pIdx = start;
        for (int i = start; i < end; i++) {
            if (array[i] <= pivot) {
                if (pIdx != i) {
                    swap(array, i, pIdx);
                }
                pIdx++;
            }
        }
        if (pIdx != end) {
            swap(array, pIdx, end);
        }
        return pIdx;
    }

    private static void swap(int[] array, int idx1, int idx2) {
        int tmp = array[idx1];
        array[idx1] = array[idx2];
        array[idx2] = tmp;
    }
}
