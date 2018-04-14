package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class LargestNumber {
    public static void main(String[] args) {
        System.out.println(new Solution().largestNumber(new int[] {3, 30, 34, 5, 9}));
    }

    static class Solution {
        static class NumberComparator implements Comparator<String> {
            public int compare(String s1, String s2) {
                return (s2 + s1).compareTo(s1 + s2);
            }
        }

        public String largestNumber(int[] nums) {
            if (nums.length == 0)
                return "";

            Arrays.sort(nums);

            int endIndex = nums.length;
            if (nums[endIndex - 1] == 0) {
                while (endIndex > 0 && nums[endIndex - 1] == 0)
                    endIndex--;
                endIndex++;
            }

            if (endIndex == 1)
                return String.valueOf(nums[0]);

            return Arrays.stream(nums, 0, endIndex)
                    .mapToObj(String::valueOf)
                    .sorted(new NumberComparator())
                    .collect(Collectors.joining(""));
        }
    }
}
