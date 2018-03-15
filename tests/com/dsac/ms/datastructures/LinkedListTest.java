package com.dsac.ms.datastructures;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {
    @Before
    public void before() {

    }

    @Test
    public void noElements() {
        LinkedList<Integer> list = new LinkedList<>();
        Assert.assertEquals("", list.toString());
        Assert.assertEquals(0, list.getSize());
    }

    @Test
    public void addIntegerElements() {
        LinkedList<Integer> list = new LinkedList<>(1, 2, 3, 4, 5);
        Assert.assertEquals("1 2 3 4 5", list.toString());
        Assert.assertEquals(5, list.getSize());
    }

    @Test
    public void addStringElements() {
        LinkedList<String> list = new LinkedList<>("Hello", " world", "!");
        Assert.assertEquals("Hello world!", list.toString());
        Assert.assertEquals(3, list.getSize());
    }

    @Test
    public void appendIntegerElements() {
        LinkedList<Integer> list = new LinkedList<>(7, 8, 9);
        
    }

    @Test
    public void prependIntegerElements() {

    }

    @Test
    public void removeIntegerElements() {

    }
}
