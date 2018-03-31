package cbox.exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* Determines the nth line of Pascal's triangle.
Steps:
- Handle edge cases:
  - Return null if n is < 1.
  - Return "1" if n == 1.
  - Return "1 1" if n == 2.
- Create two lists to keep track of previous and current lines.
- Use previous line to generate current line.
- Repeat until required line is reached.
 */

public class PascalsTriangle {

    // Using built-in arrays and taking advantage of symmetry.
    public static String exec(int n) {
        if (n < 1) {
            return null;
        } else if (n < 3) {
            int[] ret = new int[n];
            Arrays.fill(ret, 1);
            return Arrays.toString(ret);
        }

        int[] prev = new int[n], curr = new int[n];
        prev[0] = prev[1] = curr[0] = curr[n-1] = 1;
        int currN = 3;
        while (currN <= n) {
            for (int i = 1; i < (currN+1)/2; i++) {
                curr[i] = prev[i-1] + prev[i];
                curr[currN-1-i] = curr[i];
            }
            curr[currN-1] = 1;
            prev = Arrays.copyOf(curr, curr.length);
            currN++;
        }

        return Arrays.toString(curr);
    }

    // Using list.
    public static String exec2(int n) {
        if (n < 1) {
            return null;
        }
        else if (n == 1) {
            return "[1]";
        } else if (n == 2) {
            return "[1, 1]";
        }

        // List.of / Arrays.list return fixed length lists.
        List<Integer> prev = new ArrayList<>(List.of(1, 1));
        List<Integer> curr = new ArrayList<>(prev);
        int currLine = 3;
        while (currLine <= n) {
            curr.add(1, 0);
            for (int i = 1; i < curr.size() - 1; i++) {
                curr.set(i, prev.get(i - 1) + prev.get(i));
            }
            prev = new ArrayList<>(curr);
            currLine++;
        }

        return curr.toString();
    }
}
