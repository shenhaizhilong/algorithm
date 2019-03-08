package com.hui.String;

/**
 * @author: shenhaizhilong
 * @date: 2018/6/30 23:48
 */
public class ReverseInt {
    public static int reverse(int x)
    {
        if(x == 0)return x;
        if(x == Integer.MIN_VALUE || x == Integer.MAX_VALUE) return 0;

        int sign = x > 0 ? 1 : -1;
        x = Math.abs(x);
        long sum = 0L;
        int remain;
        while (x >0)
        {
            remain = x %10;
            sum = sum*10 + remain;
            x = x/10;

        }
        if(sum > Integer.MAX_VALUE)return 0;
        return sign*(int)sum;

    }


}
