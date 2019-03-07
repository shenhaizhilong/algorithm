package com.hui.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/9 19:43
 *
 * 652. Find Duplicate Subtrees
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.
 *
 * Two trees are duplicate if they have the same structure with same node values.
 *
 * Example 1:
 *
 *         1
 *        / \
 *       2   3
 *      /   / \
 *     4   2   4
 *        /
 *       4
 * The following are two duplicate subtrees:
 *
 *       2
 *      /
 *     4
 * and
 *
 *     4
 * Therefore, you need to return above trees' root in the form of a list.
 *
 *
 */
public class FindDuplicateSubtrees {

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String,Integer> counter = new HashMap<>();
        List<TreeNode> res = new ArrayList<>();
        preOrder(root, res, counter);
        return res;
    }

    private String preOrder(TreeNode root, List<TreeNode> res, Map<String,Integer> counter)
    {
        if(root == null)return "";
        String str = new StringBuilder(Integer.toString(root.val)).append(",")
                .append(preOrder(root.left, res, counter)).append(",")
                .append(preOrder(root.right,res,counter)).toString();
        int value = counter.getOrDefault(str, 0);
        if (value == 1)
        {
            res.add(root);
        }
        counter.put(str, ++value);
        return str;
    }


    public List<TreeNode> findDuplicateSubtrees2(TreeNode root) {
        Map<Long,int[]> counter = new HashMap<>();
        List<TreeNode> res = new ArrayList<>();
        preOrder2(root, res, counter);
        return res;
    }

    private int preOrder2(TreeNode root, List<TreeNode> res, Map<Long,int[]> counter)
    {
        if(root == null)return 0;

        long key = (long)root.val ;
        long left = preOrder2(root.left, res, counter) ;
        long right = preOrder2(root.right, res, counter);
        key = key <<32 |( left << 16) ^ right;
        int[] value = counter.get(key);
        if(value == null)
        {
            value = new int[]{counter.size() + 1, 1};
            counter.put(key, value);
        }else if(++value[1] == 2)
        {
            res.add(root);
        }

        return value[0];
    }


    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        TreeNode r1 = new TreeNode(2);
        TreeNode r2 = new TreeNode(4);
        TreeNode r3 = new TreeNode(3);
        TreeNode r4 = new TreeNode(2);
        TreeNode r5 = new TreeNode(4);
        TreeNode r6 = new TreeNode(4);
        root.left = r1;
        root.right = r3;
        r1.left = r2;
        r3.left = r4;
        r4.left = r5;
        r3.right = r6;


//        TreeNode root = new TreeNode(0);
//        TreeNode r1 = new TreeNode(0);
//        TreeNode r2 = new TreeNode(0);
//        TreeNode r3 = new TreeNode(0);
//        TreeNode r4 = new TreeNode(0);
//        TreeNode r5 = new TreeNode(0);
//        TreeNode r6 = new TreeNode(0);
//        root.left = r1;
//        root.right = r3;
//        r1.left = r2;
//        r3.right = r4;
//        r4.right = r5;


        FindDuplicateSubtrees findDuplicateSubtrees = new FindDuplicateSubtrees();
        List<TreeNode> list = findDuplicateSubtrees.findDuplicateSubtrees(root);
        System.out.println(list);


        List<TreeNode> list2 = findDuplicateSubtrees.findDuplicateSubtrees2(root);
        System.out.println(list2);
    }
}
