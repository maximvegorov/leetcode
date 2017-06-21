package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class FindLargestValueInEachTreeRow {
    public static void main(String[] args) {

    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static class Solution {
        public List<Integer> largestValues(TreeNode root) {
            if (root == null)
                return Collections.emptyList();
            List<Integer> result = new ArrayList<>();
            TreeNode endOfRow = new TreeNode(0);
            Queue<TreeNode> pending = new ArrayDeque<>(Arrays.asList(root, endOfRow));
            int max = Integer.MIN_VALUE;
            while (!pending.isEmpty()) {
                TreeNode cur = pending.remove();
                if (cur == endOfRow) {
                    if (!pending.isEmpty())
                        pending.add(endOfRow);
                    result.add(max);
                    max = Integer.MIN_VALUE;
                }
                else {
                    max = Math.max(cur.val, max);
                    if (cur.left != null)
                        pending.add(cur.left);
                    if (cur.right != null)
                        pending.add(cur.right);
                }
            }
            return result;
        }
    }
}
