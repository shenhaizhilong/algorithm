package com.hui.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/6 22:37
 *
 * 315. Count of Smaller Numbers After Self
 * DescriptionHintsSubmissionsDiscussSolution
 * You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 *
 * Example:
 *
 * Input: [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 *
 *
 */
public class CountofSmallerNumbersAfterSelf {

    public List<Integer> countSmaller(int[] nums) {

        List<Integer> ans = new ArrayList<>();
        if(nums == null || nums.length == 0)return ans;
        int N = nums.length;
        int[] count = new int[N];
        Node root = new Node(nums[N -1]);
        for (int i = N -2; i >=0; i--) {
            count[i] = insert(root, nums[i]);
        }

        for (int i = 0; i < N; i++) {
            ans.add(count[i]);
        }

        return ans;

    }

    private static class Node{
        int val;
        int count;  // count duplicates
        int leftCount;  // the number of keys which less than current key
        Node left, right;

        public Node(int val)
        {
            this.count = 1;
            this.val = val;
        }
    }

    private int insert(Node root, int val)
    {
        if(root.val == val)
        {
            root.count++;
            return root.leftCount;

        } else if (root.val > val)
        {
            root.leftCount++;
            if(root.left == null)
            {
                root.left = new Node(val);
                return 0;
            }
            return insert(root.left,val);
        }


        if(root.right == null)
        {
            root.right = new Node(val);
            return root.count + root.leftCount;
        }

        return root.count + root.leftCount + insert(root.right, val);

    }


    public static void main(String[] args) {

        CountofSmallerNumbersAfterSelf countofSmallerNumbersAfterSelf = new CountofSmallerNumbersAfterSelf();
        System.out.println(countofSmallerNumbersAfterSelf.countSmaller(new int[]{5,2,6,1}));
    }

}
