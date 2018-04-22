package cbox.datastructures;

import org.junit.Assert;
import org.junit.Test;

public class LinkedListTableTest {
    private LinkedListTable<Integer> table;

    @Test
    public void flatten10() {
        table = new LinkedListTable<>();
        /*
        9--3--4--1
        |  |     |
        3  3     8
        |        |
        2        6
        |
        7
         */
        table.add(9, 0, 0); table.add(3, 1, 0); table.add(2, 2, 0); table.add(7, 3, 0);
        table.add(3, 0, 1); table.add(3, 1, 1);
        table.add(4, 0, 2);
        table.add(1, 0, 3); table.add(8, 1, 3); table.add(6, 2, 3);
        Integer[] flat = new Integer[table.getSize()];
        table.flatten(flat);
        Assert.assertArrayEquals(new Integer[]{9, 3, 2, 7, 3, 3, 4, 1, 8, 6}, flat);
    }

    @Test
    public void flatten2x2() {
        table = new LinkedListTable<>();
        /*
        1--3
        |  |
        2  4
         */
        table.add(1, 0, 0);
        table.add(2, 1, 0);
        table.add(3, 0, 1);
        table.add(4, 1, 1);
        Integer[] flat = new Integer[table.getSize()];
        table.flatten(flat);
        Assert.assertArrayEquals(new Integer[]{1, 2, 3, 4}, flat);
    }

    @Test
    public void getColumns() {
        table = new LinkedListTable<>();
        /*
        1--3--2
        |
        2
         */
        table.add(1, 0, 0);
        table.add(2, 1, 0);
        table.add(3, 0, 1);
        table.add(4, 0, 2);
        Assert.assertEquals(3, table.getColumns());
    }

    @Test
    public void getMaxRows() {
        table = new LinkedListTable<>();
        /*
        1--3--2
        |
        2
         */
        table.add(1, 0, 0);
        table.add(2, 1, 0);
        table.add(3, 0, 1);
        table.add(4, 0, 2);
        Assert.assertEquals(2, table.getMaxRows());
    }

    @Test
    public void getSize() {
        table = new LinkedListTable<>();
        /*
        1--3
        |
        2
         */
        table.add(1, 0, 0);
        table.add(2, 1, 0);
        table.add(3, 0, 1);
        Assert.assertEquals(3, table.getSize());
    }

    @Test
    public void getValue2x2() {
        table = new LinkedListTable<>();
        /*
        1--3
        |  |
        2  4
         */
        table.add(1, 0, 0);
        table.add(2, 1, 0);
        table.add(3, 0, 1);
        table.add(4, 1, 1);
        Assert.assertEquals((Integer)3, table.get(0, 1));
    }
}
