package leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Triangle {
    public static void main(String[] args) {
        System.out.println(
                new Solution().minimumTotal(
                        Arrays.asList(
                                Collections.singletonList(2),
                                Arrays.asList(3, 4),
                                Arrays.asList(6, 5, 7),
                                Arrays.asList(4, 1, 8, 3))));
    }

    public static final class Solution {
        public int minimumTotal(List<List<Integer>> triangle) {
            if (triangle.isEmpty()) {
                return 0;
            }
            int n = triangle.get(triangle.size() - 1).size();
            int[] p0 = new int[n];
            int[] p1 = new int[n];

            p0[0] = triangle.get(0).get(0);
            for (int i = 1; i < n; i++) {
                List<Integer> row = triangle.get(i);
                p1[0] = p0[0] + row.get(0);
                p1[row.size() - 1] = p0[row.size() - 2] + row.get(row.size() - 1);
                for (int j = 1; j < row.size() - 1; j++) {
                    p1[j] = Math.min(p0[j - 1], p0[j]) + row.get(j);
                }
                int[] temp = p0;
                p0 = p1;
                p1 = temp;
            }

            return Arrays.stream(p0).min().getAsInt();

        }
    }
}
