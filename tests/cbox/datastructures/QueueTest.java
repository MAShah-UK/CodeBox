package cbox.datastructures;

import org.junit.Assert;
import org.junit.Test;

public class QueueTest {
    Queue<Integer> q;

    @Test
    public void isEmptyTrue() {
        q = new Queue<>();
        Assert.assertTrue(q.isEmpty());
    }

    @Test
    public void isEmptyTrue2() {
        q = new Queue<>();
        q.push(1);
        q.pop();
        Assert.assertTrue(q.isEmpty());
    }

    @Test
    public void isEmptyTrue3() {
        q = new Queue<>();
        q.push(1);
        q.clear();
        Assert.assertTrue(q.isEmpty());
    }

    @Test
    public void isEmptyFalse() {
        q = new Queue<>();
        q.push(1);
        Assert.assertFalse(q.isEmpty());
    }

    @Test
    public void peakPop() {
        q = new Queue<>();
        q.push(1);
        Assert.assertEquals((Integer) 1, q.peak());
        Assert.assertEquals((Integer) 1, q.pop());
    }

    @Test
    public void pushPopOneElement() {
        q = new Queue<>();
        q.push(1);
        Assert.assertEquals((Integer) 1, q.pop());
    }

    @Test
    public void pushPopTwoElements() {
        q = new Queue<>();
        q.push(1);
        q.push(2);
        Assert.assertEquals((Integer) 1, q.pop());
        Assert.assertEquals((Integer) 2, q.pop());
    }

    @Test
    public void pushPopThreeElements() {
        q = new Queue<>();
        q.push(1);
        q.push(2);
        q.push(3);
        Assert.assertEquals((Integer) 1, q.pop());
        Assert.assertEquals((Integer) 2, q.pop());
        Assert.assertEquals((Integer) 3, q.pop());
    }
}
