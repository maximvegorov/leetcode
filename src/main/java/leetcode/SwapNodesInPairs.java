package leetcode;

import leetcode.common.ListNode;

public final class SwapNodesInPairs {
    public static void main(String[] args) {
        ListNode.print(new Solution().swapPairs(ListNode.build(1, 2, 3)));
    }

    public static final class Solution {
        public ListNode swapPairs(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode newHead = new ListNode(0);
            ListNode curTail = newHead;
            while (head != null) {
                ListNode n1 = head;
                ListNode n2 = head.next;
                if (n2 == null) {
                    curTail.next = n1;
                    curTail = n1;
                    break;
                }
                head = n2.next;
                n2.next = n1;
                curTail.next = n2;
                curTail = n1;
            }
            curTail.next = null;
            return newHead.next;
        }
    }
}
