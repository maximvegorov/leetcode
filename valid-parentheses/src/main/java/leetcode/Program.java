package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public final class Program {
    public static void main(String[] args) {
        System.out.println(new Solution().isValid("()[]{}"));
    }

    public static class Solution {
        boolean isValid(String s) {
            if (s == null || s.length() == 0) {
                return true;
            }

            Deque<Character> stack = new ArrayDeque<>(s.length());
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                switch (c) {
                    case '(':
                        stack.addLast(')');
                        break;
                    case '[':
                        stack.addLast(']');
                        break;
                    case '{':
                        stack.addLast('}');
                        break;
                    case ')':
                    case ']':
                    case '}':
                        if (stack.isEmpty() || stack.removeLast() != c) {
                            return false;
                        }
                        break;
                    default:
                        throw new AssertionError("Unexpected");
                }
            }
            return stack.isEmpty();
        }
    }
}
