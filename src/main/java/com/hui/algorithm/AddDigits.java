package com.hui.algorithm;

/**
 *https://leetcode.com/problems/add-digits/description/
 *258. Add Digits
 * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
 * Example:
 * Input: 38
 * Output: 2
 * Explanation: The process is like: 3 + 8 = 11, 1 + 1 = 2.
 *              Since 2 has only one digit, return it.
 * Follow up:
 * Could you do it without any loop/recursion in O(1) runtime?
 * @author: shenhaizhilong
 * @date: 2018/8/17 23:35
 */
public class AddDigits {
    public static int addDigits(int num) {
        while (num > 10)
        {
            int sum = 0;
            while (num > 0)
            {
                sum += (num %10);
                num /= 10;
            }
            num = sum;
        }
        return num;
    }

    /**
     * First you should understand:
     * 10^k % 9 = 1
     * a*10^k % 9 = a % 9
     * Then let's use an example to help explain.
     * Say a number x = 23456
     * x = 2* 10000 + 3 * 1000 + 4 * 100 + 5 * 10 + 6
     * 2 * 10000 % 9 = 2 % 9
     * 3 * 1000 % 9 = 3 % 9
     *
     * 4 * 100 % 9 = 4 % 9
     *
     * 5 * 10 % 9 = 5 % 9
     *
     * Then x % 9 = ( 2+ 3 + 4 + 5 + 6) % 9, note that x = 2* 10000 + 3 * 1000 + 4 * 100 + 5 * 10 + 6
     *
     * So we have 23456 % 9 = (2 + 3 + 4 + 5 + 6) % 9
     * @param num
     * @return
     */
    public static int addDigits2(int num)
    {
        if(num ==0)return 0;
        if(num %9 == 0)return 9;
        return num%9;
    }
    public static void main(String[] args) {
        System.out.println(addDigits2(38));
    }
}
