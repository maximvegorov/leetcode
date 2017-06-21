package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContinuousSubarraySum {
    public static void main(String[] args) {
        System.out.println(new Solution().checkSubarraySum(new int[]{1, 2, 3}, 5));
    }

    public static class Solution {
        public boolean checkSubarraySum(int[] nums, int k) {
            if (nums.length == 0) {
                return false;
            }

            if (k == 0) {
                for (int i = 0; i < nums.length - 1; i++) {
                    if (nums[i] == 0 && nums[i + 1] == 0) {
                        return true;
                    }
                }
            } else {
                int[] sums = new int[nums.length + 1];
                for (int i = 1; i <= nums.length; i++) {
                    sums[i] = sums[i - 1] + nums[i - 1];
                }

                Map<Integer, List<Integer>> sameRems = new HashMap<>();
                for (int i = 0; i < nums.length; i++) {
                    sameRems.computeIfAbsent(sums[i + 1] % k, r -> new ArrayList<>())
                            .add(i);
                }

                if (sameRems.getOrDefault(0, Collections.emptyList()).stream().anyMatch(i -> i > 0)) {
                    return true;
                }

                for (Map.Entry<Integer, List<Integer>> entry : sameRems.entrySet()) {
                    if (entry.getValue().size() == 1) {
                        continue;
                    }
                    List<Integer> indexes = entry.getValue();
                    for (int i = 0; i < indexes.size() - 1; i++) {
                        for (int j = i + 1; j < indexes.size(); j++) {
                            int p = indexes.get(i) + 1;
                            int q = indexes.get(j);
                            if ((sums[q + 1] - sums[p]) % k == 0 && p < q) {
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }
    }
}
