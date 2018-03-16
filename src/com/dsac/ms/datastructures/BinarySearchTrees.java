package com.dsac.ms.datastructures;

import java.util.List;

public class BinarySearchTrees<T> {
    private BiNode<T> root;
    private int size = 0;

    public boolean isEmpty() {
        return root == null;
    }

    public boolean add(T value) {

        BiNode<T> newNode = new BiNode<>(value);

        if (isEmpty()) {
            root = newNode;
            return true;
        }

        boolean done = false;
        BiNode<T> curr = root;
        while(!done) {
            // TODO: Use comparable?
            Comparable<T> old = (Comparable<T>) value;
            if (value > curr.getValue()) {

            }



        }
    }
}
