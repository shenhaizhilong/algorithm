package com.hui.algorithm;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/24 17:07
 *
 *931. Minimum Falling Path Sum
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a square array of integers A, we want the minimum sum of a falling path through A.
 *
 * A falling path starts at any element in the first row, and chooses one element from each row.  The next row's choice must be in a column that is different from the previous row's column by at most one.
 *
 *
 *
 * Example 1:
 *
 * Input: [[1,2,3],[4,5,6],[7,8,9]]
 * Output: 12
 * Explanation:
 * The possible falling paths are:
 * [1,4,7], [1,4,8], [1,5,7], [1,5,8], [1,5,9]
 * [2,4,7], [2,4,8], [2,5,7], [2,5,8], [2,5,9], [2,6,8], [2,6,9]
 * [3,5,7], [3,5,8], [3,5,9], [3,6,8], [3,6,9]
 * The falling path with the smallest sum is [1,4,7], so the answer is 12.
 *
 *
 *
 * Note:
 *
 * 1 <= A.length == A[0].length <= 100
 * -100 <= A[i][j] <= 100
 *
 */
public class MinimumFallingPathSum {

    private int R,C;
    public int minFallingPathSum(int[][] A) {
        this.R = A.length;
        this.C = A[0].length;
        int[][] cache = new int[R][C];
        for (int i = 0; i < R; i++) {
            Arrays.fill(cache[i],Integer.MAX_VALUE);
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < C; i++) {
            ans = Math.min(ans, dfs(A,0,i,cache));
        }
        return ans;

    }

    private static final int[][] dirs = {{1,0},{1,-1},{1,1}};
    private int dfs(int[][] A, int r, int c, int[][] cache)
    {
        if(r < 0 || c < 0 || c > C -1)return Integer.MAX_VALUE;
        if(r == R -1)return A[r][c];
        if(cache[r][c] != Integer.MAX_VALUE)
        {
            return cache[r][c];
        }
        int min = Integer.MAX_VALUE;
        for(int[] dir:dirs)
        {
            int x = r + dir[0];
            int y = c + dir[1];
            if(c >= 0 && c < C)
            {
                min = Math.min(min, dfs(A, x, y,cache));
            }
        }

        cache[r][c] = min == Integer.MAX_VALUE ? Integer.MAX_VALUE: A[r][c] + min;
        return cache[r][c];
    }



    // dp
    public int minFallingPathSum2(int[][] A) {
        int R = A.length;
        int C = A[0].length;
        int[][] dp = new int[R][C];
        for (int i = 0; i < C; i++) {
            dp[R -1][i] = A[R-1][i];
        }

        for (int i = R-2; i >=0 ; i--) {
            for (int j = 0; j < C; j++) {
                dp[i][j] =Math.min(j == 0 ? Integer.MAX_VALUE: dp[i+1][j -1], Math.min( dp[i+1][j],j == C -1 ? Integer.MAX_VALUE: dp[i+1][j+1]))  + A[i][j];
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < C; i++) {
            ans = Math.min(ans, dp[0][i]);
        }

        return ans;
    }
    public static void main(String[] args) {
        int[][] A = {
                {1,2,3},
                {4,5,6},
                {7,8,9}};
        MinimumFallingPathSum minimumFallingPathSum = new MinimumFallingPathSum();
        System.out.println(minimumFallingPathSum.minFallingPathSum(A));
        System.out.println(minimumFallingPathSum.minFallingPathSum2(A));
    }

}
