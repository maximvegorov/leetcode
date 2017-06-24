package leetcode;

public class MinimumSizeSubarraySum {
    public static void main(String[] args) {
        System.out.println(new Solution().minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
    }

    public static class Solution {
        public int minSubArrayLen(int s, int[] nums) {
            int currentSum = 0;
            int minLength = nums.length;
            int l = 0;
            for (int r = 0; r < nums.length; r++) {
                if (nums[r] >= s) {
                    return 1;
                }
                currentSum += nums[r];
                if (currentSum >= s) {
                    while (l < r && currentSum - nums[l] >= s) {
                        currentSum -= nums[l];
                        l++;
                    }
                    int length = r - l + 1;
                    if (length < minLength) {
                        minLength = length;
                    }
                }
            }
            if (currentSum >= s) {
                return minLength;
            } else {
                return 0;
            }
        }
    }
}
