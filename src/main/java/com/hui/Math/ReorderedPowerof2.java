package com.hui.Math;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/22 10:02
 *
 * 869. Reordered Power of 2
 * DescriptionHintsSubmissionsDiscussSolution
 * Starting with a positive integer N, we reorder the digits in any order (including the original order) such that the leading digit is not zero.
 *
 * Return true if and only if we can do this in a way such that the resulting number is a power of 2.
 *
 *
 *
 * Example 1:
 *
 * Input: 1
 * Output: true
 * Example 2:
 *
 * Input: 10
 * Output: false
 * Example 3:
 *
 * Input: 16
 * Output: true
 * Example 4:
 *
 * Input: 24
 * Output: false
 * Example 5:
 *
 * Input: 46
 * Output: true
 *
 *
 * Note:
 *
 * 1 <= N <= 10^9
 */
public class ReorderedPowerof2 {

    /**
     *
     * we can stop the for loop at i<=29, since 2^30 = 1 073 741 824  which not in the range  1 <= N <= 10^9
     *
     * @param N
     * @return
     */
    public boolean reorderedPowerOf2(int N) {
        int[] frequency = freq(N);
        for (int i = 0; i < 30; i++) {
            if(Arrays.equals(frequency, freq(1 << i)))
                return true;
        }

        return false;


    }


    /**
     *
     * return the frequency of every digit in N
     * for example: 2343599, return [0,0,1,2,1,1,0,0,0,2]
     * @param N
     * @return
     */
    public int[] freq(int N)
    {
        int[] res = new int[10];
        while ( N > 0)
        {
            int idx = N % 10;
            res[idx]++;
            N /= 10;
        }
        return res;
    }



    public boolean reorderedPowerOf22(int N) {
        long c = counter(N);

        for (int i = 0; i < 30; i++)
        {
            if ( counter(1 << i) == c) return true;
        }

        return false;
    }

    public long counter(int N) {
        long res = 0;
        while ( N > 0)
        {
            res += (int)Math.pow(10, N % 10);
            N /= 10;
        }

        return res;
    }



//    public void calc()
//    {
//        long[] cache = new long[30];
//        for (int i = 0; i < 30; i++) {
//            cache[i] =   counter(1 << i);
//        }
//
//        System.out.println(Arrays.toString(cache));
//
//    }

    private static final long[] cache = {10, 100, 10000, 100000000, 1000010, 1100, 1010000, 100000110, 1100100, 100110, 10111, 100010101, 1001010001, 1100000110, 101011010, 111001100, 2201000, 10001121, 1020210, 200110200, 111110011, 1010100211, 1000031011, 401001001, 32000120, 223100, 212010011, 120011220, 102221100, 1111101111};

    public boolean reorderedPowerOf23(int N) {
        long c = counter(N);

        for (int i = 0; i < 30; i++)
        {
            if ( cache[i] == c) return true;
        }

        return false;
    }

    public static void main(String[] args) {

        ReorderedPowerof2 reorderedPowerof2 = new ReorderedPowerof2();

    }

}
