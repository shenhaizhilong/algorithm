package com.hui.Math;

import java.util.ArrayList;
import java.util.List;

/**
 *https://leetcode-cn.com/problems/divide-two-integers/description/
 *
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 *
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 *
 * 示例 1:
 *
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 示例 2:
 *
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 说明:
 *
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
 *
 * ***************
 *
 * dividend = divisor*(a*2^0 + b*2^1 + ...k*2^n) + r
 * 令r =0,求出最大的n ，然后 dividend  = dividend- (divisor<<n)
 * 依次求出最大的n,直到 dividend < divisor
 * n0, n1, n2 ...nm
 * 最终结果就是 results = （1<<n0) + (1<<n1) + ...(1<<nm)
 *
 * @author: shenhaizhilong
 * @date: 2018/7/2 14:10
 */
public class Divide {
    public static int divide(int dividend, int divisor) {

        if(divisor ==1)return dividend;
        if(dividend == Integer.MIN_VALUE && divisor == -1)return Integer.MAX_VALUE;
        if(divisor == -1)return -dividend;
        List<Integer> list = new ArrayList<>();

        int sign = Integer.signum(dividend)*Integer.signum(divisor);
        long newdividend = Math.abs(dividend*1L);
        long newdivisor = Math.abs(divisor*1L);

        while (newdividend > 0)
        {
            int maxPow = findMaxPow(newdividend, newdivisor);


            if(newdividend >= newdivisor)
            {
                list.add(maxPow);
            }

            newdividend = newdividend - (newdivisor<<maxPow);
        }

        int resutls = 0;
        for (Integer i :
                list) {
            resutls += (1<<i);
        }

        return sign*resutls;


    }

    public static int findMaxPow(long dividend, long divisor)
    {

        int k = 0;
        while (divisor >0 && k<=31)
        {
            divisor = divisor<<1;
            if(divisor <= dividend && divisor >0)
            {
                k++;
            }else {
                break;
            }

        }

        return k;
    }

    public static void main(String[] args) {
//        System.out.println(findMaxPow(3,1));
//        System.out.println(findMaxPow(5,2));
//        System.out.println(findMaxPow(7,3));
//        System.out.println(findMaxPow(8,2));
         System.out.println(findMaxPow(Integer.MAX_VALUE,2));
         System.out.println(findMaxPow(Integer.MAX_VALUE,1));

//        System.out.println(divide(9,3));
//        System.out.println(divide(5,2));
//        //System.out.println(divide(5,1));
//        System.out.println(divide(10,3));
//        System.out.println(divide(15,-2));
//        System.out.println(divide(0,-2));
       System.out.println(divide(Integer.MAX_VALUE,-2));
       System.out.println(divide(Integer.MAX_VALUE,1));
        System.out.println(divide(Integer.MIN_VALUE,2));
    }
}
