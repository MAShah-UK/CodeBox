package cbox.sorting;

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

    public static Integer[][] copyOf2D(Integer[][] array, int rows, int cols) {
        Integer[][] out = new Integer[rows][cols];
        for (int i = 0; i < rows; i++) {
            out[i] = Arrays.copyOf(array[i], cols);
        }
        return out;
    }

    public static void exec(Integer[] freq) {
        // Validation.
        if (freq == null || freq.length == 0 || freq.length == 1) {
            return;
        }

        // Sort 1D array.
        BubbleSort.exec(freq, true);

        Integer[][] freq2D = new Integer[freq.length][2];
        freq2D[0][0] = freq[0];
        freq2D[0][1] = 1;
        int idx2D = 0;
        for(int i = 1; i < freq.length; i++) {
            if(!freq[i].equals(freq2D[idx2D][0])) {
                idx2D++;
                freq2D[idx2D][0] = freq[i];
            }
            freq2D[idx2D][1] = (freq2D[idx2D][1] == null) ? 1 : freq2D[idx2D][1]+1;
        }
        freq2D = copyOf2D(freq2D, idx2D+1, 2);
        BubbleSort.exec(freq2D, false, 1);

        idx2D = 0;
        int freqCount = 0;
        for(int i = 0; i < freq.length; i++) {
            freq[i] = freq2D[idx2D][0];
            freqCount++;
            if (freqCount >= freq2D[idx2D][1]) {
                idx2D++;
                freqCount = 0;
            }
        }
    }
}
