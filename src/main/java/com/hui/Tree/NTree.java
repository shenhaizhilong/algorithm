package com.hui.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree/description/
 * N叉树的最大深度
 * 给定一个N叉树，找到其最大深度。
 *
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 *
 * 例如，给定一个 3叉树 :
 *                   1
 *                / | \
 *               3  2  4
 *              / \
 *             5  6
 *
 *
 *
 * 我们应返回其最大深度，3。
 *
 * 说明:
 *
 * 树的深度不会超过 1000。
 * 树的节点总不会超过 5000。
 * @author: shenhaizhilong
 * @date: 2018/8/12 12:56
 */
public class NTree {

    private Node root;


    //  层序遍历
    public int maxDepth(Node root)
    {
        if(root == null)return 0;
        int count = 1;
        int depth = 0;
        LinkedList<Node> queue = new LinkedList<>();
        Node current = root;
        queue.add(current);
        while (!queue.isEmpty())
        {
            current = queue.pollFirst();
            count--;
            if(current.children != null && !current.children.isEmpty())
            {
                queue.addAll(current.children);
            }
            if(count == 0)
            {
                count = queue.size();  // 将这一层的元素数量赋值给count
                depth++;               // 最大深度为层数
            }
        }
        return depth;
    }


    //递归
    public int maxDepth2(Node root)
    {
        if(root == null)return 0;
        int depth = 0;
        if(root.children != null && root.children.size() > 0)
        {
            for (Node t :
                    root.children) {
                depth = Math.max(depth, maxDepth2(t));
            }
        }
        return depth + 1;
    }


    /**
     *
     *
     * https://leetcode.com/problems/n-ary-tree-level-order-traversal/description/
     * 429. N-ary Tree Level Order Traversal
     * Given an n-ary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
     *
     * For example, given a 3-ary tree:
     *
     *
     *
     *
     *
     * We should return its level order traversal:
     *
     *
     *
     *
     *
     * [
     *      [1],
     *      [3,2,4],
     *      [5,6]
     * ]
     *
     *
     * Note:
     *
     * The depth of the tree is at most 1000.
     * The total number of nodes is at most 5000.
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> list = new ArrayList<>();
        if(root == null)return list;
        List<Node> queue = new LinkedList<>();
        queue.add(root);
        int count = 1;
        Node first = null;
        List<Integer> currentList = new ArrayList<>();
        while (!queue.isEmpty())
        {

            first = ((LinkedList<Node>) queue).pollFirst();
            currentList.add(first.val);
            count--;

           if(first.children != null && !first.children.isEmpty())
           {
               queue.addAll(first.children);
           }
            if(count == 0)
            {
                list.add(currentList);
                currentList = new ArrayList<>();
                count = queue.size();
            }


        }

        return list;

    }


    /**
     *
     * https://leetcode.com/problems/n-ary-tree-preorder-traversal/description/
     * 589. N-ary Tree Preorder Traversal
     * Given an n-ary tree, return the preorder traversal of its nodes' values.
     *
     *
     * For example, given a 3-ary tree:
     *
     *
     *
     *
     * Return its preorder traversal as: [1,3,5,6,2,4].
     *
     *
     * @param root
     * @return
     */
    public List<Integer> preorder2(Node root) {
        List<Integer> result = new ArrayList<Integer>();
        helperpreorder2(root, result);
        return result;
    }

    private void helperpreorder2(Node node, List<Integer> result) {
        if (node == null) {
            return;
        }

        result.add(node.val);

        for (int i = 0; i < node.children.size(); i++) {
            helperpreorder2(node.children.get(i), result);
        }
    }

    public List<Integer> preorder(Node root) {
        List<Node> stack = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        if(root == null)return res;
        stack.add(root);
        while (!stack.isEmpty())
        {
            root = ((LinkedList<Node>) stack).pop();
            if(root != null)
            {
                res.add(root.val);
                stack.addAll(0, root.children);
            }

        }

        return res;
    }


    /**
     *590. N-ary Tree Postorder Traversal
     * Given an n-ary tree, return the postorder traversal of its nodes' values.
     *
     *
     * For example, given a 3-ary tree:
     *
     *
     *
     *
     * Return its postorder traversal as: [5,6,3,2,4,1].
     *
     *
     * Note: Recursive solution is trivial, could you do it iteratively?
     *
     * @param root
     * @return
     */
    public List<Integer> postorder(Node root) {
        List<Integer> results = new ArrayList<>();
        postOrderHelper(root, results);
        return results;
    }

    private void postOrderHelper(Node n, List<Integer> results)
    {
        if(n == null)return;

        for (int i = 0; i < n.children.size(); i++) {
            postOrderHelper(n.children.get(i), results);        }
        results.add(n.val);
    }


    private static class Node{
        int val;
        List<Node> children;

        public Node(){}
        public Node(int val, List<Node> children)
        {
            this.val = val;
            this.children = children;
        }
    }
}
