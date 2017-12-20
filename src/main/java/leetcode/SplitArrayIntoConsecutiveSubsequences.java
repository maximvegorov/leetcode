package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class SplitArrayIntoConsecutiveSubsequences {
    public static void main(String[] args) {
        System.out.println(new Solution().isPossible(new int[]{1, 2, 3, 3, 4, 5}));
        System.out.println(new Solution().isPossible(new int[]{1, 2, 3, 3, 4, 4, 5, 5}));
        System.out.println(new Solution().isPossible(new int[]{4, 5, 6, 7, 7, 8, 8, 9, 10, 11}));

        System.out.println(new Solution().isPossible(new int[]{1, 2, 3, 4, 4, 5}));
        System.out.println(new Solution().isPossible(new int[]{4, 5, 6, 6, 7, 8, 9, 10, 10, 11}));
    }

    static class Solution {
        public boolean isPossible(int[] nums) {
            if (nums.length < 3) {
                return false;
            }

            List<UniqueNum> uniqueNums = new ArrayList<>(nums.length);
            int current = nums[0];
            int count = 1;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == current) {
                    count++;
                } else {
                    uniqueNums.add(new UniqueNum(current, count));
                    current = nums[i];
                    count = 1;
                }
            }
            uniqueNums.add(new UniqueNum(current, count));

            Deque<Seq> q1 = new ArrayDeque<>();
            Deque<Seq> q2 = new ArrayDeque<>();
            for (UniqueNum uniqueNum : uniqueNums) {
                int i = 0;
                for (; !q1.isEmpty() && i < uniqueNum.getCount(); i++) {
                    Seq seq = q1.removeLast();
                    if (seq.getLastNum() + 1 != uniqueNum.getNum()) {
                        if (seq.getCount() < 3) {
                            return false;
                        }
                    } else {
                        q2.addFirst(new Seq(uniqueNum.getNum(), seq.getCount() + 1));
                    }
                }

                while (!q1.isEmpty()) {
                    if (q1.removeLast().getCount() < 3) {
                        return false;
                    }
                }

                for (; i < uniqueNum.getCount(); i++) {
                    q2.addLast(new Seq(uniqueNum.getNum(), 1));
                }
                Deque<Seq> temp = q1;
                q1 = q2;
                q2 = temp;
                q2.clear();
            }
            while (!q1.isEmpty()) {
                if (q1.remove().getCount() < 3) {
                    return false;
                }
            }

            return true;
        }

        static final class UniqueNum {
            private final int num;
            private final int count;

            UniqueNum(int num, int count) {
                this.num = num;
                this.count = count;
            }

            @Override
            public String toString() {
                return "UniqueNum{" +
                        "num=" + num +
                        ", count=" + count +
                        '}';
            }

            int getNum() {
                return num;
            }

            int getCount() {
                return count;
            }
        }

        static class Seq {
            private final int lastNum;
            private final int count;

            Seq(int lastNum, int count) {
                this.lastNum = lastNum;
                this.count = count;
            }

            @Override
            public String toString() {
                return "Seq{" +
                        "lastNum=" + lastNum +
                        ", count=" + count +
                        '}';
            }

            int getLastNum() {
                return lastNum;
            }

            int getCount() {
                return count;
            }
        }
    }
}
