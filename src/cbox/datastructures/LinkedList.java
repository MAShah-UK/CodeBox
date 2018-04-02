package cbox.datastructures;

/*
Purpose: Data is contained within nodes which point to the next node in the list.
A linked list only iterates in one direction since each node points to the next node.
A doubly linked list can iterate in both directions each node points to the next and previous nodes.
Advantage:
    - Inserting elements can be quick - prepend O(1), append O(n).
Disadvantage:
    - Slow to get nth element O(n).
 */

public class LinkedList<T> {
    private Node<T> root;
    private int size;

    private static class Node<T> {
        private T value;
        private Node<T> next;
        public Node(T value) {
            this.value = value;
        }
    }

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
        if (root.value instanceof String) {
            delim = "";
        }

        StringBuilder str = new StringBuilder();
        Node<T> curr = root;
        while (curr != null) {
            str.append(curr.value.toString());
            str.append(delim);
            curr = curr.next;
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
        while (curr.next != null) {
            curr = curr.next;
        }
        return curr;
    }

    // Returns null if no node found with value.
    private Node<T> findNode(T value, boolean retPrevious) {
        Node<T> curr = root;

        if (retPrevious) {
            while (curr.next != null && !curr.next.value.equals(value)) {
                curr = curr.next;
            }
        } else {
            while (curr != null && !curr.value.equals(value)) {
                curr = curr.next;
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
            target = target.next;
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
            lastNode.next = newNode;
        }
        size++;
    }

    // Time complexity: O(1).
    public void prepend(T value) {
        Node<T> node = new Node<>(value);
        node.next = root;
        root = node;
        size++;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public void delete(T value) {
        if (size < 2) {
            clear();
            return;
        }

        Node<T> initial = findNode(value, true);
        initial.next = skipNodes(initial, 2);
        size--;
    }
}
