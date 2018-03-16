package com.dsac.ms.datastructures;

import org.junit.Assert;
import org.junit.Test;

public class LinkedListTest {
    @Test
    public void noElements() {
        LinkedList<Integer> list = new LinkedList<>();
        Assert.assertEquals("", list.toString());
    }

    @Test
    public void addIntegerElements() {
        LinkedList<Integer> list = new LinkedList<>(1, 2, 3, 4, 5);
        Assert.assertEquals("1 2 3 4 5", list.toString());
    }

    @Test
    public void addStringElements() {
        LinkedList<String> list = new LinkedList<>("Hello", " world", "!");
        Assert.assertEquals("Hello world!", list.toString());
    }

    @Test
    public void appendIntegerElements() {
        LinkedList<Integer> list = new LinkedList<>(5, 6, 7);
        list.append(8);
        list.append(9);
        Assert.assertEquals("5 6 7 8 9", list.toString());
    }

    @Test
    public void appendStringElements() {
        LinkedList<String> list = new LinkedList<>("Hello");
        list.append(" world");
        list.append("!");
        Assert.assertEquals("Hello world!", list.toString());
    }

    @Test
    public void modifyAndClearList() {
        LinkedList<Integer> list = new LinkedList<>(1, 2, 3, 4, 5);
        list.append(6);
        list.prepend(0);
        list.clear();
        Assert.assertEquals(0, list.getSize());
    }

    @Test
    public void getIntegerListSize() {
        LinkedList<Integer> list = new LinkedList<>(1, 2, 3, 4, 5);
        Assert.assertEquals(5, list.getSize());
    }

    @Test
    public void getStringListSize() {
        LinkedList<String> list = new LinkedList<>("Hello", " world", "!");
        Assert.assertEquals(3, list.getSize());
    }

    @Test
    public void prependIntegerElements() {
        LinkedList<Integer> list = new LinkedList<>(7, 8, 9);
        list.prepend(6);
        list.prepend(5);
        Assert.assertEquals("5 6 7 8 9", list.toString());
    }

    @Test
    public void prependStringElements() {
        LinkedList<String> list = new LinkedList<>("!");
        list.prepend(" world");
        list.prepend("Hello");
        Assert.assertEquals("Hello world!", list.toString());
    }

    @Test
    public void removeValidIntegerElements() {
        LinkedList<Integer> list = new LinkedList<>(1, 2, 2, 3, 4, 5);
        list.delete(2);
        list.delete(4);
        Assert.assertEquals("1 2 3 5", list.toString());
    }

    @Test
    public void removeInvalidIntegerElements() {
        LinkedList<Integer> list = new LinkedList<>(1, 2, 3, 4, 5);
        list.delete(6);
        list.delete(7);
        Assert.assertEquals("1 2 3 4 5", list.toString());
    }
}
