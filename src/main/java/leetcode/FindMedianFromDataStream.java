package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class FindMedianFromDataStream {
    public static void main(String[] args) {
        MedianFinder finder = new MedianFinder();
        finder.addNum(-1);
        finder.addNum(-2);
        finder.addNum(-3);
        finder.addNum(-4);
        finder.addNum(-5);
        System.out.println(finder.findMedian());
    }

    static class MedianFinder {
        private PriorityQueue<Integer> left;
        private PriorityQueue<Integer> right;

        public MedianFinder() {
            left = new PriorityQueue<>();
            right = new PriorityQueue<>(Comparator.<Integer>naturalOrder().reversed());
        }

        public void addNum(int num) {
            if (left.isEmpty()) {
                left.add(num);
            } else {
                if (left.peek() <= num) {
                    left.add(num);
                    if (left.size() - right.size() > 1) {
                        right.add(left.remove());
                    }
                } else {
                    right.add(num);
                    if (right.size() > left.size()) {
                        left.add(right.remove());
                    }
                }
            }
        }

        public double findMedian() {
            if (left.size() == right.size()) {
                return (left.peek() + right.peek()) / 2.0;
            } else {
                return left.peek();
            }
        }
    }
}
