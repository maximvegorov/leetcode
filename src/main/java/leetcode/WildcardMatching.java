package leetcode;

import java.util.Arrays;

public final class WildcardMatching {
    public static void main(String[] args) {
        System.out.println(new Solution().isMatch("c", "*?*"));
    }

    public static final class Solution {
        private static int processChar(
                String pattern,
                boolean[] states0,
                boolean[] states1,
                char curChar)
        {
            int transitionCount = 0;
            Arrays.fill(states1, false);
            for (int j = 0; j < states0.length; j++) {
                if (states0[j]) {
                    if (pattern.charAt(j) == '*') {
                        transitionCount++;
                        states1[j] = true;
                    }
                    int k = j + 1;
                    while (pattern.charAt(k) == '*') {
                        transitionCount++;
                        states1[k] = true;
                        k++;
                    }
                    char pj = pattern.charAt(k);
                    if (pj == '?' || pj == curChar) {
                        transitionCount++;
                        states1[k] = true;
                    }
                }
            }
            return transitionCount;
        }

        public boolean isMatch(String s, String p) {
            if (p.isEmpty()) {
                return s.isEmpty();
            }
            p = '\0' + p + '\0';
            boolean[] states0 = new boolean[p.length()];
            boolean[] states1 = new boolean[states0.length];
            states0[0] = true;
            for (int i = 0; i < s.length(); i++) {
                if (processChar(p, states0, states1, s.charAt(i)) == 0) {
                    return false;
                }
                boolean[] tmp = states0;
                states0 = states1;
                states1 = tmp;
            }
            processChar(p, states0, states1, '\0');
            return states1[states1.length - 1];
        }
    }
}
