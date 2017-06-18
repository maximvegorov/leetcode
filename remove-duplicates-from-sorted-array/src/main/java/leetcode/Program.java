package leetcode;

public final class Program {
    public static void main(String[] args) {
        System.out.println(new Solution().removeDuplicates(new int[]{1, 1, 2}));
    }

    public static final class Solution {
        int removeDuplicates(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int j = 1;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] != nums[i - 1]) {
                    nums[j] = nums[i];
                    j++;
                }
            }
            return j;
        }
    }
}
