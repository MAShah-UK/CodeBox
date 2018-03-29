package com.cbox.ms.algorithms.exercises;

import java.util.ArrayList;
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
    public static String exec(int n) {
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
