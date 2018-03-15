package com.dsac.ms.datastructures;

public class Node<T> {
    private T value;
    private Node<T> next;

    public Node(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> newNode) {
        next = newNode;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
