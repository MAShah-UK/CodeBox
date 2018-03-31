package cbox.exercises;

public class ReverseString {

    public static String exec(String str) {
        if (str == null || str.length() < 2) {
            return str;
        }

        char[] rev = str.toCharArray();
        int maxIdx = str.length()-1;
        for (int i = 0; i < str.length()/2; i++) {
            char tmp = rev[i];
            rev[i] = rev[maxIdx-i];
            rev[maxIdx-i] = tmp;
        }
        return String.valueOf(rev);
    }

    public static String exec2(String str) {
        int nChars = str.length();
        char[] rev = new char[nChars];
        for (int i = 0; i < nChars; i++) {
            rev[i] = str.charAt(nChars-1-i);
        }
        return String.valueOf(rev);
    }

    public static String exec3(String str) {
        return new StringBuilder(str).reverse().toString();
    }
}
