package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/14 20:47
 */
public class MaximumSwap {


    /**
     * 670. Maximum Swap
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.
     *
     * Example 1:
     * Input: 2736
     * Output: 7236
     * Explanation: Swap the number 2 and the number 7.
     * Example 2:
     * Input: 9973
     * Output: 9973
     * Explanation: No swap.
     * Note:
     * The given number is in the range [0, 108]
     * @param num
     * @return
     */
    public int maximumSwap(int num) {
        
        char[] vals = Integer.toString(num).toCharArray();
        int[] last = new int[10];
        for (int i = 0; i < vals.length; i++) {
            last[vals[i] - '0'] = i;   // record the last index we see this digit
        }

        //O(9n)
        for (int i = 0; i < vals.length; i++) {

            // find the biggest digit  than current digit in the future
            for (int j = 9; j > vals[i] - '0'; j--) {
                if(last[j] > i)
                {
                    char temp = vals[i];
                    vals[i] = vals[last[j]];
                    vals[last[j]] = temp;
                    return Integer.valueOf(String.valueOf(vals));
                }
            }
        }

        return num;
    }
}
