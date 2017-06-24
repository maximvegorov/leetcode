package leetcode;

public final class LongestValidParentheses {
    public static void main(String[] args) {
        System.out.println(new Solution().longestValidParentheses(")()())"));
    }

    public static final class Solution {
        public int longestValidParentheses(String s) {
            if (s.isEmpty()) {
                return 0;
            }
            int max = 0;
            int[] t = new int[s.length()];
            t[t.length - 1] = 0;
            for (int i = t.length - 2; i >= 0; i--) {
                if (s.charAt(i) == ')') {
                    t[i] = 0;
                } else {
                    int j = i + t[i + 1] + 1;
                    if (j < t.length && s.charAt(j) == ')') {
                        t[i] = 2 + t[i + 1];
                        if (j + 1 < t.length) {
                            t[i] += t[j + 1];
                        }
                    }
                }
                if (max < t[i]) {
                    max = t[i];
                }
            }
            return max;
        }
    }
}
