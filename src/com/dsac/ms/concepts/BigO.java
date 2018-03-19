package com.dsac.ms.concepts;

// BigO notation is used to determine how well an algorithm scales as data increases.
public class BigO {
    // O(1) - constant time operation. Array length can be arbitrary, but the
    // operation will complete in the same time regardless.
    public static int getElement_O1(int[] arr, int index) {
        return arr[index];
    }

    // O(n) - linear time operation. The time taken is proportional to the size
    // of the array. Since at worst we might have to go through each element.
    // Instead of n it could be a, b, c, etc. It's just a variable.
    public static int getElement_On(int[] arr, int index) {
        for (int i = 0; i < arr.length; i++) {
            if (i == index) {
                return arr[i];
            }
        }
        return arr[index];
    }

    // O(n) - linear time operation. The time taken is proportional to the size
    // of the array. Since we have to go through each element.
    public static int getSum(int[] arr) {
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        return sum;
    }

    // Rule 1) Steps with different complexities are added. So O(2n) operation.
    // Rule 2) Drop coefficients. Since we want to see rough relationship. So O(n) operation.
    public static int getSum(int[] array, int index1, int index2) {
        int val1 = getElement_On(array, index1); // O(n)
        int val2 = getElement_On(array, index2); // O(n)
        return val1 + val2;
    }

    // Rule 3) Different inputs for different variables -
    // so m for arr1 and n for arr2. Steps are repeated so multiply - O(mn).
    // If arr1 and arr2 are the same array, then O(n^2).
    public static int getCommonElementsCount(int[] arr1, int[] arr2) {
        int count = 0;
        for (int i : arr1) { // O(m)
            for (int j : arr2) { // O(n)
                if (i == j) {
                    count++;
                }
            }
        }
        return count;
    }

    // Rule 4) Drop non-dominate terms.
    // O(mn+m+1) becomes 0(mn) since the mn term will grow faster than the others.
    public static int getSumPlusCount(int[] arr1, int[] arr2) {
        int count = getCommonElementsCount(arr1, arr2); // O(mn)
        int sum = getSum(arr1); // O(m)
        return count + sum; // O(1)
    }

    // Example of O(log n) complexity. As the array doubles in size
    // the binary search only need to slice the array in half once more.
    public static int binarySearch(int[] arr, int val) {
        int low = 0;
        int high = arr.length;
        boolean found = false;
        while (low <= high && !found) {
            int mid = (low+high)/2;
            if (arr[mid] > val) {
                high = mid-1;
            } else if (arr[mid] < val) {
                low = mid+1;
            } else {
                found = true;
            }
        }
        if (!found) {
            throw new IllegalArgumentException();
        }
        return low;
    }
}
