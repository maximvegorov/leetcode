package leetcode;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

public class Subsets {
    public static void main(String[] args) {
        System.out.println(new Solution().subsets(new int[]{1, 2, 3}));
    }

    public static class Solution {
        private List<List<Integer>> powerSet(int[] nums, int size) {
            if (size == 0) {
                List<List<Integer>> result = new ArrayList<>();
                result.add(emptyList());
                return result;
            } else {
                List<List<Integer>> result = powerSet(nums, size - 1);
                int n = result.size();
                for (int i = 0; i < n; i++) {
                    List<Integer> subset = new ArrayList<>(result.get(i));
                    subset.add(nums[size - 1]);
                    result.add(subset);
                }
                return result;
            }
        }

        List<List<Integer>> subsets(int[] nums) {
            return powerSet(nums, nums.length);
        }
    }
}
