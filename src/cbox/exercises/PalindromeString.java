package cbox.exercises;

import java.util.List;

/* Determines if a string is a palindrome.
Steps:
- Remove any whitespace since it's ignored when checking for palindromes.
- Verification: if the length of the string is 0 or 1 it's a palindrome.
- Loop until the midpoint of the string and compare characters from the
  left and right sides.
  - If the characters don't match, return false.
- If the loop ends the string is a palindrome, return true.
 */

public class PalindromeString {
    public static boolean exec(String str) {
        StringBuilder whitespaceRemoved = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (c != ' ') {
                whitespaceRemoved.append(c);
            }
        }
        str = whitespaceRemoved.toString();

        if (List.of(0, 1).contains(str.length())) {
            return true;
        }

        str = str.toLowerCase();
        int midIdx = (int) Math.ceil(str.length() / 2);
        int maxIdx = str.length()-1;
        for (int i = 0; i < midIdx; i++) {
            if (str.charAt(i) != str.charAt(maxIdx-i)) {
                return false;
            }
        }

        return true;
    }
}
