package leetcode;

import java.util.Arrays;

public class CoinChange {
    public static void main(String[] args) {
        System.out.println(new Solution().coinChange(new int[]{2}, 3));
    }

    public static class Solution {
        public int coinChange(int[] coins, int amount) {
            if (amount <= 0) {
                return 0;
            }

            if (coins.length == 0) {
                return -1;
            }

            Arrays.sort(coins);

            int[] mins = new int[amount + 1];
            Arrays.fill(mins, -1);
            mins[0] = 0;
            for (int i = 1; i < mins.length; i++) {
                int min = Integer.MAX_VALUE;
                for (int j = 0; j < coins.length && coins[j] <= i; j++) {
                    if (mins[i - coins[j]] < 0) {
                        continue;
                    }
                    min = Math.min(mins[i - coins[j]], min);
                }
                mins[i] = min < Integer.MAX_VALUE ? min + 1 : -1;
            }

            return mins[mins.length - 1];
        }
    }
}
