package com.hui.DFS;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/19 15:49
 *
 *
 * 97. Interleaving String
 * DescriptionHintsSubmissionsDiscussSolution
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 *
 * Example 1:
 *
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 * Example 2:
 *
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 *
 *
 */
public class InterleavingString {


    public boolean isInterleave(String s1, String s2, String s3) {
       int M = s1.length();
       int N = s2.length();
       int K = s3.length();
       if(K != M + N)return false;
       int[][] memo = new int[M +1][N +1];
       char[] chars1 = s1.toCharArray();
       char[] chars2 = s2.toCharArray();
       char[] target = s3.toCharArray();
       boolean res = dfs(chars1, chars2, target, 0, 0, 0, memo);
       print(memo);
       return  res;

    }

    private boolean dfs(char[] chars1, char[] chars2, char[] target, int id1, int id2, int idx, int[][] memo)
    {
        if(memo[id1][id2] != 0)return memo[id1][id2] > 0;
        if(idx == target.length)return true;
        boolean flag = (id1 < chars1.length && chars1[id1] == target[idx] && dfs(chars1, chars2, target, id1+1, id2, idx +1, memo))
                ||
                (id2 < chars2.length && chars2[id2] == target[idx] && dfs(chars1, chars2, target, id1, id2 +1, idx +1, memo));

        memo[id1][id2] = flag == true ? 1 : -1;
        return flag;
    }

    private void print(int[][] matrix)
    {
        for(int[] arr: matrix)
        {
            System.out.println(Arrays.toString(arr));
        }
    }

    private void print(boolean[][] matrix)
    {
        for(boolean[] arr: matrix)
        {
            System.out.println(Arrays.toString(arr));
        }
    }



    public boolean isInterleave2(String s1, String s2, String s3) {
        int M = s1.length();
        int N = s2.length();
        int K = s3.length();
        if(K != M + N)return false;
        //dp[i][j] 长度为i + j 的 s3 的前缀子字符串是否可以有长度为i 的s1前缀 子字符串与长度为j 的s2 前缀子字符串组成
        boolean[][] dp = new boolean[M +1][N +1];
        dp[0][0] = true;
        for (int i = 1; i <= M && dp[i -1][0] ; i++) {
            dp[i][0] = s3.charAt(i -1) == s1.charAt(i -1);
        }

        for (int i = 1; i <= N && dp[0][i -1] ; i++) {
            dp[0][i] = s3.charAt(i -1) == s2.charAt(i -1);
        }

        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = (dp[i -1][j] && s1.charAt(i -1) == s3.charAt(i +j -1)) || (dp[i][j -1] && s2.charAt(j -1) == s3.charAt(i +j -1));
            }
        }

        print(dp);
        return dp[M][N];
    }


    public boolean isInterleave3(String s1, String s2, String s3) {
        int M = s1.length();
        int N = s2.length();
        int K = s3.length();
        if(K != M + N)return false;
        //dp[i][j] 长度为i + j 的 s3 的前缀子字符串是否可以有长度为i 的s1前缀 子字符串与长度为j 的s2 前缀子字符串组成
        boolean[] dp = new boolean[N +1];
        dp[0] = true;

        for (int i = 1; i <= N && dp[i -1] ; i++) {
            dp[i] = s3.charAt(i -1) == s2.charAt(i -1);
        }

        //next row can be determined by previous row, except dp[0], so we need to compute dp[0];
        for (int i = 1; i <= M; i++) {
            dp[0] = dp[0] && s3.charAt(i -1) == s1.charAt(i -1);
            for (int j = 1; j <= N; j++) {
                dp[j] = (dp[j] && s1.charAt(i -1) == s3.charAt(i +j -1)) || (dp[j -1] && s2.charAt(j -1) == s3.charAt(i +j -1));
            }
        }


        return dp[N];
    }


    public static void main(String[] args) {

        InterleavingString interleavingString = new InterleavingString();
        System.out.println(interleavingString.isInterleave("aabcc","dbbca", "aadbbcbcac"));
        System.out.println(interleavingString.isInterleave("aabcc","dbbca", "aadbbbaccc"));

        System.out.println(interleavingString.isInterleave2("aabcc","dbbca", "aadbbcbcac"));
        System.out.println(interleavingString.isInterleave2("aabcc","dbbca", "aadbbbaccc"));

        System.out.println(interleavingString.isInterleave3("aabcc","dbbca", "aadbbcbcac"));
        System.out.println(interleavingString.isInterleave3("aabcc","dbbca", "aadbbbaccc"));


    }

}
