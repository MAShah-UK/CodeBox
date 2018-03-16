package com.dsac.ms.datastructures;

// Bidirectional node.
public class BiNode<T> extends Node<T> {
    private BiNode<T> left;

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
