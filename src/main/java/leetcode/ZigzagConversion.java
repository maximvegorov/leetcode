package leetcode;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class ZigzagConversion {
    public static void main(String[] args) {
        System.out.println(new Solution().convert("PAYPALISHIRING", 3));
    }

    public static final class Solution {
        public String convert(String s, int numRows) {
            if (numRows <= 1) {
                return s;
            }
            StringBuilder[] builders = IntStream.range(0, numRows)
                    .mapToObj(StringBuilder::new)
                    .toArray(StringBuilder[]::new);
            int mod = 2 * numRows - 2;
            for (int i = 0; i < s.length(); i++) {
                int j = i % mod;
                if (j < numRows) {
                    builders[j].append(s.charAt(i));
                } else {
                    builders[mod - j].append(s.charAt(i));
                }
            }
            return Arrays.stream(builders)
                    .map(StringBuilder::toString)
                    .collect(Collectors.joining());
        }
    }
}
