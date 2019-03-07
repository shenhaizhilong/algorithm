package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/28 15:59
 *
 *
 * 583. Delete Operation for Two Strings
 * DescriptionHintsSubmissionsDiscussSolution
 * Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same, where in each step you can delete one character in either string.
 *
 * Example 1:
 * Input: "sea", "eat"
 * Output: 2
 * Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
 * Note:
 * The length of given words won't exceed 500.
 * Characters in given words can only be lower-case letters.
 *
 */
public class DeleteOperationforTwoStrings {

    // // dp[i,j] means the min cost to convert first i characters in word1 to first j characters in word2
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
        //                 remove from left, remove from right
        // dp[i,j] = Min( dp[i-1, j], dp[i,j-1]) +1
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if(word1.charAt(i) == word2.charAt(j))
                {
                    dp[i +1][j +1] = dp[i][j];
                }else {
                    dp[i +1][j +1] = Math.min(dp[i][j +1], dp[i +1][j]) + 1;
                }
            }
        }

        return dp[M][N];
    }

    public static void main(String[] args) {
        DeleteOperationforTwoStrings deleteOperationforTwoStrings = new DeleteOperationforTwoStrings();
        System.out.println(deleteOperationforTwoStrings.minDistance("sea","eat"));
    }
}
