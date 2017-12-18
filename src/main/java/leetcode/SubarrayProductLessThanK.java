package leetcode;

public class SubarrayProductLessThanK {
    public static void main(String[] args) {
        System.out.println(new Solution().numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100));
    }

    static class Solution {
        public int numSubarrayProductLessThanK(int[] nums, int k) {
            if (nums.length == 0) {
                return 0;
            }
            int count = 0;
            int maxProd = 1;
            int maxLength = 0;
            for (int i = 0; i < nums.length; i++) {
                int newMaxProd = maxProd * nums[i];
                maxLength++;
                while (maxLength > 0 && newMaxProd >= k) {
                    maxLength--;
                    newMaxProd /= nums[i - maxLength];
                }
                maxProd = newMaxProd;
                count += maxLength;
            }
            return count;
        }
    }
}
