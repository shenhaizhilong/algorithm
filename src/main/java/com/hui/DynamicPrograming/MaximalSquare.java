package com.hui.DynamicPrograming;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/13 15:40
 */
public class MaximalSquare {


    /**
     *
     * 221. Maximal Square
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
     *
     * Example:
     *
     * Input:
     *
     * 1 0 1 0 0
     * 1 0 1 1 1
     * 1 1 1 1 1
     * 1 0 0 1 0
     *
     * Output: 4
     *
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0)return 0;

        int[][] dp = new int[matrix.length +1][matrix[0].length +1];
        int maxSideLen = 0;
        for (int i = 1; i <= matrix.length; i++) {
            for (int j = 1; j <= matrix[0].length; j++) {
                if(matrix[i-1][j-1] == '1')
                {
                    dp[i][j] = Math.min(Math.min(dp[i][j-1], dp[i-1][j]), dp[i-1][j-1]) + 1;
                    maxSideLen = Math.max(maxSideLen, dp[i][j]);
                }
            }
        }

        return maxSideLen*maxSideLen;
    }
}
