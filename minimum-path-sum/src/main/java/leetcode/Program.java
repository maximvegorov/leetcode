package leetcode;

public class Program {
    public static void main(String[] args) {
    }

    public static class Solution {
        public int minPathSum(int[][] grid) {
            int[] mins = new int[grid.length];
            mins[0] = grid[0][0];

            for (int i = 1; i < grid.length; i++) {
                mins[i] = mins[i - 1] + grid[i][0];
            }

            for (int j = 1; j < grid[0].length; j++) {
                mins[0] += grid[0][j];
                for (int i = 1; i < grid.length; i++) {
                    mins[i] = Math.min(mins[i], mins[i - 1]) + grid[i][j];
                }
            }

            return mins[mins.length - 1];
        }
    }
}
