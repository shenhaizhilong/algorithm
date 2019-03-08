package com.hui.Tree;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/2 13:04
 */
public class IncreasingOrderSearchTree {

   private class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
   }

    public TreeNode increasingBST(TreeNode root) {
        TreeNode dummyRoot = new TreeNode(0);
        curr = dummyRoot;
        inOrder(root);
        return dummyRoot.right;
    }

    TreeNode curr;
   private void inOrder(TreeNode node)
   {
       if(node == null)return;
        inOrder(node.left);
        node.left = null;
        curr.right = node;
        curr = curr.right;
        inOrder(node.right);
   }


}
