package cbox.exercises;

/* Determines unique pairs in an array that add up to n.
Steps:
- Verification: If the array is null or less than 2, return.
- Sort the array.
- Start variable l from 0 and variable r from array.length-1.
- While l < r, find the sum of the array at those indices.
  - If the sum is equal to n then save the pair at the indices l and r.
  - If the sum is over n then we need to reduce it by reducing r.
  - If the sum is under n then we need to increase it by increasing l.
  - If changing l or r results in the same value from the array, change it again.
 */

public class FindPairsForN {
    public static void exec(int[] array, int n) {
        if (array == null || array.length < 2) {
            return;
        }

        sort(array);

        int l = 0;
        int r = array.length-1;
        while (l < r) {
            int sum = array[l] + array[r];
            if (sum == n) {
                System.out.println("Pair = [" + array[l] + ", " + array[r] + "] for N = " + n + ".");
                l = changeIndex(array, l, true);
            } else {
                if (sum > n) {
                    r = changeIndex(array, r, false);
                } else {
                    l = changeIndex(array, l, true);
                }
            }
        }
        System.out.println();
    }

    private static void sort(int[] array) {
        boolean changed = true;
        while (changed) {
            changed = false;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i+1]) {
                    int tmp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = tmp;
                    changed = true;
                }
            }
        }
    }

    private static int changeIndex(int[] array, int idx, boolean goingRight) {
        idx = goingRight ? idx+1 : idx-1;

        boolean changed = true;
        while (changed) {
            if (goingRight && idx < array.length-1 && array[idx] == array[idx+1]) {
                idx++;
            } else if (!goingRight && idx > 0 && array[idx] == array[idx-1]) {
                idx--;
            } else {
                changed = false;
            }
        }

        return idx;
    }

    public static void exec2(int[] array, int n) {
        if (array == null || array.length < 2) {
            return;
        }

        for (int i = 0; i < array.length; i++) {
            for (int j = i+1; j < array.length; j++) {
                if (array[i] + array[j] == n) {
                    System.out.println("Pair = [" + array[i] + ", " + array[j] + "] for N = " + n + ".");
                }
            }
        }
    }
}
