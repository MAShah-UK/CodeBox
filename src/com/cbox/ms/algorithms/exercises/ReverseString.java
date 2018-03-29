package com.cbox.ms.algorithms.exercises;

public class ReverseString {
    public static String exec(String str) {
        int nChars = str.length();
        char[] str2chars = str.toCharArray();
        char[] rev = new char[nChars];

        for (int i = 0; i < nChars; i++) {
            rev[i] = str2chars[nChars-1-i];
        }

        return String.valueOf(rev);
    }

    public static String exec2(String str) {
        return new StringBuilder(str).reverse().toString();
    }
}
