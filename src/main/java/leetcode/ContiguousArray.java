package leetcode;

import java.util.function.IntConsumer;
import java.util.function.ToIntFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class ContiguousArray {
    public static void main(String[] args) {
        System.out.println(new Solution().findMaxLength(new int[]{0, 1, 1, 0}));

    }

    public static final class Solution {
        private static <T> Collector<T, ?, IntMinMaxStatistics> minMaxInt(ToIntFunction<? super T> mapper) {
            return Collector.of(
                    IntMinMaxStatistics::new,
                    (r, t) -> r.accept(mapper.applyAsInt(t)),
                    (l, r) -> {
                        l.combine(r);
                        return l;
                    },
                    Collector.Characteristics.IDENTITY_FINISH);
        }

        public int findMaxLength(int[] nums) {
            if (nums.length <= 1) {
                return 0;
            }
            int[] sums = new int[nums.length + 1];
            sums[0] = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 1) {
                    sums[i + 1] = sums[i] + 1;
                } else {
                    sums[i + 1] = sums[i] - 1;
                }
            }
            return IntStream.range(0, sums.length)
                    .boxed()
                    .collect(
                            Collectors.groupingBy(
                                    i -> sums[i],
                                    minMaxInt(i -> i)))
                    .entrySet()
                    .stream()
                    .mapToInt(e -> e.getValue().getMax() - e.getValue().getMin())
                    .max()
                    .getAsInt();
        }

        private final static class IntMinMaxStatistics implements IntConsumer {
            private int min = Integer.MAX_VALUE;
            private int max = Integer.MIN_VALUE;

            @Override
            public void accept(int value) {
                min = Math.min(min, value);
                max = Math.max(max, value);
            }

            public void combine(IntMinMaxStatistics other) {
                min = Math.min(min, other.min);
                max = Math.max(max, other.max);
            }

            public final int getMin() {
                return min;
            }

            public final int getMax() {
                return max;
            }
        }
    }
}
