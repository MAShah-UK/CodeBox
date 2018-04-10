package cbox.sorting;

import org.junit.Assert;
import org.junit.Test;

public class FrequencySortTest_exec2 {
    public void checkEqual(Integer[] expected, Integer[] array) {
        FrequencySort.exec2(array);
        Assert.assertArrayEquals(expected, array);
    }

    @Test
    public void checkNull() {
        checkEqual(null, null);
    }

    @Test
    public void emptyArray() {
        checkEqual(new Integer[]{}, new Integer[]{});
    }

    @Test
    public void manyRepeatingElements() {
        checkEqual(new Integer[]{-2, -2, 0, 0, 1, 1, -1, 2}, new Integer[]{-2, -2, -1, 0, 0, 1, 1, 2});
    }

    @Test
    public void manyRepeatingElements2() {
        checkEqual(new Integer[]{4, 4, 4, 5, 5, 6}, new Integer[]{4, 5, 6, 4, 5, 4});
    }


    @Test
    public void negativeToPositiveElements() {
        checkEqual(new Integer[]{-1, 0, 1}, new Integer[]{-1, 0, 1});
    }

    @Test
    public void oneElement() {
        checkEqual(new Integer[]{5}, new Integer[]{5});
    }

    @Test
    public void oneRepeatingElement() {
        checkEqual(new Integer[]{2, 2, 1}, new Integer[]{1, 2, 2});
    }

    @Test
    public void sameElements() {
        checkEqual(new Integer[]{1, 1, 1}, new Integer[]{1, 1, 1});
    }

    @Test
    public void twoRepeatingElements() {
        checkEqual(new Integer[]{1, 1, 2, 2, 3}, new Integer[]{3, 2, 1, 1, 2});
    }

    @Test
    public void uniqueElementsOrdered() {
        checkEqual(new Integer[]{1, 2, 3}, new Integer[]{1, 2, 3});
    }

    @Test
    public void uniqueElementsUnordered() {
        checkEqual(new Integer[]{1, 2, 3}, new Integer[]{2, 3, 1});
    }
}
