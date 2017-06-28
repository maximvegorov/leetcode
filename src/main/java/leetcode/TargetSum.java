package leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public final class TargetSum {
    public static void main(String[] args) {
        System.out.println(
                new Solution()
                        .findTargetSumWays(
                                new int[]{33, 36, 38, 40, 25, 49, 1, 8, 50, 13, 41, 50, 29, 27, 18, 25, 37, 8, 0, 48},
                                0));
    }

    public static final class Solution {
        private static final int MAX_SUM = 1000;
        private static final int ZERO_INDEX = MAX_SUM;

        public int findTargetSumWays(int[] nums, int S) {
            if (S < -MAX_SUM || S > MAX_SUM) {
                return 0;
            }
            int[] partialSums0 = new int[2 * MAX_SUM + 1];
            int[] partialSums1 = new int[2 * MAX_SUM + 1];
            Queue<Integer> q = new ArrayDeque<>(2 * MAX_SUM + 1);
            q.add(0);
            for (int i = 0; i < nums.length; i++) {
                Arrays.fill(partialSums1, 0);
                int m = q.size();
                for (int j = 1; j <= m; j++) {
                    int partialSum = q.remove();
                    partialSums1[ZERO_INDEX + partialSum + nums[i]]++;
                    q.add(partialSum + nums[i]);
                    partialSums1[ZERO_INDEX + partialSum - nums[i]]++;
                    q.add(partialSum - nums[i]);
                }
                int[] temp = partialSums0;
                partialSums0 = partialSums1;
                partialSums1 = temp;
            }
            return partialSums0[ZERO_INDEX + S];
        }
    }
}
