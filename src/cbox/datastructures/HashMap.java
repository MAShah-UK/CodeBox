package cbox.datastructures;

// TODO: Make generic.
public class HashMap {

    private Node[] table;
    private int size;

    private static class Node {
        private final int hash;
        private final Integer key;
        private String value;
        private Node next;
        public Node(int hash, Integer key, String value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
        public int getHash() {
            return hash;
        }
        public Integer getKey() {
            return key;
        }
        public String getValue() {
            return value;
        }
        public void setValue(String value) {
            this.value = value;
        }
        public Node getNext() {
            return next;
        }
        public void setNext(Node next) {
            this.next = next;
        }
    }

    public HashMap(int size) {
        table = new Node[size];
        this.size = size;
    }

    public String get(Integer key) {
        int hash = key.hashCode();
        int idx = hash % size;
        // If empty bucket, return null.
        if(table[idx] == null) {
            return null;
        }
        // If non-empty bucket, find existing node and return value.
        Node curr = table[idx];
        while(curr != null) {
            if(hash == curr.getHash() && key.equals(curr.getKey())) {
                return curr.getValue();
            }
        }
        // If non-empty bucket, non-existing node, return null;
        return null;
    }

    public void put(Integer key, String value) {
        int hash = key.hashCode();
        int idx = hash % size;
        // If empty bucket, then add.
        if(table[idx] == null) {
            table[idx] = new Node(idx, key, value);
            return;
        }
        // If non-empty bucket, then find existing node.
        Node prev = null;
        Node curr = table[idx];
        while(curr != null) {
            if(hash == curr.getHash() && key.equals(curr.getKey())) {
                curr.setValue(value);
                return;
            }
            prev = curr;
            curr = curr.getNext();
        }
        // If non-empty bucket, and non-existing node.
        prev.setNext(new Node(hash, key, value));
    }
}
