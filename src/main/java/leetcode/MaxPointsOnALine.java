package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class MaxPointsOnALine {
    public static void main(String[] args) {
        System.out.println(
                new Solution()
                        .maxPoints(
                                new Point[]{
                                        new Point(0, -12),
                                        new Point(5, 2),
                                        new Point(2, 5),
                                        new Point(0, -5),
                                        new Point(1, 5),
                                        new Point(2, -2),
                                        new Point(5, -4),
                                        new Point(3, 4),
                                        new Point(-2, 4),
                                        new Point(-1, 4),
                                        new Point(0, -5),
                                        new Point(0, -8),
                                        new Point(-2, -1),
                                        new Point(0, -11),
                                        new Point(0, -9)}));
    }

    public static class Solution {
        int maxPoints(Point[] points) {
            if (points.length == 0) {
                return 0;
            }
            if (points.length <= 2) {
                return points.length;
            }

            Map<Line, Set<Point>> lines = new HashMap<>();
            for (int i = 0; i < points.length; i++) {
                for (int j = i + 1; j < points.length; j++) {
                    Set<Point> onLinePoints = lines.computeIfAbsent(
                            Line.from(points[i], points[j]),
                            k -> new HashSet<>());
                    onLinePoints.add(points[i]);
                    onLinePoints.add(points[j]);
                }
            }

            return lines.entrySet()
                    .stream()
                    .mapToInt(e -> e.getValue().size())
                    .max()
                    .orElseThrow(NoSuchElementException::new);
        }

        static class Line {
            private final int a;
            private final int b;
            private final int c;

            private Line(int a, int b, int c) {
                int k = gcd(a, gcd(b, c));
                this.a = a / k;
                this.b = b / k;
                this.c = c / k;
            }

            static Line from(Point p1, Point p2) {
                int dx = p1.x - p2.x;
                int dy = p1.y - p2.y;

                if (dx < 0) {
                    dx = -dx;
                    dy = -dy;
                }

                if (dx == 0) {
                    return new Line(1, 0, -p1.x);
                } else {
                    return new Line(dy, -dx, dx * p1.y - dy * p1.x);
                }
            }

            private int gcd(int x, int y) {
                if (x < 0) {
                    x = -x;
                }
                if (y < 0) {
                    y = -y;
                }

                while (x != 0 && y != 0) {
                    if (x > y) {
                        x = x % y;
                    } else {
                        y = y % x;
                    }
                }

                return Math.max(1, Math.max(x, y));
            }

            @Override
            public String toString() {
                return "Line{" +
                        "a=" + a +
                        ", b=" + b +
                        ", c=" + c +
                        '}';
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (o == null || getClass() != o.getClass()) {
                    return false;
                }

                Line line = (Line) o;

                return a == line.a && b == line.b && c == line.c;
            }

            @Override
            public int hashCode() {
                int result = a;
                result = 31 * result + b;
                result = 31 * result + c;
                return result;
            }
        }
    }

    static class Point {
        int x;
        int y;

        Point(int a, int b) {
            x = a;
            y = b;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
