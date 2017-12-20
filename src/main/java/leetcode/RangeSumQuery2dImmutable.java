package leetcode;

public class RangeSumQuery2dImmutable {
    static class NumMatrix {
        private final int[][] prefixSums;

        public NumMatrix(int[][] matrix) {
            int m = matrix.length;
            int n = m == 0 ? 0 : matrix[0].length;
            prefixSums = new int[m + 1][n + 1];
            for (int j = n - 1; j >= 0; j--) {
                for (int i = m - 1; i >= 0; i--) {
                    prefixSums[i][j] =
                            prefixSums[i][j + 1] + matrix[i][j] + prefixSums[i + 1][j] - prefixSums[i + 1][j + 1];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return prefixSums[row1][col1] - prefixSums[row1][col2 + 1] - prefixSums[row2 + 1][col1] + prefixSums[row2
                    + 1][col2 + 1];
        }
    }

}
