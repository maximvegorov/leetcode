package leetcode;

public class IntegerBreak {
    public static void main(String[] args) {
        System.out.println(new Solution().integerBreak(10));
    }

    static class Solution {
        public int integerBreak(int n) {
            int[] dp = new int[n + 1];

            dp[1] = 1;
            dp[2] = 1;

            for (int i = 3; i <= n; i++) {
                int max = 0;
                for (int j = 1; j < i; j++) {
                    max = Math.max(max, Math.max(j, dp[j]) * Math.max(i - j, dp[i - j]));
                }
                dp[i] = max;
            }

            return dp[n];
        }
    }
}
