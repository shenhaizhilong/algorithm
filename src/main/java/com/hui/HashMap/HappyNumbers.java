package com.hui.HashMap;

import java.util.HashSet;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/15 11:37
 */
public class HappyNumbers {

    /**
     *
     * Happy Number
     *https://leetcode.com/problems/happy-number/description/
     *
     * Write an algorithm to determine if a number is "happy".
     *
     * A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
     *
     * Example:
     *
     * Input: 19
     * Output: true
     * Explanation:
     * 1^2 + 9^2 = 82
     * 8^2 + 2^2 = 68
     * 6^2 + 8^2 = 100
     * 1^2 + 0^2 + 0^2 = 1
     *
     * https://en.wikipedia.org/wiki/Happy_number
     * Numbers that are happy follow a sequence that ends in 1. All non-happy numbers follow sequences that reach the cycle:
     *
     * 4, 16, 37, 58, 89, 145, 42, 20, 4......
     * To see this fact, first note that if n has m digits, then the sum of the squares of its digits is at most  9^{2}m, or 81m.
     *
     * For  m=4 and above,
     *
     * n > 10^{m-1}> 81m
     */
    private static final int[] cycleNumbers = {4, 16, 37, 58, 89, 145, 42, 20};
    private static  HashSet<Integer> cycle;
    static {
        if(cycle == null)
        {
            cycle = new HashSet<>(cycleNumbers.length);
            for (int i :
                    cycleNumbers) {
                cycle.add(i);
            }
        }
    }

    public boolean isHappy(int n) {
       if(n <1)return false;
       if(n == 1)return true;

       int rem = 0;
       int sum = 0;
       while (true)
       {
           while (n>0)
           {
               rem = n %10;
               sum += rem*rem;
               n = n/10;
           }
           if(cycle.contains(sum)) return false;
           if(sum == 1) break;
           n = sum;
           sum = 0;
       }
       return true;

    }


    public static void main(String[] args) {
        HappyNumbers happyNumbers = new HappyNumbers();
        for (int i = 0; i < 1001; i++) {
            if(happyNumbers.isHappy(i))
                System.out.println(i);
        }

    }


}
