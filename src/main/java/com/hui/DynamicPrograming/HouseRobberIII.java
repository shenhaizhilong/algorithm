package com.hui.DynamicPrograming;

/**
 *
 *
 * 337. House Robber III
 * DescriptionHintsSubmissionsDiscussSolution
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.
 *
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 *
 * Example 1:
 *
 * Input: [3,2,3,null,3,null,1]
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 *
 * Output: 7
 * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * Example 2:
 *
 * Input: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 *
 * Output: 9
 * Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 * @author: shenhaizhilong
 * @date: 2018/8/24 18:27
 */
public class HouseRobberIII {
    public int rob(TreeNode root) {
        int[] maxSum = dp(root);
        return Math.max(maxSum[0], maxSum[1]);
    }

    private int[] dp(TreeNode root)
    {
        if(root == null)
            return new int[]{0,0};

        //抢劫或者不抢劫 左孩子节点获取的最大值
        int[] left = dp(root.left);
        //抢劫或者不抢劫 右孩子节点获取的最大值
        int[] right = dp(root.right);
        //抢劫或者不抢劫 当前节点获取的最大值
        int[] max = new int[]{0,0};
        //抢劫当前节点,所获取的最大值,左右节点只能是不抢劫时所获取的最大值
        max[0] = left[1] + right[1] + root.val;
        //不抢劫当前节点所获取的最大值，左右节点可以是抢劫，也可以不抢劫
        max[1] =Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return max;


    }


     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
  }
}
