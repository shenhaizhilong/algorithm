package com.hui.Math;

/**
 *https://leetcode.com/problems/rotated-digits/description/
 *
 * 788. Rotated Digits
 * DescriptionHintsSubmissionsDiscussSolution
 * X is a good number if after rotating each digit individually by 180 degrees, we get a valid number that is different from X.  Each digit must be rotated - we cannot choose to leave it alone.
 *
 * A number is valid if each digit remains a digit after rotation. 0, 1, and 8 rotate to themselves; 2 and 5 rotate to each other; 6 and 9 rotate to each other, and the rest of the numbers do not rotate to any other number and become invalid.
 *
 * Now given a positive number N, how many numbers X from 1 to N are good?
 *
 * Example:
 * Input: 10
 * Output: 4
 * Explanation:
 * There are four good numbers in the range [1, 10] : 2, 5, 6, 9.
 * Note that 1 and 10 are not good numbers, since they remain unchanged after rotating.
 * Note:
 *
 *
 * http://www.frankmadrid.com/ALudicFallacy/2018/02/28/rotated-digits-leet-code-788/
 *
 *
 * N  will be in range [1, 10000].
 * @author: shenhaizhilong
 * @date: 2018/8/21 15:29
 */
public class RotatedDigits {

    private static final int[] map = {0,1,5,-1,-1,2,9,-1,8,6};
    public static int rotatedDigits(int N) {

        int count = 0;
        for (int i = 1; i <= N; i++) {
            if(isGood(i))count++;
        }

        return count;

    }

    private static boolean isGood(int n)
    {
        int sum = 0;
        int k = n;
        int t = 1;
        while (k >0)
        {
            int r = k%10;
            if(map[r] <0)return false;
            sum = sum + map[r]*t;
            k = k/10;
            t *=10;
        }
        if(sum == n)return false;
        return true;
    }

    public static void main(String[] args) {
        System.out.println(rotatedDigits(10000));
    }
}
