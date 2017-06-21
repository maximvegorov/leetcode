package leetcode;

public final class SetMatrixZeroes {
    public static void main(String[] args) {
        int[][] A = new int[][]{
                {0, 1},
                {1, 0}
        };
        new Solution().setZeroes(A);
    }

    public static final class Solution {
        void setZeroes(int[][] matrix) {
            int m = matrix.length;
            if (m == 0) {
                return;
            }
            int n = matrix[0].length;
            boolean[] zeroRows = new boolean[m];
            boolean[] zeroCols = new boolean[n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == 0) {
                        zeroRows[i] = true;
                        zeroCols[j] = true;
                    }
                }
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (zeroRows[i] || zeroCols[j]) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }
    }
}
