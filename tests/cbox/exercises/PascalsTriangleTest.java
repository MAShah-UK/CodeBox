package cbox.exercises;

import cbox.exercises.PascalsTriangle;
import org.junit.Assert;
import org.junit.Test;

public class PascalsTriangleTest {
    public void checkEquals(int n, String expected) {
        Assert.assertEquals(expected, PascalsTriangle.exec(n));
    }

    public void checkNull(int n) {
        Assert.assertNull(PascalsTriangle.exec(n));
    }

    @Test
    public void negativeLine() {
        checkNull(-5);
    }

    @Test
    public void line0() {
        checkNull(0);
    }

    @Test
    public void line1() {
        checkEquals(1, "[1]");
    }

    @Test
    public void line2() {
        checkEquals(2, "[1, 1]");
    }

    @Test
    public void line3() {
        checkEquals(3, "[1, 2, 1]");
    }

    @Test
    public void line4() {
        checkEquals(4, "[1, 3, 3, 1]");
    }

    @Test
    public void line5() {
        checkEquals(5, "[1, 4, 6, 4, 1]");
    }

    @Test
    public void line10() {
        checkEquals(10, "[1, 9, 36, 84, 126, 126, 84, 36, 9, 1]");
    }
}
