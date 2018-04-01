package cbox.exercises;

import org.junit.Assert;
import org.junit.Test;

public class ArrayBinarySearchTest {
    public <T extends Comparable<T>> void checkEqual(int expectedIdx, T[] array, T value) {
        Assert.assertEquals(expectedIdx, ArrayBinarySearch.exec(array, value));
    }

    @Test
    public void invalid_valueBetween() {
        checkEqual(-1, new Integer[]{-10, -5, 0, 5, 10}, 1);
    }

    @Test
    public void invalid_valueTooLarge() {
        checkEqual(-1, new Integer[]{-10, -5, 0, 5, 10}, 11);
    }

    @Test
    public void invalid_valueTooSmall() {
        checkEqual(-1, new Integer[]{-10, -5, 0, 5, 10}, -11);
    }

    @Test
    public void valid_firstInteger() {
        checkEqual(0, new Integer[]{-10, -5, 0, 5, 10}, -10);
    }

    @Test
    public void valid_lastInteger() {
        checkEqual(4, new Integer[]{-10, -5, 0, 5, 10}, 10);
    }

    @Test
    public void valid_negativeInteger() {
        checkEqual(1, new Integer[]{-10, -5, 0, 5, 10}, -5);
    }

    @Test
    public void valid_positiveInteger() {
        checkEqual(3, new Integer[]{-10, -5, 0, 5, 10}, 5);
    }
}
