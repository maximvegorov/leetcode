package leetcode;

import java.util.Arrays;
import java.util.Comparator;

public final class NonOverlappingIntervals {
    public static void main(String[] args) {
        System.out.println(new Solution().eraseOverlapIntervals(
                new Interval[]{
                        new Interval(1, 2),
                        new Interval(1, 2),
                        new Interval(1, 2)
                }));
    }

    static class Interval {
        int start;
        int end;

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    public static class Solution {
        public int eraseOverlapIntervals(Interval[] intervals) {
            if (intervals.length <= 1) {
                return 0;
            }
            Arrays.sort(intervals, Comparator.comparingInt(i -> i.end));
            int count = 0;
            int i = 0;
            while (i < intervals.length) {
                int j = i + 1;
                while (j < intervals.length && intervals[i].end > intervals[j].start) {
                    j++;
                }
                count++;
                i = j;
            }
            return intervals.length - count;
        }
    }
}
