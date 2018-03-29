package com.cbox.ms.algorithms.exercises;

import org.junit.Assert;
import org.junit.Test;

public class AnagramStringsTest {

    public void checkTrue(String str1, String str2) {
        Assert.assertTrue(AnagramStrings.exec(str1, str2));
    }
    public void checkFalse(String str1, String str2) {
        Assert.assertFalse(AnagramStrings.exec(str1, str2));
    }

    @Test
    public void invalid_emptyStringVsWord() {
        checkFalse("", "hello");
    }

    @Test
    public void invalid_whitespaceBetween() {
        checkFalse("hello", "he llo");
    }

    @Test
    public void valid_emptyStrings() {
        checkTrue("", "");
    }

    @Test
    public void valid_mixedCase() {
        checkTrue("Hello World!", "HeLlO WoRlD!");
    }

    @Test
    public void valid_jumbledSentences() {
        checkTrue("Hello World!", "Helol Wordl!");
    }

    @Test
    public void valid_jumbledWords() {
        checkTrue("hello", "helol");
    }

    @Test
    public void valid_leadingWhitespace() {
        checkTrue("hello", "   hello");
    }

    @Test
    public void valid_leadingTrailingWhitespace() {
        checkTrue("hello", "   hello   ");
    }

    @Test
    public void valid_sentences() {
        checkTrue("Hello World!", "Hello World!");
    }

    @Test
    public void valid_trailingWhitespace() {
        checkTrue("hello", "hello   ");
    }

    @Test
    public void valid_words() {
        checkTrue("hello", "hello");
    }
}
