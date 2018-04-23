package cbox.exercises;

import org.junit.Assert;
import org.junit.Test;

public class IsSubstringTest {
    @Test
    public void checkNull() {
        Assert.assertFalse(IsSubstring.exec(null, null));
    }

    @Test
    public void empty() {
        Assert.assertTrue(IsSubstring.exec("", ""));
    }

    @Test
    public void endSubstring() {
        Assert.assertTrue(IsSubstring.exec("carpet", "pet"));
    }

    @Test
    public void invalidSubstring() {
        Assert.assertFalse(IsSubstring.exec("carpet", "ape"));
    }

    @Test
    public void startSubstring() {
        Assert.assertTrue(IsSubstring.exec("carpet", "car"));
    }
}
