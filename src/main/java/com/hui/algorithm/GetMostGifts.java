package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2019/1/17 10:46
 *
 * 在一个 m*n 的棋盘的每一个格都放有一个礼物，每个礼物都有一定价值（大于 0）。从左上角开始拿礼物，每次向右或向下移动一格，直到右下角结束。给定一个棋盘，求拿到礼物的最大价值。例如，对于如下棋盘
 *
 * 1    10   3    8
 * 12   2    9    6
 * 5    7    4    11
 * 3    7    16   5
 * 礼物的最大价值为 1+12+5+7+7+16+5=53。
 *
 */
public class GetMostGifts {

    public int getMostGifts(int[][] grid)
    {
        int R = grid.length;
        int C = grid[0].length;
        int[][] dp = new int[R][C];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < R; i++) {
            dp[i][0] = dp[i -1][0] + grid[i][0];
        }
        for (int i = 1; i < C; i++) {
            dp[0][i] = dp[0][i -1] + grid[0][i];
        }

        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                dp[i][j] = Math.max(dp[i -1][j], dp[i][j -1]) + grid[i][j];
            }
        }
        return dp[R-1][C -1];
    }

    public int getMostGifts2(int[][] grid)
    {
        int R = grid.length;
        int C = grid[0].length;
        int[] dp = new int[C];

        for (int i = 0; i < R; i++) {
            dp[0] += grid[i][0];
            for (int j = 1; j < C; j++) {
                dp[j] = Math.max(dp[j], dp[j -1]) + grid[i][j];
            }
        }
        return dp[C -1];
    }



    public static void main(String[] args) {
        int[][] grid = {
                {1,10,3,8},
                {12,2,9,6},
                {5,7,4,11},
                {3,7,16,5}};
        GetMostGifts getMostGifts = new GetMostGifts();
        System.out.println(getMostGifts.getMostGifts(grid));
        System.out.println(getMostGifts.getMostGifts2(grid));
    }
}
