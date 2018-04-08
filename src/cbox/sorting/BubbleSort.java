package cbox.sorting;

public class BubbleSort { // O(n^2) average complexity.
    public static <S extends Comparable<S>> void exec(S[] array, boolean ascend) {
        if (array == null) {
            return;
        }

        int multiplier = ascend ? 1 : -1;
        for (int i = 0; i < array.length-1; i++) {
            if (array[i].compareTo(array[i+1]) * multiplier > 0) {
                S tmp = array[i];
                array[i] = array[i+1];
                array[i+1] = tmp;

                i = (i==0) ? -1 : i-2;
            }
        }
    }
}
