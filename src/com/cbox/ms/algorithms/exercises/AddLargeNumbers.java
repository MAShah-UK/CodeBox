package com.cbox.ms.algorithms.exercises;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* Adds large numbers that don't fit into an byte/int/short/long/etc.
Steps:
- Validation.
  - Check that data was input.
  - Check that data consists of integers.
- Make array lengths uniform by appending extra 0s in shorter array.
- Loop backwards with as many iterations and input arrays.
  - Calculate new sum for that position by adding integers from both arrays and adding carry (initially 0).
  - Set carry to 0.
  - If new sum is > 10, - 10 from it, and store 1 in carry variable.
  - Save new sum at same position.
- If carry is set to 1, insert it at index 0.
 */

public class AddLargeNumbers {
    public static boolean onlyDigits(String...strs) {
        for (String str : strs) {
            for (char c : str.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
        }
        return true;
    }

    // String input/output solution.
    public static String exec(String num1, String num2) {
        if (List.of(num1.length(), num2.length()).contains(0)) {
            return null;
        }
        if (List.of(onlyDigits(num1), onlyDigits(num2)).contains(false)) {
            return null;
        }

        int lengthDiff = Math.abs(num1.length() - num2.length());
        if (lengthDiff > 0) {
            StringBuilder padding = new StringBuilder(lengthDiff);
            for (int i = 0; i < lengthDiff; i++) {
                padding.append('0');
            }
            if (num1.length() > num2.length()) {
                padding.append(num2);
                num2 = padding.toString();
            } else {
                padding.append(num1);
                num1 = padding.toString();
            }
        }

        char[] padding = new char[num1.length()];
        Arrays.fill(padding, '0');
        StringBuilder sum = new StringBuilder(String.valueOf(padding));

        int carry = 0;
        for (int i = num1.length()-1; i >= 0; i--) {
            int digitSum = carry + num1.charAt(i)-'0' + num2.charAt(i)-'0';
            carry = 0;
            if (digitSum >= 10) {
                digitSum %= 10;
                carry = 1;
            }
            sum.setCharAt(i, (char)(digitSum+'0'));
        }
        if (carry > 0) {
            sum.insert(0, carry);
        }

        for (char c : sum.toString().toCharArray()) {
            if (c != '0') {
                break;
            }
            sum.deleteCharAt(0);
        }
        if (sum.length() == 0) {
            sum.append(0);
        }

        return sum.toString();
    }

    public static void padWithZeros(List<Integer> list, int count) {
        for (int i = 0; i < count; i++) {
            list.add(0, 0);
        }
    }

    // List input/output solution
    public static List<Integer> exec2(List<Integer> num1, List<Integer> num2) {
        if (List.of(num1.size(), num2.size()).contains(0)) {
            return null;
        }
        int sizeDiff = Math.abs(num1.size() - num2.size());
        padWithZeros(num1.size() > num2.size() ? num2 : num1, sizeDiff);

        Integer[] padding = new Integer[num1.size()];
        List<Integer> sum = Arrays.asList(padding);

        int carry = 0;
        for (int i = num1.size()-1; i >= 0; i--) {
            int digitSum = carry + num1.get(i) + num2.get(i);
            carry = 0;
            if (digitSum >= 10) {
                digitSum %= 10;
                carry = 1;
            }
            sum.set(i, digitSum);
        }
        if (carry > 0) {
            sum.add(carry);
        }

        Iterator<Integer> iter = sum.iterator();
        while (iter.next() == 0) {
            iter.remove();
        }
        if (sum.size() == 0) {
            sum.add(0);
        }

        return sum;
    }

    // Built-in solution.
    public static String exec3(String num1, String num2) {
        BigInteger bigInt1 = new BigInteger(num1);
        BigInteger bigInt2 = new BigInteger(num2);
        BigInteger sum = bigInt1.add(bigInt2);
        return sum.toString();
    }
}
