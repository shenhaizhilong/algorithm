package com.hui.algorithm;

/**
 *
 *
 * 449. Serialize and Deserialize BST
 * DescriptionHintsSubmissionsDiscussSolution
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.
 *
 * The encoded string should be as compact as possible.
 *
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless
 *
 * @author: shenhaizhilong
 * @date: 2018/9/27 18:34
 */
public class BSTSerializeandDeserialize {

    private void serializeHelper(TreeNode root, StringBuilder sb)
    {
        if(root == null)
        {
            // this node is null
            return;
        }
        byte[] val = intToBytes(root.val);
        for (int i = 0; i < val.length; i++) {
            sb.append((char)(val[i]));  // must be char
        }
        serializeHelper(root.left, sb);
        serializeHelper(root.right, sb);

    }


    /**
     *
     * convert one int to 4 bytes
     * @param n
     * @return
     */
    private byte[] intToBytes(int n)
    {
        byte[] res = new byte[4];
        res[0] = (byte)(n >> 24);
        res[1] = (byte)(n >> 16);
        res[2] = (byte)(n >> 8);
        res[3] = (byte)n;
        return res;
    }

    /**
     * convert 4 bytes to one int
     * @param val
     * @return
     */
    private int bytesToInt(byte[] val)
    {
        return (val[0]<<24)&0xff000000|
                (val[1]<<16)&0x00ff0000|
                (val[2]<< 8)&0x0000ff00|
                (val[3]<< 0)&0x000000ff;
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

    private TreeNode deserializeHelper(String data, int[] index, int min, int max)
    {

        if(index[0] >= data.length())return null;
        int id = index[0];
        // read 4 bytes
        int val = bytesToInt(new byte[]{(byte)data.charAt(id), (byte)data.charAt(id +1), (byte)data.charAt(id + 2), (byte)data.charAt(id + 3)});
        if(val < min || val > max)return null; // null node

        index[0] += 4;
        TreeNode root = new TreeNode(val);
        root.left = deserializeHelper(data, index, min, val);
        root.right = deserializeHelper(data, index, val, max);
        return root;
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {

        if (data == null || data.length() == 0) {
            return null;
        }
        int[] index = {0};
        return deserializeHelper(data, index, Integer.MIN_VALUE, Integer.MAX_VALUE);

    }



    //Definition for a binary tree node.
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
