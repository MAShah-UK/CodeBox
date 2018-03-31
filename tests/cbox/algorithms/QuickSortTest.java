package cbox.algorithms;

import org.junit.Assert;
import org.junit.Test;

public class QuickSortTest {
    public void checkEquals(int[] expected, int[] actual) {
        QuickSort.exec(actual);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void emptyArray() {
        checkEquals(new int[]{1}, new int[]{1});
    }

    @Test
    public void largeArray() {
        checkEquals(new int[]{-10, -5, -1, 0, 1, 20}, new int[]{20, -10, 0, -1, 1, -5});
    }

    @Test
    public void mixedSignElements() {
        checkEquals(new int[]{-4, 0, 5}, new int[]{5, -4, 0});
    }

    @Test
    public void nullArray() {
        int[] actual = null;
        QuickSort.exec(actual);
        Assert.assertNull(actual);
    }

    @Test
    public void oneElement() {
        checkEquals(new int[]{}, new int[]{});
    }

    @Test
    public void twoElements() {
        checkEquals(new int[]{3, 5}, new int[]{5, 3});
    }
}
