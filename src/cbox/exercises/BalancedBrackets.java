package cbox.exercises;

import java.util.List;
import java.util.Stack;

/* To check to see if a string of brackets is balanced, use a stack.
- Go through the string character by character.
  - If the next character is an opening bracket, push it to the stack.
  - If the next character is a closing bracket, pop the stack and compare them.
    - If the stack is empty, the brackets are unbalanced.
    - If the popped character matches with the closing bracket, move to the next iteration.
    - If the popped character doesn't match with the closing bracket, the brackets are unbalanced.
- After comparing all characters.
  - If the stack is empty the brackets are balanced.
  - If the stack is not empty the brackets are unbalanced. */

public class BalancedBrackets {
    private static List<Character> openB = List.of('(', '[', '{');
    private static List<Character> closedB = List.of(')', ']', '}');

    public static boolean exec(String seq) {
        Stack<Character> seqStack = new Stack<>();
        for (char c : seq.toCharArray()) {
            if (openB.contains(c)) {
                seqStack.push(c);
            } else if (!seqStack.empty()) {
                int openBIdx = openB.indexOf(seqStack.pop());
                int closedBIdx = closedB.indexOf(c);
                if (openBIdx != closedBIdx) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return seqStack.empty();
    }
}
