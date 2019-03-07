package com.hui.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/2 21:19
 */
public class UniqueBinarySearchTreesII {


    /**
     *95. Unique Binary Search Trees II
     * DescriptionHintsSubmissionsDiscussSolution
     * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.
     *
     * Example:
     *
     * Input: 3
     * Output:
     * [
     *   [1,null,3,2],
     *   [3,2,null,1],
     *   [3,1,null,null,2],
     *   [2,1,3],
     *   [1,null,2,null,3]
     * ]
     * Explanation:
     * The above output corresponds to the 5 unique BST's shown below:
     *
     *    1         3     3      2      1
     *     \       /     /      / \      \
     *      3     2     1      1   3      2
     *     /     /       \                 \
     *    2     1         2                 3
     *
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        if(n == 0)return new ArrayList<TreeNode>();
        return generateTreesHelper(1, n);

    }

    private List<TreeNode> generateTreesHelper(int start, int end)
    {
        List<TreeNode> list = new ArrayList<>();
        if(start > end)
        {

            list.add(null);
            return list;
        }
        if(start == end)
        {
            list.add(new TreeNode(start));
            return list;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = generateTreesHelper(start, i - 1);
            List<TreeNode> rightTrees = generateTreesHelper(i + 1, end);
            for (int j = 0; j < leftTrees.size(); j++) {
                for (int k = 0; k < rightTrees.size(); k++) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftTrees.get(j);
                    root.right = rightTrees.get(k);
                    list.add(root);
                }
            }
        }

        return list;


    }

    private  class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }

    public static void main(String[] args) {


        UniqueBinarySearchTreesII binarySearchTreesII = new UniqueBinarySearchTreesII();
        binarySearchTreesII.generateTrees(3);

    }
}
