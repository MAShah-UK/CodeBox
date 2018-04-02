package cbox.datastructures;

public class Stack<T> {
    private Node<T> head;

    private static class Node<T> {
        private T value;
        private Node<T> next;
        public Node(T value) {
            this.value = value;
        }
    }

    public void clear() {
        head = null;
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
        return value;
    }

    public void push(T value) {
        Node<T> node = new Node<>(value);
        node.next = head;
        head = node;
    }
}
