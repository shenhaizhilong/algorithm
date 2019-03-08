package com.hui.DynamicPrograming;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/22 0:52
 *
 *
 * 376. Wiggle Subsequence
 * DescriptionHintsSubmissionsDiscussSolution
 * A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly alternate between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with fewer than two elements is trivially a wiggle sequence.
 *
 * For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive and negative. In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, the first because its first two differences are positive and the second because its last difference is zero.
 *
 * Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence. A subsequence is obtained by deleting some number of elements (eventually, also zero) from the original sequence, leaving the remaining elements in their original order.
 *
 * Example 1:
 *
 * Input: [1,7,4,9,2,5]
 * Output: 6
 * Explanation: The entire sequence is a wiggle sequence.
 * Example 2:
 *
 * Input: [1,17,5,10,13,15,10,5,16,8]
 * Output: 7
 * Explanation: There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].
 * Example 3:
 *
 * Input: [1,2,3,4,5,6,7,8,9]
 * Output: 2
 * Follow up:
 * Can you do it in O(n) time?
 *
 *
 */
public class WiggleSubsequence {



    public int wiggleMaxLength(int[] nums) {
        int N = nums.length;
        int[] dpUp = new int[N];//  dpUp[i] means the maxLen wiggle Subsequence so far at index i, where  nums[i] >  nums[i -1]
        int[] dpDown = new int[N]; // dpDown[i] means the maxLen wiggle Subsequence so far at index i, where nums[i] < nums[i -1]
        dpDown[0] = 1;
        dpUp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            int diff = nums[i] - nums[i -1];
            if(diff > 0)
            {
                dpUp[i] = dpDown[i -1] +1;
                dpDown[i] = dpDown[-1];
            }else if(diff < 0) {
                dpDown[i] = dpUp[i -1] +1;
                dpUp[i] = dpUp[i -1];
            }else {
                dpDown[i] = dpDown[i-1];
                dpUp[i] = dpUp[i -1];
            }
        }

        return Math.max(dpDown[N-1], dpUp[N -1]);
    }


    public int wiggleMaxLength2(int[] nums) {
        if(nums == null || nums.length == 0)return 0;
        int dpUp = 1; //  dpUp[i] means the maxLen wiggle Subsequence so far at index i, where  nums[i] >  nums[i -1]
        int dpDown = 1; // dpDown[i] means the maxLen wiggle Subsequence so far at index i, where nums[i] < nums[i -1]

        for (int i = 1; i < nums.length; i++) {
            int diff = nums[i] - nums[i -1];
            if(diff > 0)
            {
                dpUp = dpDown +1;
            }else if(diff < 0) {
                dpDown = dpUp +1;
            }
        }

        return Math.max(dpDown, dpUp);
    }

    public int wiggleMaxLength3(int[] nums) {
        if(nums == null || nums.length == 0)return 0;
        int prediff = 0;
        int maxCount = 1;
        for (int i = 1; i < nums.length; i++) {
            int diff = nums[i] - nums[i -1];
            if( (diff > 0 && prediff <= 0) || (diff < 0 && prediff >= 0))
            {
                maxCount++;
                prediff = diff;
            }
        }

        return maxCount;
    }


    public static void main(String[] args) {


    }
}
