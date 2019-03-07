package com.hui.algorithm;

import java.util.*;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/26 13:57
 *
 *958. Check Completeness of a Binary Tree
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a binary tree, determine if it is a complete binary tree.
 *
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: [1,2,3,4,5,6]
 * Output: true
 * Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.
 * Example 2:
 *
 *
 *
 * Input: [1,2,3,4,5,null,7]
 * Output: false
 * Explanation: The node with value 7 isn't as far left as possible.
 *
 * Note:
 *
 * The tree will have between 1 and 100 nodes.
 *
 */
public class CheckCompletenessofaBinaryTree {

    //https://leetcode.com/problems/check-completeness-of-a-binary-tree/discuss/205682/JavaC++Python-BFS-Level-Order-Traversal
    //Use BFS to do a level order traversal,
    //add childrens to the bfs queue,
    //until we met the first empty node.
    //
    //For a complete binary tree,
    //there should not be any node after we met an empty one.
    public boolean isCompleteTree(TreeNode root) {
        if(root == null)return true;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        while (queue.peekFirst() != null)
        {

            TreeNode curr = queue.pollFirst();
            // Optimization
            if(curr.left == null && curr.right != null)return false;
            queue.addLast(curr.left);
            queue.addLast(curr.right);
        }

        while (!queue.isEmpty() && queue.peekFirst() == null)
        {
            queue.pollFirst();
        }

        return queue.isEmpty();

    }

   public class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
   }

}
