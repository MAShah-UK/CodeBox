package cbox.datastructures;

import org.junit.Assert;
import org.junit.Test;

public class HashMapTest {
    private HashMap map;

    @Test
    public void putZeroElements() {
        map = new HashMap(3);
        Assert.assertNull(map.get(5));
    }

    @Test
    public void putOneElement() {
        map = new HashMap(3);
        map.put(1, "HELLO");
        Assert.assertEquals("HELLO", map.get(1));
    }

    @Test
    public void putTwoElements_getFirst() {
        map = new HashMap(3);
        map.put(1, "HELLO");
        map.put(2, "WORLD");
        Assert.assertEquals("HELLO", map.get(1));
    }

    @Test
    public void putTwoElements_getSecond() {
        map = new HashMap(3);
        map.put(1, "HELLO");
        map.put(2, "WORLD");
        Assert.assertEquals("WORLD", map.get(2));
    }
}
