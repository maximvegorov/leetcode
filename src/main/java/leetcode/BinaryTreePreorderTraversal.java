package leetcode;

import leetcode.common.TreeNode;

import java.util.*;

public class BinaryTreePreorderTraversal {
    public static void main(String[] args) {
        System.out.println(
                new Solution()
                        .preorderTraversal(
                                new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null))));
    }

    static class Solution {
        public List<Integer> preorderTraversal(TreeNode root) {
            if (root == null)
                return Collections.emptyList();
            List<Integer> result = new ArrayList<>();
            Deque<TreeNode> parents = new ArrayDeque<>();
            TreeNode current = root;
            for (; ; ) {
                if (current == null) {
                    while (!parents.isEmpty() && current == null) {
                        TreeNode parent = parents.removeLast();
                        current = parent.right;
                    }
                    if (current == null)
                        break;
                }
                result.add(current.val);
                parents.addLast(current);
                current = current.left;
            }
            return result;
        }
    }
}
