package com.cbox.ms.datastructures;

// Bidirectional node.
public class BiNode<T> extends Node<T> {
    private BiNode<T> left;

    @Override
    public BiNode<T> getRight() {
        return (BiNode<T>) super.getRight();
    }

    public BiNode(T value) {
        super(value);
    }

    public BiNode<T> getLeft() {
        return left;
    }

    public void setLeft(BiNode<T> left) {
        this.left = left;
    }
}
