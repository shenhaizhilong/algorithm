package com.hui.Math;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/29 17:54
 */
public class LargestPalindromeProduct {


    /**
     *
     * 479. Largest Palindrome Product
     * DescriptionHintsSubmissionsDiscussSolution
     * Find the largest palindrome made from the product of two n-digit numbers.
     *
     * Since the result could be very large, you should return the largest palindrome mod 1337.
     *
     * Example:
     *
     * Input: 2
     *
     * Output: 987
     *
     * Explanation: 99 x 91 = 9009, 9009 % 1337 = 987
     *
     * Note:
     *
     * The range of n is [1,8].
     *
     *
     * Seems that if n is an even number, you can guess out the largest palindromic number.
     *
     * n=2:
     * 101*99=99,99; take out the middle 99, which is 990/99=10, then it is (101-10)*99=91*99=9009
     *
     * n=4:
     * 10001*9999=9999,9999, take out the middle 9999, which is 999900/9999=100, then it is (10001-100)*9999=9901*9999=99000099
     *
     * and so on.
     * @param n
     * @return
     */
    public static int largestPalindrome(int n) {
        if (n==1) return 9;
        int max=(int)Math.pow(10, n)-1;
        for (int v=max;v>max/10;v--) {
            long u=Long.valueOf(v+new StringBuilder().append(v).reverse().toString());
            for (long x=max;x*x>=u;x--)
                if (u%x==0)
                    return (int)(u%1337);
        }
        return 0;
    }



    public static int largestPalindrome2(int n) {
        int[] x = {9,99,993,9999,99979,999999,9998017,99999999};
        int[] y = {1,91,913,9901,99681,999001,9997647,99990001};

        return ((x[n-1]%1337)*(y[n-1]%1337))%1337;
    }

    public static void main(String[] args) {

        for (int i = 1; i < 9; i++) {
            System.out.println(largestPalindrome(i));
            System.out.println(largestPalindrome2(i));
        }


    }
}
