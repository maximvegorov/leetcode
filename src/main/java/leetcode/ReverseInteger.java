package leetcode;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class ReverseInteger {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(
                IntStream.of(123, -123)
                        .map(solution::reverse)
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(",")));
    }

    public static final class Solution {
        public int reverse(int x) {
            long s = x;
            boolean isNeg = x < 0;
            if (isNeg) {
                s = -s;
            }
            long r = 0;
            while (s != 0) {
                int digit = (int) s % 10;
                if (digit < 0) {
                    digit += 10;
                }
                r = 10 * r + digit;
                s /= 10;
            }
            if (isNeg) {
                r = -r;
            }
            if (r < Integer.MIN_VALUE || r > Integer.MAX_VALUE) {
                return 0;
            }
            return (int) r;
        }
    }
}
