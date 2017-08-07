package leetcode;

import leetcode.common.ListNode;

public class LinkedListCycle {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2, head);

        System.out.println(new Solution().hasCycle(head));
    }

    public static final class Solution {
        public boolean hasCycle(ListNode head) {
            if (head == null || head.next == null)
                return false;
            if (head.next == head)
                return true;

            ListNode fakeHead = new ListNode(0);

            ListNode p = fakeHead;
            ListNode q = head;
            while (q != null && q != fakeHead) {
                ListNode next = q.next;
                q.next = p;
                p = q;
                q = next;
            }

            boolean result = q == fakeHead;

            while (p != fakeHead) {
                ListNode next = p.next;
                p.next = q;
                q = p;
                p = next;
            }

            return result;
        }
    }
}
