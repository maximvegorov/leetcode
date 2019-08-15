package leetcode;

import java.util.*;

public class Permutations2 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }
        Set<Permutation> resultSet = new HashSet<>();
        generatePerms(resultSet, nums, 0);
        List<List<Integer>> resultList = new ArrayList<>(resultSet.size());
        for (Permutation permutation : resultSet) {
            resultList.add(permutation.toList());
        }
        return resultList;
    }

    private static void generatePerms(Set<Permutation> resultSet, int[] nums, int l) {
        if (l >= nums.length) {
            resultSet.add(new Permutation(Arrays.copyOf(nums, nums.length)));
        }

        for (int i = l; i < nums.length; i++) {
            Integer num = nums[i];

            nums[i] = nums[l];
            nums[l] = num;

            generatePerms(resultSet, nums, l + 1);

            nums[l] = nums[i];
            nums[i] = num;
        }
    }

    private static final class Permutation {
        private final int[] elements;
        private final int hashCode;

        Permutation(int[] elements) {
            this.elements = elements;
            this.hashCode = Arrays.hashCode(elements);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Permutation that = (Permutation) o;
            if (hashCode != that.hashCode) {
                return false;
            }
            return Arrays.equals(elements, that.elements);
        }

        @Override
        public int hashCode() {
            return hashCode;
        }

        public ListOfInteger toList() {
            return new ListOfInteger(elements);
        }
    }

    private static final class ListOfInteger extends java.util.AbstractList<Integer> {
        private final int[] elements;

        ListOfInteger(int[] elements) {
            this.elements = elements;
        }

        @Override
        public Integer get(int index) {
            return elements[index];
        }

        @Override
        public int size() {
            return elements.length;
        }
    }
}
