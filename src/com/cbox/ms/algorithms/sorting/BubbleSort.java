package com.cbox.ms.algorithms.sorting;

public class BubbleSort { // O(n^2) average complexity.
    public static <S extends Comparable<S>> void exec(S[] array, boolean ascend) {
        if (array == null) {
            return;
        }

        int multiplier = ascend ? 1 : -1;
        boolean changed = true;
        while (changed) {
            changed = false;
            for (int i = 0; i < array.length-1; i++) {
                if (array[i+1].compareTo(array[i]) * multiplier > 0) {
                    S tmp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = tmp;
                    changed = true;
                }
            }
        }
    }
}
