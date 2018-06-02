package leetcode;

public class BestTimeToBuyAndSellStock2 {
    public static void main(String[] args) {
        System.out.println(new Solution().maxProfit(new int[] {7,1,5,3,6,4}));

    }

    static class Solution {
        public int maxProfit(int[] prices) {
            if (prices.length <= 1)
                return 0;
            int profit = 0;
            int start = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i - 1] >= prices[i]) {
                    profit += prices[i - 1] - prices[start];
                    start = i;
                }
            }
            profit += prices[prices.length - 1] - prices[start];
            return profit;
        }
    }
}
