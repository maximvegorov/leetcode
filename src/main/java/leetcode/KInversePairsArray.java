package leetcode;

import java.util.Arrays;

public final class KInversePairsArray {
    public static void main(String[] args) {
        System.out.println(new Solution().kInversePairs(3, 3));
    }

    public static final class Solution {
        private static final int MOD = 1_000_000_000 + 7;

        public int kInversePairs(int n, int m) {
            if (n == 0 || m == 0) {
                return m == 0 ? 1 : 0;
            }
            int maxK = (n * (n - 1)) / 2;
            if (m > maxK) {
                return 0;
            }
            int[] p0 = new int[m + 1];
            int[] p1 = new int[m + 1];
            p0[0] = 1;
            for (int i = 1; i < n; i++) {
                Arrays.fill(p1, 0);
                p1[0] = p0[0];
                for (int j = 1; j <= m; j++) {
                    int p1j = p1[j - 1];
                    if (j > i) {
                        p1j = (p1j - p0[j - i - 1] + MOD) % MOD;
                    }
                    p1[j] = (p1j + p0[j]) % MOD;
                }
                int[] temp = p0;
                p0 = p1;
                p1 = temp;
            }

            return p0[m];
        }
    }
}
