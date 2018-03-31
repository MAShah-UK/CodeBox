package cbox.exercises;

import cbox.exercises.AddLargeNumbers;
import org.junit.Assert;
import org.junit.Test;

public class AddLargeNumbersTest {
    public void checkEqual(String num1, String num2, String expected) {
        Assert.assertEquals(expected, AddLargeNumbers.exec(num1, num2));
    }

    public void checkNull(String num1, String num2) {
        Assert.assertNull(AddLargeNumbers.exec(num1, num2));
    }

    @Test
    public void carriedDigit() {
        checkEqual("2", "18", "20");
    }

    @Test
    public void emptyString() {
        checkNull("", "12345");
    }

    @Test
    public void largeNumber() {
        checkEqual("123456789123456789", "123456789123456789", "246913578246913578");
    }

    @Test
    public void leadingZeros() {
        checkEqual("0001", "002", "3");
    }

    @Test
    public void newDigitFromCarry() {
        checkEqual("2", "8", "10");
    }

    @Test
    public void singleDigits() {
        checkEqual("1", "2", "3");
    }

    @Test
    public void smallNumber() {
        checkEqual("150", "150", "300");
    }

    @Test
    public void sumToZero() {
        checkEqual("0", "0", "0");
    }
}
