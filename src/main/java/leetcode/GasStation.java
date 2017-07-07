package leetcode;

public final class GasStation {
    public static void main(String[] args) {
        System.out.println(
                new Solution().canCompleteCircuit(
                        new int[]{2, 4}, new int[]{3, 4}));
    }

    public static class Solution {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int j = gas.length - 1;
            int i = gas.length - 1;
            int ni = 0;
            int r = 0;
            for (; ; ) {
                int delta = gas[i] - cost[i] + r;
                if (delta < 0) {
                    if (j == 0) {
                        return -1;
                    }
                    j--;
                    r += gas[j] - cost[j];
                } else {
                    i = ni;
                    if (i == j) {
                        return j;
                    }
                    ni++;
                    r = delta;
                }
            }
        }
    }
}
