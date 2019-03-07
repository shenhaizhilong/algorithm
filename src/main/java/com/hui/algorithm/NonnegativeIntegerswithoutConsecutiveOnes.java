package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/14 10:46
 *
 *600. Non-negative Integers without Consecutive Ones
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a positive integer n, find the number of non-negative integers less than or equal to n, whose binary representations do NOT contain consecutive ones.
 *
 * Example 1:
 * Input: 5
 * Output: 5
 * Explanation:
 * Here are the non-negative integers <= 5 with their corresponding binary representations:
 * 0 : 0
 * 1 : 1
 * 2 : 10
 * 3 : 11
 * 4 : 100
 * 5 : 101
 * Among them, only integer 3 disobeys the rule (two consecutive ones) and the other 5 satisfy the rule.
 * Note: 1 <= n <= 109
 *
 */
public class NonnegativeIntegerswithoutConsecutiveOnes {


    public int findIntegers(int num) {
        int ans = 0;
        for (int i = 0; i <= num; i++) {
            if(!hasConsecutiveOnes(i))
            {
                ans++;
            }
        }
        return ans;
    }

    private boolean hasConsecutiveOnes(int n)
    {
        int prev = 0;

        while ( n > 0)
        {
            int curr = n &1;
            if(prev == 1 && curr ==1)return true;
            prev = curr;
            n = n >>1;
        }
        return false;
    }


    public int findIntegers2(int num) {
        int ans = 0;
        for (int i = 0; i <= num; i++) {
            if(!hasConsecutiveOnes(i))
            {
                ans++;
            }
        }
        return ans;
    }
    private boolean hasConsecutiveOnes2(int n)
    {
        while ( n > 0)
        {
            int r = n %10;
            if(r == 3)return true;
            n = n >>1;
        }
        return false;
    }


    public int findIntegers3(int num) {

        // f(k) = f(k-1) + f(k-2), where k>=2, f(0) = 1, f(1) =2;
        // f(k) means the count    without Consecutive Ones, from 0, to 2^k -1;
        // k is the length of binary string , k = len(bin(2^k -1)) for example : f(5) = count( 00000 - 11111),
        // count( 00000 - 11111) = count(00000 - 01111) + count(10000 - 10111) + count(11000 - 11111)
        //                       = count(00000 - 01111) + count(10000 - 10111)
        //                       = f(4) + f(3)
        int[] fn = new int[32];
        fn[0] = 1;
        fn[1] = 2;
        for (int i = 2; i < fn.length; i++) {
            fn[i] = fn[i-1] + fn[i-2];
        }

        char[] binChr = Integer.toBinaryString(num).toCharArray();
        int len = binChr.length -1;
        int ans = 0;
        boolean preBit = false;
        for (int i = 0; i < binChr.length; i++) {
            if(binChr[i] == '1')
            {
                ans += fn[len - i];
                if(preBit)return ans;
                preBit = true;
            }
            else {
                preBit = false;
            }
        }

        // including it's self.
        return ans +1;
    }





    /**
     *
     * https://leetcode.com/problems/non-negative-integers-without-consecutive-ones/discuss/103754/C++-Non-DP-O(32)-Fibonacci-solution
     *
     * The solution is based on 2 facts:
     *
     * the number of length k string without consecutive 1 is Fibonacci sequence f(k);
     * For example, if k = 5, the range is 00000-11111. We can consider it as two ranges, which are 00000-01111 and 10000-10111. Any number >= 11000 is not allowed due to consecutive 1. The first case is actually f(4), and the second case is f(3), so f(5)= f(4)+f(3).
     * Scan the number from most significant digit, i.e. left to right, in binary format. If we find a '1' with k digits to the right, count increases by f(k) because we can put a '0' at this digit and any valid length k string behind; After that, we continue the loop to consider the remaining cases, i.e., we put a '1' at this digit. If consecutive 1s are found, we exit the loop and return the answer. By the end of the loop, we return count+1 to include the number n itself.
     * For example, if n is 10010110,
     * we find first '1' at 7 digits to the right, we add range 00000000-01111111, which is f(7);
     * second '1' at 4 digits to the right, add range 10000000-10001111, f(4);
     * third '1' at 2 digits to the right, add range 10010000-10010011, f(2);
     * fourth '1' at 1 digits to the right, add range 10010100-10010101, f(1);
     * Those ranges are continuous from 00000000 to 10010101. And any greater number <= n will have consecutive 1.
     * @param num
     * @return
     */
    public int findIntegers4(int num) {

        // f(k) = f(k-1) + f(k-2), where k>=2, f(0) = 1, f(1) =2;
        // f(k) means the count    without Consecutive Ones, from 0, to 2^k -1;
        // k is the length of binary string , k = len(bin(2^k -1)) for example : f(5) = count( 00000 - 11111),
        // count( 00000 - 11111) = count(00000 - 01111) + count(10000 - 10111) + count(11000 - 11111)
        //                       = count(00000 - 01111) + count(10000 - 10111)
        //                       = f(4) + f(3)
        int[] fn = new int[32];
        fn[0] = 1;
        fn[1] = 2;
        for (int i = 2; i < fn.length; i++) {
            fn[i] = fn[i-1] + fn[i-2];
        }


        int ans = 0;
        int k = 30;
        boolean preBit = false;
        while (k >=0)
        {
            if( (num & (1<<k)) != 0)
            {
                ans += fn[k];
                if(preBit)return ans;
                preBit = true;
            }else {
                preBit = false;
            }
            k--;

        }

        // including it's self.
        return ans +1;
    }


