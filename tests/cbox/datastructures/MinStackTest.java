package cbox.datastructures;

import org.junit.Assert;
import org.junit.Test;

public class MinStackTest {
    private MinStack<Integer> ms;

    @Test
    public void isEmptyTrue() {
        ms = new MinStack<>();
        Assert.assertTrue(ms.isEmpty());
    }

    @Test
    public void isEmptyTrue2() {
        ms = new MinStack<>();
        ms.push(1);
        ms.pop();
        Assert.assertTrue(ms.isEmpty());
    }

    @Test
    public void isEmptyTrue3() {
        ms = new MinStack<>();
        ms.push(1);
        ms.clear();
        Assert.assertTrue(ms.isEmpty());
    }

    @Test
    public void isEmptyFalse() {
        ms = new MinStack<>();
        ms.push(1);
        Assert.assertFalse(ms.isEmpty());
    }

    @Test
    public void peakPop() {
        ms = new MinStack<>();
        ms.push(1);
        Assert.assertEquals((Integer) 1, ms.peak());
        Assert.assertEquals((Integer) 1, ms.pop());
    }

    @Test
    public void pushPopOneElement() {
        ms = new MinStack<>();
        ms.push(1);
        Assert.assertEquals((Integer) 1, ms.pop());
    }

    @Test
    public void pushPopTwoElements() {
        ms = new MinStack<>();
        ms.push(1);
        ms.push(2);
        Assert.assertEquals((Integer) 2, ms.pop());
        Assert.assertEquals((Integer) 1, ms.pop());
    }

    @Test
    public void pushPopThreeElements() {
        ms = new MinStack<>();
        ms.push(1);
        ms.push(2);
        ms.push(3);
        Assert.assertEquals((Integer) 3, ms.pop());
        Assert.assertEquals((Integer) 2, ms.pop());
        Assert.assertEquals((Integer) 1, ms.pop());
    }
}
