package com.cbox.ms.datastructures;

public class BinarySearchTree<T extends Comparable<T>> {
    private BiNode<T> root;
    private int size = 0;

    public boolean isEmpty() {
        return root == null;
    }

    public boolean add(T value) {
        boolean isValid;
        if (isEmpty()) {
            root = new BiNode<>(value);
            isValid = true;
        } else {
            isValid = add(value, root);
        }
        return isValid;
    }

    private boolean add(T value, BiNode<T> curr) {
        boolean added;
        int comp = value.compareTo(curr.getValue());
        if (comp > 0) {
            if (curr.getRight() == null) {
                curr.setRight(new BiNode<>(value));
                added = true;
            } else {
                added = add(value, curr.getRight());
            }
        } else if (comp < 0) {
            if (curr.getLeft() == null) {
                curr.setLeft(new BiNode<>(value));
                added = true;
            } else {
                added = add(value, curr.getLeft());
            }
        } else {
            added = false;
        }
        return added;
    }
}