    /**
     *
     *
     * Count number of binary strings without consecutive 1’s
     * Given a positive integer N, count all possible distinct binary strings of length N such that there are no consecutive 1’s.
     * Examples:
     *
     * dp method
     *
     * This problem can be solved using Dynamic Programming. Let a[i] be the number of binary strings of length i which do not contain any two consecutive 1’s and which end in 0. Similarly, let b[i] be the number of such strings which end in 1. We can append either 0 or 1 to a string ending in 0, but we can only append 0 to a string ending in 1. This yields the recurrence relation:
     *
     * a[i] = a[i - 1] + b[i - 1]
     * b[i] = a[i - 1]
     * The base cases of above recurrence are a[1] = b[1] = 1. The total number of strings of length i is just a[i] + b[i].
     *
     * @param n is the binary string length
     * @return
     */
    public int kLen(int n) {
        int[] endingZero = new int[n];
        int[] endingOne = new int[n];
        endingOne[0] = 1;
        endingZero[0] =1;
        for (int i = 1; i < n; i++) {
            endingZero[i] = endingOne[i-1] + endingZero[i -1];
            endingOne[i] = endingZero[i -1];
        }
        return endingOne[n-1] + endingZero[n-1];
    }

    public int kLen2(int n) {
        if(n == 0)return 1;
        if(n == 1)return 2;
        int[] fn = new int[n];
        fn[0] =1;
        fn[1] = 2;
        for (int i = 1; i < n; i++) {
            fn[i] = fn[i-2] + fn[i -1];
        }
        return fn[n-2] + fn[n-1];
    }

    public int kLen3(int n) {
        if(n == 0)return 1;
        if(n == 1)return 2;
        int  fnPrevPrev =1;
        int  fnPrev = 2;
        for (int i = 1; i < n; i++) {
            int fn = fnPrevPrev + fnPrev;
            fnPrevPrev = fnPrev;
            fnPrev = fn;
        }
        return fnPrevPrev + fnPrev;
    }

    public static void main(String[] args) {

        NonnegativeIntegerswithoutConsecutiveOnes consecutiveOnes = new NonnegativeIntegerswithoutConsecutiveOnes();
//        System.out.println(150 + "," + Integer.toBinaryString(150) +"," + consecutiveOnes.findIntegers(150));
//        System.out.println(150 + "," + Integer.toBinaryString(150) +"," + consecutiveOnes.findIntegers3(150));
//        System.out.println(150 + "," + Integer.toBinaryString(150) +"," + consecutiveOnes.findIntegers4(150));
        System.out.println(99 + "," + Integer.toBinaryString(99) +"," + consecutiveOnes.findIntegers4(99));
        System.out.println("********************************");
        for (int i = 0; i < 100; i++) {
            System.out.println(i + "," + Integer.toBinaryString(i) +"," + consecutiveOnes.findIntegers3(i));
        }

        System.out.println("********************************");
        for (int i = 0; i < 100; i++) {
            System.out.println(i + "," + Integer.toBinaryString(i) +"," + consecutiveOnes.findIntegers4(i));
        }
    }
}
