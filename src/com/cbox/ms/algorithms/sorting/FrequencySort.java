package com.cbox.ms.algorithms.sorting;

import java.util.Arrays;

/* Returns a 1D array of elements sorted by frequency.
Steps:
- Validation: if the input array is null, or has a length of 0 or 1, return it.
- Sort the input array.
- Create a 2D frequency array.
  - Same number of rows as input arrays length since the input array could have unique elements.
  - Two columns. The first column stores unique elements from the input array. The second
    column stores the number of times they appear - frequency.
- Sort the 2D array by its second column.
- Create a 1D array in
 */

public class FrequencySort {
    public static int[] exec(int[] inputArray) {
        // Validation.
        if (inputArray == null || inputArray.length == 0 || inputArray.length == 1) {
            return inputArray;
        }

        // Sort 1D array.
        int[] array = Arrays.copyOf(inputArray, inputArray.length);
        sort(array);

        // Create 2D frequency array.
        int[][] freq2D = new int[array.length][2];
        freq2D[0][0] = array[0];
        int freqRow = 0;
        int totalElements = 0;
        for (int i = 0; i < array.length; i++) {
           if (array[i] > freq2D[freqRow][0]) {
               freqRow++;
               freq2D[freqRow][0] = array[i];
           }
           freq2D[freqRow][1]++;
           totalElements++;
        }

        // Sort 2D array by frequency.
        sort(freq2D);

        // Create 1D array sorted by frequency.
        int[] freq1D = new int[totalElements];
        int idx = 0;
        for (int[] row : freq2D) {
            for (int i = 0; i < row[1]; i++) {
                freq1D[idx] = row[0];
                idx++;
            }
            if (idx == totalElements) {
                break;
            }
        }

        return freq1D;
    }

    private static void sort(int[] array) {
        // Bubble sort.
        boolean changed = true;
        while (changed) {
            changed = false;
            for (int i = 0; i < array.length-1; i++) {
                if (array[i] > array[i+1]) {
                    int tmp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = tmp;
                    changed = true;
                }
            }
        }
    }

    private static void sort(int[][] array) {
        // Bubble sort on second column.
        boolean changed = true;
        while (changed) {
            changed = false;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i][1] < array[i+1][1]) {
                    int[] tmp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = tmp;
                }
            }
        }
    }
}
