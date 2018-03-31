package cbox.datastructures;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

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

    @Test
    public void getSortedIntegerArray() {
        intTree = new BinarySearchTree<>(5, 3, 7, 2, 4, 6, 8);
        Integer[] expected = new Integer[]{2, 3, 4, 5, 6, 7, 8};
        Integer[] actual = new Integer[intTree.getSize()];
        intTree.toArray(actual);
        System.out.println(Arrays.toString(expected));
        System.out.println(Arrays.toString(actual));
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void getSortedStringArray() {
        strTree = new BinarySearchTree<>("milk", "cheese", "tea", "apples");
        String[] expected = new String[]{"apples", "cheese", "milk", "tea"};
        String[] actual = new String[strTree.getSize()];
        strTree.toArray(actual);
        Assert.assertArrayEquals(expected, actual);
    }
}
