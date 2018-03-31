package cbox.exercises;

import cbox.exercises.BalancedBrackets;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BalancedBracketsTest {
    public void checkTrue(String seq) {
        assertTrue(BalancedBrackets.exec(seq));
    }

    public void checkFalse(String seq) {
        assertFalse(BalancedBrackets.exec(seq));
    }

    @Test
    public void balanced_emptyString() {
        checkTrue("");
    }

    @Test
    public void balanced_curvedBracketPair() {
        checkTrue("()");
    }

    @Test
    public void balanced_allBracketPairs() {
        checkTrue("()[]{}");
    }

    @Test
    public void balanced_curvedBracketPairsNested() {
        checkTrue("(())");
    }

    @Test
    public void balanced_allBracketPairsNested() {
        checkTrue("([{}])");
    }

    @Test
    public void balanced_varyingNestedBrackets() {
        checkTrue("{[]([])}");
    }

    @Test
    public void unbalanced_invertedBracketPair() {
        checkFalse(")(");
    }

    @Test
    public void unbalanced_extraBracket() {
        checkFalse("{{}");
    }

    @Test
    public void unbalanced_nonMatchingPair() {
        checkFalse("(]");
    }

    @Test
    public void unbalanced_singleOpenBracket() {
        checkFalse("[");
    }

    @Test
    public void unbalanced_singleClosedBracket() {
        checkFalse("]");
    }
}
