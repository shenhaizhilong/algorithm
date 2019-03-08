package com.hui.Math;

/**
 * @author: shenhaizhilong
 * @date: 2018/6/27 17:21
 */
public class PowerTest {

    public static boolean isPowerOfTwo(long n)
    {
        if(n < 0)
            throw new  IllegalArgumentException(" I must bigger than zero");

        return (n&(n-1)) == 0;

    }

    public static boolean isPowerOfFour(long n)
    {
        return n>0 &&(n&(n-1)) == 0 && (n & 0x55555555) !=0;
    }

    public static boolean isPowerOfThree(int n)
    {
        return n>0 && (1162261467 %n)==0;
    }
}
