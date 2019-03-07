package com.hui.algorithm;


/**
 * @author: shenhaizhilong
 * @date: 2018/12/21 12:10
 *
 * 给定一个长度为n（n <= 1000）的字符串A，求插入最少多少个字符使得它变成一个回文串。
 *
 *
 *        // dp[i,j] , i < j , the min number of chars to insert
 *        // if A[i] == A[j] , dp[i,j] = dp[i+1,j -1]; i < j
 *         // 1) insert A[j] before A[i];
 *         // 2) insert A[i] after A[j];
 *         // if A[i] != A[j], dp[i,j] = 1 + Math.min(dp[i,j -1], dp[i +1,j]); i < j
 *
 */
public class Palindrome_min_insert_char {

    public int insertCount(String A)
    {

        int[][] dp = new int[A.length()][A.length()];

        return dfs(A,0, A.length() -1, dp);

    }

    private int dfs(String A, int i, int j, int[][] memo)
    {
        if(i >= j)return 0;
        if(memo[i][j] > 0)return memo[i][j];
        if(A.charAt(i) == A.charAt(j)){
            return dfs(A, i +1, j -1, memo);
        }
        memo[i][j] = 1 + Math.min(dfs(A,i, j -1, memo), dfs(A,i +1, j, memo));
        return memo[i][j];
    }




    public static void main(String[] args) {
        Palindrome_min_insert_char palindrome_min_insert_char = new Palindrome_min_insert_char();
        //System.out.println(palindrome_min_insert_char.insertCount("aba"));
        System.out.println(palindrome_min_insert_char.insertCount("abaaaab"));
    }
}
