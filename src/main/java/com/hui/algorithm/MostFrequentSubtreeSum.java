package com.hui.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/28 17:58
 *
 *508. Most Frequent Subtree Sum
 * DescriptionHintsSubmissionsDiscussSolution
 * Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself). So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.
 *
 * Examples 1
 * Input:
 *
 *   5
 *  /  \
 * 2   -3
 * return [2, -3, 4], since all the values happen only once, return all of them in any order.
 * Examples 2
 * Input:
 *
 *   5
 *  /  \
 * 2   -5
 * return [2], since 2 happens twice, however -5 only occur once.
 * Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.
 */
public class MostFrequentSubtreeSum {

    private  int maxCount;
    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer,Integer> counter = new HashMap<>();
        maxCount = 0;
        subtreeSum(root, counter);
        List<Integer> list = new ArrayList<>();
        for(int k:counter.keySet())
        {
            if(counter.get(k).intValue() == maxCount)
                list.add(k);
        }
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    private int subtreeSum(TreeNode root, Map<Integer,Integer> counter)
    {
        if(root == null)return 0;
        int left = subtreeSum(root.left, counter);
        int right = subtreeSum(root.right, counter);
        int currSum = left + right + root.val;
        int count = counter.getOrDefault(currSum,0) +1;
        if(count > maxCount)maxCount = count;
        counter.put(currSum, count);
        return currSum;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
