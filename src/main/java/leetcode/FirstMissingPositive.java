package leetcode;

public final class FirstMissingPositive {
    public static void main(String[] args) {
        System.out.println(new Solution().firstMissingPositive(new int[]{1, 2, 0}));
    }

    public static final class Solution {
        public int firstMissingPositive(int[] nums) {
            boolean[] set = new boolean[nums.length];
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= 0 || nums[i] > nums.length)
                    continue;
                set[nums[i] - 1] = true;
            }
            for (int i = 0; i < set.length; i++) {
                if (!set[i])
                    return i + 1;
            }
            return nums.length + 1;
        }
    }
}
