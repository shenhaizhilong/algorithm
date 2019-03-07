package com.hui.algorithm;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/28 18:08
 *
 *919. Complete Binary Tree Inserter
 * DescriptionHintsSubmissionsDiscussSolution
 * A complete binary tree is a binary tree in which every level, except possibly the last, is completely filled, and all nodes are as far left as possible.
 *
 * Write a data structure CBTInserter that is initialized with a complete binary tree and supports the following operations:
 *
 * CBTInserter(TreeNode root) initializes the data structure on a given tree with head node root;
 * CBTInserter.insert(int v) will insert a TreeNode into the tree with value node.val = v so that the tree remains complete, and returns the value of the parent of the inserted TreeNode;
 * CBTInserter.get_root() will return the head node of the tree.
 *
 *
 * Example 1:
 *
 * Input: inputs = ["CBTInserter","insert","get_root"], inputs = [[[1]],[2],[]]
 * Output: [null,1,[1,2]]
 * Example 2:
 *
 * Input: inputs = ["CBTInserter","insert","insert","get_root"], inputs = [[[1,2,3,4,5,6]],[7],[8],[]]
 * Output: [null,3,4,[1,2,3,4,5,6,7,8]]
 *
 *
 * Note:
 *
 * The initial given tree is complete and contains between 1 and 1000 nodes.
 * CBTInserter.insert is called at most 10000 times per test case.
 * Every value of a given or inserted node is between 0 and 5000.
 *
 */


class CBTInserter {

    Queue<TreeNode> queue;
    private TreeNode root;
    public CBTInserter(TreeNode root) {
        this.root = root;
        queue = new LinkedList<>();
        queue.add(root);
        while (queue.peek().left != null && queue.peek().right != null)
        {
            queue.offer(queue.peek().left);
            queue.offer(queue.poll().right);
        }
    }

    public int insert(int v) {
        TreeNode head = queue.peek();
        if(head.left == null)
        {
            head.left = new TreeNode(v);
        }else {
            head.right = new TreeNode(v);
            queue.offer(head.left);
            queue.offer(queue.poll().right);
        }

        return head.val;
    }

    public TreeNode get_root() {
        return root;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}


