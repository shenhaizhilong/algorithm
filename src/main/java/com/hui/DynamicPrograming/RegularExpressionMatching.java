package com.hui.DynamicPrograming;

import com.hui.Array.Matrix;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/21 15:23
 *
 *
 * 10. Regular Expression Matching
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
 *
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 *
 * Note:
 *
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like . or *.
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
 * p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 * Example 3:
 *
 * Input:
 * s = "ab"
 * p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 * Example 4:
 *
 * Input:
 * s = "aab"
 * p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
 * Example 5:
 *
 * Input:
 * s = "mississippi"
 * p = "mis*is*p*."
 * Output: false
 *
 *
 *https://leetcode.com/problems/regular-expression-matching/discuss/5651/Easy-DP-Java-Solution-with-detailed-Explanation?page=3
 *
 * Here are some conditions to figure out, then the logic can be very straightforward.
 *
 * 1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
 * 2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
 * 3, If p.charAt(j) == '*':
 *    here are two sub conditions:
 *                1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
 *                2   if p.charAt(j-1) == s.charAt(i) or p.charAt(i-1) == '.':
 *                               dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a
 *                            or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
 *                            or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty
 *
 * 为了计算方便对dp[i][j] 平移：
 * 1   if p.charAt(j-1) != s.charAt(i) : dp[i +1][j +1] = dp[i+1][j-1]  //in this case, a* only counts as empty
 *  *                2   if p.charAt(j-1) == s.charAt(i) or p.charAt(i-1) == '.':
 *  *                               dp[i +1][j +1] = dp[i][j +1]    //in this case, a* counts as multiple a
 *  *                            or dp[i +1][j +1] = dp[i+1][j]     // in this case, a* counts as single a
 *  *                            or dp[i +1][j +1] = dp[i+1][j-1]   // in this case, a* counts as empty
 *
 *
 *   dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a
 *    means s.charAt(i) == char , So we need to check, if s.substring(0, i-1 +1)  also matches p.substring(0, j +1).
 *
 *
 */
public class RegularExpressionMatching {


    public boolean isMatch(String s, String p) {
        int M = s.length();
        int N = p.length();

        // dp[i][j] means isMathch(S.sub(0,i), P.sub(0,j)); i in range[1,M], j in range[1,N]
        boolean[][] dp = new boolean[M +1][N +1];
        dp[0][0] = true;

        // for an example : a*
        // 对于长度为0 的 目标字符串是否与pattern.sub(0, i) 匹配, i in range[1,N],  pattern can't be "*"
        for (int i = 1; i < p.length(); i++) {
            if(p.charAt(i) == '*' && dp[0][i-1])  //
            {
                dp[0][i+1] = true;
            }
        }

        for (int i = 0; i < s.length() ; i++) {

            for (int j = 0; j < p.length(); j++) {
                if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '.')
                {
                    dp[i +1][j +1] = dp[i][j];
                }else if (p.charAt(j) == '*')
                {

                    if(p.charAt(j -1) != s.charAt(i) &&  p.charAt(j -1) != '.')
                    {
                        dp[i +1][j +1] = dp[i +1][j -1];
                    }else{
                        dp[i +1][j +1] = (dp[i+1][j -1] || dp[i+1][j] || dp[i][j +1]);
                    }
                }
            }
        }

        Matrix.print(dp);
        return dp[M][N];
    }




    public static void main(String[] args) {
            RegularExpressionMatching regularExpressionMatching = new RegularExpressionMatching();
       // System.out.println(regularExpressionMatching.isMatch("bbabb","b*ab*"));
        System.out.println(regularExpressionMatching.isMatch("aa","a"));
    }
}
