package leetcode;

import leetcode.common.ListNode;

public final class RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        ListNode.print(new Solution().removeNthFromEnd(ListNode.build(1), 1));
    }

    public static final class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            if (head == null) {
                return null;
            }
            ListNode newHead = new ListNode(0);
            newHead.next = head;
            ListNode p = newHead;
            ListNode q = head;
            int i = 0;
            while (i < n && q != null) {
                q = q.next;
                i++;
            }
            if (i >= n || q != null) {
                while (q != null) {
                    p = p.next;
                    q = q.next;
                }
                p.next = p.next.next;
            }
            return newHead.next;
        }
    }
}
