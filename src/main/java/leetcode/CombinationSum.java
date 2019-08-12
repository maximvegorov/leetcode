package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CombinationSum {
    public static void main(String[] args) {
        System.out.println(new CombinationSum().combinationSum(new int[] {2, 3, 6, 7}, 7));
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return Collections.emptyList();
        }

        Arrays.sort(candidates);

        return doCombinationSum(candidates, target, candidates.length);
    }

    private List<List<Integer>> doCombinationSum(int[] candidates, int target, int toIndex) {
        if (target < candidates[0]) {
            if (target == 0) {
                return new ArrayList<>(Collections.singletonList(new ArrayList<>()));
            }
            return Collections.emptyList();
        }

        List<List<Integer>> result = new ArrayList<>();

        int fromIndex = Arrays.binarySearch(candidates, 0, toIndex, target);
        if (fromIndex < 0) {
            fromIndex = -(fromIndex + 1) - 1;
        }
        for (int i = fromIndex; i >= 0; i--) {
            int tail = candidates[i];
            List<List<Integer>> heads = doCombinationSum(candidates, target - tail, i + 1);
            for (List<Integer> head : heads) {
                head.add(tail);
            }
            result.addAll(heads);
        }

        return result;

    }
}
