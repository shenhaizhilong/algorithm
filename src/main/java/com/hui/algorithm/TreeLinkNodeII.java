package com.hui.algorithm;

/**
 *
 * 117. Populating Next Right Pointers in Each Node II
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
 * Example:
 *
 * Given the following binary tree,
 *
 *      1
 *    /  \
 *   2    3
 *  / \    \
 * 4   5    7
 * After calling your function, the tree should look like:
 *
 *      1 -> NULL
 *    /  \
 *   2 -> 3 -> NULL
 *  / \    \
 * 4-> 5 -> 7 -> NULL
 *
 * @author: shenhaizhilong
 * @date: 2018/9/10 9:56
 */
public class TreeLinkNodeII {


    public void connect(TreeLinkNode root) {

        if(root == null)return;
        if(root.left != null)
        {
            if(root.right != null)
            {
                root.left.next = root.right;
                root.right.next = findNext(root);
            }
            else {
                root.left.next = findNext(root);
            }

        }else if (root.right != null)
        {
            root.right.next = findNext(root);
        }
        connect(root.right);
        connect(root.left);

    }

    private TreeLinkNode findNext(TreeLinkNode root)
    {
        TreeLinkNode sibling = root.next;
        while (sibling != null)
        {
            if(sibling.left == null && sibling.right == null)
            {
                sibling = sibling.next;
            }else {
                break;
            }
        }
        if(sibling == null)return null;
        return sibling.left == null ? sibling.right : sibling.left;
    }

    public void connectIterative(TreeLinkNode root) {
        if(root == null)return;
        TreeLinkNode dummyHead = new TreeLinkNode(0);
        TreeLinkNode pre = dummyHead;
        while ( root != null)
        {
            pre = dummyHead;
            while (root != null)
            {
                if(root.left != null)
                {
                    pre.next = root.left;
                    pre = pre.next;
                }
                if(root.right != null)
                {
                    pre.next = root.right;
                    pre = pre.next;
                }
                root = root.next;  // sibling node
            }
            root = dummyHead.next; // next level
            dummyHead.next = null;
        }
    }

    // Definition for binary tree with next pointer.
    private static class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;
        TreeLinkNode(int x) { val = x; }
    }

    public static void main(String[] args) {

        TreeLinkNode root = new TreeLinkNode(2);
        root.left = new TreeLinkNode(1);
        root.right = new TreeLinkNode(3);
        root.left.left = new TreeLinkNode(0);
        root.left.right = new TreeLinkNode(7);
        root.right.left = new TreeLinkNode(9);
        root.right.right = new TreeLinkNode(1);
        root.left.left.left = new TreeLinkNode(2);
        root.left.right.left = new TreeLinkNode(1);
        root.left.right.right = new TreeLinkNode(0);
        root.right.right.left = new TreeLinkNode(8);
        root.right.right.right = new TreeLinkNode(8);
        root.left.right.right.left = new TreeLinkNode(7);
        TreeLinkNodeII treeLinkNodeII = new TreeLinkNodeII();
        treeLinkNodeII.connect(root);

    }
}
