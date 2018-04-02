package cbox.datastructures;

public class Queue<T> {
    private Node<T> head;
    private Node<T> tail;

    private static class Node<T> {
        private T value;
        private Node<T> next;
        private Node(T value) {
            this.value = value;
        }
    }

    public void clear() {
        head = null;
        tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public T peak() {
        return head.value;
    }

    public T pop() {
        T value = head.value;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return value;
    }

    // Head stays with the first element, tail moves one along.
    public void push(T value) {
        Node<T> node = new Node<>(value);
        if (head == null) {
            head = node;
        }
        if (tail != null) {
            tail.next = node;
        }
        tail = node;
    }
}
