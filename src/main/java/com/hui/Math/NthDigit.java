package com.hui.Math;

/**
 *
 * https://leetcode.com/problems/nth-digit/description/
 *400. Nth Digit
 * DescriptionHintsSubmissionsDiscussSolution
 * Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...
 *
 * Note:
 * n is positive and will fit within the range of a 32-bit signed integer (n < 231).
 *
 * Example 1:
 *
 * Input:
 * 3
 *
 * Output:
 * 3
 * Example 2:
 *
 * Input:
 * 11
 *
 * Output:
 * 0
 *
 * Explanation:
 * The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.
 *
 * @author: shenhaizhilong
 * @date: 2018/8/22 10:50
 */
public class NthDigit {
    public int findNthDigit(int n) {
        int start = 1;
        int len = 1;
        long count = 9;   //防止溢出
        while (n > len*count)
        {
            n -= len*count;
            len++;
            start *= 10;
            count *= 10;
        }

        start += (n -1)/len;  // len 个数字组成一个数，从10的k次开始 偏移 (n-1)/len
        String s = Integer.toString(start);
        return s.charAt((n-1)%len) -'0';

    }

    public static void main(String[] args) {
        NthDigit nthDigit = new NthDigit();
        System.out.println(nthDigit.findNthDigit(Integer.MAX_VALUE));
    }
}
