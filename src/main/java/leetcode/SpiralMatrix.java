package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SpiralMatrix {
    public static void main(String[] args) {
        System.out.println(
                new Solution().spiralOrder(new int[][]{
                        new int[]{1, 2, 3},
                        new int[]{4, 5, 6},
                        new int[]{7, 8, 9}}));
    }

    public static final class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            if (matrix.length == 0 || matrix[0].length == 0) {
                return Collections.emptyList();
            }
            List<Integer> result = new ArrayList<>(matrix.length * matrix[0].length);
            int t = 0;
            int r = matrix[0].length - 1;
            int b = matrix.length - 1;
            int l = 0;
            while (t < b && l < r) {
                for (int j = l; j < r; j++) {
                    result.add(matrix[t][j]);
                }
                for (int i = t; i < b; i++) {
                    result.add(matrix[i][r]);
                }
                for (int j = r; j > l; j--) {
                    result.add(matrix[b][j]);
                }
                for (int i = b; i > t; i--) {
                    result.add(matrix[i][l]);
                }
                t++;
                r--;
                b--;
                l++;
            }
            if (t == b) {
                for (int j = l; j <= r; j++) {
                    result.add(matrix[t][j]);
                }
            } else if (l == r) {
                for (int i = t; i <= b; i++) {
                    result.add(matrix[i][l]);
                }
            }
            return result;
        }
    }
}
