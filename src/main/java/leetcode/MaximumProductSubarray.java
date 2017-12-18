package leetcode;

public class MaximumProductSubarray {
    public static void main(String[] args) {
        System.out.println(new Solution().maxProduct(new int[]{2, 3, -2, 4}));
    }

    static class Solution {
        public int maxProduct(int[] nums) {
            if (nums.length == 0) {
                return -1;
            }
            int max = nums[0];
            int lmin = nums[0];
            int lmax = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] >= 0) {
                    lmin = Math.min(lmin * nums[i], nums[i]);
                    lmax = Math.max(lmax * nums[i], nums[i]);
                } else {
                    int newLmin = Math.min(lmax * nums[i], nums[i]);
                    lmax = Math.max(lmin * nums[i], nums[i]);
                    lmin = newLmin;
                }
                if (lmax > max) {
                    max = lmax;
                }
            }
            return max;
        }
    }
}
