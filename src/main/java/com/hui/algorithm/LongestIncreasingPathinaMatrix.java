package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/15 10:06
 */
public class LongestIncreasingPathinaMatrix {


    /**
     *329. Longest Increasing Path in a Matrix
     * DescriptionHintsSubmissionsDiscussSolution
     * Given an integer matrix, find the length of the longest increasing path.
     *
     * From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
     *
     * Example 1:
     *
     * Input: nums =
     * [
     *   [9,9,4],
     *   [6,6,8],
     *   [2,1,1]
     * ]
     * Output: 4
     * Explanation: The longest increasing path is [1, 2, 6, 9].
     * Example 2:
     *
     * Input: nums =
     * [
     *   [3,4,5],
     *   [3,2,6],
     *   [2,2,1]
     * ]
     * Output: 4
     * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
     *
     * @param matrix
     * @return
     */
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length ==0)return 0;
        int R = matrix.length;
        int C = matrix[0].length;
        if(R == 0 || C == 0)return 0;
        int[][]  cache = new int[R][C]; // remember the position we visited.
        int maxLen = 1;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                int tempLen = dfs(matrix, r, c, R, C, cache);
                maxLen = Math.max(maxLen, tempLen);
            }
        }

        return maxLen;

    }

    private static int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};  // right, left, down, up direction.


    private int dfs(int[][] matrix, int r, int c, int R, int C, int[][] cache)
    {
        if(cache[r][c] != 0)return cache[r][c];
        int maxLen = 1;
        for (int i = 0; i < directions.length; i++) {
            int[] direction = directions[i]; //  right or left or down or up direction.
            int dr = r + direction[0];
            int dc = c + direction[1];
            // skip the out of boundary and the smaller position.
            if(dr < 0 || dc < 0 || dr >= R || dc >= C || matrix[dr][dc] <= matrix[r][c])continue;
            int len = 1 + dfs(matrix, dr, dc, R, C, cache);  // visited current position and next position
            maxLen = Math.max(len, maxLen);  // max length in 4 directions.
        }

        cache[r][c] = maxLen;
        return maxLen;

    }
}
