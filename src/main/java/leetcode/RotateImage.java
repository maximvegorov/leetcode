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

            n--;

            int t = 0;
            int r = n;
            int b = n;
            int l = 0;
            while (t <= b && l <= r) {
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

                n -= 2;
                t++;
                r--;
                b--;
                l++;
            }
        }
    }
}
