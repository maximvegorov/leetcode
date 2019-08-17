package leetcode;

import leetcode.common.TreeNode;

import java.util.*;

public class BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        List<List<Integer>> result = new ArrayList<>();

        TreeNode newLevel = new TreeNode(0);
        Deque<TreeNode> curLevelUnprocessed = new ArrayDeque<>(Arrays.asList(root, newLevel));
        Deque<TreeNode> nextLevelUnprocessed = new ArrayDeque<>();
        List<Integer> currentLevel = new ArrayList<>();
        boolean isReverseLevel = false;
        while (!curLevelUnprocessed.isEmpty()) {
            TreeNode current = curLevelUnprocessed.removeFirst();
            if (current == newLevel) {
                result.add(currentLevel);
                if (nextLevelUnprocessed.isEmpty()) {
                    break;
                }
                nextLevelUnprocessed.addLast(newLevel);
                Deque<TreeNode> temp = curLevelUnprocessed;
                curLevelUnprocessed = nextLevelUnprocessed;
                nextLevelUnprocessed = temp;
                currentLevel = new ArrayList<>();
                isReverseLevel = !isReverseLevel;
            }
            else {
                currentLevel.add(current.val);
                if (isReverseLevel) {
                    if (current.right != null) {
                        nextLevelUnprocessed.addFirst(current.right);
                    }
                    if (current.left != null) {
                        nextLevelUnprocessed.addFirst(current.left);
                    }
                }
                else {
                    if (current.left != null) {
                        nextLevelUnprocessed.addLast(current.left);
                    }
                    if (current.right != null) {
                        nextLevelUnprocessed.addLast(current.right);
                    }
                }
            }
        }

        return result;
    }
}
