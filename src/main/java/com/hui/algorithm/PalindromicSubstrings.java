package com.hui.algorithm;

/**
 *
 *
 * 647. Palindromic Substrings
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a string, your task is to count how many palindromic substrings in this string.
 *
 * The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.
 *
 * Example 1:
 * Input: "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 * Example 2:
 * Input: "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 * Note:
 * The input string length won't exceed 1000.
 *
 *
 *
 * @author: shenhaizhilong
 * @date: 2018/9/29 11:46
 */
public class PalindromicSubstrings {


    /**
     *
     *    pre process Transform s into t.
     *      For example, if s = "abba", then t = "^#a#b#b#a#$"
     *      the # are interleaved to avoid even/odd-length palindromes uniformly
     *     ^and $ are prepended and appended to each end to avoid bounds checking
     *
     * @param s
     * @return
     */
    private char[] preProcess(String s)
    {
        int n = s.length();
        char[] t = new char[2*n + 3];
        t[0] = '^';
        t[t.length -1] = '$';
        for (int i = 0; i < s.length(); i++) {
            t[2*i + 1] = '#';
            t[2*i + 2] = s.charAt(i);
        }
        t[t.length -2] = '#';

        return t;
    }


    /**
     *
     * https://leetcode.com/problems/palindromic-substrings/solution/
     *
     * @param S
     * @return
     */
    public int countSubstrings(String S) {

        char[] T = preProcess(S);
        int n = T.length;

        // P[i] means,
        int[] P = new int[n];
        int C = 0, R = 0;

        int res = 0;

        for (int i = 1; i < n -1; i++) {
            int i_mirror = 2*C - i; // due to  i' = C - (i - C)
            P[i] = (R > i) ? Math.min(R -i, P[i_mirror]):0;

            // // attempt to expand palindrome centered at i
            while (T[i  + P[i] + 1]  == T[i - P[i] - 1])
            {
                P[i]++;
            }
            // if palindrome centered at i expands past right,
            // adjust center based on expanded palindrome.
            if(i + P[i] > R)
            {
                C = i;
                R = i + P[i];
            }

            // We are dividing by 2 because we were using
            // half-lengths instead of lengths. For example we actually had the palindrome a#b#c#d#e#d#c#b#a, so our length is twice as big.
            res += (P[i] + 1)/2;
        }

        return res;
    }

    public int countSubstrings2(String S) {
        if(S == null || S.length() < 1)return 0;
        int res = 0;
        for (int i = 0; i < S.length(); i++) {
            res += expand(S, i, i);  // center is i
            res += expand(S, i, i + 1);  // center is between i and i + 1

        }
        return res;
    }

    private int expand(String s, int left, int right)
    {
        int res = 0;
        int L = left;
        int R = right;
        while (L >=0 && R < s.length() && s.charAt(L) == s.charAt(R))
        {
            L--;
            R++;
            res++;
        }
        return res;
    }




        public static void main(String[] args) {

        PalindromicSubstrings palindromicSubstrings = new PalindromicSubstrings();
        System.out.println(palindromicSubstrings.countSubstrings("aaa"));
            System.out.println(palindromicSubstrings.countSubstrings2("aaa"));
    }
}
