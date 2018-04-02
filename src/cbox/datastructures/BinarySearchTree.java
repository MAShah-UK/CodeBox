package cbox.datastructures;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node<T> root;
    private int size = 0;

    private static class Node<T> {
        private T key;
        private Node<T> left;
        private Node<T> right;
        public Node(T value) {
            this.key = value;
        }
    }

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
        Node<T> newNode = new Node<>(value);
        boolean added = false;
        if (isEmpty()) {
            root = newNode;
            added = true;
        } else {
            Node<T> p = findParent(value, root);
            Node<T> l = p.left;
            Node<T> r = p.right;
            int comp = value.compareTo(p.key);
            if (comp < 0 && p.left == null) {
                p.left = newNode;
                added = true;
            } else if (comp > 0 && p.right == null) {
                p.right = newNode;
                added = true;
            }
        }
        if (added) {
            size++;
        }
        return added;
    }

    private Node<T> findParent(T value, Node<T> curr) {
        int comp = value.compareTo(curr.key);
        if (comp > 0) {
            if (curr.right == null || curr.right.key.equals(value)) {
                return curr;
            } else {
                return findParent(value, curr.right);
            }
        } else if (comp < 0) {
            if (curr.left == null || curr.left.key.equals(value)) {
                return curr;
            } else {
                return findParent(value, curr.left);
            }
        } else {
            return curr;
        }
    }

    public boolean contains(T value) {
        if (isEmpty()) {
            return false;
        }
        Node<T> p = findParent(value, root);
        Node<T> l = p.left;
        Node<T> r = p.right;
        if ( (l != null && l.key.equals(value)) ||
             (r != null && r.key.equals(value)) ) {
            return true;
        }
        return false;
    }

    // TODO: Doesn't seem like the cleanest way to keep track of the index,
    // but I don't want to use an ArrayList since that means that my data
    // structure is relying on another data structure. I rather minimise
    // dependencies and stick to built in solutions - i.e. arrays.
    private int toArrayIdx;
    // Returns elements 'in-order'.
    private void toArray(Node<T> curr, T[] array) {
        if (curr.left != null) {
            toArray(curr.left, array);
        }
        array[toArrayIdx] = curr.key;
        toArrayIdx++;
        if (curr.right != null) {
            toArray(curr.right, array);
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
