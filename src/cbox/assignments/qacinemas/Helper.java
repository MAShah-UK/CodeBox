package cbox.assignments.qacinemas;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

// Contains methods to simplify small operations.
class Helper {
    public static String currDate(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }

    public static String currDayOfWeek() {
        return currDate("E");
    }

    public static String joinBy(String str1, String str2, char joinChar) {
        return str1 + joinChar + str2;
    }

    public static String padStr(String source, int length, char padChar) {
        int num = length - source.length();
        char[] padding = new char[(num > 0) ? num : 0];
        Arrays.fill(padding, padChar);
        return source + String.valueOf(padding);
    }
}
