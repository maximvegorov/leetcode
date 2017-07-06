package leetcode.common;

public final class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
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

    public static void print(ListNode head) {
        for (ListNode node = head; node != null; node = node.next) {
            System.out.println(node.val);
        }
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                '}';
    }
}
