package cbox.exercises;

public class IsSubstring {
    public static boolean exec(String str1, String str2) {
        if(str1 == null || str2 == null) {
            return false;
        }
        if(str1.length() < str2.length()) {
            return exec(str2, str1);
        }
        if(str1.isEmpty() || str2.isEmpty()) {
            return true;
        }

        int match = 0;
        int idx = 1;
        char[] str1c = str1.toCharArray();
        char[] str2c = str2.toCharArray();
        for(char c: str1c) {
            if(c == str2c[match]) {
                match++;
                if(match == str2.length()) {
                    return true;
                }
            } else {
                match = 0;
                if(str1.length()-idx < str2.length()) {
                    return false;
                }
            }
            idx++;
        }
        return false;
    }
}
