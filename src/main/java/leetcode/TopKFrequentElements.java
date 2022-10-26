package leetcode;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class TopKFrequentElements {
    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(new Solution().topKFrequent(new int[] {1,1,1,2,2,3}, 2)));
    }

    public static class Solution {
        int[] topKFrequent(int[] nums, int k) {
            var freqs = Arrays.stream(nums)
                    .boxed()
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            var pq = new PriorityQueue<Map.Entry<Integer, Long>>(Comparator.comparingLong(Map.Entry::getValue));
            for (var entry : freqs.entrySet()) {
                if (pq.size() < k) {
                    pq.add(entry);
                } else {
                    var top = pq.peek();
                    if (top.getValue() < entry.getValue()) {
                        pq.remove();
                        pq.add(entry);
                    }
                }
            }
            return pq.stream()
                    .mapToInt(Map.Entry::getKey)
                    .toArray();
        }
    }
}
