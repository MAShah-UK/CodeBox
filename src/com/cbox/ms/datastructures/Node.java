package com.cbox.ms.datastructures;

public class Node<T> {
    private T value;
    private Node<T> right;

    public Node(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setRight(Node<T> newNode) {
        right = newNode;
    }
}
