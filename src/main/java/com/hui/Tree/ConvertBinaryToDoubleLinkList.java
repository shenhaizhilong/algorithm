package com.hui.Tree;

/**
 * @author: shenhaizhilong
 * @date: 2019/1/17 11:17
 *
 * 二叉搜索树与双向链表
 *
 *
 * 题目描述
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
 */
public class ConvertBinaryToDoubleLinkList {


    private TreeNode head = null;
    private TreeNode pre = null;

    public TreeNode convert(TreeNode root)
    {
        if(root == null)return root;
        inOrder(root);
        return head;
    }

    private void inOrder(TreeNode treeNode)
    {
        if(treeNode == null)return;
        inOrder(treeNode.left);
        treeNode.left = pre;
        if(pre != null)
        {
            pre.right = treeNode;
        }
        pre = treeNode;
        if(head == null)
        {
            head = treeNode;
        }
        inOrder(treeNode.right);

    }

    private static class TreeNode
    {
        TreeNode left,right;
        int value;
        public TreeNode(int val)
        {
            this.value = val;
        }
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(0);
        root.right = new TreeNode(10);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        ConvertBinaryToDoubleLinkList convertBinaryToDoubleLinkList = new ConvertBinaryToDoubleLinkList();
        TreeNode head = convertBinaryToDoubleLinkList.convert(root);
        TreeNode curr = head;
        while (curr != null)
        {
            System.out.print(curr.value + ", ");
            curr = curr.right;
        }
    }
}
