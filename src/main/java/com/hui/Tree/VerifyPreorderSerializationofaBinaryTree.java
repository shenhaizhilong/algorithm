package com.hui.Tree;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/29 14:17
 *
 * 331. Verify Preorder Serialization of a Binary Tree
 * DescriptionHintsSubmissionsDiscussSolution
 * One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.
 *
 *      _9_
 *     /   \
 *    3     2
 *   / \   / \
 *  4   1  #  6
 * / \ / \   / \
 * # # # #   # #
 * For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.
 *
 * Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.
 *
 * Each comma separated value in the string must be either an integer or a character '#' representing null pointer.
 *
 * You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".
 *
 * Example 1:
 *
 * Input: "9,3,4,#,#,1,#,#,2,#,6,#,#"
 * Output: true
 * Example 2:
 *
 * Input: "1,#"
 * Output: false
 * Example 3:
 *
 * Input: "9,#,#,1"
 * Output: false
 *
 */
public class VerifyPreorderSerializationofaBinaryTree {


    // every node's in degree is 1, out degree is two
    public boolean isValidSerialization(String preorder) {

        String[] nodes = preorder.split(",");
        if(!nodes[nodes.length -1].equals("#"))return false;  // last node must be null
        int diff = 1;
        for (int i = 0; i < nodes.length; i++) {
            String node = nodes[i];
            if(--diff < 0)return false;  // every node has one in edge
            if(!node.equals("#"))  // every non- null node has two out edges
                diff += 2;
        }
        return diff == 0;
    }

    public static void main(String[] args) {
        VerifyPreorderSerializationofaBinaryTree tree = new VerifyPreorderSerializationofaBinaryTree();
        System.out.println(tree.isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
    }

}
