package leetcode;

import java.util.*;

public class CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return Collections.emptyList();
        }

        Arrays.sort(candidates);

        Set<Combination> combinations = doCombinationSum(candidates, target, candidates.length);

        List<List<Integer>> result = new ArrayList<>(combinations.size());
        for (Combination combination : combinations) {
            result.add(combination.toList());
        }
        return result;
    }

    private Set<Combination> doCombinationSum(int[] candidates, int target, int toIndex) {
        if (target < candidates[0]) {
            if (target == 0) {
                return Collections.singleton(new Combination());
            }
            return Collections.emptySet();
        }

        Set<Combination> result = new HashSet<>();

        int fromIndex = Arrays.binarySearch(candidates, 0, toIndex, target);
        if (fromIndex < 0) {
            fromIndex = -(fromIndex + 1) - 1;
        }

        for (int i = fromIndex; i >= 0; i--) {
            int tail = candidates[i];
            Set<Combination> heads = doCombinationSum(candidates, target - tail, i);
            for (Combination head : heads) {
                head.add(tail);
                result.add(head);
            }
        }

        return result;
    }

    private static final class Combination {
        private int[] numbers = new int[8];
        private int hashOfNumbers = 0;
        private int size;

        void add(int number) {
            if (numbers.length == size) {
                numbers = Arrays.copyOf(numbers, 2 * size);
            }
            numbers[size] = number;
            hashOfNumbers = 31 * hashOfNumbers + number;
            size++;

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Combination that = (Combination) o;

            if (hashOfNumbers != that.hashOfNumbers) {
                return false;
            }

            if (size != that.size) {
                return false;
            }

            for (int i = 0; i < size; i++) {
                if (numbers[i] != that.numbers[i]) {
                    return false;
                }
            }

            return true;
        }

        @Override
        public int hashCode() {
            return hashOfNumbers;
        }

        List<Integer> toList() {
            List<Integer> list = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                list.add(numbers[i]);
            }
            return list;
        }
    }
}
