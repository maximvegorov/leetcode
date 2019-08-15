package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Permutations {
    public static void main(String[] args) {
        for (List<Integer> perm : new Solution().permute(new int[]{1, 2, 3})) {
            System.out.println(perm.toString());
        }
    }

    public static class Solution {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> resultList = new ArrayList<>();
            generatePerms(resultList, nums, 0);
            return resultList;
        }

        private static void generatePerms(List<List<Integer>> resultList, int[] nums, int l) {
            if (l >= nums.length) {
                resultList.add(new Permutation(Arrays.copyOf(nums, nums.length)));
            }

            for (int i = l; i < nums.length; i++) {
                int num = nums[i];

                nums[i] = nums[l];
                nums[l] = num;

                generatePerms(resultList, nums, l + 1);

                nums[l] = nums[i];
                nums[i] = num;
            }
        }

        private static final class Permutation extends java.util.AbstractList<Integer> {
            private final int[] elements;

            Permutation(int[] elements) {
                this.elements = elements;
            }

            @Override
            public Integer get(int index) {
                return elements[index];
            }

            @Override
            public int size() {
                return elements.length;
            }
        }
    }
}
