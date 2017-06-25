package leetcode;

public final class SpiralMatrix2 {
    public static final class Solution {
        public int[][] generateMatrix(int n) {
            int[][] result = new int[Math.max(n, 0)][Math.max(n, 0)];

            if (n <= 0) {
                return result;
            }

            int cur = 1;

            int t = 0;
            int r = n - 1;
            int b = n - 1;
            int l = 0;
            while (t < b && l < r) {
                for (int j = l; j < r; j++) {
                    result[t][j] = cur++;
                }
                for (int i = t; i < b; i++) {
                    result[i][r] = cur++;
                }
                for (int j = r; j > l; j--) {
                    result[b][j] = cur++;
                }
                for (int i = b; i > t; i--) {
                    result[i][l] = cur++;
                }
                t++;
                r--;
                b--;
                l++;
            }
            if (t == b) {
                for (int j = l; j <= r; j++) {
                    result[t][j] = cur++;
                }
            } else if (l == r) {
                for (int i = t; i <= b; i++) {
                    result[i][l] = cur++;
                }
            }

            return result;
        }
    }
}
