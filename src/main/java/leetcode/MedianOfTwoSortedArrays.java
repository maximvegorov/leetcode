package leetcode;

public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        System.out.println(
                new Solution().findMedianSortedArrays(
                        new int[] {1, 4}, new int[] { 2, 3, 5, 6, 7, 8 }));

    }

    public static class Solution {
        private double findMedian(int[] nums) {
            double result = nums[nums.length / 2];
            if (nums.length % 2 == 0) {
                result = (result + nums[nums.length / 2 - 1]) / 2;
            }
            return result;
        }

        private int getByIndexOfDisjoint(int[] nums1, int[] nums2, int index) {
            if (index < nums1.length) {
                return nums1[index];
            } else {
                return nums2[index - nums1.length];
            }
        }

        private double findMedianOfDisjoint(int[] nums1, int[] nums2) {
            int length = nums1.length + nums2.length;
            int secondIndex = length / 2;
            double result = getByIndexOfDisjoint(nums1, nums2, secondIndex);
            if (length % 2 == 0) {
                result = (result + getByIndexOfDisjoint(nums1, nums2, secondIndex - 1)) / 2;
            }
            return result;
        }

        private int binarySearch(int[] nums, int fromIndex, int toIndex, int value) {
            int l = fromIndex;
            int r = toIndex;
            while (l < r) {
                int probe = (l + r) / 2;
                if (nums[probe] <= value) {
                    l = probe + 1;
                }
                else {
                    r = probe;
                }
            }
            return r;
        }

        private double getByIndexOfOverlap(int[] nums1, int[] nums2, int index) {
            int l1 = 0;
            int r1 = nums1.length;
            int l2 = 0;
            int r2 = nums2.length;
            for (;l1 < r1 && l2 < r2;) {
                int probe1 = l1 + (r1 - l1) / 2;
                int probe2 = l2 + (r2 - l2) / 2;
                int probe1Value = nums1[probe1];
                int probe2Value = nums2[probe2];
                if (probe1Value <= probe2Value) {
                    probe1 = binarySearch(nums1, probe1, r1, probe2Value);
                }
                else {
                    probe2 = binarySearch(nums2, probe2, r2, probe1Value);
                }
                int mid = probe1 + probe2;
                if (mid == index) {
                    return Math.max(probe1Value, probe2Value);
                }
                else if (index < mid) {
                    r1 = probe1;
                    r2 = probe2;
                }
                else {
                    l1 = probe1;
                    l2 = probe2;
                    if (probe1Value <= probe2Value)
                        l2 = probe2 + 1;
                    else
                        l1 = probe1 + 1;
                }
            }
            if (l1 >= r1) {
                return nums2[index - l1];
            }
            else {
                return nums1[index - l2];
            }
        }

        double findMedianSortedArrays(int[] nums1, int[] nums2) {
            if (nums1.length == 0 && nums2.length == 0) {
                return 0;
            } else if (nums1.length == 0) {
                return findMedian(nums2);
            } else if (nums2.length == 0) {
                return findMedian(nums1);
            }

            if (nums1[nums1.length - 1] <= nums2[0]) {
                return findMedianOfDisjoint(nums1, nums2);
            } else if (nums2[nums2.length - 1] <= nums1[0]) {
                return findMedianOfDisjoint(nums2, nums1);
            }

            int length = nums1.length + nums2.length;
            int secondIndex = length / 2;
            double second = getByIndexOfOverlap(nums1, nums2, secondIndex);

            double first;
            if (length % 2 == 0) {
                first = getByIndexOfOverlap(nums1, nums2, secondIndex - 1);
            } else {
                first = second;
            }

            return (first + second) / 2;
        }
    }
}
