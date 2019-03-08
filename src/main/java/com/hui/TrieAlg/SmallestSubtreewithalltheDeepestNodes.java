package com.hui.TrieAlg;


/**
 * @author: shenhaizhilong
 * @date: 2018/11/28 17:10
 *
 *
 * 865. Smallest Subtree with all the Deepest Nodes
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a binary tree rooted at root, the depth of each node is the shortest distance to the root.
 *
 * A node is deepest if it has the largest depth possible among any node in the entire tree.
 *
 * The subtree of a node is that node, plus the set of all descendants of that node.
 *
 * Return the node with the largest depth such that it contains all the deepest nodes in its subtree.
 *
 *
 *
 * Example 1:
 *
 * Input: [3,5,1,6,2,0,8,null,null,7,4]
 * Output: [2,7,4]
 * Explanation:
 *
 *
 *
 * We return the node with value 2, colored in yellow in the diagram.
 * The nodes colored in blue are the deepest nodes of the tree.
 * The input "[3, 5, 1, 6, 2, 0, 8, null, null, 7, 4]" is a serialization of the given tree.
 * The output "[2, 7, 4]" is a serialization of the subtree rooted at the node with value 2.
 * Both the input and output have TreeNode type.
 *
 *
 * Note:
 *
 * The number of nodes in the tree will be between 1 and 500.
 * The values of each node are unique.
 *
 *
 *https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/discuss/146808/One-pass
 * if root == null, return pair(0, null)
 * left = deep(root.left)
 * right = deep(root.left)
 *
 * if left depth == right depth,
 * deepest nodes both in the left and right subtree,
 * return pair (left.depth + 1, root)
 *
 * if left depth > right depth,
 * deepest nodes only in the left subtree,
 * return pair (left.depth + 1, left subtree)
 *
 * if left depth < right depth,
 * deepest nodes only in the right subtree,
 * return pair (right.depth + 1, right subtree)
 *
 */
public class SmallestSubtreewithalltheDeepestNodes {

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return deep(root).value;
    }

    private Pair deep(TreeNode root)
    {

        if(root == null)return new Pair(0, null);
        Pair left = deep(root.left);
        Pair right = deep(root.right);
        if(left.key == right.key)return new Pair(left.key +1, root);
        if(left.key > right.key)return new Pair(left.key +1, left.value);
        return new Pair(right.key +1, right.value);
    }

    private class Pair{
        int key;
        TreeNode value;
        public Pair(int key, TreeNode value)
        {
            this.key = key;
            this.value = value;
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
