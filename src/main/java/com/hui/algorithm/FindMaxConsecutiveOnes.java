package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/7/5 17:40
 */
public class FindMaxConsecutiveOnes {
    public static int findMaxConsecutiveOnes(int[] nums) {
            int thisSum = 0;
            int maxCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if((nums[i] &0x01) == 0)
            {
                thisSum = 0;
                continue;

            }
            thisSum++;
            maxCount = thisSum > maxCount ? thisSum:maxCount;

        }
        return maxCount;
    }
}
