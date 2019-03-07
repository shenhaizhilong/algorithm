package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/31 21:24
 */
public class SecondMinimumNode {


    int min1;
    long min2 = Long.MAX_VALUE;
    public int findSecondMinimumValue(TreeNode root) {
        min1 = root.val;
        dfs(root);
        return min2 < Long.MAX_VALUE ? (int)(min2): -1;

    }

    public void dfs(TreeNode root)
    {
        if(root != null)
        {
            if(min1 < root.val && root.val <= min2)
            {
                min2 = root.val;
            }else if(min1 == root.val) {
                dfs(root.left);
                dfs(root.right);
            }
        }
    }

   private static class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
    }
}
