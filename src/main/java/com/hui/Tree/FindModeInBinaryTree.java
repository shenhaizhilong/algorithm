package com.hui.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *https://leetcode.com/problems/find-mode-in-binary-search-tree/description/
 * 501. Find Mode in Binary Search Tree
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.
 *
 * Assume a BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 * The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 *
 * For example:
 * Given BST [1,null,2,2],
 *
 *    1
 *     \
 *      2
 *     /
 *    2
 *
 *
 * return [2].
 *
 * Note: If a tree has more than one mode, you can return them in any order.
 *
 * Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
 *
 * @author: shenhaizhilong
 * @date: 2018/8/22 15:26
 */
public class FindModeInBinaryTree {
    private int maxCount = 0;
    private int current = Integer.MIN_VALUE;
    private int currentCount = 0;

    List<Integer> list = new ArrayList<>();
    public int[] findMode(TreeNode root) {
        visitNode(root);
        int[] results = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            results[i] = list.get(i);
        }

        return results;

    }

    private void visitNode(TreeNode root)
    {
        if(root == null)return;
        visitNode(root.left);
        currentCount++;

        // first time visit this node.
        if(root.val != current)
        {
            currentCount = 1;
            current = root.val;
        }
        if(currentCount > maxCount)
        {
            maxCount = currentCount;
            list.clear();
            list.add(current);
        }else if(currentCount == maxCount)
        {
            list.add(current);
        }

        visitNode(root.right);


    }


    /**
     *
     * 超过百分百提交的一位大神StefanPochmann的代码，两次中序遍历，
     * 第一次得到modeCount 众数的数量和最大频率数maxCount，
     * 第二次当出现的频率数等于最大频率数时对数组赋值
     *
     * https://leetcode.com/problems/find-mode-in-binary-search-tree/discuss/98101/Proper-O(1)-space
     *
     * @param root
     * @return
     */

    public int[] findMode2(TreeNode root) {
        inorder(root);
        modes2 = new int[modeCount2];
        modeCount2 = 0;
        currCount2 = 0;
        inorder(root);
        return modes2;
    }

    private int currVal2;
    private int currCount2 = 0;
    private int maxCount2 = 0;
    private int modeCount2 = 0;

    private int[] modes2;

    private void handleValue(int val) {
        if (val != currVal2) {
            currVal2 = val;
            currCount2 = 0;
        }
        currCount2++;
        if (currCount2 > maxCount2) {
            maxCount2 = currCount2;
            modeCount2 = 1;
        } else if (currCount2 == maxCount2) {
            if (modes2 != null)
                modes2[modeCount2] = currVal2;
            modeCount2++;
        }
    }

    private void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        handleValue(root.val);
        inorder(root.right);
    }

   private class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }
}
