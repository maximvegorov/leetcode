package leetcode;

public class FindMinimumInRotatedSortedArray {
    public static void main(String[] args) {
        System.out.println(new Solution().findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));
    }

    static final class Solution {
        int findMin(int[] nums) {
            int nums0 = nums[0];
            if (nums.length == 1 || nums0 < nums[nums.length - 1]) {
                return nums0;
            }
            int l = 1;
            int r = nums.length - 1;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (nums[mid] > nums0) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            return nums[l];
        }
    }
}
