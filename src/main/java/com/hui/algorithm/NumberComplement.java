package com.hui.algorithm;

/**
 *
 * https://leetcode.com/problems/number-complement/description/
 *476. Number Complement
 * Given a positive integer, output its complement number. The complement strategy is to flip the bits of its binary representation.
 *
 * Note:
 * The given integer is guaranteed to fit within the range of a 32-bit signed integer.
 * You could assume no leading zero bit in the integer’s binary representation.
 * Example 1:
 * Input: 5
 * Output: 2
 * Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.
 * Example 2:
 * Input: 1
 * Output: 0
 * Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.
 * @author: shenhaizhilong
 * @date: 2018/8/18 20:03
 */
public class NumberComplement {
    public static int findComplement(int num) {
        int h = Integer.highestOneBit(num);
        return ((~num) & (h -1));
    }

    public static void main(String[] args) {
        System.out.println(findComplement(5));
        System.out.println(findComplement(1));
    }
}
