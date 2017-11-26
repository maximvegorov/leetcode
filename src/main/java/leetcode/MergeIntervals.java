package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import leetcode.common.Interval;

public final class MergeIntervals {
    public static void main(String[] args) {
        System.out.println(
                new Solution().merge(
                        Arrays.asList(
                                new Interval(1, 3),
                                new Interval(2, 6),
                                new Interval(8, 10),
                                new Interval(15, 18))).toString());
    }

    public static class Solution {
        public List<Interval> merge(List<Interval> intervals) {
            if (intervals.isEmpty()) {
                return Collections.emptyList();
            }

            List<Interval> result = new ArrayList<>(intervals.size());

            intervals.sort(Comparator.comparingInt(i -> i.start));

            int start = intervals.get(0).start;
            int end = intervals.get(0).end;
            for (int i = 1; i < intervals.size(); i++) {
                Interval interval = intervals.get(i);
                if (interval.start <= end) {
                    end = Math.max(end, interval.end);
                } else {
                    result.add(new Interval(start, end));
                    start = interval.start;
                    end = interval.end;
                }
            }
            result.add(new Interval(start, end));

            return result;
        }
    }
}
