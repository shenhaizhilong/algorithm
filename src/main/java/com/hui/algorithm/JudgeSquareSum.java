package com.hui.algorithm;

/**
 *https://leetcode-cn.com/problems/length-of-last-word/description/
 * a*a + b*b -2ab >=0
 * a*a + b*b >=2ab
 * c>=2ab
 * c/2 >=ab
 * a<=c/2
 * b<=c/2
 * @author: shenhaizhilong
 * @date: 2018/7/1 21:31
 */
public class JudgeSquareSum {

    public static boolean judgeSquareSum(int c) {

        if(c < 0) return false;
        int mid = (int)Math.sqrt(c);
        int left =0, right = mid;
        while (left <= right)
        {
            int temp = left*left + right*right;
            if(c == temp)return true;
            else if(c > temp)left++;
            else right--;
        }

        return false;

    }

}
