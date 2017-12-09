public class LongestPalindromicSubsequence {
    public static void main(String[] args) {
        System.out.println(new Solution().longestPalindromeSubseq("cbbd"));
    }

    static class Solution {
        public int longestPalindromeSubseq(String s) {
            int[][] dp = new int[s.length() + 1][s.length()];
            for (int i = 0; i < s.length(); i++) {
                dp[1][i] = 1;
            }
            for (int l = 2; l <= s.length(); l++) {
                for (int i = 0; i <= s.length() - l; i++) {
                    if (s.charAt(i) == s.charAt(i + l - 1)) {
                        dp[l][i] = 2 + dp[l - 2][i + 1];
                    } else {
                        dp[l][i] = Math.max(dp[l - 1][i], dp[l - 1][i + 1]);
                    }
                }
            }

            return dp[s.length()][0];
        }
    }
}
