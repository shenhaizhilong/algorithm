package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/22 19:52
 *493. Reverse Pairs
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].
 *
 * You need to return the number of important reverse pairs in the given array.
 *
 * Example1:
 *
 * Input: [1,3,2,3,1]
 * Output: 2
 * Example2:
 *
 * Input: [2,4,3,5,1]
 * Output: 3
 * Note:
 * The length of the given array will not exceed 50,000.
 * All the numbers in the input array are in the range of 32-bit integer.
 *
 */
public class ReversePairs {

    public int reversePairs1(int[] nums) {
        if(nums == null || nums.length < 2)return 0;
        int count = 0;
        for (int i = 0; i < nums.length -1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if(((nums[i] + 1) >>> 1) > nums[j])
                {
                    count++;
                }
            }
        }

        return count;
    }

    private static class Node{
        Node left, right;
        int value;
        // the number of node's which greater or equal than current node's value.
        int count_ge;

        Node(int value)
        {
            this.value = value;
            count_ge = 1;
        }
    }

   private   Node insert(Node node, int value)
    {
        if(node == null)
            return new Node(value);
        else if(value < node.value)
        {
            node.left = insert(node.left, value);
        }else if(value > node.value)
        {
            node.count_ge++;
            node.right = insert(node.right, value);
        }else {
            node.count_ge++;
        }
        return node;
    }

    private int search(Node node, long val)
    {
        if(node == null)return 0;

         if(node.value == val)
        {
            return node.count_ge;
        }else if(val < node.value)
        {
            return node.count_ge + search(node.left, val);
        }


        return search(node.right, val);

    }


    public int reversePairs(int[] nums)
    {
        Node root = null;
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            count += search(root, nums[i]*2L + 1);
            root = insert(root, nums[i]);
        }

        return count;
    }
    public static void main(String[] args) {

        ReversePairs reversePairs = new ReversePairs();
        System.out.println(reversePairs.reversePairs1(new int[]{1,3,2,3,1}));
        System.out.println(reversePairs.reversePairs1(new int[]{2,4,3,5,1}));

        System.out.println(reversePairs.reversePairs(new int[]{1,3,2,3,1}));
        System.out.println(reversePairs.reversePairs(new int[]{2,4,3,5,1}));

    }
}
