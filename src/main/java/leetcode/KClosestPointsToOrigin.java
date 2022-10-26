package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KClosestPointsToOrigin {
    class Solution {
        public int[][] kClosest(int[][] points, int k) {
            var pq = new PriorityQueue<>(Comparator.comparingInt(Point::distanceFromOrigin).reversed());
            for (var pa : points) {
                var p = new Point(pa[0], pa[1]);
                if (pq.size() < k) {
                    pq.add(p);
                } else {
                    var top = pq.peek();
                    assert top != null;
                    if (top.distanceFromOrigin() > p.distanceFromOrigin()) {
                        pq.remove();
                        pq.add(p);
                    }
                }
            }
            return pq.stream()
                    .map(p -> new int[]{p.x, p.y})
                    .toArray(int[][]::new);
        }

        record Point(int x, int y) {
            int distanceFromOrigin() {
                return x*x + y*y;
            }
        }
    }
}
