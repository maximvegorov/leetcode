package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class TheSkylineProblem {
    public static void main(String[] args) {
        new Solution().getSkyline(
                new int[][]{
                        {1, 2, 1},
                        {1, 2, 2},
                        {1, 2, 3}
                }).forEach(l -> System.out.println(Arrays.toString(l)));
    }

    static class Solution {
        public List<int[]> getSkyline(int[][] buildings) {
            if (buildings.length == 0) {
                return Collections.emptyList();
            } else if (buildings.length == 1) {
                return Arrays.asList(
                        new int[]{buildings[0][0], buildings[0][2]},
                        new int[]{buildings[0][1], 0});
            }

            Arrays.sort(
                    buildings,
                    Comparator.<int[]>comparingInt(e -> e[0])
                            .thenComparingInt(e -> -e[2]).thenComparingInt(e -> e[1]));

            List<int[]> result = new ArrayList<>(buildings.length);
            PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.<int[]>comparingInt(e -> e[2]).reversed());
            result.add(new int[]{buildings[0][0], buildings[0][2]});
            int[] current = buildings[0];
            int i = 1;
            while (i < buildings.length) {
                int[] building = buildings[i];
                if (current[1] < building[0]) {
                    while (!q.isEmpty() && q.peek()[1] <= current[1]) {
                        q.remove();
                    }
                    int[] newCurrent = q.poll();
                    if (newCurrent != null) {
                        result.add(new int[]{current[1], newCurrent[2]});
                        current = newCurrent;
                        continue;
                    } else {
                        result.add(new int[]{current[1], 0});
                        current = building;
                        result.add(new int[]{current[0], current[2]});
                    }
                } else {
                    if (building[2] > current[2]) {
                        if (current[1] > building[1]) {
                            q.add(current);
                        }
                        current = building;
                        result.add(new int[]{current[0], current[2]});
                    } else if (building[2] == current[2] && current[1] < building[1]) {
                        current = building;
                    } else {
                        q.add(building);
                    }
                }
                i++;
            }

            while (!q.isEmpty()) {
                while (!q.isEmpty() && q.peek()[1] <= current[1]) {
                    q.remove();
                }
                int[] top = q.poll();
                if (top != null) {
                    if (top[2] < current[2]) {
                        result.add(new int[]{current[1], top[2]});
                        current = top;
                    } else if (top[1] > current[1]) {
                        current = top;
                    }
                }
            }

            result.add(new int[]{current[1], 0});

            return result;
        }
    }
}
