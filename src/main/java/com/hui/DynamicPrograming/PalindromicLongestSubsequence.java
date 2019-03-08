package com.hui.DynamicPrograming;

/**
 *
 * 516. Longest Palindromic Subsequence
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 * Input:
 *
 * "bbbab"
 * Output:
 * 4
 * One possible longest palindromic subsequence is "bbbb".
 * Example 2:
 * Input:
 *
 * "cbbd"
 * Output:
 * 2
 * One possible longest palindromic subsequence is "bb".
 *
 * @author: shenhaizhilong
 * @date: 2018/9/29 12:39
 */
public class PalindromicLongestSubsequence {

    public int longestPalindromeSubseq(String s) {
        if(s == null || s.length() < 1)return 0;
        char[] data = s.toCharArray();
        // dp[i][j] 从i开始到J 的最长回文子序列的长度
        int[][] dp = new int[data.length][data.length];
        for (int i = 0; i < data.length; i++) {
            dp[i][i] = 1;   // every single character is a palindrome by itself of length 1
        }

        for (int subLen = 2; subLen <= data.length; subLen++) {
            for (int i = 0; i <= dp.length - subLen; i++) {
                int j = i + subLen - 1;
                if(data[i] == data[j] && subLen == 2)
                {
                    dp[i][j] = 2;          // first and last characters are same and sublen = 2
                }else if(data[i] == data[j])
                {
                    dp[i][j] = dp[i + 1][j -1] + 2;   // first and last characters are same
                }else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j-1]); // first and last characters are not same
                }
            }
        }
        printMatrix(dp);

        return dp[0][dp.length -1];
    }

    public int longestPalindromeSubseq2(String s) {
        // dp[i][j] 从i开始到J 的最长回文子序列的长度
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int i = len -1; i >=0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < len ; j++) {
                if(s.charAt(i) == s.charAt(j))
                {
                    dp[i][j] = dp[i + 1][j-1] + 2;
                }else {
                    dp[i][j] = Math.max(dp[i +1][j], dp[i][j-1]);
                }
            }
            printMatrix(dp);
            System.out.println("****************************");
        }

        printMatrix(dp);
        return dp[0][len -1];

    }

    public int longestPalindromeSubseq3(String s) {
        // dp[i][j] 从i开始到J 的最长回文子序列的长度
        int len = s.length();
        int[] pre = new int[len];
        int[] curr = new int[len];
        for (int i = len -1; i >=0; i--) {
            curr[i] = 1;
            for (int j = i + 1; j < len ; j++) {
                if(s.charAt(i) == s.charAt(j))
                {
                    curr[j] = pre[j-1] + 2;
                }else {
                    curr[j] = Math.max(pre[j], curr[j-1]);
                }
            }
            int[] temp = curr;
            curr = pre;
            pre = temp;
           // System.out.println(Arrays.toString(pre));
        }
        return pre[len -1];

    }


    public void printMatrix(int[][] matrix)
    {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(" " + matrix[i][j]);
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {

        PalindromicLongestSubsequence palindromicLongestSubsequence = new PalindromicLongestSubsequence();
       // System.out.println(palindromicLongestSubsequence.longestPalindromeSubseq("AABCDEBAZ"));
        System.out.println(palindromicLongestSubsequence.longestPalindromeSubseq2("AABCDEBAZZ"));
        System.out.println(palindromicLongestSubsequence.longestPalindromeSubseq3("AABCDEBAZZ"));
    }


}
