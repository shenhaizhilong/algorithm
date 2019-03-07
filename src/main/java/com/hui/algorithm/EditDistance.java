package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/28 15:39
 *
 * 72. Edit Distance
 * DescriptionHintsSubmissionsDiscussSolution
 * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
 *
 * You have the following 3 operations permitted on a word:
 *
 * Insert a character
 * Delete a character
 * Replace a character
 * Example 1:
 *
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 * Example 2:
 *
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 *
 *
 */
public class EditDistance {


    // dp[i,j] means the min cost to convert first i characters in word1 to first j characters in word2
    public int minDistance(String word1, String word2) {
        if(word1.length() == 0 || word2.length() == 0)return Math.max(word1.length(), word2.length());

        int M = word1.length();
        int N = word2.length();
        int[][] dp = new int[M +1][N +1];
        for (int i = 0; i <= M; i++) {
            dp[i][0] = i;
        }

        for (int i = 0; i <= N; i++) {
            dp[0][i] = i;
        }

        // case 1: word1[i] == word2[j]
        // dp[i,j] = dp[i-1,j -1]
        // case 2: word1[i] != word2[j]
        //                replace        remove       insert
        // dp[i,j] = Min(dp[i -1, j -1], dp[i-1, j], dp[i,j-1]) +1
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if(word1.charAt(i) == word2.charAt(j))
                {
                    dp[i +1][j +1] = dp[i][j];
                }else {
                    dp[i +1][j +1] = Math.min(Math.min(dp[i][j], dp[i][j +1]), dp[i +1][j]) +1;
                }
            }
        }
        return dp[M][N];
    }

}
