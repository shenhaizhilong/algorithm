package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/3 23:41
 */
public class SingleNumberII {


    /**
     *
     * 137. Single Number II
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a non-empty array of integers, every element appears three times except for one, which appears exactly once. Find that single one.
     *
     * Note:
     *
     * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
     *
     * Example 1:
     *
     * Input: [2,2,3,2]
     * Output: 3
     * Example 2:
     *
     * Input: [0,1,0,1,0,1,99]
     * Output: 99
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {

        // https://leetcode.com/problems/single-number-ii/discuss/43296/An-General-Way-to-Handle-All-this-sort-of-questions./171921
        //we need to implement a tree-time counter(base 3) that if a bit appears three time ,it will be zero.
        //#curent  income  output
        //# ab      c/c       ab/ab
        //# 00      1/0       01/00
        //# 01      1/0       10/01
        //# 10      1/0       00/10
        // a=~abc+a~b~c;
        // b=~a~bc+~ab~c;
        int a = 0;
        int b = 0;
        for(int i: nums)
        {
            int tempA = (~a&b&i) |(a&~b&~i);
            b = (~a&~b&i) | (~a&b&~i);
            a = tempA;

        }

        //we need find the number that is 01,10 => 1, 00 => 0.
        //return b one time
        //return a two times
        //return a |b  one or two
        return b;

    }



    public static void main(String[] args) {

        System.out.println(singleNumber(new int[]{2,2,8,3,3,3,2}));
        System.out.println(singleNumber(new int[]{2,2,8,8,3,3,3,2}));


    }
}
