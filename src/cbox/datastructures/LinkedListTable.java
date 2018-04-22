package cbox.datastructures;

/*
Elements can be accessed using a row and column index. The table can be flattened into a regular
linked list. The nodes are linked as shown:

    x--x--x--x
    |  |     |
    x  x     x
    |        |
    x        x
    |
    x

The 1st column contains 4 rows. The 2nd column contains 2 rows.
The 3rd column contains 1 row.  The 4th column contains 3 rows.
 */

public class LinkedListTable<T> {
    private Node<T> root;
    private int maxRows;
    private int columns;
    private int size;

    private static class Node<T> {
        private T value;
        private Node<T> right;
        private Node<T> down;

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getRight() {
            return right;
        }

        public void setRight(Node<T> right) {
            this.right = right;
        }

        public Node<T> getDown() {
            return down;
        }

        public void setDown(Node<T> down) {
            this.down = down;
        }
    }

    public int getMaxRows() {
        return maxRows;
    }

    public int getColumns() {
        return columns;
    }

    public int getSize() {
        return size;
    }

    // Specify index as -1 to get last column.
    private Node<T> getColumn(int columnIdx) {
        if(columnIdx < -1 || columnIdx > columns) {
            return null;
        }
        Node<T> column = root;
        int count = 0;
        while(column.getRight() != null && count != columnIdx) {
            column = column.getRight();
            count++;
        }
        if (count < columnIdx) {
            column = null;
        }
        return column;
    }

    // Specify index as -1 to get last row.
    private Node<T> getRow(int rowIdx, int columnIdx) {
        // maxRows is only true for one column.
        // It's a quick and dirty check.
        if(rowIdx < -1 || rowIdx > maxRows) {
            return null;
        }
        Node<T> row = getColumn(columnIdx);
        int count = 0;
        while(row.getDown() != null && count != rowIdx) {
            row = row.getDown();
            count++;
        }
        maxRows = Math.max(maxRows, count);
        if (count < rowIdx) {
            row = null;
        }
        return row;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public T get(int rowIdx, int columnIdx) {
        if(isEmpty()) {
            return null;
        }
        Node<T> node = getRow(rowIdx, columnIdx);
        if(node == null) {
            return null;
        }
        return node.getValue();
    }

    public void add(T value, int rowIdx, int columnIdx) {
        Node<T> node = new Node<>(value);
        if(rowIdx == 0 && columnIdx == 0 && isEmpty()) {
            root = node;
            maxRows++;
            columns++;
        } else if(rowIdx == 0) {
            Node<T> element = getColumn(columnIdx-1);
            node.setRight(element.getRight());
            element.setRight(node);
            columns++;
        } else {
            Node<T> element = getRow(rowIdx-1, columnIdx);
            node.setDown(element.getDown());
            element.setDown(node);
            maxRows++;
        }
        size++;
    }

    public void flatten(T[] target) {
        if(isEmpty()) {
            return;
        }
        Node<T> column = root;
        Node<T> row;
        int count = 0;
        while(column != null) {
            row = column;
            while(row != null) {
                target[count] = row.getValue();
                row = row.getDown();
                count++;
                if(count > target.length) {
                    return;
                }
            }
            column = column.getRight();
        }
    }

    public void clear() {
        root = null;
        columns = 0;
        maxRows = 0;
        size = 0;
    }
}
