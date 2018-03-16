package com.dsac.ms.datastructures;

// Bidrectional nodes.
public class BiNode<T> extends Node<T> {
    private BiNode<T> previous;

    public BiNode(T value) {
        super(value);
    }

    public BiNode<T> getPrevious() {
        return previous;
    }

    public void setPrevious(BiNode<T> previous) {
        this.previous = previous;
    }
}
