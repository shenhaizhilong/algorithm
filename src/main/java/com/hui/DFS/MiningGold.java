package com.hui.DFS;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/21 10:58
 *
 *图中的橙色小方块就是传说中的作者，他可以在一个N*M的棋盘上行走，但是只有两个方向，一个是向右，
 * 一个是向下（如绿色箭头所示），棋盘上有很多的金矿，走到格子上就能取走那里的金矿，每个格子的金矿数目不同（用蓝色数字表示金矿的数量）
 * ，问作者在这样一个棋盘上最多可以拿到多少金矿。
 * http://www.cppblog.com/menjitianya/archive/2015/10/09/211980.html
 *
 *
 */
public class MiningGold {

    public int getMaxGold(int[][] arr)
    {
        int M = arr.length;
        int N = arr[0].length;
        int[][] memo = new int[M][N];
        for(int[] row: memo)
        {
            Arrays.fill(row, -1);
        }
        return dfs(arr, M -1, N -1, memo);

    }

    private int dfs(int[][] arr, int i, int j, int[][] memo)
    {
        if(i < 0 || j < 0 )return 0;
        if(memo[i][j] != -1)return memo[i][j];
        int currMax = arr[i][j] + Math.max(dfs(arr, i-1,j, memo), dfs(arr, i,j -1, memo));
        memo[i][j] = currMax;
        return currMax;
    }

    public static void main(String[] args) {
        int[][] arr = {
                {0,0,0,1,0,0},
                {0,0,2,0,5,0},
                {0,3,0,5,0,0},
                {1,0,2,0,0,0}
        };
        MiningGold miningGold = new MiningGold();
        System.out.println(miningGold.getMaxGold(arr));
    }
}
