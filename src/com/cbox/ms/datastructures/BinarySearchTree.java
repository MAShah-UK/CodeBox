package com.cbox.ms.datastructures;

public class BinarySearchTree<T extends Comparable<T>> {
    private BiNode<T> root;
    private int size = 0;

    public BinarySearchTree(T...values) {
        add(values);
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    // Returns false if not all values could be added.
    public boolean add(T[] values) {
        boolean addedAll = true;
        for (T val : values) {
            boolean output = add(val);
            if (!output) {
                addedAll = false;
            }
        }
        return addedAll;
    }

    // Returns false if element already exists.
    public boolean add(T value) {
        BiNode<T> newNode = new BiNode<>(value);
        boolean added = false;
        if (isEmpty()) {
            root = newNode;
            added = true;
        } else {
            BiNode<T> p = findParent(value, root);
            BiNode<T> l = p.getLeft();
            BiNode<T> r = p.getRight();
            int comp = value.compareTo(p.getValue());
            if (comp < 0 && p.getLeft() == null) {
                p.setLeft(newNode);
                added = true;
            } else if (comp > 0 && p.getRight() == null) {
                p.setRight(newNode);
                added = true;
            }
        }
        if (added) {
            size++;
        }
        return added;
    }

    private BiNode<T> findParent(T value, BiNode<T> curr) {
        int comp = value.compareTo(curr.getValue());
        if (comp > 0) {
            if (curr.getRight() == null || curr.getRight().getValue().equals(value)) {
                return curr;
            } else {
                return findParent(value, curr.getRight());
            }
        } else if (comp < 0) {
            if (curr.getLeft() == null || curr.getLeft().getValue().equals(value)) {
                return curr;
            } else {
                return findParent(value, curr.getLeft());
            }
        } else {
            return curr;
        }
    }

    public boolean contains(T value) {
        if (isEmpty()) {
            return false;
        }
        BiNode<T> p = findParent(value, root);
        BiNode<T> l = p.getLeft();
        BiNode<T> r = p.getRight();
        if ( (l != null && l.getValue().equals(value)) ||
             (r != null && r.getValue().equals(value)) ) {
            return true;
        }
        return false;
    }

    // TODO: Doesn't seem like the cleanest way to keep track of the index.
    private int toArrayIdx;
    // Returns elements 'in-order'.
    private void toArray(BiNode<T> curr, T[] array) {
        if (curr.getLeft() != null) {
            toArray(curr.getLeft(), array);
        }
        array[toArrayIdx] = curr.getValue();
        toArrayIdx++;
        if (curr.getRight() != null) {
            toArray(curr.getRight(), array);
        }
    }

    // TODO: Will be better to return an array rather than expecting
    // user to provide it, but I don't know how to initialise a generic
    // array directly without compilation errors.
    public void toArray(T[] array) {
        if (isEmpty()) {
            return;
        }
        if (array.length < size) {
            throw new IndexOutOfBoundsException();
        }
        toArrayIdx = 0;
        toArray(root, array);
    }
}
