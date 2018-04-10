package cbox.sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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

    public static void exec(int[] array) {
        // Validation.
        if (array == null || array.length < 2) {
            return;
        }
        boolean diff = false;
        for(int i = 1; i < array.length; i++) {
            if (array[i] != array[i-1]) {
                diff = true;
                break;
            }
        }
        if (!diff) {
            return;
        }

        // Sort input array.
        Arrays.sort(array);

        // Create and sort frequency array.
        class Data {
            private int value;
            private int freq;
            public Data(int value, int freq) {
                this.value = value;
                this.freq = freq;
            }
            public int getValue() {
                return value;
            }
            public int getFreq() {
                return freq;
            }
            public void incrementFreq() {
                freq++;
            }
        }
        Data[] valFreq = new Data[array.length];
        valFreq[0] = new Data(array[0], 1);
        int dataIdx = 0;
        for(int i = 1; i < array.length; i++) {
            if(array[i] != valFreq[dataIdx].getValue()) {
                dataIdx++;
                valFreq[dataIdx] = new Data(array[i], 0);
            }
            valFreq[dataIdx].incrementFreq();
        }
        Arrays.sort(valFreq, new Comparator<Data>() {
            @Override
            public int compare(Data d1, Data d2) {
                // Since not all elements of valFreq are initialised.
                if (d1 == null || d2 == null) {
                    return 0;
                }
                int[] diff = new int[2];
                diff[0] = d1.getFreq()-d2.getFreq();   // Frequency difference.
                diff[1] = d1.getValue()-d2.getValue(); // Value difference.
                for (int i: diff) {
                    if(i != 0) {
                        return i/Math.abs(i) * -1; // -1 for descending order.
                    }
                }
                return 0;
            }
        });

        // Store result in original array.
        dataIdx = 0;
        int count = 0;
        for(int i = 0; i < array.length; i++) {
            array[i] = valFreq[dataIdx].getValue();
            count++;
            if(count >= valFreq[dataIdx].getFreq()) {
                dataIdx++;
                count = 0;
            }
        }
    }

    public static void exec2(Integer[] freq) {
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

    public static Integer[][] copyOf2D(Integer[][] array, int rows, int cols) {
        Integer[][] out = new Integer[rows][cols];
        for (int i = 0; i < rows; i++) {
            out[i] = Arrays.copyOf(array[i], cols);
        }
        return out;
    }
}
