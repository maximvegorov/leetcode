package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThreeSum {
    public static void main(String[] args) {
        System.out.println(new Solution().threeSum(
                new int[]{13, 14, 1, 2, -11, -11, -1, 5, -1, -11, -9, -12, 5, -3, -7, -4, -12, -9, 8, -13, -8, 2, -6, 8,
                        11, 7, 7, -6, 8, -9, 0, 6, 13, -14, -15, 9, 12, -9, -9, -4, -4, -3, -9, -14, 9, -8, -11, 13,
                        -10, 13, -15, -11, 0, -14, 5, -4, 0, -3, -3, -7, -4, 12, 14, -14, 5, 7, 10, -5, 13, -14, -2, -6,
                        -9, 5, -12, 7, 4, -8, 5, 1, -10, -3, 5, 6, -9, -5, 9, 6, 0, 14, -15, 11, 11, 6, 4, -6, -10, -1,
                        4, -11, -8, -13, -10, -2, -1, -7, -9, 10, -7, 3, -4, -2, 8, -13}));
    }

    public static class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            Map<Integer, Integer> negNumsMap = new HashMap<>();
            int zeroCount = 0;
            Map<Integer, Integer> posNumsMap = new HashMap<>();
            for (Integer num : nums) {
                if (num < 0) {
                    negNumsMap.put(num, negNumsMap.getOrDefault(num, 0) + 1);
                } else if (num > 0) {
                    posNumsMap.put(num, posNumsMap.getOrDefault(num, 0) + 1);

                } else {
                    zeroCount++;
                }
            }

            if (zeroCount >= 3) {
                result.add(Arrays.asList(0, 0, 0));
            }

            Integer[] posNums = posNumsMap.keySet().toArray(new Integer[posNumsMap.size()]);
            for (Integer negNum : negNumsMap.keySet()) {
                for (Integer posNum : posNums) {
                    int inv_a_plus_b = -(negNum + posNum);
                    if (inv_a_plus_b > 0) {
                        if (inv_a_plus_b < posNum) {
                            continue;
                        }
                        Integer count = posNumsMap.get(inv_a_plus_b);
                        if (count == null || (inv_a_plus_b == posNum && count == 1)) {
                            continue;
                        }
                        result.add(Arrays.asList(negNum, posNum, inv_a_plus_b));
                    } else if (inv_a_plus_b < 0) {
                        if (inv_a_plus_b < negNum) {
                            continue;
                        }
                        Integer count = negNumsMap.get(inv_a_plus_b);
                        if (count == null || (inv_a_plus_b == negNum && count == 1)) {
                            continue;
                        }
                        result.add(Arrays.asList(negNum, inv_a_plus_b, posNum));
                    } else if (zeroCount > 0) {
                        result.add(Arrays.asList(negNum, 0, posNum));
                    }
                }
            }
            return result;
        }
    }
}