package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/10 16:07
 */
public class DecodeWays {


    /**
     *
     *91. Decode Ways
     * DescriptionHintsSubmissionsDiscussSolution
     * A message containing letters from A-Z is being encoded to numbers using the following mapping:
     *
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     * Given a non-empty string containing only digits, determine the total number of ways to decode it.
     *
     * Example 1:
     *
     * Input: "12"
     * Output: 2
     * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
     * Example 2:
     *
     * Input: "226"
     * Output: 3
     * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
     * @param s
     * @return
     */
    public int numDecodings(String s) {

        if(s == null || s.isEmpty())return 0;
        int len = s.length();

        // dp[n] means how many ways to decode the String S, S len is n
        int[] dp = new int[len +1];
        dp[1] = s.charAt(0) == '0' ? 0:1;
        if(len == 1)return dp[1];

        if(s.charAt(1) !='0')
        {
            dp[2] = dp[1];
        }

        int t = Integer.parseInt(s.substring(0,2));
        if(t >= 10 && t <=26)
        {
            dp[2]++;
        }

        for (int i = 3; i <= len ; i++) {
            if(s.charAt(i -1) != '0')
            {
                dp[i] = dp[i-1];
            }
            int twoDigits = Integer.parseInt(s.substring(i-2,i));
            if(twoDigits >=10 && twoDigits <=26)
            {
                dp[i] += dp[i-2];
            }
        }

        return dp[len];

    }

    public static void main(String[] args) {
        DecodeWays decodeWays = new DecodeWays();
        System.out.println(decodeWays.numDecodings("110"));
        System.out.println(decodeWays.numDecodings("111"));
        System.out.println(decodeWays.numDecodings("226"));
        System.out.println(decodeWays.numDecodings("22"));
        System.out.println(decodeWays.numDecodings("227"));
        System.out.println(decodeWays.numDecodings("10"));
    }
}
