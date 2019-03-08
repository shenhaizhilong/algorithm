package com.hui.DynamicPrograming;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2019/1/19 16:12
 *
 *
 *  str1 = "fish"
 *  str2 = "fosh"
 *  the longest common sub string is "sh", so return len(sh) == 2
 *       f      i    s    h
 *   f   1      0    0    0
 *   o   0      0    0    0
 *   s   0      0    1    0
 *   h   0      0    0    2
 *
 *
 */
public class LongestCommonSubString {

    public int LengthOfLongestCommonSubString(String A, String B)
    {
        int M = A.length();
        int N = B.length();
        int[][] dp = new int[M +1][N +1];
        int max = 0;
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                if(A.charAt(i -1) == B.charAt(j -1))
                {
                    dp[i][j] = dp[i -1][j -1] + 1;
                }else {
                    dp[i][j] = 0;
                }
                max = Math.max(dp[i][j], max);
            }
        }
      //  Matrix.print(dp);
        return max;
    }


    public int LengthOfLongestCommonSubString2(String A, String B)
    {
        int M = A.length();
        int N = B.length();
        int[] dp = new int[N +1];
        int[] last = new int[N +1];
        int max = 0;
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                dp[j] = 0;
                if(A.charAt(i -1) == B.charAt(j -1))
                {
                    dp[j] = last[j -1] + 1;
                }
                max = Math.max(dp[j], max);
            }
            last = Arrays.copyOf(dp,dp.length);
        }

        return max;
    }

    public static void main(String[] args) {
        LongestCommonSubString longestCommonSubString = new LongestCommonSubString();
        System.out.println("************");
        System.out.println(longestCommonSubString.LengthOfLongestCommonSubString("fish","fosh"));
        System.out.println(longestCommonSubString.LengthOfLongestCommonSubString("presum","persu"));
        System.out.println(longestCommonSubString.LengthOfLongestCommonSubString("vista","hish"));

        System.out.println("************");
        System.out.println(longestCommonSubString.LengthOfLongestCommonSubString2("fish","fosh"));
        System.out.println(longestCommonSubString.LengthOfLongestCommonSubString2("presum","persu"));
        System.out.println(longestCommonSubString.LengthOfLongestCommonSubString2("vista","hish"));
    }

}
