package cbox.exercises;

import org.junit.Assert;
import org.junit.Test;

public class TableStairSearchTest {
    @Test
    public void checkNull() {
        Assert.assertFalse(TableStairSearch.exec(null, null));
    }

    @Test
    public void oneElement_valid() {
        Assert.assertTrue(TableStairSearch.exec(new Integer[][]{{1}}, 1));
    }

    @Test
    public void oneElement_invalid() {
        Assert.assertFalse(TableStairSearch.exec(new Integer[][]{{1}}, 2));
    }

    @Test
    public void oneColumn_valid() {
        Assert.assertTrue(TableStairSearch.exec(new Integer[][]{{-1}, {1}}, -1));
    }

    @Test
    public void oneColumn_invalid() {
        Assert.assertFalse(TableStairSearch.exec(new Integer[][]{{-1}, {1}}, 2));
    }

    @Test
    public void oneRow_valid() {
        Assert.assertTrue(TableStairSearch.exec(new Integer[][]{{-1, 1}}, 1));
    }

    @Test
    public void oneRow_invalid() {
        Assert.assertFalse(TableStairSearch.exec(new Integer[][]{{-1, 1}}, 2));
    }

    @Test
    public void rectMatrix_valid() {
        Assert.assertTrue(TableStairSearch.exec(new Integer[][]{{1, 2, 3}, {7, 8, 9}}, 9));
    }

    @Test
    public void rectMatrix_invalid() {
        Assert.assertFalse(TableStairSearch.exec(new Integer[][]{{1, 2, 3}, {7, 8, 9}}, 20));
    }

    @Test
    public void squareMatrix_valid() {
        Assert.assertTrue(TableStairSearch.exec(new Integer[][]{{1, 2, 3}, {7, 8, 9}, {15, 16, 17}},
                16));
    }

    @Test
    public void squareMatrix_invalid() {
        Assert.assertFalse(TableStairSearch.exec(new Integer[][]{{1, 2, 3}, {7, 8, 9}, {15, 16, 17}},
                20));
    }
}
