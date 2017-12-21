package leetcode;

import java.util.Collections;
import java.util.NavigableSet;
import java.util.TreeSet;

public class ThreeSumClosest {
    public static void main(String[] args) {
        System.out.println(new Solution().threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
    }

    static class Solution {
        public int threeSumClosest(int[] nums, int target) {
            int closestSum = nums[0] + nums[1] + nums[2];
            NavigableSet<Integer> twoSums = new TreeSet<>(Collections.singleton(nums[0] + nums[1]));
            for (int j = 2; j < nums.length; j++) {
                Integer twoSum = target - nums[j];
                Integer floor = twoSums.floor(twoSum);
                Integer ceiling = twoSums.ceiling(twoSum);
                if (floor == null) {
                    floor = ceiling;
                }
                if (ceiling == null) {
                    ceiling = floor;
                }

                int floorClosestSum = floor + nums[j];
                if (Math.abs(floorClosestSum - target) < Math.abs(closestSum - target)) {
                    closestSum = floorClosestSum;
                }
                int ceilingClosestSum = ceiling + nums[j];
                if (Math.abs(ceilingClosestSum - target) < Math.abs(closestSum - target)) {
                    closestSum = floorClosestSum;
                }

                for (int i = 0; i < j; i++) {
                    twoSums.add(nums[i] + nums[j]);
                }
            }
            return closestSum;
        }
    }
}
