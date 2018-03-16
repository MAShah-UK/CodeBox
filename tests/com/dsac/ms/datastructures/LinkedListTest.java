package com.dsac.ms.datastructures;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LinkedListTest {
    private LinkedList<Integer> intList;
    private LinkedList<String> strList;

    @Test
    public void noElements() {
        intList = new LinkedList<>();
        assertEquals("", intList.toString());
    }

    @Test
    public void addIntegerElements() {
        intList = new LinkedList<>(1, 2, 3, 4, 5);
        assertEquals("1 2 3 4 5", intList.toString());
    }

    @Test
    public void addStringElements() {
        strList = new LinkedList<>("Hello", " world", "!");
        assertEquals("Hello world!", strList.toString());
    }

    @Test
    public void appendIntegerElements() {
        intList = new LinkedList<>(5, 6, 7);
        intList.append(8);
        intList.append(9);
        assertEquals("5 6 7 8 9", intList.toString());
    }

    @Test
    public void appendStringElements() {
        strList = new LinkedList<>("Hello");
        strList.append(" world");
        strList.append("!");
        assertEquals("Hello world!", strList.toString());
    }

    @Test
    public void modifyAndClearList() {
        intList = new LinkedList<>(1, 2, 3, 4, 5);
        intList.append(6);
        intList.prepend(0);
        intList.clear();
        assertEquals(0, intList.getSize());
    }

    @Test
    public void getIntegerListSize() {
        intList = new LinkedList<>(1, 2, 3, 4, 5);
        assertEquals(5, intList.getSize());
    }

    @Test
    public void getStringListSize() {
        strList = new LinkedList<>("Hello", " world", "!");
        assertEquals(3, strList.getSize());
    }

    @Test
    public void prependIntegerElements() {
        intList = new LinkedList<>(7, 8, 9);
        intList.prepend(6);
        intList.prepend(5);
        assertEquals("5 6 7 8 9", intList.toString());
    }

    @Test
    public void prependStringElements() {
        strList = new LinkedList<>("!");
        strList.prepend(" world");
        strList.prepend("Hello");
        assertEquals("Hello world!", strList.toString());
    }

    @Test
    public void removeValidIntegerElements() {
        intList = new LinkedList<>(1, 2, 2, 3, 4, 5);
        intList.delete(2);
        intList.delete(4);
        assertEquals("1 2 3 5", intList.toString());
    }

    @Test
    public void removeInvalidIntegerElements() {
        intList = new LinkedList<>(1, 2, 3, 4, 5);
        intList.delete(6);
        intList.delete(7);
        assertEquals("1 2 3 4 5", intList.toString());
    }
}
