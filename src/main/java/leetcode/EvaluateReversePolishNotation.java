package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class EvaluateReversePolishNotation {
    public static void main(String[] args) {
        System.out.println(new Solution().evalRPN(new String[] {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
    }

    static class Solution {
        public int evalRPN(String[] tokens) {
            if (tokens.length == 0)
                return 0;
            Deque<Integer> stack = new ArrayDeque<>(tokens.length - 1);
            for (String token : tokens) {
                switch (token) {
                    case "+":
                        stack.addLast(stack.removeLast() + stack.removeLast());
                        break;
                    case "-": {
                        Integer op2 = stack.removeLast();
                        Integer op1 = stack.removeLast();
                        stack.addLast(op1 - op2);
                        break;
                    }
                    case "*":
                        stack.addLast(stack.removeLast() * stack.removeLast());
                        break;
                    case "/": {
                        Integer op2 = stack.removeLast();
                        Integer op1 = stack.removeLast();
                        stack.addLast(op1 / op2);
                        break;
                    }
                    default:
                        stack.addLast(Integer.valueOf(token));
                        break;
                }
            }
            return stack.removeLast();
        }
    }
}
