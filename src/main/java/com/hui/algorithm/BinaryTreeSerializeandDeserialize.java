package com.hui.algorithm;

/**
 *
 *
 * 297. Serialize and Deserialize Binary Tree
 * DescriptionHintsSubmissionsDiscussSolution
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 *
 * Example:
 *
 * You may serialize the following tree:
 *
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 *
 * as "[1,2,3,null,null,4,5]"
 * Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 *
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 *
 *
 *
 * and
 *
 * 449. Serialize and Deserialize BST
 * DescriptionHintsSubmissionsDiscussSolution
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.
 *
 * The encoded string should be as compact as possible.
 *
 *
 * @author: shenhaizhilong
 * @date: 2018/9/27 17:10
 */
public class BinaryTreeSerializeandDeserialize {

    private void serializeHelper(TreeNode root, StringBuilder sb)
    {
        if(root == null)
        {
            sb.append("n,");  // this node is null
            return;
        }

        sb.append(root.val);
        sb.append(',');
        serializeHelper(root.left, sb);
        serializeHelper(root.right, sb);

    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
       StringBuilder sb = new StringBuilder();
       serializeHelper(root, sb);
       return sb.toString();
    }

    private TreeNode deserializeHelper(String[] val, int[] index)
    {
      if(index[0] >= val.length)return null;
      String str = val[index[0]++];
      if(str.equals("n"))return null;
      TreeNode root = new TreeNode(Integer.valueOf(str));
      root.left = deserializeHelper(val, index);
      root.right = deserializeHelper(val, index);
      return root;

    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {

        if (data == null || data.length() == 0) {
            return null;
        }
        String[] val = data.split(",");
        int[] index = {0};
        return deserializeHelper(val, index);

    }


   //Definition for a binary tree node.
   private static class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
   }

}
