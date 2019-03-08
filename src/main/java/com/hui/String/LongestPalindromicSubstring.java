package com.hui.String;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/28 11:45
 */
public class LongestPalindromicSubstring {


    /**
     *5. Longest Palindromic Substring
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
     *
     * Example 1:
     *
     * Input: "babad"
     * Output: "bab"
     * Note: "aba" is also a valid answer.
     * Example 2:
     *
     * Input: "cbbd"
     * Output: "bb"
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {

        if(s == null || s.length() < 2)return s;
        int left = 0, right = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);  // center is i
            int len2 = expandAroundCenter(s, i, i + 1);  // center is between i and i + 1
            int len = Math.max(len1, len2); // find the max len
            if(len > right - left)
            {
                left = i - (len - 1)/2;
                right = i + len/2;
            }
        }


        return s.substring(left, right + 1);
    }

    private int expandAroundCenter(String s, int left, int right)
    {
        int L= left;
        int R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R))
        {
            L--;
            R++;
        }
        return R - L -1;
    }


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
     * http://www.cnblogs.com/grandyang/p/4475985.html
     * https://articles.leetcode.com/longest-palindromic-substring-part-ii/
     * https://algs4.cs.princeton.edu/53substring/Manacher.java.html
     * https://www.felix021.com/blog/read.php?2040
     *
     * @param s
     * @return
     */
    public String manacher(String s)
    {
        char[] T = preProcess(s);
        int n = T.length;
        int[] P = new int[n];
        int C = 0, R = 0;


        int maxLen = 0;
        int centerIndex = 0;

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

            // Find the maximum element in P.
            if (P[i] > maxLen) {
                maxLen = P[i];
                centerIndex = i;
            }
        }

        return s.substring((centerIndex  - maxLen -1)/2, (centerIndex -1 + maxLen)/2);

    }



    public String longestPalindrome2(String s)
    {
        if(s == null || s.length() < 2)return s;
        int n = s.length();

        // dp[i][j] means whether s[i,j] is palindrome
        boolean[][] dp = new boolean[n][n];
        String res = "";
        for(int i = n -1; i >= 0; i--)
        {
            for(int j = i; j < n; j++)
            {
                if(s.charAt(i) == s.charAt(j) && (j - i +1 <=3 || dp[i+1][j -1]))
                {
                    dp[i][j] = true;
                }

                if(dp[i][j] && (j - i +1 > res.length()))
                {
                    res = s.substring(i,j +1);
                }
            }
        }

        return res;

    }

    public static void main(String[] args) {

        LongestPalindromicSubstring longestPalindromicSubstring = new LongestPalindromicSubstring();
      //  System.out.println(longestPalindromicSubstring.manacher("cbbd"));
     //   System.out.println(longestPalindromicSubstring.manacher("babad"));
     //   System.out.println(longestPalindromicSubstring.manacher("abcbd"));
        System.out.println(longestPalindromicSubstring.manacher("aaaaa"));

    }
}
