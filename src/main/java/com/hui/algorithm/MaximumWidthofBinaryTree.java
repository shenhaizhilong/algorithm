package com.hui.algorithm;

import java.util.HashMap;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/24 12:17
 *
 * 662. Maximum Width of Binary Tree
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.
 *
 * The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.
 *
 * Example 1:
 * Input:
 *
 *            1
 *          /   \
 *         3     2
 *        / \     \
 *       5   3     9
 *
 * Output: 4
 * Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
 * Example 2:
 * Input:
 *
 *           1
 *          /
 *         3
 *        / \
 *       5   3
 *
 * Output: 2
 * Explanation: The maximum width existing in the third level with the length 2 (5,3).
 * Example 3:
 * Input:
 *
 *           1
 *          / \
 *         3   2
 *        /
 *       5
 *
 * Output: 2
 * Explanation: The maximum width existing in the second level with the length 2 (3,2).
 * Example 4:
 * Input:
 *
 *           1
 *          / \
 *         3   2
 *        /     \
 *       5       9
 *      /         \
 *     6           7
 * Output: 8
 * Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).
 *
 *
 * Note: Answer will in the range of 32-bit signed integer.
 *
 *
 */
public class MaximumWidthofBinaryTree {

    public int widthOfBinaryTree(TreeNode root) {
        int[] maxWidth = new int[1];
        dfs(root, 0 ,0, new HashMap(), maxWidth);
        return maxWidth[0];
    }

    private void dfs(TreeNode root, int depth, int idx, HashMap<Integer,Integer> leftMost, int[] maxWidth)
    {
        if(root == null)return;
        if(!leftMost.containsKey(depth))
        {
            leftMost.put(depth, idx);
        }
        // recording the left most layer's index
        maxWidth[0] = Math.max(maxWidth[0], idx - leftMost.get(depth) +1);
        // the index of binary heap, starting from zero
        int leftIdx = (idx << 1) +1;
        int rightIdx = leftIdx +1;
        dfs(root.left, depth +1, leftIdx, leftMost, maxWidth);
        dfs(root.right, depth +1, rightIdx, leftMost, maxWidth);

    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
