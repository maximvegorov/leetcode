package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public final class Permutations {
    public static void main(String[] args) {
        for (List<Integer> perm : new Solution().permute(new int[]{1, 2, 3})) {
            System.out.println(perm.toString());
        }
    }

    public static class Solution {
        private static List<List<Integer>> perms(Integer[] nums, int l) {
            if (l >= nums.length) {
                return Collections.singletonList(new ArrayList<>(nums.length));
            }

            List<List<Integer>> result = new ArrayList<>();

            for (int i = l; i < nums.length; i++) {
                Integer num = nums[i];

                nums[i] = nums[l];
                nums[l] = num;

                List<List<Integer>> perms = perms(nums, l + 1);

                nums[l] = nums[i];
                nums[i] = num;

                for (List<Integer> perm : perms) {
                    perm.add(num);
                }

                result.addAll(perms);
            }

            return result;

        }

        public List<List<Integer>> permute(int[] nums) {
            return perms(IntStream.of(nums).boxed().toArray(Integer[]::new), 0);
        }
    }
}
