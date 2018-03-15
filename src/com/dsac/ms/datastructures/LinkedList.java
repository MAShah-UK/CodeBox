package com.dsac.ms.datastructures;

/*
Purpose:

Advantage:
    - Inserting elements can be quick - prepend O(1), append O(n).
    -
Disadvantage:
    Slow to get nth element O(n).
 */

public class LinkedList<T> {
    private Node<T> root;
    private int size;

    public LinkedList() {

    }

    public LinkedList(T...values) {
        for (T value : values) {
            append(value);
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "";
        }

        String delim = " ";
        if (root.getValue() instanceof String) {
            delim = "";
        }

        StringBuilder str = new StringBuilder();
        Node<T> curr = root;
        while (curr != null) {
            str.append(curr.getValue().toString());
            str.append(delim);
            curr = curr.getNext();
        }

        if (delim.equals(" ")) {
            str.deleteCharAt(str.length()-1);
        }

        return str.toString();
    }

    // Returns null if there are no elements.
    private Node<T> getLastNode() {
        if (isEmpty()) {
            return null;
        }

        Node<T> curr = root;
        while (curr.getNext() != null) {
            curr = curr.getNext();
        }
        return curr;
    }

    // Returns null if no node found with value.
    private Node<T> findNode(T value, boolean retOneBefore) {
        Node<T> curr = root;

        if (retOneBefore) {
            while (curr.getNext() != null && !curr.getNext().getValue().equals(value)) {
                curr = curr.getNext();
            }
        } else {
            while (curr != null && !curr.getValue().equals(value)) {
                curr = curr.getNext();
            }
        }
        return curr;
    }

    // Returns null if target node not found.
    private Node<T> skipNodes(Node<T> initial, int offset) {
        if (initial == null || offset < 1) {
            return null;
        }

        Node<T> target = initial;
        for (int i = 0; i < offset; i++) {
            target = target.getNext();
            if (target == null) {
                break;
            }
        }

        return target;
    }

    // Time complexity: O(n).
    public void append(T value) {
        Node<T> newNode = new Node<>(value);
        Node<T> lastNode = getLastNode();
        if (lastNode == null) {
            root = newNode;
        } else {
            lastNode.setNext(newNode);
        }
        size++;
    }

    // Time complexity: O(1).
    public void prepend(T value) {
        Node<T> node = new Node<>(value);
        node.setNext(root);
        root = node;
        size++;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public void deleteWithValue(T value) {
        if (root == null) {
            return;
        }

        Node<T> initial = findNode(value, true);
        if (initial == root) {
            clear();
        } else {
            Node<T> target = skipNodes(initial, 2);
            initial.setNext(target);
        }
        size--;
    }
}
