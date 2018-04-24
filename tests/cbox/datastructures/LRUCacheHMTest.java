package cbox.datastructures;

import org.junit.Assert;
import org.junit.Test;

public class LRUCacheHMTest {
    private LRUCacheHM<Integer> cache;

    public void checkEquals(Integer[] input, Integer[] expected, int cacheSize) {
        cache = new LRUCacheHM<>(cacheSize);
        for(int i: input) {
            cache.put(i);
        }
        Integer[] result = new Integer[cache.getCurrentSize()];
        cache.toArray(result);
        Assert.assertArrayEquals(expected, result);
    }

    @Test
    public void addOne() {
        checkEquals(new Integer[]{1}, new Integer[]{1}, 3);
    }

    @Test
    public void addTwo() {
        checkEquals(new Integer[]{1, 2}, new Integer[]{1, 2}, 3);
    }

    @Test
    public void addThree() {
        checkEquals(new Integer[]{1, 3, 2}, new Integer[]{1, 3, 2}, 3);
    }

    @Test
    public void addTwo_repeatTwo() {
        checkEquals(new Integer[]{1, 1}, new Integer[]{1}, 3);
    }

    @Test
    public void addThree_repeatTwo() {
        checkEquals(new Integer[]{1, 2, 1}, new Integer[]{2, 1}, 3);
    }

    @Test
    public void addFour_extraOne() {
        checkEquals(new Integer[]{1, 2, 3, 4}, new Integer[]{2, 3, 4}, 3);
    }

    @Test
    public void addFive_extraTwo_repeatOne() {
        checkEquals(new Integer[]{1, 2, 3, 1, 4}, new Integer[]{3, 1, 4}, 3);
    }
}
