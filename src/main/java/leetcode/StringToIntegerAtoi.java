package leetcode;

public final class StringToIntegerAtoi {
    public static void main(String[] args) {
        System.out.println(new Solution().myAtoi("  12345 "));
    }

    public static final class Solution {
        private static final int ERROR = 0;

        public int myAtoi(String str) {
            int i = 0;
            while (i < str.length() && Character.isWhitespace(str.charAt(i))) {
                i++;
            }
            if (i >= str.length()) {
                return ERROR;
            }

            boolean isNeg = false;
            if (str.charAt(i) == '-' || str.charAt(i) == '+') {
                isNeg = str.charAt(i) == '-';
                i++;
            }
            if (i >= str.length() || !Character.isDigit(str.charAt(i))) {
                return ERROR;
            }

            long r = 0;
            long maxR = Integer.MAX_VALUE + 1L;
            while (i < str.length() && Character.isDigit(str.charAt(i)) && r <= maxR) {
                r = 10 * r + str.charAt(i) - '0';
                i++;
            }

            if (isNeg) {
                r = -r;
            }

            if (r < Integer.MIN_VALUE) {
                r = Integer.MIN_VALUE;
            } else if (r > Integer.MAX_VALUE) {
                r = Integer.MAX_VALUE;
            }

            return (int) r;
        }
    }
}
