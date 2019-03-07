package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/6/27 13:00
 */
public class Pow {

    /**
     *  calculator the pow of a.
     *  O(logb)
     * @param a
     * @param b
     * @return  pow of a
     * @throws IllegalArgumentException if b < 0
     */
    public static long pow(long a, long b)
    {
        if(a ==0L) return 0L;
        if(a == 1L) return 1L;

        if(b<0)
        {
            throw new IllegalArgumentException("b can't less than zero");
        }
        long results = 1;
        while (b>0)
        {
            if((b&0x01) ==1)
            {
                results *=a;
            }
            b >>=1;
            a = a*a;
        }
        if(results < 0)
        {
            throw new IllegalArgumentException(" b is too big, the results overflow");
        }
        return results;
    }
}
