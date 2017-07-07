package leetcode;

public final class LongestPalindromicSubsequence {
    public static void main(String[] args) {
        System.out.println(new Solution().longestPalindromeSubseq("bbbab"));
    }

    public static class Solution {
        public int longestPalindromeSubseq(String s) {
            int[][] p = new int[s.length()][s.length()];
            for (int i = 0; i < s.length(); i++) {
                p[i][i] = 1;
            }
            for (int l = 2; l <= s.length(); l++) {
                for (int i = 0; i <= s.length() - l; i++) {
                    int j = i + l - 1;
                    if (s.charAt(i) == s.charAt(j)) {
                        int innerMax;
                        if (l > 2) {
                            innerMax = p[i + 1][j - 1];
                        } else {
                            innerMax = 0;
                        }
                        p[i][j] = innerMax + 2;
                    } else {
                        p[i][j] = Math.max(p[i + 1][j], p[i][j - 1]);
                    }
                }
            }
            return p[0][s.length() - 1];
        }
    }
}
