package com.cbox.ms.algorithms.exercises;

import org.junit.Test;

// TODO: Fix the output so that there's something to test.

public class FindPairsForNTest {
    public void checkEqual(int[] array, int n) {
        FindPairsForN.exec(array, n);
    }

    @Test
    public void emptyArray() {
        checkEqual(new int[]{}, 5);
    }

    @Test
    public void invalidPositivePair() {
        checkEqual(new int[]{1, 5}, 5);
    }

    @Test
    public void oneElementArray() {
        checkEqual(new int[]{1}, 5);
    }

    @Test
    public void nullArray() {
        checkEqual(null, 5);
    }

    @Test
    public void repeatedElements() {
        checkEqual(new int[]{0, 0, 20, -20, 20, -20, 11, 9, 9, 8, 12}, 20);
    }

    @Test
    public void validPositivePair() {
        checkEqual(new int[]{1, 4}, 5);
    }

    @Test
    public void validMixedSignPairs() {
        checkEqual(new int[]{5, 3, 1, 0, 0, -1, -3, -4, 1}, 0);
    }
}
