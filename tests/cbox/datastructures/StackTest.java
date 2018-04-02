package cbox.datastructures;

import org.junit.Assert;
import org.junit.Test;

public class StackTest {
    private Stack<Integer> s;

    @Test
    public void isEmptyTrue() {
        s = new Stack<>();
        Assert.assertTrue(s.isEmpty());
    }

    @Test
    public void isEmptyTrue2() {
        s = new Stack<>();
        s.push(1);
        s.pop();
        Assert.assertTrue(s.isEmpty());
    }

    @Test
    public void isEmptyTrue3() {
        s = new Stack<>();
        s.push(1);
        s.clear();
        Assert.assertTrue(s.isEmpty());
    }

    @Test
    public void isEmptyFalse() {
        s = new Stack<>();
        s.push(1);
        Assert.assertFalse(s.isEmpty());
    }

    @Test
    public void peakPop() {
        s = new Stack<>();
        s.push(1);
        Assert.assertEquals((Integer) 1, s.peak());
        Assert.assertEquals((Integer) 1, s.pop());
    }

    @Test
    public void pushPopOneElement() {
        s = new Stack<>();
        s.push(1);
        Assert.assertEquals((Integer) 1, s.pop());
    }

    @Test
    public void pushPopTwoElements() {
        s = new Stack<>();
        s.push(1);
        s.push(2);
        Assert.assertEquals((Integer) 2, s.pop());
        Assert.assertEquals((Integer) 1, s.pop());
    }

    @Test
    public void pushPopThreeElements() {
        s = new Stack<>();
        s.push(1);
        s.push(2);
        s.push(3);
        Assert.assertEquals((Integer) 3, s.pop());
        Assert.assertEquals((Integer) 2, s.pop());
        Assert.assertEquals((Integer) 1, s.pop());
    }
}
