package leetcode;

public final class MergeSortedArray {
    public static final class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            if (n == 0) {
                return;
            }

            for (int i = m - 1; i >= 0; i--) {
                nums1[nums1.length - (m - i)] = nums1[i];
            }

            int i = nums1.length - m;
            int j = 0;
            int k = 0;
            while (i < nums1.length && j < n) {
                if (nums1[i] <= nums2[j]) {
                    nums1[k++] = nums1[i++];
                } else {
                    nums1[k++] = nums2[j++];
                }
            }
            while (i < nums1.length) {
                nums1[k++] = nums1[i++];
            }
            while (j < n) {
                nums1[k++] = nums2[j++];
            }
        }
    }
}
