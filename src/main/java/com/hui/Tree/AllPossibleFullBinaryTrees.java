package com.hui.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/23 17:50
 *
 *894. All Possible Full Binary Trees
 *
 *
 * A full binary tree is a binary tree where each node has exactly 0 or 2 children.
 *
 * Return a list of all possible full binary trees with N nodes.  Each element of the answer is the root node of one possible tree.
 *
 * Each node of each tree in the answer must have node.val = 0.
 *
 * You may return the final list of trees in any order.
 *Example 1:
 *
 * Input: 7
 * Output: [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
 * Explanation:
 */
public class AllPossibleFullBinaryTrees {

    public List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> res = new ArrayList<>();
        if(N < 1)return res;
        if(N == 1){
            res.add(new TreeNode(0));
            return res;
        }

        N--;
        for (int i = 1; i < N; i += 2) {
            for(TreeNode left: allPossibleFBT(i))
            {
                for(TreeNode right: allPossibleFBT(N - i))
                {
                    TreeNode root = new TreeNode(0);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        return res;
    }


    // 所有左子树与右子树的组合, N % 2 == 0 时为空
    Map<Integer,List<TreeNode>> cache = new HashMap<>();
    public List<TreeNode> allPossibleFBT2(int N) {
        if(cache.containsKey(N))return cache.get(N);
        List<TreeNode> res = new ArrayList<>();
        if(N < 1 || (N & 1) == 0)return res;
        if(N == 1){
            res.add(new TreeNode(0));
            cache.put(1, res);
            return res;
        }

        if (N == 3) {
            TreeNode root = new TreeNode(0);
            root.left = new TreeNode(0);
            root.right = new TreeNode(0);
            res.add(root);
            cache.put(3, res);
            return res;
        }

        N--;
        for (int i = 1; i < N; i += 2) {
            for(TreeNode left: allPossibleFBT2(i))
            {
                for(TreeNode right: allPossibleFBT2(N - i))
                {
                    TreeNode root = new TreeNode(0);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        cache.put(N, res);
        return res;
    }



    public List<TreeNode> allPossibleFBT3(int N) {
        if(N < 1 || (N & 1) == 0)return new ArrayList<>();
        return helper(N);
    }

    public List<TreeNode> helper(int N) {

        if(cache.containsKey(N))return cache.get(N);
        List<TreeNode> res = new ArrayList<>();
        if(N == 1){
            res.add(new TreeNode(0));
            cache.put(1, res);
            return res;
        }else if (N == 3) {
            TreeNode root = new TreeNode(0);
            root.left = new TreeNode(0);
            root.right = new TreeNode(0);
            res.add(root);
            cache.put(3, res);
            return res;
        }

        N--;
        for (int i = 1; i < N; i += 2) {
            for(TreeNode left: helper(i))
            {
                for(TreeNode right: helper(N - i))
                {
                    TreeNode root = new TreeNode(0);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        cache.put(N, res);
        return res;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    public static void main(String[] args) {

    }
}
