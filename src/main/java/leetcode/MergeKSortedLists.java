package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import leetcode.common.ListNode;

public final class MergeKSortedLists {
    public static void main(String[] args) {
        ListNode res = new Solution().mergeKLists(new ListNode[0]);
        while (res != null) {
            System.out.print(res.val);
            System.out.print(' ');
            res = res.next;
        }
    }

    public static final class Solution {
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists.length == 0) {
                return null;
            }

            List<ListNode> l = new ArrayList<>(lists.length * 25);
            for (ListNode list : lists) {
                if (list == null) {
                    continue;
                }
                ListNode cur = list;
                while (cur != null) {
                    l.add(cur);
                    cur = cur.next;
                }
            }

            if (l.isEmpty()) {
                return null;
            }

            Collections.sort(l, Comparator.comparing(n -> n.val));

            ListNode head = l.get(0);
            ListNode cur = head;
            for (int i = 1; i < l.size(); i++) {
                cur.next = l.get(i);
                cur = cur.next;
            }
            cur.next = null;

            return head;
        }
    }

}
