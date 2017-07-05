package leetcode;

public final class SwapNodesInPairs {
    public static void main(String[] args) {
        new Solution().swapPairs(ListNode.build(1, 2, 3)).print();
    }

    public static final class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        public static ListNode build(int... values) {
            if (values.length == 0) {
                return null;
            }
            ListNode root = new ListNode(values[0]);
            ListNode tail = root;
            for (int i = 1; i < values.length; i++) {
                ListNode node = new ListNode(values[i]);
                tail.next = node;
                tail = node;
            }
            return root;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    '}';
        }

        public void print() {
            for (ListNode node = this; node != null; node = node.next) {
                System.out.println(node.val);
            }
        }
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
