package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class InsertInterval {
    public static void main(String[] args) {
        System.out.println(
                new Solution().insert(
                        Collections.singletonList(
                                new Interval(1, 5)),
                        new Interval(6, 8)).toString());
    }

    public static final class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public String toString() {
            return "[" + start + "," + end + "]";
        }
    }

    public static final class Solution {
        public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
            if (intervals.isEmpty()) {
                return Collections.singletonList(newInterval);
            }

            List<Interval> result = new ArrayList<>(intervals.size());

            int i = 0;
            while (i < intervals.size() && intervals.get(i).end < newInterval.start) {
                result.add(intervals.get(i));
                i++;
            }

            if (i >= intervals.size()) {
                result.add(newInterval);
            } else {
                int start = Math.min(intervals.get(i).start, newInterval.start);
                while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
                    i++;
                }

                if (i == 0) {
                    result.add(new Interval(start, newInterval.end));
                } else {
                    result.add(new Interval(start, Math.max(intervals.get(i - 1).end, newInterval.end)));
                }

                if (i < intervals.size()) {
                    result.addAll(intervals.subList(i, intervals.size()));
                }
            }

            return result;
        }
    }
}
