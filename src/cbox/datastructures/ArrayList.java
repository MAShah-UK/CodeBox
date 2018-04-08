package cbox.datastructures;

/* ArrayLists use a dynamically resizing array to contain data.
Properties:
Implementation steps:
- Constructor.
  - Store provided elements / arrays as a field.
- Add elements.
  - Create a new array that's large enough to store values for the current array field and the new array.
  - Copy original array into larger array.
  - Copy new array into larger array.
- Remove elements.
  - Create a new array that's smaller than the current array by 1.
  - Copy all of the elements from the original array, but skip the required index.
- Contains element.
  - Use a for each loop to check each element.
TODO:
- Support generics.
- Implement more features.
- Use efficient sorting.
 */

import java.util.Arrays;

public class ArrayList {
    private int[] array;

    @Override
    public String toString() {
        if (array == null || array.length == 0) {
            return "[]";
        }

        StringBuilder output = new StringBuilder();
        output.append('[');
        for (int i: array) {
            output.append(i).append(',').append(' ');
        }
        output.delete(output.length()-2, output.length());
        output.append(']');
        return output.toString();
    }

    public ArrayList() {
    }

    public ArrayList(int...elements) {
        add(elements);
    }

    public int size() {
        return array.length;
    }

    public void add(int...elements) {
        if (array == null || array.length == 0) {
            array = elements;
            return;
        }

        int[] largeArray = new int[array.length + elements.length];
        for (int i = 0; i < array.length; i++) {
            largeArray[i] = array[i];
        }
        for (int i = 0; i < elements.length; i++) {
            int offset = i + array.length;
            largeArray[offset] = elements[i];
        }
        array = largeArray;
    }

    public void remove(int idx) {
        remove(idx, idx);
    }

    public void remove(int from, int to) {
        if (from > to) {
            throw new IllegalArgumentException();
        }

        int elems = to-from+1;
        int[] smallArray = new int[array.length-elems];
        for (int i = 0; i < from; i++) {
            smallArray[i] = array[i];
        }
        for (int i = to + 1; i < array.length; i++) {
            int offset = i-elems;
            smallArray[offset] = array[i];
        }
        array = smallArray;
    }

    public boolean contains(int element) {
        for (int currElem : array) {
            if (currElem == element) {
                return true;
            }
        }
        return false;
    }

    public int get(int idx) {
        return array[idx];
    }
}
