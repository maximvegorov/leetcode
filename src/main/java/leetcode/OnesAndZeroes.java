package leetcode;

import java.util.Arrays;

public final class OnesAndZeroes {
    public static void main(String[] args) {
        System.out.println(
                new Solution()
                        .findMaxForm(
                                new String[]{"10", "0001", "111001", "1", "0"},
                                5, 3));
    }

    public static final class Solution {
        static final Pair buildPair(String str) {
            int zeroCount = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '0') {
                    zeroCount++;
                }
            }
            return new Pair(zeroCount, str.length() - zeroCount);
        }

        public int findMaxForm(String[] strs, int m, int n) {
            if (strs.length == 0) {
                return 0;
            }

            Pair[] pairs = Arrays.stream(strs)
                    .map(Solution::buildPair)
                    .toArray(Pair[]::new);

            int[][][] table = new int[m + 1][n + 1][pairs.length + 1];

            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    for (int k = 1; k <= pairs.length; k++) {
                        Pair pair = pairs[k - 1];
                        if (pair.zeroCount > i || pair.oneCount > j) {
                            table[i][j][k] = table[i][j][k - 1];
                        } else {
                            table[i][j][k] = Math.max(
                                    table[i - pair.zeroCount][j - pair.oneCount][k - 1] + 1,
                                    table[i][j][k - 1]);
                        }
                    }
                }
            }

            return table[m][n][pairs.length];
        }

        static final class Pair {
            final int zeroCount;
            final int oneCount;

            public Pair(int zeroCount, int oneCount) {
                this.zeroCount = zeroCount;
                this.oneCount = oneCount;
            }

            @Override
            public String toString() {
                return "Pair{" +
                        "zeroCount=" + zeroCount +
                        ", oneCount=" + oneCount +
                        '}';
            }
        }
    }
}
