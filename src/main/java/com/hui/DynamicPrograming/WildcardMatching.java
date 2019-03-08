package com.hui.DynamicPrograming;

import java.util.Arrays;

import static com.hui.Array.Matrix.print;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/21 18:30
 *
 *
 * 44. Wildcard Matching
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
 *
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 *
 * Note:
 *
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like ? or *.
 * Example 1:
 *
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 *
 * Input:
 * s = "aa"
 * p = "*"
 * Output: true
 * Explanation: '*' matches any sequence.
 * Example 3:
 *
 * Input:
 * s = "cb"
 * p = "?a"
 * Output: false
 * Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 * Example 4:
 *
 * Input:
 * s = "adceb"
 * p = "*a*b"
 * Output: true
 * Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
 * Example 5:
 *
 * Input:
 * s = "acdcb"
 * p = "a*c?b"
 * Output: false
 *
 *
 *
 *  1. p[j] != '*',  s[i] == p[j]  or p[i] == ?
 *         dp[i][j] = dp[i-1][j-1]
 *
 *
 *    2. p[j] == '*',
 *       dp[i][j] = dp[i][j -1]     // a* as a, * means empty
 *                 or dp[i -1][j]  // a*, * means a sequence
 *
 *
 */
public class WildcardMatching {

    public boolean isMatch(String s, String p) {

        int M = s.length();
        int N = p.length();
        // dp[i][j] means isMathch(S.sub(0,i), P.sub(0,j)); i in range[1,M], j in range[1,N]
        boolean[][] dp = new boolean[M +1][N +1];
        dp[0][0] = true;
        for (int i = 0; i < p.length(); i++) {
            if(p.charAt(i) == '*' && dp[0][i])
            {
                dp[0][i +1] = true;
            }
        }

        for (int i = 0; i < s.length() ; i++) {

            for (int j = 0; j < p.length(); j++) {
                if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')
                {
                    dp[i +1][j +1] = dp[i][j];
                }else if (p.charAt(j) == '*')
                {
                    dp[i +1][j +1] = dp[i +1][j] || dp[i][j +1];
                }
            }
        }

          print(dp);
        return dp[M][N];
    }

    public boolean isMatch2(String s, String p) {

        int N = p.length();
        // dp[i][j] means isMathch(S.sub(0,i), P.sub(0,j)); i in range[1,M], j in range[1,N]
        boolean[] dp = new boolean[N +1];
        boolean[] lastdp = new boolean[N +1];
        dp[0] = true;
        lastdp[0] = true;
        for (int i = 0; i < p.length(); i++) {
            if(p.charAt(i) == '*' && dp[i])
            {
                dp[i +1] = true;
                lastdp[i+1] = true;
            }
        }

        for (int i = 0; i < s.length() ; i++) {

            for (int j = 0; j < p.length(); j++) {
                if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')
                {
                    dp[j +1] = lastdp[j];
                }else if (p.charAt(j) == '*')
                {
                    dp[j +1] = dp[j] || lastdp[j +1];

                }else{
                    dp[j +1] = false;
                }

            }

            dp[0] = false;
            lastdp = Arrays.copyOf(dp,dp.length);
        }
        return dp[N];
    }

    public static void main(String[] args) {

        WildcardMatching wildcardMatching = new WildcardMatching();
//        System.out.println(wildcardMatching.isMatch("acdcb","a*c?b"));
//        System.out.println(wildcardMatching.isMatch("acdcb","*a*b"));
//
//        System.out.println(wildcardMatching.isMatch("aa","a"));
//        System.out.println(wildcardMatching.isMatch("cb","?a"));
//        System.out.println(wildcardMatching.isMatch("aab","c*a*b"));
//        System.out.println("**********");
//
//        System.out.println(wildcardMatching.isMatch2("acdcb","a*c?b"));
//        System.out.println(wildcardMatching.isMatch2("acdcb","*a*b"));
//
//        System.out.println(wildcardMatching.isMatch2("aa","a"));
//        System.out.println(wildcardMatching.isMatch2("cb","?a"));
//        System.out.println(wildcardMatching.isMatch2("aab","c*a*b"));

        System.out.println(wildcardMatching.isMatch("aa","aa"));
        System.out.println(wildcardMatching.isMatch2("aa","aa"));


    }
}
