package com.hui.Tree;

/**
 *
 *
 *654. Maximum Binary Tree
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:
 *
 * The root is the maximum number in the array.
 * The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
 * The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
 * Construct the maximum tree by the given array and output the root node of this tree.
 *
 * Example 1:
 * Input: [3,2,1,6,0,5]
 * Output: return the tree root node representing the following tree:
 *
 *       6
 *     /   \
 *    3     5
 *     \    /
 *      2  0
 *        \
 *         1
 * Note:
 * The size of the given array will be in the range [1,1000].
 * @author: shenhaizhilong
 * @date: 2018/8/31 10:00
 */
public class MaximumBinaryTree {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if(nums == null || nums.length ==0)return null;
        TreeNode root = null;
        return buildTreeHelper(root, nums, 0, nums.length -1);
    }

    private TreeNode buildTreeHelper(TreeNode root, int[] nums, int start, int end)
    {
        if(start <=end)
        {
            int[] val = getIndexAndMax(nums,start, end);
            root = new TreeNode(val[1]);
            TreeNode left = buildTreeHelper(root.left, nums, start, val[0] -1);
            TreeNode right = buildTreeHelper(root.right, nums, val[0] +1, end);
            root.left = left;
            root.right = right;
        }

        return root;

    }

    private int[] getIndexAndMax(int[] nums, int start, int end)
    {
        //这里还可以优化掉，不需要额外的空间
        int[] res = new int[2];
        res[0] = start;
        res[1] = nums[start];
        for (int i = start +1; i <=end ; i++) {
            if(nums[i] > res[1])
            {
                res[1] = nums[i];
                res[0] = i;
            }
        }
        return res;
    }

    private static class TreeNode{
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {

        int[] nums = {3,2,1,6,0,5};
        MaximumBinaryTree tree = new MaximumBinaryTree();
        TreeNode root =  tree.constructMaximumBinaryTree(nums);

    }
}
