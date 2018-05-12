package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangleInHistogram {
    public static void main(String[] args) {
        System.out.println(new Solution().largestRectangleArea(new int[] {2,1,5,6,2,3}));
    }

    static class Solution {
        public int largestRectangleArea(int[] heights) {
            Deque<PosHeight> stack = new ArrayDeque<>(heights.length);
            long maxSquare = 0;
            for (int i = 0; i < heights.length; i++) {
                int height = heights[i];
                int pos = i;
                while (!stack.isEmpty() && stack.peekLast().getHeight() >= height) {
                    pos = stack.removeLast().getPos();
                }
                stack.addLast(new PosHeight(pos, height));
                for (PosHeight ph : stack) {
                    int square = (i - ph.getPos() + 1) * ph.getHeight();
                    if (maxSquare < square)
                        maxSquare = square;

                }
            }
            return (int)maxSquare;
        }

        static class PosHeight {
            private final int pos;
            private final int height;

            PosHeight(int pos, int height) {
                this.pos = pos;
                this.height = height;
            }

            public int getPos() {
                return pos;
            }

            public int getHeight() {
                return height;
            }
        }
    }
}
