package leetcode;

public class GuessNumberHigherOrLower2 {
    public static void main(String[] args) {
        System.out.println(new Solution().getMoneyAmount(10));
    }

    public static class Solution {
        public int getMoneyAmount(int n) {
            if (n <= 1) {
                return 0;
            }
            int[][] costs = new int[n + 1][n + 1];
            for (int l = 2; l <= n; l++) {
                for (int i = 1; i <= n - l + 1; i++) {
                    int j = i + l - 1;
                    int min = Math.min(i + costs[i + 1][j], j + costs[i][j - 1]);
                    for (int k = i + 1; k < j; k++) {
                        min = Math.min(min, Math.max(costs[i][k - 1], costs[k + 1][j]) + k);
                    }
                    costs[i][j] = min;
                }
            }
            return costs[1][n];
        }
    }
}
