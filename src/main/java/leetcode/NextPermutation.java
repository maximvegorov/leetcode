package leetcode;

import java.util.Arrays;

public class NextPermutation {
    public static void main(String[] args) {
        NextPermutation np = new NextPermutation();
        int[] nums = {2, 2, 7, 5, 4, 3, 2, 2, 1};
        np.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void nextPermutation(int[] nums) {
        for (int i = nums.length - 1; i >= 0; i--) {
            int minGreaterThanIdx = findMinGreaterThanIdx(nums, i, nums[i]);
            if (nums[minGreaterThanIdx] > nums[i]) {
                int t = nums[i];
                nums[i] = nums[minGreaterThanIdx];
                nums[minGreaterThanIdx] = t;
                reverse(nums, i + 1);
                return;
            }
        }

        reverse(nums, 0);
    }

    private int findMinGreaterThanIdx(int[] nums, int fromIndex, int num) {
        int l = fromIndex;
        int r = nums.length;
        while (r - l > 1) {
            int mid = (l + r) / 2;
            if (nums[mid] <= num) {
                r = mid;
            }
            else {
                l = mid;
            }
        }
        return l;
    }

    private void reverse(int[] nums, int fromIndex) {
        for (int i = fromIndex, j = nums.length - 1; i < j; i++, j--) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    }
}
