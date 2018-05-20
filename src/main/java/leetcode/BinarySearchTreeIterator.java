package leetcode;

import leetcode.common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class BinarySearchTreeIterator {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2, new TreeNode(1), null);
        BSTIterator iterator = new BSTIterator(root);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public static class BSTIterator {
        private final Deque<TreeNode> stack = new ArrayDeque<>();
        private TreeNode current;

        public BSTIterator(TreeNode root) {
            this.current = root;
        }

        public boolean hasNext() {
            return !stack.isEmpty() || current != null;
        }

        public int next() {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            TreeNode result = stack.pop();
            current = result.right;
            return result.val;
        }
    }
}
