package leetcode;

public class MaximalSquare2 {
    static class Solution {
        public int maximalSquare(char[][] matrix) {
            int m = matrix.length;
            if (m == 0) {
                return 0;
            }
            int n = matrix[0].length;
            int[][] maxSquare = new int[m + 1][n + 1];

            int maxSize = 0;
            for (int i = m - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    if (matrix[i][j] == '1') {
                        int w = Math.min(maxSquare[i][j + 1], maxSquare[i + 1][j + 1]);
                        int h = Math.min(maxSquare[i + 1][j], maxSquare[i + 1][j + 1]);
                        maxSquare[i][j] = 1 + Math.min(w, h);
                        maxSize = Math.max(maxSize, maxSquare[i][j]);
                    }
                }
            }

            return maxSize * maxSize;
        }
    }
}
