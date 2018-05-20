package leetcode;

import leetcode.common.TreeNode;

import java.util.*;

public class BinaryTreeInorderTraversal {
    public static void main(String[] args) {
        System.out.println(
                new Solution()
                        .inorderTraversal(
                                new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null))));
    }

    static class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {
            if (root == null)
                return Collections.emptyList();
            List<Integer> result = new ArrayList<>();
            Deque<TreeNode> parents = new ArrayDeque<>();
            TreeNode current = root;
            for (; ; ) {
                if (current == null) {
                    while (!parents.isEmpty() && current == null) {
                        TreeNode parent = parents.removeLast();
                        result.add(parent.val);
                        current = parent.right;
                    }
                    if (current == null)
                        break;
                }
                parents.addLast(current);
                current = current.left;
            }
            return result;
        }
    }
}
