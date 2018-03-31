package cbox.exercises;

import cbox.exercises.ReverseString;
import org.junit.Assert;
import org.junit.Test;

public class ReverseStringTest {

    public void checkReverse(String str) {
        StringBuilder rev = new StringBuilder(str).reverse();
        Assert.assertEquals(rev.toString(), ReverseString.exec(str));
    }

    @Test
    public void emptyString() {
        checkReverse("");
    }

    @Test
    public void helloWorld() {
        checkReverse("Hello world");
    }

    @Test
    public void numbersOnly() {
        checkReverse("192457601");
    }

    @Test
    public void palindromeWord() {
        checkReverse("reviver");
    }

    @Test
    public void random() {
        checkReverse("^%Zlqwpo20?!asd#@");
    }
}
