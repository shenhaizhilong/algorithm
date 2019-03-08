package com.hui.BitOperation;


/**
 * @author: shenhaizhilong
 * @date: 2018/7/1 0:50
 */
public class BitUtil {

    static final int MAXIMUM_CAPACITY = 1 << 30;
    public static int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    /**
     * return floor power of two x <=n
     *
     * @param n
     * @return
     */
    public static int floorPowerOfTwo(int n) {

        n = n | (n >>> 1);
        n = n | (n >>> 2);
        n = n | (n >>> 4);
        n = n | (n >>> 8);
        n = n | (n >>> 16);
        return n - (n >>> 1);
    }

    /**
     * celling power of 2, x >=n
     *
     * @param n
     * @return
     */
    public static int cellingPowerOfTwo(int n) {
        n = n - 1;
        n = n | (n >>> 1);
        n = n | (n >>> 2);
        n = n | (n >>> 4);
        n = n | (n >>> 8);
        return n + 1;
    }

    /**
     * Hacker's delight 2nd version, page 88 ,
     *
     * @param n
     * @return
     */
    public static int evenOrOdd(int n) {

        n = n ^ (n >>> 1);
        n = n ^ (n >>> 2);
        n = n ^ (n >>> 4);
        n = n ^ (n >>> 8);
        n = n ^ (n >>> 16);

        return n & 0x01;
    }

    public static int revserse(int n)
    {
        n = (n&0x55555555)<<1 | ((n>>>1) & 0x55555555);
        n = (n&0x33333333)<<2 | ((n>>>2) & 0x33333333);
        n = (n&0x0f0f0f0f)<<4 | ((n>>>4) & 0x0f0f0f0f);
        n = (n&0x00ff00ff)<<8 | ((n>>>8) & 0x00ff00ff);
        n = (n&0x0000ffff)<<16 | ((n>>>16) & 0x0000ffff);
        return n;
    }

/**
 * Returns the value obtained by reversing the order of the bytes in the
 * two's complement representation of the specified {@code int} value.
 *
 * @param n the value whose bytes are to be reversed
 * @return the value obtained by reversing the bytes in the specified
 *     {@code int} value.
 *
 *    **/

    public static int reverseBytes(int n)
    {
        if(n == 0)return 0;
        int y = (n >>>24) | (n >> 8) &0xFF00 | (n << 8) &0xFF0000 | (n << 24);
        return y;
    }

    public static int[] countBits(int num) {
        if(num <0)
            throw new  IllegalArgumentException("num should >=0");
        int[] results = new int[num +1];
        for (int i = 1; i <= num; i++) {
            results[i] = bitCount(i);
        }

        return results;

    }

    /**
     * cal
     * @param num
     * @return int[]
     */
    public static int[] countBits2(int num) {
        if(num <0)
            throw new  IllegalArgumentException("num should >=0");
        int[] results = new int[num +1];
        for (int i = 1; i <= num; i++) {
            results[i] = results[i>>>1] + (i&0x01);  // get the bitCount of number (i>>>1) and then we plus the last bit in x
        }

        return results;

    }

    /**
     * return int n's bits string
     * @param n
     * @return String
     */

