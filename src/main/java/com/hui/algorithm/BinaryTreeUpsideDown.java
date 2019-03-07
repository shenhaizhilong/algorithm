package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/29 13:19
 *
 *
 * Leetcode 156: Binary Tree Upside Down
 * https://leetcode.com/problems/binary-tree-upside-down/
 *
 * Binary Tree Upside Down
 * Total Accepted: 4861 Total Submissions: 13934 Difficulty: Medium
 * Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.
 *
 * For example:
 * Given a binary tree {1,2,3,4,5},
 *
 *
 *     1
 *    / \
 *   2   3
 *  / \
 * 4   5
 * 1
 * 2
 * 3
 * 4
 * 5
 *     1
 *    / \
 *   2   3
 *  / \
 * 4   5
 * return the root of the binary tree [4,5,2,#,#,3,1].
 *
 *
 *    4
 *   / \
 *  5   2
 *     / \
 *    3   1
 * 1
 * 2
 * 3
 * 4
 * 5
 *    4
 *   / \
 *  5   2
 *     / \
 *    3   1
 *
 */
public class BinaryTreeUpsideDown {

    public TreeNode UpsideDownBinaryTree(TreeNode root) {
        if(root == null)return root;
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;
        root.right = null;

        //  update the left and right children, form the new tree, then update root
        while (left != null)
        {
            TreeNode newLeft = left.left;
            TreeNode newRight = left.right;
            left.right = root;
            left.left = right;
            root = left;
            left = newLeft;
            right = newRight;
        }
        return root;

    }


    public static class TreeNode {

        int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int x) {
            val = x;
        }
    }
}
