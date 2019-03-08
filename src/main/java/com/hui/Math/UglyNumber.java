package com.hui.Math;

import java.util.Arrays;

/**
 *
 *https://leetcode.com/problems/ugly-number/description/
 *
 * 263. Ugly Number
 * DescriptionHintsSubmissionsDiscussSolution
 * Write a program to check whether a given number is an ugly number.
 *
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 *
 * Example 1:
 *
 * Input: 6
 * Output: true
 * Explanation: 6 = 2 × 3
 * Example 2:
 *
 * Input: 8
 * Output: true
 * Explanation: 8 = 2 × 2 × 2
 * Example 3:
 *
 * Input: 14
 * Output: false
 * Explanation: 14 is not ugly since it includes another prime factor 7.
 * Note:
 *
 * 1 is typically treated as an ugly number.
 * Input is within the 32-bit signed integer range: [−231,  231 − 1].
 * @author: shenhaizhilong
 * @date: 2018/8/19 7:37
 */
public class UglyNumber {
    public static boolean isUgly(int num) {
        if(num <= 0)return false;
        if(num == 1)return true;
        while ((num &0x01) == 0)
        {
            num = num >>>1;
        }

        while ( num %3 == 0)
        {
            num = num/3;
        }

        while (num %5 == 0)
        {
            num = num/5;
        }
        if(num != 1)return false;
        return true;

    }


    /**
     *
     *https://leetcode.com/problems/ugly-number-ii/description/
     * 264. Ugly Number II
     * DescriptionHintsSubmissionsDiscussSolution
     * Write a program to find the n-th ugly number.
     *
     * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
     *
     * Example:
     *
     * Input: n = 10
     * Output: 12
     * Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
     * Note:
     *
     * 1 is typically treated as an ugly number.
     * n does not exceed 1690.
     *
     * The idea of this solution is from this page:http://www.geeksforgeeks.org/ugly-numbers/
     *
     * The ugly-number sequence is 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, …
     * because every number can only be divided by 2, 3, 5, one way to look at the sequence is to split the sequence to three groups as below:
     *
     * (1) 1×2, 2×2, 3×2, 4×2, 5×2, …
     * (2) 1×3, 2×3, 3×3, 4×3, 5×3, …
     * (3) 1×5, 2×5, 3×5, 4×5, 5×5, …
     * We can find that every sub-sequence is the ugly-sequence itself (1, 2, 3, 4, 5, 6, 8, 9, 10, 12 .....) multiply 2, 3, 5.
     *
     * Then we use similar merge method as merge sort, to get every ugly number from the three subsequence.
     *
     * Every step we choose the smallest one, and move one step after,including nums with same value.
     *
     * @param n
     * @return
     */
    public static int nthUglyNumber(int n) {
     int[] uglyNumbers = new int[1690];
     uglyNumbers[0] = 1;
     int index2 = 0, index3 = 0, index5 = 0;
     int next2  = 2, next3 = 3, next5 = 5;
     for (int i = 1; i < n; i++) {
        int min = Math.min(Math.min(next2,next3),next5);
        uglyNumbers[i] = min;
        if(next2 == min)
            next2 = 2*uglyNumbers[++index2];
        if(next3 == min)
            next3 = 3*uglyNumbers[++index3];
        if(next5 == min)
            next5 = 5*uglyNumbers[++index5];
     }

     return uglyNumbers[n-1];


    }


    /**
     *https://leetcode.com/problems/super-ugly-number/description/
     * 313. Super Ugly Number
     * DescriptionHintsSubmissionsDiscussSolution
     * Write a program to find the nth super ugly number.
     *
     * Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k.
     *
     * Example:
     *
     * Input: n = 12, primes = [2,7,13,19]
     * Output: 32
     * Explanation: [1,2,4,7,8,13,14,16,19,26,28,32] is the sequence of the first 12
     *              super ugly numbers given primes = [2,7,13,19] of size 4.
     * Note:
     *
     * 1 is a super ugly number for any given primes.
     * The given numbers in primes are in ascending order.
     * 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
     * The nth super ugly number is guaranteed to fit in a 32-bit signed integer.
     * @param n
     * @param primes
     * @return
     */
    public static int nthSuperUglyNumber2(int n, int[] primes) {
        int[] uglyNumbers = new int[n];
        int[] indexN = new int[primes.length];
        int[] nextN = Arrays.copyOf(primes, primes.length);
        uglyNumbers[0] = 1;
        for (int i = 1; i < n; i++) {
            int min = min(nextN);
            uglyNumbers[i] = min;
            for (int j = 0; j < nextN.length; j++) {
                if(nextN[j] == min)
                {
                    nextN[j] = primes[j]*uglyNumbers[++indexN[j]];
                }
            }

        }

        return uglyNumbers[n-1];
    }
    private static int min(int[] arr)
    {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if( arr[i] < min)min = arr[i];
        }
        return min;
    }


    public static void main(String[] args) {
//        for (int i = -100; i < 100; i++) {
//           if(isUgly(i)) System.out.println(i);
//        }


//        for (int i = 1; i < 1000; i++) {
//            System.out.println(nthUglyNumber(i));
//        }

        System.out.println(nthUglyNumber(1690));

        int[] primes = {7,19,29,37,41,47,53,59,61,79,83,89,101,103,109,127,131,137,139,157,167,179,181,199,211,229,233,239,241,251};
        System.out.println(nthSuperUglyNumber2(100000, primes ));

    }
}
