package com.hui.algorithm;

import java.util.HashSet;

/**
 *https://leetcode.com/problems/prime-number-of-set-bits-in-binary-representation/description/
 *
 * 762. Prime Number of Set Bits in Binary Representation
 * DescriptionHintsSubmissionsDiscussSolution
 * Given two integers L and R, find the count of numbers in the range [L, R] (inclusive) having a prime number of set bits in their binary representation.
 *
 * (Recall that the number of set bits an integer has is the number of 1s present when written in binary. For example, 21 written in binary is 10101 which has 3 set bits. Also, 1 is not a prime.)
 *
 * Example 1:
 *
 * Input: L = 6, R = 10
 * Output: 4
 * Explanation:
 * 6 -> 110 (2 set bits, 2 is prime)
 * 7 -> 111 (3 set bits, 3 is prime)
 * 9 -> 1001 (2 set bits , 2 is prime)
 * 10->1010 (2 set bits , 2 is prime)
 * Example 2:
 *
 * Input: L = 10, R = 15
 * Output: 5
 * Explanation:
 * 10 -> 1010 (2 set bits, 2 is prime)
 * 11 -> 1011 (3 set bits, 3 is prime)
 * 12 -> 1100 (2 set bits, 2 is prime)
 * 13 -> 1101 (3 set bits, 3 is prime)
 * 14 -> 1110 (3 set bits, 3 is prime)
 * 15 -> 1111 (4 set bits, 4 is not prime)
 * Note:
 *
 * L, R will be integers L <= R in the range [1, 10^6].
 * R - L will be at most 10000.
 *
 *
 * Intuition and Approach
 *
 * For each number from L to R, let's find out how many set bits it has. If that number is 2, 3, 5, 7, 11, 13, 17, or 19, then we add one to our count. We only need primes up to 19 because R \leq 10^6 < 2^{20}R≤10
 * ​6
 * ​​ <2
 * ​20
 * ​​ .
 * @author: shenhaizhilong
 * @date: 2018/8/19 23:48
 */
public class CountPrimeSetBits {

    public static int countPrimeSetBits(int L, int R) {
                         //[0,  1,      2,    3,    4,    5    ,6    , 7   , 8,     9,    10 ,   11,   12,     13,  14,     15,   16,    17,   18,    19
        boolean[] isPrime = {false,false, true, true, false, true, false, true, false, false, false, true, false, true, false, false, false, true, false, true};
        int count = 0;
        for (int i = L; i <=R ; i++) {
            if(isPrime[countBit(i)])count++;
        }
        return count;
    }

    private static int countBit(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n &= (n - 1);
        }
        return count;
    }


    private static HashSet<Integer> set;
    static {
        set = new HashSet<>();
        set.add(2);
        set.add(3);
        set.add(5);
        set.add(7);
        set.add(11);
        set.add(13);
        set.add(17);
        set.add(19);
        set.add(23);
    }
    public static int countPrimeSetBits2(int L, int R) {
        int count = 0;
        for (int i = L; i <=R ; i++) {
            if(set.contains(countBit(i)))count++;
        }
        return count;
    }



    public static void main(String[] args) {
        System.out.println(countPrimeSetBits(10,15));
    }



}
