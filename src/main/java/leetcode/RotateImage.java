package leetcode;

public final class RotateImage {
    public static void main(String[] args) {

    }

    public static final class Solution {
        public void rotate(int[][] matrix) {
            int n = matrix.length;
            if (n == 0) {
                return;
            }
            int t = 0;
            int r = n - 1;
            int b = n - 1;
            int l = 0;
            while (t <= b && l <= r) {
                n--;

                for (int i = 0; i < n; i++) {
                    int lt = matrix[t][l + i];
                    int rt = matrix[t + i][r];
                    int rb = matrix[b][r - i];
                    int lb = matrix[b - i][l];

                    matrix[t + i][r] = lt;
                    matrix[b][r - i] = rt;
                    matrix[b - i][l] = rb;
                    matrix[t][l + i] = lb;
                }

                t++;
                r--;
                b--;
                l++;
            }
        }
    }
}
