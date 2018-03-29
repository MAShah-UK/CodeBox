package com.cbox.ms.algorithms.exercises;

/* Determines maximum profit that can be attained given share prices on different days.
Assumptions:
- Minimum profit is zero since you could always choose not to buy shares.
  (To change this set maxProfit to -Integer.MAX_VALUE.
- You can't buy and sell on the same day.
  (To change this switch maxProfit and min in the loop)
Steps:
- Go through the array element by element.
  - Record the maximum profit.
  - Record the minimum price. */

public class MaxShareProfit {
    private int min;
    private int maxProfit;

    public int getMin() {
        return min;
    }

    public int getMaxProfit() {
        return maxProfit;
    }

    public void exec(int[] shares) {
        min = Integer.MAX_VALUE; // Buy at minimum, sell at maximum.
        maxProfit = 0;
        for (int val : shares) {
            maxProfit = Math.max(maxProfit, val-min);
            min = Math.min(min, val);
        }
    }
}
