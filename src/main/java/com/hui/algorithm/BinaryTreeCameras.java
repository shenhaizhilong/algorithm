package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/30 16:20
 *
 *968. Binary Tree Cameras
 * Hard
 *
 * 14
 *
 * 0
 *
 * Favorite
 *
 * Share
 * Given a binary tree, we install cameras on the nodes of the tree.
 *
 * Each camera at a node can monitor its parent, itself, and its immediate children.
 *
 * Calculate the minimum number of cameras needed to monitor all nodes of the tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: [0,0,null,0,0]
 * Output: 1
 * Explanation: One camera is enough to monitor all nodes if placed as shown.
 * Example 2:
 *
 *
 * Input: [0,0,null,0,null,0,null,null,0]
 * Output: 2
 * Explanation: At least two cameras are needed to monitor all nodes of the tree. The above image shows one of the valid configurations of camera placement.
 *
 * Note:
 *
 * The number of nodes in the given tree will be in the range [1, 1000].
 * Every node has value 0.
 *
 * states:
 * leaf node => 0
 * it has at least one child and  have a camera in this node  => 1
 * null nodes or without a camera, but it's covered by left node or right node => 2
 *
 */
public class BinaryTreeCameras {

    public int minCameraCover(TreeNode root) {
        if(root == null)return 0;
        int[] res = {0};
        int ans = dfs(root,res );
        //at last, if root node not covered, we need a camera at the root node
        return (ans < 1 ? 1: 0) + res[0];
    }

    private int dfs(TreeNode root, int[] ans)
    {
        // null node or covered without a camera we see it's state to 2
       int left = root.left == null ? 2: dfs(root.left, ans);
       int right = root.right == null ?2:dfs(root.right, ans);

       // at least have one leaf,
       if(left == 0 || right == 0)
       {
           ans[0]++;
           return 1;
       }

       // curr node was covered by children
       if(left == 1 || right == 1)return 2;
       return 0;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
