package com.hui.Math;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/16 0:22
 *
 *
 * 233. Number of Digit One
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.
 *
 * Example:
 *
 * Input: 13
 * Output: 6
 * Explanation: Digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
 *
 *
 */
public class NumberOfDigitOne {

    public int countDigitOne(int n)
    {
        int res = 1;
        for (int i = 10; i <=n; i++) {
            int count = 0;
            int curr = i;
            while (curr > 0)
            {
                if(curr % 10 == 1)count++;
                curr /=10;
            }
            res += count;
        }

        return res;
    }
    public int countDigitOne2(int n)
    {
        int res = 0;
        for (long i = 1; i <=n ; i *= 10) {

            long divider = i*10;
            res += (n/divider)*i + Math.min(i, Math.max(0, (n % divider) - i + 1));
        }
        return res;

    }

    public static void main(String[] args) {
        NumberOfDigitOne numberOfDigitOne = new NumberOfDigitOne();
        System.out.println(numberOfDigitOne.countDigitOne(13));
        System.out.println(numberOfDigitOne.countDigitOne2(13));
    }
}
