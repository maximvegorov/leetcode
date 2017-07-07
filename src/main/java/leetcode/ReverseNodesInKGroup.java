package leetcode;

import leetcode.common.ListNode;

public final class ReverseNodesInKGroup {
    public static void main(String[] args) {
    }

    public static class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode newHead = new ListNode(0);
            ListNode last = newHead;
            while (head != null) {
                ListNode headOfKGroup = head;
                ListNode cur = null;
                int count = 0;
                while (count < k && head != null) {
                    ListNode t = head.next;
                    head.next = cur;
                    cur = head;
                    head = t;
                    count++;
                }
                if (count >= k) {
                    last.next = cur;
                    last = headOfKGroup;
                } else {
                    head = cur;
                    cur = null;
                    while (head != null) {
                        ListNode t = head.next;
                        head.next = cur;
                        cur = head;
                        head = t;
                    }
                    last.next = cur;
                }
            }
            return newHead.next;
        }
    }
}
