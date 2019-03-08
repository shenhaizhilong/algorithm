package com.hui.BitOperation;

/**
 *
 * https://leetcode.com/problems/sum-of-two-integers/description/
 * Sum of Two Integers
 *
 *
 * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
 *
 * Example:
 * Given a = 1 and b = 2, return 3.
 *
 * @author: shenhaizhilong
 * @date: 2018/8/15 18:37
 */
public class SumOfTwoIntegers {

    public static int getSum(int a, int b)
    {
        int carry = -1;
        while (carry != 0)
        {
            //进位项
            carry = (a & b)<<1;
            //非进位项
            a = a ^ b;
            b = carry;

        }
        return a;


    }

    public static void main(String[] args) {
        System.out.println(getSum(10,11));
    }
}
