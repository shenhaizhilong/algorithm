package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/10 8:58
 */
public class TreeLink {


    /**
     *
     * 116. Populating Next Right Pointers in Each Node
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a binary tree
     *
     * struct TreeLinkNode {
     *   TreeLinkNode *left;
     *   TreeLinkNode *right;
     *   TreeLinkNode *next;
     * }
     * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
     *
     * Initially, all next pointers are set to NULL.
     *
     * Note:
     *
     * You may only use constant extra space.
     * Recursive approach is fine, implicit stack space does not count as extra space for this problem.
     * You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
     * Example:
     *
     * Given the following perfect binary tree,
     *
     *      1
     *    /  \
     *   2    3
     *  / \  / \
     * 4  5  6  7
     * After calling your function, the tree should look like:
     *
     *      1 -> NULL
     *    /  \
     *   2 -> 3 -> NULL
     *  / \  / \
     * 4->5->6->7 -> NULL
     *
     * @param root
     */
    public void connectRecursive(TreeLinkNode root) {
        if(root == null)return;
        if(root.left != null)
            root.left.next = root.right;
        if(root.right != null)
        {
            root.right.next = (root.next == null) ? null : root.next.left;
        }

        connectRecursive(root.left);
        connectRecursive(root.right);
    }

    public void connect(TreeLinkNode root) {
        if(root == null)return;
        TreeLinkNode pre = root;
        TreeLinkNode curr = null;
        while (pre != null)
        {
            curr = pre;
            while (curr != null)
            {
                if(curr.left != null)
                {
                    curr.left.next = curr.right;
                }
                if(curr.right != null && curr.next != null)
                {
                    curr.right.next = curr.next.left;
                }
                curr = curr.next;  // sibling node
            }
            pre = pre.left;  // next level
        }

    }





     // Definition for binary tree with next pointer.
    private class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;
        TreeLinkNode(int x) { val = x; }
    }


}
