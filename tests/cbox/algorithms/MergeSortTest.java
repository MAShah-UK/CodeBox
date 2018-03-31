package cbox.algorithms;

import org.junit.Assert;
import org.junit.Test;

public class MergeSortTest {
    public void checkEqual(int[] expected, int[] actual) {
        MergeSort.exec(actual);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void nullArray() {
        int[] actual = null;
        MergeSort.exec(actual);
        Assert.assertNull(actual);
    }

    @Test
    public void oneElement() {
        checkEqual(new int[]{1}, new int[]{1});
    }

    @Test
    public void sortedArray() {
        checkEqual(new int[]{-2, -1, 0, 1, 2}, new int[]{-2, -1, 0, 1, 2});
    }

    @Test
    public void unsortedArray_multipleElements() {
        checkEqual(new int[]{-2, -1, 0, 1, 2}, new int[]{0, 2, 1, -1, -2});
    }

    @Test
    public void unsortedArray_twoElements() {
        checkEqual(new int[]{1, 2}, new int[]{2, 1});
    }
}
