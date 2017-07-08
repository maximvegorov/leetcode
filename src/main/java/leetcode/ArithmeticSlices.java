package leetcode;

public final class ArithmeticSlices {
    public static void main(String[] args) {
        System.out.println(
                new Solution().numberOfArithmeticSlices(new int[]{1, 2, 3, 4}));
    }

    public static final class Solution {
        public int numberOfArithmeticSlices(int[] A) {
            if (A.length == 0) {
                return 0;
            }
            int prevCount = 0;
            int totalCount = 0;
            for (int i = 2; i < A.length; i++) {
                if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                    int count = prevCount + 1;
                    prevCount = count;
                    totalCount += count;
                } else {
                    prevCount = 0;
                }
            }
            return totalCount;
        }
    }
}
