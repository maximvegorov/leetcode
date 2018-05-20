package leetcode;

import leetcode.common.TreeNode;

import java.util.*;

public class BinaryTreePostorderTraversal {
    public static void main(String[] args) {
        System.out.println(
                new Solution()
                        .postorderTraversal(
                                new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null))));
    }

    static class Solution {
        private static boolean isRightNullOrEqualTo(TreeNode current, TreeNode prevCurrent) {
            return current.right == null || current.right == prevCurrent;
        }

        public List<Integer> postorderTraversal(TreeNode root) {
            if (root == null)
                return Collections.emptyList();
            List<Integer> result = new ArrayList<>();
            Deque<TreeNode> unprocessedParents = new ArrayDeque<>(Collections.singletonList(root));
            outer: for (; ; ) {
                TreeNode current = unprocessedParents.peekLast();
                if (current.left == null) {
                    if (current.right != null) {
                        unprocessedParents.addLast(current.right);
                    } else {
                        TreeNode prevCurrent;
                        do {
                            prevCurrent = unprocessedParents.removeLast();
                            result.add(prevCurrent.val);
                            if (unprocessedParents.isEmpty())
                                break outer;
                            current = unprocessedParents.peekLast();
                        }
                        while (isRightNullOrEqualTo(current, prevCurrent));
                        unprocessedParents.addLast(current.right);
                    }
                } else {
                    unprocessedParents.addLast(current.left);
                }
            }
            return result;
        }
    }
}
