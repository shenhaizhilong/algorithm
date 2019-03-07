package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/29 15:07
 *
 *There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
 *
 * The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.
 *
 * Note:
 * All costs are positive integers.
 *
 */
public class PaintHouse {

    // dp[i][j] = costs[i][j] + min(dp[i -1, (j +1)%3], dp[i -1, (j +2)%3]);
   public int minCost(int[][] costs) {
       if(costs == null || costs.length == 0 || costs[0].length == 0)return 0;
       int N = costs.length;
       int M = costs[0].length;
       int[][] dp = new int[M][N];
       dp[0][0] = costs[0][0];
       dp[0][1] = costs[0][1];
       dp[0][2] = costs[0][2];
       for (int i = 1; i < N; i++) {
           for (int j = 0; j < M; j++) {
               dp[i][j] = costs[i][j] + Math.min(dp[i -1][(j +1)%3], dp[i -1][(j +2)%3]);
           }
       }
       return Math.min(dp[N -1][0], Math.min(dp[N -1][1], dp[N -1][2]));
   }

    // dp[i][j] = costs[i][j] + min(dp[i -1, (j +1)%3], dp[i -1, (j +2)%3]);
    public int minCost2(int[][] costs) {
       if(costs == null || costs.length == 0 || costs[0].length == 0)return 0;
        int N = costs.length;
        int M = costs[0].length;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                costs[i][j] += Math.min(costs[i -1][(j +1)%3], costs[i -1][(j +2)%3]);
            }
        }
        return Math.min(costs[N -1][0], Math.min(costs[N -1][1], costs[N -1][2]));
    }


    // dp[i][j] = costs[i][j] + min(dp[i -1, (j +1)%3], dp[i -1, (j +2)%3]);
    public int minCost3(int[][] costs) {
        if(costs == null || costs.length == 0 || costs[0].length == 0)return 0;
        int N = costs.length;
        for (int i = 1; i < N; i++) {
            costs[i][0] += Math.min(costs[i -1][1], costs[i -1][2]);
            costs[i][1] += Math.min(costs[i -1][0], costs[i -1][2]);
            costs[i][2] += Math.min(costs[i -1][0], costs[i -1][1]);
        }
        return Math.min(costs[N -1][0], Math.min(costs[N -1][1], costs[N -1][2]));
    }
}
