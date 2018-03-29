package com.cbox.ms.algorithms.exercises;

import com.test.exercises.FrequencySort;
import org.junit.Assert;
import org.junit.Test;

public class FrequencySortTest {
    public void checkEqual(int[] expected, int[] array) {
        Assert.assertArrayEquals(expected, FrequencySort.exec(array));
    }

    public void checkNull(int[] array) {
        Assert.assertNull(FrequencySort.exec(array));
    }

    @Test
    public void emptyArray() {
        checkEqual(new int[]{}, new int[]{});
    }

    @Test
    public void manyRepeatingElements() {
        checkEqual(new int[]{-2, -2, 0, 0, 1, 1, -1, 2}, new int[]{-2, -2, -1, 0, 0, 1, 1, 2});
    }

    @Test
    public void manyRepeatingElements2() {
        checkEqual(new int[]{4, 4, 4, 5, 5, 6}, new int[]{4, 5, 6, 4, 5, 4});
    }


    @Test
    public void negativeToPositiveElements() {
        checkEqual(new int[]{-1, 0, 1}, new int[]{-1, 0, 1});
    }

    @Test
    public void nullArray() {
        checkEqual(null, null);
    }

    @Test
    public void oneElement() {
        checkEqual(new int[]{5}, new int[]{5});
    }

    @Test
    public void oneRepeatingElement() {
        checkEqual(new int[]{2, 2, 1}, new int[]{1, 2, 2});
    }

    @Test
    public void sameElements() {
        checkEqual(new int[]{1, 1, 1}, new int[]{1, 1, 1});
    }

    @Test
    public void twoRepeatingElements() {
        checkEqual(new int[]{1, 1, 2, 2, 3}, new int[]{3, 2, 1, 1, 2});
    }

    @Test
    public void uniqueElementsOrdered() {
        checkEqual(new int[]{1, 2, 3}, new int[]{1, 2, 3});
    }

    @Test
    public void uniqueElementsUnordered() {
        checkEqual(new int[]{1, 2, 3}, new int[]{2, 3, 1});
    }
}
