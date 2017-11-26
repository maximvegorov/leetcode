package leetcode;

public final class UniquePaths2 {
    public static void main(String[] args) {
        System.out.println(new Solution().uniquePathsWithObstacles(
                new int[][]{
                        {0, 0, 0},
                        {0, 1, 0},
                        {0, 0, 0}
                }));
    }

    public static final class Solution {
        public int uniquePathsWithObstacles(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;

            int[][] counts = new int[m][n];

            if (grid[m - 1][n - 1] != 0) {
                return 0;
            }

            counts[m - 1][n - 1] = 1;

            for (int i = m - 2; i >= 0; i--) {
                if (grid[i][n - 1] != 0) {
                    counts[i][n - 1] = 0;
                } else {
                    counts[i][n - 1] = counts[i + 1][n - 1];
                }
            }

            for (int j = n - 2; j >= 0; j--) {
                if (grid[m - 1][j] != 0) {
                    counts[m - 1][j] = 0;
                } else {
                    counts[m - 1][j] = counts[m - 1][j + 1];
                }
            }

            for (int j = n - 2; j >= 0; j--) {
                for (int i = m - 2; i >= 0; i--) {
                    if (grid[i][j] != 0) {
                        counts[i][j] = 0;
                    } else {
                        counts[i][j] = counts[i][j + 1] + counts[i + 1][j];
                    }
                }
            }

            return counts[0][0];
        }
    }
}
