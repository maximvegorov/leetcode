package leetcode;

public class DecodeWays {
    public static void main(String[] args) {
        System.out.println(new Solution().numDecodings("101"));
    }

    public static final class Solution {
        public int numDecodings(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            if (s.charAt(0) == '0') {
                return 0;
            }
            int n = s.length();
            int[] counts = new int[n + 1];
            counts[n] = 1;
            counts[n - 1] = s.charAt(n - 1) != '0' ? 1 : 0;
            for (int i = n - 2; i >= 0; i--) {
                int code1 = s.charAt(i) - '0';
                int code2 = 10 * code1 + s.charAt(i + 1) - '0';
                int sum1 = 0;
                if (code1 != 0) {
                    sum1 = counts[i + 1];
                }
                int sum2 = 0;
                if (10 <= code2 && code2 <= 26) {
                    sum2 = counts[i + 2];
                }
                counts[i] = sum1 + sum2;
            }
            return counts[0];
        }
    }
}
