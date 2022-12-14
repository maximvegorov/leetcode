package leetcode;

import java.util.Arrays;

public final class TargetSum {
    public static void main(String[] args) {
        System.out.println(
                new Solution()
                        .findTargetSumWays(
                                new int[]{1, 1, 1, 1, 1}, 3));
    }

    public static final class Solution {
        public int findTargetSumWays(int[] nums, int S) {
            int totalSum = Arrays.stream(nums).sum();
            if (S < -totalSum || S > totalSum) {
                return 0;
            }
            int zeroIndex = totalSum;
            int[] partialSums0 = new int[2 * totalSum + 1];
            int[] partialSums1 = new int[2 * totalSum + 1];
            partialSums0[zeroIndex] = 1;
            for (int i = 0; i < nums.length; i++) {
                Arrays.fill(partialSums1, 0);
                for (int j = 0; j < partialSums1.length; j++) {
                    if (partialSums0[j] == 0) {
                        continue;
                    }
                    partialSums1[j + nums[i]] += partialSums0[j];
                    partialSums1[j - nums[i]] += partialSums0[j];
                }
                int[] temp = partialSums0;
                partialSums0 = partialSums1;
                partialSums1 = temp;
            }
            return partialSums0[zeroIndex + S];
        }
    }
}
