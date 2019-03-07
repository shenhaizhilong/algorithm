package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/4 0:42
 *
 *
 */
public class FlipEquivalentBinaryTrees {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if(root1 == null)return root2 == null;
        if(root2 == null)return root1 == null;
        if(root1.val != root2.val)return false;
        // left
        if(!(flipEquiv(root1.left, root2.right) || flipEquiv(root1.left, root2.left)))return false;
        // right
        return flipEquiv(root1.right, root2.left) || flipEquiv(root1.right,root2.right);
    }


 public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }

}
