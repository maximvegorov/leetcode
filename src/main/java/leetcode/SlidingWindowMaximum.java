package leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public final class SlidingWindowMaximum {
    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(
                        new Solution().maxSlidingWindow(
                                new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
    }

    public static class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums.length == 0 || k == 0) {
                return new int[0];
            }

            int[] result = new int[nums.length - k + 1];

            SlideMaxQueue queue = new SlideMaxQueue(k);
            for (int i = 0; i < k - 1; i++) {
                queue.enqueue(nums[i]);
            }
            int j = 0;
            for (int i = k - 1; i < nums.length; i++) {
                queue.enqueue(nums[i]);
                result[j++] = queue.dequeueMax();
            }
            return result;
        }

        private static final class SlideMaxQueue {
            private final StackWithMax enqueued;
            private final StackWithMax dequeued;

            private SlideMaxQueue(int size) {
                this.enqueued = new StackWithMax(size);
                this.dequeued = new StackWithMax(size);
            }

            void enqueue(int value) {
                enqueued.push(value);
            }

            int dequeueMax() {
                if (dequeued.isEmpty()) {
                    while (!enqueued.isEmpty()) {
                        dequeued.push(enqueued.pop());
                    }
                }
                int result = dequeued.popMax();
                if (!enqueued.isEmpty()) {
                    result = Math.max(result, enqueued.peekMax());
                }
                return result;
            }

            private static final class StackWithMax {
                private final Deque<StackWithMaxItem> stack;

                StackWithMax(int size) {
                    stack = new ArrayDeque<>(size);
                }

                boolean isEmpty() {
                    return stack.isEmpty();
                }

                void push(int value) {
                    stack.addLast(
                            !stack.isEmpty()
                                    ? new StackWithMaxItem(value, Math.max(value, stack.peekLast().getMax()))
                                    : new StackWithMaxItem(value, value));
                }

                int pop() {
                    return stack.removeLast().getValue();
                }

                int popMax() {
                    return stack.removeLast().getMax();
                }

                int peekMax() {
                    return stack.peekLast().getMax();
                }

                private static final class StackWithMaxItem {
                    private final int value;
                    private final int max;

                    StackWithMaxItem(int value, int max) {
                        this.value = value;
                        this.max = max;
                    }

                    int getValue() {
                        return value;
                    }

                    int getMax() {
                        return max;
                    }
                }
            }
        }
    }
}
