package com.hui.Tree;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/20 17:42
 *
 *938. Range Sum of BST
 * DescriptionHintsSubmissionsDiscussSolution
 * Given the root node of a binary search tree, return the sum of values of all nodes with value between L and R (inclusive).
 *
 * The binary search tree is guaranteed to have unique values.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
 * Output: 32
 * Example 2:
 *
 * Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
 * Output: 23
 *
 *
 * Note:
 *
 * The number of nodes in the tree is at most 10000.
 * The final answer is guaranteed to be less than 2^31.
 *
 */
public class RangeSumofBST {

    public int rangeSumBST(TreeNode root, int L, int R) {
        int[] sum = new int[1];
        preOrder(root, L, R, sum);
        return sum[0];
    }

    private void preOrder(TreeNode root, int L, int R, int[] sum)
    {
        if(root == null)return;
        if(root.val >= L && root.val <= R)
        {
            sum[0] += root.val;
            preOrder(root.left, L, R, sum);
            preOrder(root.right, L, R, sum);
        }else if(root.val < L)
        {
             preOrder(root.right, L, R, sum);
        }else if(root.val > R)
        {
            preOrder(root.left, L, R, sum);
        }
    }


    public int rangeSumBST2(TreeNode root, int L, int R) {
        int[] sum = new int[1];
        preOrder2(root, L, R, sum);
        return sum[0];
    }
    private void preOrder2(TreeNode root, int L, int R, int[] sum)
    {
        if(root == null)return;
        if(root.val >= L && root.val <= R)
        {
            sum[0] += root.val;
        }

        if(root.val > L)
        {
            preOrder2(root.left, L, R, sum);
        }

        if(root.val < R)
        {
            preOrder2(root.right, L, R, sum);
        }
    }


    public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
    }


}
