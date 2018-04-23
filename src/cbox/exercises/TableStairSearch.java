package cbox.exercises;

// Stair case search time complexity is O(n).
// Requires that each row is sorted in ascending order.
// If the entire table is sorted in ascending order then binary search is more efficient.
public class TableStairSearch {
    public static <S extends Comparable<S>> boolean exec(S[][] table, S value) {
        if (table == null || value == null) {
            return false;
        }

        final int maxRows = table.length;
        final int maxCols = table[0].length;
        int row = 0;
        int col = maxCols-1;
        while(row <= maxRows-1 && col >= 0) {
            int comp = table[row][col].compareTo(value);
            if(comp < 0) {
                row++;
            } else if(comp > 0) {
                col--;
            } else {
                return true;
            }
        }
        return false;
    }
}
