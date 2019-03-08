package com.hui.Tree;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/29 19:02
 *
 *Given a binary tree, write a program to count the number of Single Valued Subtrees. A Single Valued Subtree is one in which all the nodes have same value. Expected time complexity is O(n).
 *
 * Example:
 *
 * Input: root of below tree
 *               5
 *              / \
 *             1   5
 *            / \   \
 *           5   5   5
 * Output: 4
 * There are 4 subtrees with single values.
 *
 *
 * Input: root of below tree
 *               5
 *              / \
 *             4   5
 *            / \   \
 *           4   4   5
 * Output: 5
 * There are five subtrees with single values.
 *
 */
public class CountUnivalueSubtrees {

    public int countUnivalSubtrees(TreeNode root) {
        int[] ans = {0};
        isSingleSubtree(root, ans);
        return ans[0];
    }

    private boolean isSingleSubtree(TreeNode root, int[] ans)
    {
        if(root == null)return true;
        boolean left = isSingleSubtree(root.left, ans);
        boolean right = isSingleSubtree(root.right, ans);
        if(!left || !right)return false;
        if(root.left != null && root.left.val != root.val)return false;
        if(root.right != null && root.right.val != root.val)return false;
        ans[0]++;
        return true;
    }

    private static class TreeNode
    {
        TreeNode left;
        TreeNode right;
        int val;
        public TreeNode(int val)
        {
            this.val = val;
        }
    }

    public static void main(String[] args) {


        CountUnivalueSubtrees tree = new CountUnivalueSubtrees();
        TreeNode root = new TreeNode(5);

        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(5);

        System.out.println("The count of single valued sub trees is : "
                + tree.countUnivalSubtrees(root));
    }

}
