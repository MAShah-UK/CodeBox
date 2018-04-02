package cbox.datastructures;

public class MinStack<T extends Comparable<T>> {
    private Stack<T> values = new Stack<>();
    private Stack<T> minimum = new Stack<>();

    public void clear() {
        values.clear();
        minimum.clear();
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }

    public T min() {
        return minimum.peak();
    }

    public T peak() {
        return values.peak();
    }

    public T pop() {
        T value = values.pop();
        if (value.equals(minimum.peak())) {
            minimum.pop();
        }
        return value;
    }

    public void push(T value) {
        values.push(value);
        if (minimum.isEmpty() || value.compareTo(minimum.peak()) < 0) {
            minimum.push(value);
        }
    }
}
