package cbox.exercises;

/* Determines if two given strings are anagrams.

 */

public class AnagramStrings {
    public static boolean exec(String str1, String str2) {
        str1 = str1.trim().toLowerCase();
        str2 = str2.trim().toLowerCase();
        if (str1.length() != str2.length()) {
            return false;
        }
        int[] charCounter = new int[256];
        for (char c : str1.toCharArray()) { // O(m)
            charCounter[c]++;
        }
        for (char c : str2.toCharArray()) { // O(n)
            charCounter[c]--;
        }
        for (int i : charCounter) {         // O(1)
            if (i != 0) {
                return false;
            }
        }

        return true;                        // O(m+n+1) or O(n)
    }
}
