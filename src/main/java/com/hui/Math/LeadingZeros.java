package com.hui.Math;

/**
 * @author: shenhaizhilong
 * @date: 2018/6/27 12:33
 */
public class LeadingZeros {

    /**
     *     To count the number of leading zeros for a int,
     *     we can use the following intuitive (bruteforce) method to
     *     increment the number of zeros until a first one (from left to right) is met, using a loop.
     * @param  n
     * @return the number of leading zeros.
     */
    public static int leadingZeros(int n)
    {
        if(n == 0)return 32;
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if(n<0)return count;
            count++;
            n <<=1;
        }
        return count;
    }

    /**
     *  To count the number of leading zeros for a long,
     * @param n
     * @return
     */
    public static int leadingZeros(long n)
    {
        if(n == 0)return 64;
        int count = 0;
        for (int i = 0; i < 64; i++) {
            if(n<0)return count;
            count++;
            n <<=1;
        }
        return count;
    }




}
