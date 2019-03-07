package com.hui.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 *
 * 173. Binary Search Tree Iterator
 * DescriptionHintsSubmissionsDiscussSolution
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 *
 * Calling next() will return the next smallest number in the BST.
 *
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 *
 * Credits:
 * Special thanks to @ts for adding this problem and creating all test cases.
 *
 * @author: shenhaizhilong
 * @date: 2018/9/7 19:08
 */
public class BinarySearchTreeIterator {

        Deque<TreeNode> stack ;
        public BinarySearchTreeIterator(TreeNode root) {
            stack = new ArrayDeque<>();
            addAll(root);
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /** @return the next smallest number */
        public int next() {
            TreeNode curr = stack.pollFirst();
            addAll(curr.right);
            return curr.val;
        }

        private void addAll(TreeNode curr)
        {
            while (curr != null)
            {
                stack.addFirst(curr);
                curr = curr.left;
            }
        }

        private class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            TreeNode(int x) {
                val = x;
            }
        }




}
