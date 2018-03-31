package cbox.exercises;

import cbox.exercises.MaxShareProfit;
import org.junit.Assert;
import org.junit.Test;

public class MaxShareProfitTest {
    private MaxShareProfit msp = new MaxShareProfit();

    public void check(int[] shares, int expectedMin, int expectedProfit) {
        msp.exec(shares);
        Assert.assertEquals(expectedMin, msp.getMin());
        Assert.assertEquals(expectedProfit, msp.getMaxProfit());
    }

    @Test
    public void ascendingShares() {
        check(new int[]{10, 20, 30, 40, 50}, 10, 40);
    }

    @Test
    public void descendingShares() {
        check(new int[]{50, 40, 30, 20, 10}, 10, 0);
    }

    @Test
    public void oneShare() {
        check(new int[]{10}, 10, 0);
    }

    @Test
    public void randomShares() {
        check(new int[]{100, 80, 120, 130, 70, 60, 100, 125}, 60, 65);
    }

    @Test
    public void stagnantShares() {
        check(new int[]{10, 10, 10, 10, 10}, 10, 0);
    }
}
