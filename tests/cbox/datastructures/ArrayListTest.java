package cbox.datastructures;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class ArrayListTest {
    private ArrayList list;

    public void checkEqual(int[] expected, ArrayList actual) {
        Assert.assertEquals(Arrays.toString(expected), actual.toString());
    }

    @Test
    public void addMultipleElements() {
        list = new ArrayList();
        list.add(1, 2);
        list.add(3);
        checkEqual(new int[]{1, 2, 3}, list);
    }

    @Test
    public void containsTrue() {
        list = new ArrayList(1, 2, 3);
        Assert.assertTrue(list.contains(2));
    }

    @Test
    public void containsFalse() {
        list = new ArrayList(1, 2);
        list.add(3);
        Assert.assertFalse(list.contains(4));
    }

    @Test
    public void getElement() {
        list = new ArrayList(1, 2, 3);
        Assert.assertEquals(2, list.get(1));
    }

    @Test
    public void getSize() {
        list = new ArrayList(1, 2);
        list.add(3);
        Assert.assertEquals(3, list.size());
    }

    @Test
    public void initialiseMultipleElements() {
        list = new ArrayList(1, 2, 3);
        checkEqual(new int[]{1, 2, 3}, list);
    }

    @Test
    public void initialiseOneElement() {
        list = new ArrayList(1);
        checkEqual(new int[]{1}, list);
    }

    @Test
    public void initialiseZeroElements() {
        list = new ArrayList();
        checkEqual(new int[]{}, list);
    }

    @Test
    public void removeMultipleElements() {
        list = new ArrayList(1, 2, 3);
        list.remove(0, 1);
        checkEqual(new int[]{3}, list);
    }

    @Test
    public void removeOneElement() {
        list = new ArrayList(1, 2, 3);
        list.remove(2);
        checkEqual(new int[]{1, 2}, list);
    }
}
