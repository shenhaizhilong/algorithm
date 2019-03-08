package com.hui.Tree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/6 18:53
 */
public class FlattenBinaryTreetoLinkedList {

    public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }



    TreeNode curr;

    /**
     *
     * 14. Flatten Binary Tree to Linked List
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a binary tree, flatten it to a linked list in-place.
     *
     * For example, given the following tree:
     *
     *     1
     *    / \
     *   2   5
     *  / \   \
     * 3   4   6
     * The flattened tree should look like:
     *
     * 1
     *  \
     *   2
     *    \
     *     3
     *      \
     *       4
     *        \
     *         5
     *          \
     *           6
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        TreeNode dummyHead = new TreeNode(0);
        curr = dummyHead;
        preOrder(root);
        root = dummyHead.right;
    }

    private void preOrder(TreeNode root )
    {
        if(root == null)return;
        TreeNode left = root.left;
        TreeNode right = root.right;
        curr.right = root;
        curr.left = null;
        curr = curr.right;
        preOrder(left);
        preOrder(right);
    }


    /**
     *
     * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/discuss/36977/My-short-post-order-traversal-Java-solution-for-share
     *
     */
    private TreeNode pre = null;
    private void  flatten2(TreeNode root)
    {
        if(root == null)return;
        flatten2(root.right);
        flatten2(root.left);
        root.right = pre;
        root.left = null;
        pre = root;
    }

    private TreeNode levelOrder(TreeNode root)
    {
        if(root == null)return root;
        TreeNode dummyHead = new TreeNode(0);
        Deque<TreeNode> deque = new ArrayDeque<>();
        TreeNode curr = dummyHead;
        deque.addLast(root);
        while (!deque.isEmpty())
        {
            TreeNode temp = deque.pollFirst();
            if(temp.left != null)
            {
                deque.addLast(temp.left);
            }

            if(temp.right != null)
            {
                deque.addLast(temp.right);
            }
            temp.left = null;
            curr.right = temp;
            curr = curr.right;


        }
        return dummyHead.right;
    }
}
