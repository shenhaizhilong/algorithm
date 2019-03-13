package com.hui.Tree;

/**
 * @author: shenhaizhilong
 * @date: 2019/3/13 9:51
 *
 * 寻找二叉树P 节点与 Q 节点的最低公共祖先
 *
 *  方法一： 自底向上，回溯法
 *  方法二： 自底向上，建立两个单链表；
 *  例如： p -> pp -> ppp -> ..>common->.. Root
 *         Q -> QQ -> QQQ -> .>common->.. Root
 *
 *         问题转化为求两个单链表的第一个相交节点： 算法：com.hui.linkedlist ->  FindFirstCommonNode
 *
 */
public class LCS {


    public TreeNode LCS(TreeNode root, TreeNode p, TreeNode q)
    {
        if(root == null)return null;
        if(root == p || root == q)return root;

        TreeNode left = LCS(root.left, p, q);
        TreeNode right = LCS(root.right, p, q);
        if(left != null && right != null)return root;
        return left != null ? left: right;
    }


    private static class TreeNode{
        TreeNode left, right;
        int val;
        public TreeNode(int val)
        {
            this.val = val;
        }
    }


    public static void main(String[] args) {


        TreeNode root = new  TreeNode(10);
        TreeNode node1 = new TreeNode(12);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(1);
        root.left = node2;
        root.right = node1;
        node2.left = node4;
        node2.right = node3;
        node4.left = node6;
        node4.right = node5;

        LCS lcs = new LCS();

        TreeNode ans = lcs.LCS(root, root, node1);
        System.out.println(ans.val);

    }
}
