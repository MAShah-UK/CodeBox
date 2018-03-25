package com.cbox.ms.algorithms.sorting;

/* Uses merge sort algorithm to sort an array.
Steps:
- Recursively split the original array into left and right arrays.
- Then merge the sorted pieces back into the original array.
Properties:
- Divide and conquer: splits array into small solvable pieces.
- Recursive: a method that calls itself in the same manner.
- Stable: Maintains relative order for elements with the same key.
- Not in-place: Requires more memory proportional to the size of the array.
- O(nlogn) worst case time complexity.
- O(n) space complexity. */

public class MergeSort {
    public static void exec(int[] array) {
        split(array);
    }

    public static void split(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        int n = array.length;
        int mid = n/2;
        int[] left = new int[mid];
        int[] right = new int[n-mid];
        for (int i = 0; i < mid; i++) {
            left[i] = array[i];
        }
        for (int i = mid; i < n; i++) {
            right[i-mid] = array[i];
        }

        split(left);
        split(right);
        merge(left, right, array);
    }

    private static void merge(int[] left, int[] right, int[] target) {
        int l, r, t;
        l = r = t = 0;

        while (l < left.length && r < right.length) {
            if (left[l] <= right[r]) {
                target[t] = left[l];
                t++; l++;
            } else {
                target[t] = right[r];
                t++; r++;
            }
        }
        while (l < left.length) {
            target[t] = left[l];
            t++; l++;
        }
        while (r < right.length) {
            target[t] = right[r];
            t++; r++;
        }
    }
}
