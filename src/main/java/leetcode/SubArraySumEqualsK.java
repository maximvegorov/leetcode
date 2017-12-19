package leetcode;

import java.util.HashMap;
import java.util.Map;

public class SubArraySumEqualsK {
    public static void main(String[] args) {
        System.out.println(new Solution().subarraySum(new int[]{1, 1, 1}, 2));
    }

    static class Solution {
        public int subarraySum(int[] nums, int k) {
            if (nums.length == 0) {
                return 0;
            }
            int[] sums = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                sums[i + 1] = sums[i] + nums[i];
            }
            int count = 0;
            Map<Integer, Integer> map = new HashMap<>(nums.length);
            map.put(0, 1);
            for (int i = 0; i < nums.length; i++) {
                int sum = sums[i + 1];
                count += map.getOrDefault(sum - k, 0);
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
            return count;
        }
    }
}
