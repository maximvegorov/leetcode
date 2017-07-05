package leetcode;

public final class ContainerWithMostWater {
    public static void main(String[] args) {
        System.out.println(new Solution().maxArea(new int[]{1, 2}));
    }

    public static class Solution {
        public int maxArea(int[] heights) {
            int Smax = 0;
            int Hleft = 0;
            for (int i = 0; i < heights.length - 1; i++) {
                if (heights[i] <= Hleft) {
                    continue;
                }
                Hleft = heights[i];
                int Hright = 0;
                for (int j = heights.length - 1; j > i && Hright < Hleft; j--) {
                    if (heights[j] <= Hright) {
                        continue;
                    }
                    Hright = heights[j];
                    int S = Math.min(Hleft, Hright) * (j - i);
                    if (S > Smax) {
                        Smax = S;
                    }
                }

            }
            return Smax;
        }
    }
}
