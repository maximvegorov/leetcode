package leetcode;

import java.util.Arrays;

public class EditDistance {
    public static void main(String[] args) {
        System.out.println(new Solution().minDistance("horse", "ros"));
    }

    static class Solution {
        public int minDistance(String word1, String word2) {
            return new Solver(word1, word2).solve(word1.length(), word2.length());
        }

        static class Solver {
            private final String word1;
            private final String word2;
            private final int[] dp;

            Solver(String word1, String word2) {
                this.word1 = word1;
                this.word2 = word2;
                this.dp = new int[word1.length() * word2.length()];
                Arrays.fill(this.dp, -1);
            }

            public int solve(int m, int n) {
                if (m == 0)
                    return n;
                if (n == 0)
                    return m;
                int i = (m - 1) * word2.length() + (n - 1);
                int dpi = dp[i];
                if (dpi == -1) {
                    if (word1.charAt(m - 1) == word2.charAt(n - 1))
                        dpi = solve(m - 1, n - 1);
                    else {
                        dpi = Math.min(solve(m - 1, n - 1), Math.min(solve(m - 1, n), solve(m, n - 1))) + 1;
                    }
                    dp[i] = dpi;
                }
                return dpi;
            }
        }
    }
}
