package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Program {
    public static void main(String[] args) {
        System.out.println(
                new Solution().topKFrequent(new int[] {1,1,1,2,2,3}, 2).toString());
    }

    public static class Solution {
        List<Integer> topKFrequent(int[] nums, int k) {
            return Arrays.stream(nums)
                    .boxed()
                    .collect(
                            groupingBy(Function.identity(), counting()))
                    .entrySet()
                    .stream()
                    .sorted(
                            Comparator.<Map.Entry<Integer, Long>>comparingLong(Map.Entry::getValue)
                                    .reversed())
                    .limit(k)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
        }
    }
}
