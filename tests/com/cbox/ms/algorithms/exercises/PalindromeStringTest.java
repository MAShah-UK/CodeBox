package com.cbox.ms.algorithms.exercises;

import org.junit.Assert;
import org.junit.Test;

public class PalindromeStringTest {
    private void checkTrue(String str) {
        Assert.assertTrue(PalindromeString.exec(str));
    }

    public void checkFalse(String str) {
        Assert.assertFalse(PalindromeString.exec(str));
    }

    @Test
    public void emptyString() {
        checkTrue("");
    }

    @Test
    public void evenLengthWord() {
        checkTrue("peep");
    }

    @Test
    public void invalidSentence() {
        checkFalse("Hello world!");
    }

    @Test
    public void mixedCase() {
        checkTrue("HelleH");
    }

    @Test
    public void oddLengthWord() {
        checkTrue("pop");
    }

    @Test
    public void oneLetter() {
        checkTrue("A");
    }

    @Test
    public void oneNumber() {
        checkTrue("1");
    }

    @Test
    public void punctuation() {
        checkTrue("!^. .^!");
    }

    @Test
    public void unbalancedSentence() {
        checkTrue("nurses run");
    }

    @Test
    public void validSentence() {
        checkTrue("Hello ana olleH");
    }
}
