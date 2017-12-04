package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Subsets2 {
    public static void main(String[] args) {
        System.out.println(
                new Solution().subsetsWithDup(1, 2, 2).toString());
    }

    static class Solution {
        public List<List<Integer>> subsetsWithDup(int... nums) {
            List<List<Integer>> result = new ArrayList<>();
            ValueFreq[] values = IntStream.of(nums)
                    .boxed()
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .entrySet()
                    .stream()
                    .map(e -> new ValueFreq(e.getKey(), e.getValue().intValue()))
                    .toArray(ValueFreq[]::new);
            SubsetIterator iterator = new SubsetIterator(values);
            while (iterator.hasNext()) {
                result.add(iterator.next());
            }
            return result;
        }

        static final class ValueFreq {
            private final int value;
            private final int freq;

            ValueFreq(int value, int freq) {
                this.value = value;
                this.freq = freq;
            }

            public int getValue() {
                return value;
            }

            public int getFreq() {
                return freq;
            }

            @Override
            public String toString() {
                return "ValueFreq{" +
                        "value=" + value +
                        ", freq=" + freq +
                        '}';
            }
        }

        static final class SubsetIterator {
            private final ValueFreq[] values;
            private final int[] counts;
            private int pos;

            SubsetIterator(ValueFreq[] values) {
                this.values = values;
                this.counts = new int[values.length + 1];
            }

            boolean hasNext() {
                return counts[values.length] == 0;
            }

            List<Integer> next() {
                List<Integer> result = IntStream.range(0, values.length)
                        .filter(i -> counts[i] > 0)
                        .mapToObj(i -> Collections.nCopies(counts[i], values[i].getValue()).stream())
                        .flatMap(Function.identity())
                        .collect(Collectors.toList());
                if (counts[pos] < values[pos].getFreq()) {
                    counts[pos]++;
                } else {
                    while (pos < values.length && counts[pos] == values[pos].getFreq()) {
                        pos++;
                    }
                    Arrays.fill(counts, 0, pos, 0);
                    counts[pos]++;
                    pos = 0;
                }
                return result;
            }
        }
    }
}
