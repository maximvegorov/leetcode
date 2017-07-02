package leetcode;

public final class BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
        System.out.println(new Solution().maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

    public static final class Solution {
        public int maxProfit(int[] prices) {
            if (prices.length <= 1) {
                return 0;
            }
            int min = prices[0];
            int max = 0;
            for (int i = 1; i < prices.length; i++) {
                int delta = prices[i] - min;
                if (delta > max) {
                    max = delta;
                }
                min = Math.min(min, prices[i]);
            }
            return max;
        }
    }
}
