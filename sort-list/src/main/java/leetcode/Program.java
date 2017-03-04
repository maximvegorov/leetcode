package leetcode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Program {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(
                new File("input.txt").toPath());
        new Solution().sortList(
                toLinkedList(
                        Arrays.stream(lines.get(0).split(","))
                                .mapToInt(Integer::parseInt)
                                .toArray()));
    }

    private static ListNode toLinkedList(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        ListNode root = new ListNode(nums[0]);
        ListNode p = root;
        for (int i = 1; i < nums.length; i++) {
            p.next = new ListNode(nums[i]);
            p = p.next;
        }
        return root;
    }

    public static class Solution {
        private static final int SIZE = 5000;

        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }

            ListNode headOfSorted = new ListNode(Integer.MIN_VALUE);
            ListNode headOfUnsorted = head;

            List<ListNode> nodes = new ArrayList<>(SIZE);

            while (headOfUnsorted != null) {
                nodes.clear();

                for (int i = 0; i < SIZE && headOfUnsorted != null; i++) {
                    nodes.add(headOfUnsorted);
                    headOfUnsorted = headOfUnsorted.next;
                }
                nodes.sort(Comparator.comparingInt(n -> n.val));

                ListNode newSortedBatch = nodes.get(0);
                for (int i = 0; i < nodes.size() - 1; i++) {
                    nodes.get(i).next = nodes.get(i + 1);
                }
                nodes.get(nodes.size() - 1).next = null;

                if (headOfSorted.next == null) {
                    headOfSorted.next = newSortedBatch;
                } else {
                    ListNode p = headOfSorted;
                    ListNode q = newSortedBatch;
                    while (p.next != null && q != null) {
                        if (p.next.val > q.val) {
                            ListNode t = q.next;
                            q.next = p.next;
                            p.next = q;
                            p = q;
                            q = t;
                        }
                        else {
                            p = p.next;
                        }
                    }
                    if (p.next == null) {
                        p.next = q;
                    }
                }
            }

            return headOfSorted.next;
        }
    }

    public static class ListNode {
        int val;

        ListNode next;

        ListNode(int x) {
            val = x;
        }

        ListNode(int x, ListNode next) {
            val = x;
            this.next = next;
        }
    }
}
