package leetcode;

import java.util.stream.IntStream;

public final class ZigzagConversion {
    public static void main(String[] args) {
        System.out.println(new Solution().convert("PAYPALISHIRING", 3));
    }

    public static final class Solution {
        public String convert(String s, int numRows) {
            if (s.isEmpty() || numRows <= 1) {
                return s;
            }
            int mod = 2 * numRows - 2;
            int[] newIndexes = IntStream.range(0, s.length())
                    .boxed()
                    .sorted((i1, i2) -> {
                        int j1 = i1 % mod;
                        if (j1 >= numRows) {
                            j1 = mod - j1;
                        }
                        int j2 = i2 % mod;
                        if (j2 >= numRows) {
                            j2 = mod - j2;
                        }
                        int cmp = Integer.compare(j1, j2);
                        if (cmp == 0) {
                            cmp = Integer.compare(i1, i2);
                        }
                        return cmp;
                    })
                    .mapToInt(i -> i)
                    .toArray();
            char[] result = new char[s.length()];
            for (int i = 0; i < newIndexes.length; i++) {
                result[i] = s.charAt(newIndexes[i]);
            }
            return new String(result);
        }
    }
}
