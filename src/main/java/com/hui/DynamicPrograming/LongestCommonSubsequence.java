package com.hui.DynamicPrograming;

import com.hui.Array.Matrix;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2019/1/19 15:45
 *
 *  str1 = "fish"
 *  str2 = "fosh"
 *
 *  the longest common subsequence is "fsh", so return len(fsh) == 3
 *
 *       f      i    s    h
 *   f   1      1    1    1
 *   o   1      1    1    1
 *   s   1      1    2    2
 *   h   1      1    2    3
 */
public class LongestCommonSubsequence {

    public int LengthOfLongestCommonSubsequence(String A, String B)
    {
        int M = A.length();
        int N = B.length();
        int[][] dp = new int[M +1][N +1];
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                if(A.charAt(i -1) == B.charAt(j -1))
                {
                    dp[i][j] = dp[i -1][j -1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j -1]);
                }
            }
        }
        Matrix.print(dp);
        return dp[M][N];
    }


    public int LengthOfLongestCommonSubsequence2(String A, String B)
    {
        int M = A.length();
        int N = B.length();
        int[][] dp = new int[M +1][N +1];
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j -1]);
                if(A.charAt(i -1) == B.charAt(j -1))
                {
                    dp[i][j] = dp[i -1][j -1] + 1;
                }
            }
        }
        Matrix.print(dp);
        return dp[M][N];
    }


    public int LengthOfLongestCommonSubsequence3(String A, String B)
    {
        int M = A.length();
        int N = B.length();
        int[] dp = new int[N +1];
        int[] last = new int[N +1];
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                dp[j] = Math.max(last[j], dp[j -1]);
                if(A.charAt(i -1) == B.charAt(j -1))
                {
                    dp[j] = last[j -1] + 1;
                }
            }
            last = Arrays.copyOf(dp,dp.length);
            Matrix.print(dp);
        }
        return dp[N];
    }

    public static void main(String[] args) {

        LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence();
        System.out.println(longestCommonSubsequence.LengthOfLongestCommonSubsequence("fish","fofshff"));
        System.out.println(longestCommonSubsequence.LengthOfLongestCommonSubsequence("pressum","persu"));


        System.out.println("****************");
        System.out.println(longestCommonSubsequence.LengthOfLongestCommonSubsequence2("fish","fofshff"));
        System.out.println(longestCommonSubsequence.LengthOfLongestCommonSubsequence2("pressum","persu"));

        System.out.println("****************");
        System.out.println(longestCommonSubsequence.LengthOfLongestCommonSubsequence3("fish","fofshff"));
        System.out.println(longestCommonSubsequence.LengthOfLongestCommonSubsequence3("pressum","persu"));
    }
}
