package cbox.exercises;

// Finds value with time complexity O(logn).
// Requires each element in table to be in ascending order.
public class TableBinarySearch {
    public static <S extends Comparable<S>> boolean exec(S[][] table, S value) {
        if(table == null || value == null) {
            return false;
        }

        final int maxRows = table.length;
        final int maxCols = table[0].length;
        final int maxElems = maxRows * maxCols;

        int low = 0;
        int high = maxElems-1;
        while(low <= high) {
            int mid = (low + high)/2;
            int row = mid / maxCols;
            int col = mid % maxCols;

            int comp = table[row][col].compareTo(value);
            if(comp < 0) {
                low = mid+1;
            } else if (comp > 0) {
                high = mid-1;
            } else {
                return true;
            }
        }

        return false;
    }
}
