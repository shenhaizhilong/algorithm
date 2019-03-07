package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/6/24 23:37
 */
public class ModForPowerOfTwoReduceOne {

    private static int n = (1<<6) -1;
    public static int index(int h)
    {
        return h & n;
    }

    public static void main(String[] args) {

        int mod;
        for (int i = 0; i < 100; i++) {
          mod = i% (n+1);
            System.out.println( "current i = " + i + (mod == index(i)));
        }
    }

}
