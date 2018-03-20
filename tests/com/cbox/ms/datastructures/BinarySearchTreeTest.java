package com.cbox.ms.datastructures;

import org.junit.Assert;
import org.junit.Test;

public class BinarySearchTreeTest {
    private BinarySearchTree<Integer> intTree;
    private BinarySearchTree<String> strTree;

    @Test
    public void addIntegerElements() {

    }

    @Test
    public void checkSizeOfIntegerTree1() {
        intTree = new BinarySearchTree<>(5, 3, 7, 2, 4, 6, 8);
        Assert.assertEquals(7, intTree.getSize());
    }

    @Test
    public void checkSizeOfIntegerTree2() {
        intTree = new BinarySearchTree<>(5);
        intTree.add(3);
        intTree.add(7);
        Assert.assertEquals(3, intTree.getSize());
    }

    @Test
    public void checkSizeOfRepeatedIntegerTree() {
        intTree = new BinarySearchTree<>(5, 3, 7, 3);
        Assert.assertEquals(3, intTree.getSize());
    }

    @Test
    public void checkContainsIntegerElement() {
        intTree = new BinarySearchTree<>(5, 3, 7, 2, 4, 6, 8);
        Assert.assertEquals(true, intTree.contains(7));
        Assert.assertEquals(false, intTree.contains(10));
    }
}
