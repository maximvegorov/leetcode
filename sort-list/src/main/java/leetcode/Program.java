package leetcode;

public class Program {
    public static void main(String[] args) {
        new Solution().sortList(new ListNode(3, new ListNode(2, new ListNode(4, null))));

    }

    public static class Solution {
        private static void quickSort(ListNode from, ListNode to) {
            if (from == to || from.next == to) {
                return;
            }

            int partition = from.val;
            ListNode p = from;
            for (ListNode q = from.next; q != to; q = q.next) {
                if (q.val < partition) {
                    int t = p.val;
                    p.val = q.val;
                    q.val = t;
                    p = p.next;
                }
            }
            quickSort(from, p);
            if (p != from) {
                quickSort(p, to);
            } else {
                quickSort(p.next, to);
            }

        }

        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            quickSort(head, null);
            return head;
        }
    }

    public static class ListNode {
        int val;

        ListNode next;

        ListNode(int x, ListNode next) {
            val = x;
            this.next = next;
        }
    }
}