    public static String getBitsStr(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            if ((n & 0x01) == 1) {
                sb.append('1');
            } else {
                sb.append('0');
            }
            n = n >>> 1;
        }
        sb.append("b0");
        return sb.reverse().toString();


    }


    /**
     * return long n's bits string
     * @param n
     * @return String
     */
    public static String getBitsStr(long n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 64; i++) {
            if ((n & 0x01) != 0) {
                sb.append('1');
            } else {
                sb.append('0');
            }
            n = n >>> 1;
        }
        sb.append("b0");
        return sb.reverse().toString();


    }

    /**
     * get the hamming distance from int a and int b
     * @param a
     * @param b
     * @return int hamming distance for int a and int b
     */
    public static int hammingDistance(int a, int b)
    {
        int v = a ^b;
        return Integer.bitCount(v);
    }

    /**
     * get the hamming distance from long a and long b
     * @param a
     * @param b
     * @return int hamming distance for long a and long b
     */

    public static int hammingDistance(long a, long b)
    {
        long v = a^b;
        return Long.bitCount(v);
    }


    /**
     * another method to calculate the bitcount for int x
     * @param x
     * @return int type, the number of bit count for x
     */
    public static int bitCount(int x)
    {
        int r = 0;
        while (x != 0)
        {
            r++;
            x = x&(x -1);
        }
        return r;
    }


    /**
     *  another method to calculate the bit count for long x
     * @param x
     * @return int type, the number of bit 1 in long x
     */
    public static int bitCount(long x)
    {
        int r = 0;
        while (x != 0)
        {
            r++;
            x = x&(x -1L);
        }
        return r;
    }


    /**
     *
     * 693. Binary Number with Alternating Bits
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a positive integer, check whether it has alternating bits: namely, if two adjacent bits will always have different values.
     *
     * Example 1:
     * Input: 5
     * Output: True
     * Explanation:
     * The binary representation of 5 is: 101
     * Example 2:
     * Input: 7
     * Output: False
     * Explanation:
     * The binary representation of 7 is: 111.
     * Example 3:
     * Input: 11
     * Output: False
     * Explanation:
     * The binary representation of 11 is: 1011.
     * Example 4:
     * Input: 10
     * Output: True
     * Explanation:
     * The binary representation of 10 is: 1010.
     * @param n
     * @return
     */
    public static boolean hasAlternatingBits(int n) {
        if(n <1)return false;
        int last = (n & 0x01);
        n = n>>>1;
        while (n>0)
       {
           if(last == (n & 0x01))return false;
            last = (n & 0x01);
           n = n>>>1;
       }
       return true;
    }

    /**
     *      n =         1010101      101010
     *    n>>>1 =       0101010      010101
     *    n= n^(n>>>1)  1111111      111111
     *    n=(n & n+1)  10000000     1000000
     *
     *
     * @param n
     * @return
     */
    public static boolean hasAlternatingBits2(int n) {
        if(n <1)return false;
        n = n ^ (n>>1);
        return (n & n+1) == 0;
    }

    /**
     *
     *
     *      n =         1010101      101010
     *    n>>>1 =       0010101      001010
     *    n= n^(n>>>2)  1000000      100000
     *    n=(n & n-1)
     *
     *
     * @param n
     * @return
     */
    public static boolean hasAlternatingBits3(int n) {
        if(n <1)return false;
        n = n ^ (n>>2);
        return (n & n-1) == 0;
    }

    public static boolean hasAlternatingBits4(int n)
    {
        if(n <1)return false;
        String binaryStr = Integer.toBinaryString(n);
        return binaryStr.matches("(10)*1?");
    }


    /**
     * https://leetcode.com/problems/convert-a-number-to-hexadecimal/description/
     * 405. Convert a Number to Hexadecimal
     * DescriptionHintsSubmissionsDiscussSolution
     * Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two’s complement method is used.
     *
     * Note:
     *
     * All letters in hexadecimal (a-f) must be in lowercase.
     * The hexadecimal string must not contain extra leading 0s. If the number is zero, it is represented by a single zero character '0'; otherwise, the first character in the hexadecimal string will not be the zero character.
     * The given number is guaranteed to fit within the range of a 32-bit signed integer.
     * You must not use any method provided by the library which converts/formats the number to hex directly.
     * Example 1:
     *
     * Input:
     * 26
     *
     * Output:
     * "1a"
     * Example 2:
     *
     * Input:
     * -1
     *
     * Output:
     * "ffffffff"
     * @param num
     * @return
     */
    public static String toHex(int num) {
        StringBuilder sb = new StringBuilder();
        char[] hexCharTable = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        int mask = 0xf;
        if(num == 0)return "0";
        while (num !=0)
        {
            int index = num & mask;

            sb.append(hexCharTable[index]);
            num >>>=4;
        }

        return sb.reverse().toString();
    }


    /**
     *https://leetcode.com/problems/total-hamming-distance/description/
     * 477. Total Hamming Distance
     * DescriptionHintsSubmissionsDiscussSolution
     * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
     *
     * Now your job is to find the total Hamming distance between all pairs of the given numbers.
     *
     * Example:
     * Input: 4, 14, 2
     *
     * Output: 6
     *
     * Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just
     * showing the four bits relevant in this case). So the answer will be:
     * HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
     * Note:
     * Elements of the given array are in the range of 0 to 10^9
     * Length of the array will not exceed 10^4.
     * @param nums
     * @return
     */
    public static int totalHammingDistance(int[] nums) {

        int sum = 0;
        for (int i = 0; i < 32; i++) {
            int countOne = 0;
            for (int j = 0; j < nums.length; j++) {
                countOne += (nums[j] >>>i) & 0x1;
            }
            sum += countOne*(nums.length - countOne);
        }
        return sum;
    }
}
