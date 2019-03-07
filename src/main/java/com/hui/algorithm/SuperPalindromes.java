package com.hui.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/8 23:08
 *
 *
 *
 * 906. Super Palindromes
 * DescriptionHintsSubmissionsDiscussSolution
 * Let's say a positive integer is a superpalindrome if it is a palindrome, and it is also the square of a palindrome.
 *
 * Now, given two positive integers L and R (represented as strings), return the number of superpalindromes in the inclusive range [L, R].
 *
 *
 *
 * Example 1:
 *
 * Input: L = "4", R = "1000"
 * Output: 4
 * Explanation: 4, 9, 121, and 484 are superpalindromes.
 * Note that 676 is not a superpalindrome: 26 * 26 = 676, but 26 is not a palindrome.
 *
 *
 * Note:
 *
 * 1 <= len(L) <= 18
 * 1 <= len(R) <= 18
 * L and R are strings representing integers in the range [1, 10^18).
 * int(L) <= int(R)
 *
 *
 */
public class SuperPalindromes {

    public int superpalindromesInRange(String L, String R) {
        long left = Long.valueOf(L);
        long right = Long.valueOf(R);
        int result = 0;
        for (long i = (long)Math.sqrt(left); i*i   <= right ; ) {
            long p = nextP(i);
            long product = p*p;
            if(product <= right && isPalindromes(Long.toString(product)))
            {
                result++;
                System.out.println(p + ","+ product);
            }
            i = p +1;
        }
        return result;

    }

    private long nextP(long l)
    {
        String s = Long.toString(l);
        int N = s.length();
        String half = s.substring(0, (N + 1)/2);
        String reverse = new StringBuilder(half.substring(0, N/2)).reverse().toString();
        long first = Long.valueOf(half + reverse);
        if(first >= l)return first;
        String nexthalf = Long.toString(Long.valueOf(half) +1);
        String reverseNextHalf = new StringBuilder(nexthalf.substring(0,N/2)).reverse().toString();
        long second = Long.valueOf(nexthalf + reverseNextHalf);
        return second;

    }


    private boolean isPalindromes(String s)
    {
        int i = 0;
        int j = s.length() -1;
        while (i < j)
        {
            if(s.charAt(i++) != s.charAt(j--))return false;
        }
        return true;
    }
    /**
     *
     * super palindromes, after 3, all i is combination of 0,1,2 , middle digit is 0,1,2 or ""
     *
     * (i,i^2)
     * 1,1
     * 2,4
     * 3,9
     * -------
     * 11,121
     * 22,484
     * 101,10201
     * 111,12321
     * 121,14641
     * 202,40804
     * 212,44944
     * 1001,1002001
     * 1111,1234321
     * 2002,4008004
     * 10001,100020001
     * 10101,102030201
     * 10201,104060401
     * 11011,121242121
     * 11111,123454321
     * 11211,125686521
     * 20002,400080004
     * 20102,404090404
     *
     */
    char[] chars = new char[] {'0', '1', '2'};
    int[] consts = new int[]{1,4,9};

    public int superpalindromesInRange2(String L, String R) {
        long left = Long.parseLong(L);
        long right = Long.parseLong(R);
        int res = 0;
        for (int i = 0; i < consts.length; i++) {
            if(consts[i] >= left && consts[i] <= right)res++;
        }
        res += dfs("", new StringBuilder(), R.length() / 2 + 1, left, right);  // number length is even, we can image middle is ""
        res += dfs("0", new StringBuilder(), R.length() / 2 + 1, left, right); // middle is 0
        res += dfs("1", new StringBuilder(), R.length() / 2 + 1, left, right); // middle is 1
        res += dfs("2", new StringBuilder(), R.length() / 2 + 1, left, right); // middle is 2
        return res;
    }

    private int dfs(String mid, StringBuilder sb, int len, long left, long right) {
        if (sb.length() * 2 + mid.length() > len) {
            return 0;
        } else {
            int res = 0;
            String s = sb.toString() + mid + sb.reverse().toString();
            sb.reverse();
            long number = s.length() == 0 ? 0 : Long.parseLong(s);
            long product = number * number;
            if (product >= 10 && product >= left && product <= right && isPalindromes(Long.toString(product))) {
               // System.out.println(number + "," + product);
                res++;
            }
            for (int i = 0; i < 3; i++) {
                if (sb.length() == 0 && i == 0) {
                    continue;
                }
                sb.append(chars[i]);
                res += dfs(mid, sb, len, left, right);
                sb.setLength(sb.length() - 1);
            }
            return res;
        }
    }


    public int superpalindromesInRange3(String L, String R) {

        long left = Long.parseLong(L);
        long right = Long.parseLong(R);
        int ans = 0;
        List<String> prev = new ArrayList<>();
        List<String> curr = null;
        // add 1,4,9 if its in range [left,right]
        for (int i = 0; i < consts.length; i++) {
            if(consts[i] >= left && consts[i] <= right)ans++;
        }


        prev.add("11");
        prev.add("22");
        long i = 11L;
        while (i*i <= right)
        {
            curr = new ArrayList<>();
            for (String digit: prev)
            {
                long p = Long.valueOf(digit);
                long product = p*p;
                if(product >= left && product <= right && isPalindromes(Long.toString(product)))
                {
                   // System.out.println(p + "," + product);
                    ans++;
                }

                StringBuilder sb = new StringBuilder(digit.substring(0,digit.length()/2));
                sb.append(chars[0]);
                sb.append(digit.substring(digit.length()/2));
                if(isPalindromes(sb.toString()))
                {
                    curr.add(sb.toString());
                }
                for (int j = 1; j <chars.length ; j++) {
                    sb.setCharAt(digit.length()/2, chars[j]);
                    if(isPalindromes(sb.toString()))
                    {
                        curr.add(sb.toString());
                    }
                }

            }
            prev = curr;
            i = Long.valueOf(prev.get(0));
        }

        return ans;

    }


    public static void main(String[] args) {
        SuperPalindromes superPalindromes = new SuperPalindromes();
        superPalindromes.superpalindromesInRange("1", Integer.toString(Integer.MAX_VALUE));
        System.out.println("***************************");
       // System.out.println(superPalindromes.superpalindromesInRange2("1", Integer.toString(Integer.MAX_VALUE)));

       // System.out.println(superPalindromes.superpalindromesInRange3("1", Integer.toString(Integer.MAX_VALUE)));
        System.out.println(superPalindromes.superpalindromesInRange3("1", Integer.toString(19028)));

    }
}
