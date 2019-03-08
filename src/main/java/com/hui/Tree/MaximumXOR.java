package com.hui.Tree;

/**
 *https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/description/
 *
 * 421. Maximum XOR of Two Numbers in an Array
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.
 *
 * Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.
 *
 * Could you do this in O(n) runtime?
 *
 * Example:
 *
 * Input: [3, 10, 5, 25, 2, 8]
 *
 * Output: 28
 *
 * Explanation: The maximum result is 5 ^ 25 = 28.
 *
 *https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/discuss/130427/()-Beats-92/168323?page=1
 * 思路类似上面的帖子，不过做了一些改动
 *https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/discuss/91059/Java-O(n)-solution-using-Trie
 *
 * @author: shenhaizhilong
 * @date: 2018/8/24 12:45
 */
public class MaximumXOR {
     TreeNode root = new TreeNode(0);

    public int findMaximumXOR(int[] nums) {
        int max = findMax(nums);
        int maxBit = 32 - Integer.numberOfLeadingZeros(max);
        //build prefix tree
        TreeNode currNode = root;
        for (int i = 0; i < nums.length; i++) {
            for (int j = maxBit -1; j >=0 ; j--) {
                int tmp = (nums[i] >>j)& 1;
                if(tmp == 0)
                {
                    if(currNode.left == null)
                    {
                        currNode.left = new TreeNode(0);
                    }
                    currNode = currNode.left;
                }else {
                    if(currNode.right == null)
                    {
                        currNode.right = new TreeNode(1);
                    }
                    currNode = currNode.right;
                }
            }
            // finished for nums[i], will do it for nums[i++];
            currNode = root;
        }

        //find max xor value
        int maxXOR = 0;

        for (int i = 0; i < nums.length; i++) {
            int res = 0;
            for (int j = maxBit -1; j >=0; j--) {
                int tmp = (nums[i] >>j)& 1;
                if(currNode.left != null && currNode.right != null)
                {
                    if(tmp == 0)
                    {
                        currNode = currNode.right;
                    }else {
                        currNode = currNode.left;
                    }
                }else {
                    currNode = currNode.left == null ? currNode.right : currNode.left;
                }

                res += (tmp^currNode.val) << j;
            }
            currNode = root;
            maxXOR = maxXOR > res ? maxXOR : res;
        }
        return maxXOR;
    }
    private int findMax(int[] nums)
    {
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] > max)
            {
                max = nums[i];
            }
        }
        return max;
    }
    private static class TreeNode{
        int val;
        TreeNode left,right;
        TreeNode(int val)
        {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        MaximumXOR maximumXOR = new MaximumXOR();
        int[] a = {3,10,5,25,2,8};
        System.out.println(maximumXOR.findMaximumXOR(a));
    }
}
