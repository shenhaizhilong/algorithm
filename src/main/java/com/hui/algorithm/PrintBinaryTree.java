package com.hui.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/23 16:28
 *
 *
 *
 * 655. Print Binary Tree
 * DescriptionHintsSubmissionsDiscussSolution
 * Print a binary tree in an m*n 2D string array following these rules:
 *
 * The row number m should be equal to the height of the given binary tree.
 * The column number n should always be an odd number.
 * The root node's value (in string format) should be put in the exactly middle of the first row it can be put. The column and the row where the root node belongs will separate the rest space into two parts (left-bottom part and right-bottom part). You should print the left subtree in the left-bottom part and print the right subtree in the right-bottom part. The left-bottom part and the right-bottom part should have the same size. Even if one subtree is none while the other is not, you don't need to print anything for the none subtree but still need to leave the space as large as that for the other subtree. However, if two subtrees are none, then you don't need to leave space for both of them.
 * Each unused space should contain an empty string "".
 * Print the subtrees following the same rules.
 * Example 1:
 * Input:
 *      1
 *     /
 *    2
 * Output:
 * [["", "1", ""],
 *  ["2", "", ""]]
 * Example 2:
 * Input:
 *      1
 *     / \
 *    2   3
 *     \
 *      4
 * Output:
 * [["", "", "", "1", "", "", ""],
 *  ["", "2", "", "", "", "3", ""],
 *  ["", "", "4", "", "", "", ""]]
 * Example 3:
 * Input:
 *       1
 *      / \
 *     2   5
 *    /
 *   3
 *  /
 * 4
 * Output:
 *
 * [["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
 *  ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
 *  ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
 *  ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]
 * Note: The height of binary tree is in the range of [1, 10].
 *
 *
 */
public class PrintBinaryTree {

    public List<List<String>> printTree(TreeNode root) {
        int h = height(root);

        // init res
        int size = (1<<h) -1;
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < h; i++) {
            List<String> temp = new ArrayList<>(size);
            for (int j = 0; j < size; j++) {
                temp.add("");
            }
            res.add(temp);
        }


        fill(root, 0, 0, size, res );
        return res;

    }

    private void fill(TreeNode root, int idx, int L, int R, List<List<String>> res)
    {
        if(root == null)return;
        int p = (L + R)/2;
        res.get(idx).set(p, Integer.toString(root.val));
        fill(root.left, idx +1, L, p, res);
        fill(root.right, idx +1, p +1, R, res);
    }

    private int height(TreeNode root)
    {
        if(root == null)return 0;
        return 1 + Math.max(height(root.left), height(root.right));
    }


    public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }
}
