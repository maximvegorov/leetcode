package leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class FourSum {
    public static void main(String[] args) {
        System.out.println(new Solution().fourSum(new int[]{1, 1}, 2));
    }

    static class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<Pair> pairs = new ArrayList<>(nums.length * nums.length);
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    pairs.add(new Pair(i, j, nums[i] + nums[j]));
                }
            }

            Map<Integer, List<Pair>> sums2 = pairs.stream()
                    .collect(Collectors.groupingBy(Pair::getSum));

            return pairs.stream()
                    .flatMap(p1 -> sums2.getOrDefault(target - p1.getSum(), Collections.emptyList()).stream()
                            .map(p2 -> Quadruplet.from(p1, p2, nums)))
                    .filter(Objects::nonNull)
                    .distinct()
                    .map(Quadruplet::toList)
                    .collect(Collectors.toList());
        }

        static class Pair {
            private final int first;
            private final int second;
            private final Integer sum;

            Pair(int first, int second, Integer sum) {
                this.first = first;
                this.second = second;
                this.sum = sum;
            }

            int getFirst() {
                return first;
            }

            int getSecond() {
                return second;
            }

            Integer getSum() {
                return sum;
            }
        }

        static class Quadruplet {
            private final Integer[] values;

            private Quadruplet(Integer[] values) {
                this.values = values;
                Arrays.sort(this.values);
            }

            public static Quadruplet from(Pair pair1, Pair pair2, int[] nums) {
                if (pair1.getFirst() == pair2.getFirst() || pair1.getFirst() == pair2.getSecond())
                    return null;
                if (pair1.getSecond() == pair2.getFirst() || pair1.getSecond() == pair2.getSecond())
                    return null;
                return new Quadruplet(new Integer[]{
                        nums[pair1.getFirst()], nums[pair1.getSecond()], nums[pair2.getFirst()], nums[pair2.getSecond()]});
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Quadruplet that = (Quadruplet) o;
                return Arrays.equals(values, that.values);
            }

            @Override
            public int hashCode() {
                return Arrays.hashCode(values);
            }

            public List<Integer> toList() {
                return Arrays.asList(values);
            }
        }
    }
}
