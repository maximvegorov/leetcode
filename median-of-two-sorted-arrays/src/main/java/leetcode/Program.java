package leetcode;

public class Program {
    public static void main(String[] args) {
        System.out.println(
                new Solution().findMedianSortedArrays(
                        new int[] {1, 2}, new int[] { 1, 2 }));

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

        private int binarySearch(int[] nums, int toIndex, int value) {
            int l = 0;
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

        private Double getByIndexOfOverlap(int[] nums1, int[] nums2, int index) {
            int l = 0;
            int r = nums1.length;
            while (l < r) {
                int probe = (l + r) / 2;

                int remainMax = binarySearch(nums2, nums2.length, nums1[probe]);
                int remainMin = binarySearch(nums2, remainMax, nums1[probe] - 1);
                if (probe + remainMin <= index && index <= probe + remainMax)
                    return (double)nums1[probe];
                else if (probe + remainMax < index)
                    l = probe + 1;
                else {
                    r = probe;
                }
            }
            return null;
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
            Double second = getByIndexOfOverlap(nums1, nums2, secondIndex);
            if (second == null) {
                second = getByIndexOfOverlap(nums2, nums1, secondIndex);
                assert second != null;
            }

            Double first;
            if (length % 2 == 0) {
                first = getByIndexOfOverlap(nums1, nums2, secondIndex - 1);
                if (first == null) {
                    first = getByIndexOfOverlap(nums2, nums1, secondIndex - 1);
                    assert first != null;
                }
            } else {
                first = second;
            }

            return (first + second) / 2;
        }
    }
}
