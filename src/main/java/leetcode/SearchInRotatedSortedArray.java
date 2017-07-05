package leetcode;

import java.util.Arrays;

public final class SearchInRotatedSortedArray {
    public static void main(String[] args) {
        System.out.println(new Solution().search(new int[]{3, 1}, 1));
    }

    public static final class Solution {
        private static int binarySearch(int[] nums, int l, int r, int v) {
            int idx = Arrays.binarySearch(nums, l, r, v);
            return idx >= 0 ? idx : -1;
        }

        public int search(int[] nums, int target) {
            if (nums.length == 0) {
                return -1;
            }
            int l = 0;
            int r = nums.length - 1;
            while (l <= r) {
                int m = l + (r - l) / 2;
                if (nums[m] == target) {
                    return m;
                }
                if (nums[l] < nums[m] || l == m) {
                    if (nums[l] <= target && target < nums[m]) {
                        return binarySearch(nums, l, m, target);
                    } else {
                        l = m + 1;
                    }

                } else {
                    if (nums[m] < target && target <= nums[r]) {
                        return binarySearch(nums, m + 1, r + 1, target);
                    } else {
                        r = m - 1;
                    }
                }
            }
            return -1;
        }
    }
}
