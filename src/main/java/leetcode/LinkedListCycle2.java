package leetcode;

import leetcode.common.ListNode;

import java.util.Optional;

public final class LinkedListCycle2 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = head.next;

        System.out.println(
                Optional.ofNullable(new Solution().detectCycle(head))
                        .map(n -> Integer.toString(n.val))
                        .orElse("null"));
    }

    public static final class Solution {
        public ListNode detectCycle(ListNode head) {
            if (head == null || head.next == null)
                return null;
            if (head.next == head)
                return head;

            ListNode fast = head;
            ListNode slow = head;
            do {
                if (fast.next == null || fast.next.next == null)
                    return null;
                fast = fast.next.next;
                slow = slow.next;
            }
            while (fast != slow);

            ListNode h = head;
            for (; ; ) {
                if (h == fast)
                    return h;
                for (ListNode p = fast.next; p != fast; p = p.next) {
                    if (p == h)
                        return h;
                }
                h = h.next;
            }
        }
    }
}
