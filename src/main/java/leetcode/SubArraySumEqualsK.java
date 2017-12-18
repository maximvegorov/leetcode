package leetcode;

public class SubArraySumEqualsK {
    public static void main(String[] args) {
        System.out.println(new Solution().subarraySum(new int[]{1, 1, 1}, 2));
    }

    static class Solution {
        public int subarraySum(int[] nums, int k) {
            if (nums.length == 0) {
                return 0;
            }
            int[] sums = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                sums[i + 1] = sums[i] + nums[i];
            }
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j <= nums.length; j++) {
                    if (sums[j] - sums[i] == k) {
                        count++;
                    }
                }
            }
            return count;
        }
    }
}
