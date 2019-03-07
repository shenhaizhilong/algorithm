package com.hui.algorithm;

/**
 *
 * https://leetcode-cn.com/problems/palindrome-number/description/
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 *
 * 输入: 121
 * 输出: true
 * 示例 2:
 *
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 *
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 * 进阶:
 *
 * 你能不将整数转为字符串来解决这个问题吗？
 * @author: shenhaizhilong
 * @date: 2018/6/29 17:12
 */
public class Palindrome {
    public static boolean isPalindrome(int x) {

        int remain, sum = 0;
        int temp = x;
        while (x>0)
        {
            remain = x %10;
            x = x/10;
            sum = sum *10+ remain;
        }
        return sum == temp;
    }


    /**
     * https://leetcode-cn.com/problems/valid-palindrome/description/
     *验证回文串
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     *
     * 说明：本题中，我们将空字符串定义为有效的回文串。
     *
     * 示例 1:
     *
     * 输入: "A man, a plan, a canal: Panama"
     * 输出: true
     * 示例 2:
     *
     * 输入: "race a car"
     * 输出: false
     *
     * @param s
     * @return
     */
    public static boolean isPalindrome(String s)
    {
        s = s.replaceAll("[^a-zA-Z0-9]", "");
        s = s.toLowerCase();


       int left = 0;
       int right = s.length() -1;
       while (left<right)
       {
           if(s.charAt(left) != s.charAt(right))
               return false;
           left++;
           right--;
       }
       return true;
    }

    /**
     *
     *
     * 866. Prime Palindrome
     * DescriptionHintsSubmissionsDiscussSolution
     * Find the smallest prime palindrome greater than or equal to N.
     *
     * Recall that a number is prime if it's only divisors are 1 and itself, and it is greater than 1.
     *
     * For example, 2,3,5,7,11 and 13 are primes.
     *
     * Recall that a number is a palindrome if it reads the same from left to right as it does from right to left.
     *
     * For example, 12321 is a palindrome.
     *
     *
     *
     * Example 1:
     *
     * Input: 6
     * Output: 7
     * Example 2:
     *
     * Input: 8
     * Output: 11
     * Example 3:
     *
     * Input: 13
     * Output: 101
     *
     *
     * Note:
     *
     * 1 <= N <= 10^8
     * The answer is guaranteed to exist and be less than 2 * 10^8.
     *
     * @param N
     * @return
     */
    public static int primePalindrome(int N) {
        int limit = 200000000;
        while (N <= limit)
        {
            if(isPalindrome(N) && Prime.isPrime4(N))
            {
                return N;
            }

            // Our brute force works except when NN is 8 digits (as explained in
            // Approach Framework when we established that all 8 digit palindromes are not prime),
            // so we can skip all 8 digit numbers.
            //https://leetcode.com/problems/prime-palindrome/solution/
            if (10_000_000 < N && N < 100_000_000)
                N = 100_000_000;
            N++;
        }
        return -1;
    }



    public static void main(String[] args) {
//        for (int i = 0; i < Integer.MAX_VALUE; i++) {
//           if(isPalindrome(i))
//           {
//               //System.out.println(i);
//           }
//        }

        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));

        System.out.println(primePalindrome(6));
        System.out.println(primePalindrome(8));
        System.out.println(primePalindrome(13));
        for (int i = 10000000; i < 100000000; i++) {
            if( isPalindrome(i) && Prime.isPrime4(i))
            {
                System.out.println(i);
            }
        }
    }
}
