package leetcode;

public final class PerfectSquares {
    public static void main(String[] args) {
        System.out.println(new Solution().numSquares(13));
    }

    public static final class Solution {
        public int numSquares(int n) {
            int[] nums = new int[n + 1];
            nums[1] = 1;
            int nextSquare = 2;
            int nextSquare2 = nextSquare * nextSquare;
            for (int i = 2; i <= n; i++) {
                if (i == nextSquare2) {
                    nums[i] = 1;
                    nextSquare++;
                    nextSquare2 = nextSquare * nextSquare;
                } else {
                    int min = Integer.MAX_VALUE;
                    for (int j = 1; j < i; j++) {
                        int num = nums[j] + nums[i - j];
                        if (num < min) {
                            min = num;
                        }
                    }
                    nums[i] = min;
                }
            }
            return nums[n];
        }
    }
}
