package com.dsac.ms.datastructures;

/*
Purpose:

Advantage:
    - Inserting elements can be quick - prepend O(1), append O(n).
    -
Disadvantage:
    Slow to get nth element O(n).
 */

class Node<T> {
    private T value;
    private Node<T> next;

    public Node(T value) {
        this.value = value;
    }
}

public class LinkedList<T> {
    private Node root;

    public boolean isEmpty() {
        return root == null;
    }

    public void add(T value) {
        Node current = root;
        while (current != null) {
            
        }
    }
}
