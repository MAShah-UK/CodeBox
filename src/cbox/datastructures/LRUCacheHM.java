package cbox.datastructures;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Least recently used cache, implemented using a HashMap.
// Contains a fixed set of values, removes the oldest first is size is exceeded.
public class LRUCacheHM<T> {
    private int maxSize;
    private Map<T, Node<T>> map;
    private Node<T> head;
    private Node<T> tail;

    private static class Node<T> {
        private T key;
        private Node<T> prev;
        private Node<T> next;
        public Node(T key) {
            this.key = key;
        }
        public T getKey() {
            return key;
        }
        public Node<T> getPrev() {
            return prev;
        }
        public void setPrev(Node<T> prev) {
            this.prev = prev;
        }
        public Node<T> getNext() {
            return next;
        }
        public void setNext(Node<T> next) {
            this.next = next;
        }
    }

    public LRUCacheHM(int maxSize) {
        this.maxSize = maxSize;
        map = new HashMap<>(maxSize);
    }

    public int getMaxSize() {
        return maxSize;
    }

    public int getCurrentSize() {
        return map.size();
    }

    public T get(T key) {
        Node<T> node = map.get(key);
        if(node == null) {
            return null;
        }
        remove(node, false);
        addToEnd(node);
        return node.getKey();
    }

    public void put(T key) {
        Node<T> node = new Node<>(key);
        if(map.size() >= maxSize) {
            remove(head, true);
        }
        if(head == null) {
            head = node;
            tail = node;
            map.put(key, node);
        } else {
            Node<T> exists = map.get(key);
            if(exists != tail) {
                if(exists == null) {
                    map.put(key, node);
                    exists = node;
                } else {
                    remove(exists, false);
                }
                addToEnd(exists);
            }
        }
    }

    private void addToEnd(Node<T> node) {
        tail.setNext(node);
        node.setPrev(tail);
        tail = node;
    }

    private void remove(Node<T> node, boolean fromMap) {
        Node<T> prev = node.getPrev();
        Node<T> next = node.getNext();
        if(prev != null) {
            prev.setNext(next);
        } else { // node is head node.
            head = head.getNext();
            if(head == null) {
                tail = null;
            }
        }
        if(next != null) {
            next.setPrev(prev);
        }
        if(fromMap) {
            map.remove(node.getKey());
        }
    }

    public T[] toArray(T[] array) {
        Node<T> curr = head;
        int idx = 0;
        while(curr != null && idx < array.length) {
            array[idx] = curr.getKey();
            curr = curr.getNext();
            idx++;
        }
        return array;
    }
}
