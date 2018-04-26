package cbox.datastructures;

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
        private Node<T> left;
        private Node<T> right;
        public Node(T key) {
            this.key = key;
        }
        public T getKey() {
            return key;
        }
        public Node<T> getLeft() {
            return left;
        }
        public void setLeft(Node<T> left) {
            this.left = left;
        }
        public Node<T> getRight() {
            return right;
        }
        public void setRight(Node<T> right) {
            this.right = right;
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

    // TODO.
    public T get(T key) {
        Node<T> node = map.get(key);
        if(node == null) {
            return null;
        }
        remove(node, false);
        addToEnd(node, false);
        return node.getKey();
    }

    public void put(T key) {
        Node<T> node = new Node<>(key);
        if(head == null) {
            head = node;
            tail = node;
            map.put(key, node);
        } else {
            Node<T> exists = map.get(key);
            if(exists != tail) {
                if(exists == null) {
                    tail.setRight(node);
                    node.setLeft(tail);
                    tail = node;
                    map.put(key, node);
                } else {
                    Node<T> left = exists.getLeft();
                    Node<T> right = exists.getRight();
                    if(left != null) {
                        left.setRight(right);
                    } else {
                        head = head.getRight();
                        if(head == null) {
                            tail = null;
                        }
                    }
                    if(right != null) {
                        right.setLeft(left);
                    }

                    tail.setRight(exists);
                    exists.setLeft(tail);
                    exists.setRight(null);
                    tail = exists;
                }
            }
        }
        if(map.size() > maxSize) {
            head = head.getRight();
            head.setLeft(null);
            if(head == null) {
                tail = null;
            }
            map.remove(key);
        }
    }

    private void addToEnd(Node<T> node, boolean toMap) {
        if(tail == null) {
            tail = node;
        } else {
            tail.setRight(node);
            node.setLeft(tail);
            node.setRight(null);
            tail = node;
        }
        if(toMap) {
            map.put(node.getKey(), node);
        }
    }

    private void remove(Node<T> node, boolean fromMap) {
        Node<T> left = node.getLeft();
        Node<T> right = node.getRight();
        if(left != null) {
            left.setRight(right);
        } else {
            head = head.getRight();
            if(head == null) {
                tail = null;
            }
        }
        if(right != null) {
            right.setLeft(left);
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
            curr = curr.getRight();
            idx++;
        }
        return array;
    }
}
