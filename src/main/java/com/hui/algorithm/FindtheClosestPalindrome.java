package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/9 16:42
 *
 *
 * 564. Find the Closest Palindrome
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an integer n, find the closest integer (not including itself), which is a palindrome.
 *
 * The 'closest' is defined as absolute difference minimized between two integers.
 *
 * Example 1:
 * Input: "123"
 * Output: "121"
 * Note:
 * The input n is a positive integer represented by string, whose length will not exceed 18.
 * If there is a tie, return the smaller one as answer.
 *
 */
public class FindtheClosestPalindrome {

    public String nearestPalindromic(String n) {
        int len = n.length();
        long target = Long.valueOf(n);
        if(target <= 10)return Long.toString(target -1);
        if(target == 11)return Long.toString(9);
        String half = n.substring(0, (len +1)/2);
        String reverse = new StringBuilder(n.substring(0,len/2)).reverse().toString();
        long curr = Long.valueOf(half + reverse);
        long halfLong = Long.valueOf(half);
        long nextHalfLong = halfLong +1;
        String nextHalfLongRev = new StringBuilder(Long.toString(nextHalfLong).substring(0,len/2)).reverse().toString();


        long nextLong =  Long.valueOf( nextHalfLong  + nextHalfLongRev);
        long prevLong =  -1;
        if(halfLong % 10 == 0)
        {
            prevLong = (long)Math.pow(10,len -1) -1;
        }else {
            long prevHalfLong = halfLong -1;
            String prevHalfLongRev = new StringBuilder(Long.toString(prevHalfLong).substring(0,len/2)).reverse().toString();
            prevLong = Long.valueOf( prevHalfLong +prevHalfLongRev);
        }

        long diffCurr = Math.abs(curr - target);
        long diffNext = Math.abs(nextLong - target);
        long diffPrev = Math.abs(prevLong - target);
        long min = Math.min(diffCurr, diffNext);
        min = Math.min(min, diffPrev);

        if(min == 0)
        {
            return diffPrev <= diffNext ? Long.toString(prevLong): Long.toString(nextLong);
        }

        if(diffPrev <= diffCurr && diffPrev <= diffNext)return Long.toString(prevLong);
        if(diffCurr <= diffNext && diffCurr < diffPrev) return Long.toString(curr);
        return Long.toString(nextLong);


    }

    public static void main(String[] args) {

        FindtheClosestPalindrome closestPalindrome = new FindtheClosestPalindrome();
        System.out.println(closestPalindrome.nearestPalindromic("45654"));
//        System.out.println(closestPalindrome.nearestPalindromic("100"));
//        System.out.println(closestPalindrome.nearestPalindromic("10000"));
//        System.out.println(closestPalindrome.nearestPalindromic("1000"));
       // System.out.println(closestPalindrome.nearestPalindromic("123"));
//        for (int i = 1; i <= 100; i++) {
//            System.out.println(i + ", " + closestPalindrome.nearestPalindromic(Long.toString(i)));
//        }

    }

}
