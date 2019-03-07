package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/29 17:59
 *
 *
 *
 * Leetcode – Paint House II solution (Java)
 * Tags: Algorithm, Facebook, LeetCode
 * There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different.
 *
 * You have to paint all the houses such that no two adjacent houses have the same color. The cost of painting each house with a certain color is represented by a n x k cost matrix.
 *
 * For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on… Find the minimum cost to paint all houses.
 *
 *
 * Note:
 * All costs are positive integers.
 *
 * Follow up:
 * Could you solve it in O(nk) runtime?
 *
 *
 * Analysis
 * This is a follow up question of Paint House I. The basic idea to solve Paint House I is to define DP[i][j] as the minimum cost to paint house until i with color j. where i  ∈ [1, n]  and j ∈ {0, 1, 2}.
 *
 * In the beginning, DP[0][j] = cost[0][j].  Then we can use the following rules to update the table in bottom up manner.
 *
 *
 * DP[i][0] += Math.min(DP[i-1][1], DP[i-1][2]);
 *
 * DP[i][1] += Math.min(DP[i-1][0], DP[i-1][2]);
 *
 * DP[i][2] += Math.min(DP[i-1][0], DP[i-1][1]);
 * 1
 * 2
 * 3
 * 4
 * 5
 * DP[i][0] += Math.min(DP[i-1][1], DP[i-1][2]);
 *
 * DP[i][1] += Math.min(DP[i-1][0], DP[i-1][2]);
 *
 * DP[i][2] += Math.min(DP[i-1][0], DP[i-1][1]);
 * For this problem, we can also define DP[i][j] as the minimum cost to paint house until i with color j.
 *
 * Then DP[i][j] = cost[i][j] + min({DP[i - 1][m] | m = 0, 1, 2, …., k and m != j }). We can use an variable preMin to record the minimum cost of DP[i – 1] [m], and the corresponding color minColor.
 *
 * One problem is that the minColor (color k with minimum value) can equal to the current color.  In this case we need to use the color with second smallest cost. So define another variable secondMin to record the second minimum cost of DP[i – 1][m] along with the corresponding color secondMinColor.
 *
 */
public class PaintHouseII {

    public int minCostII(int[][] costs) {
        int preColor = -1;
        int preMin = 0;
        int preSecondMin = 0;
        int N = costs.length;
        int K = costs[0].length;
        for (int i = 0; i < N; i++) {

            int currColor = -1;
            int currMin = Integer.MAX_VALUE;
            int currSecondMin = Integer.MAX_VALUE;
            for (int j = 0; j < K; j++) {
                costs[i][j] = costs[i][j] + (preColor == j ? preSecondMin: preMin);
                if(costs[i][j] < currMin)
                {
                    currSecondMin = currMin;
                    currColor = j ;
                    currMin = costs[i][j];
                }else if(costs[i][j] < currSecondMin)
                {
                    currSecondMin = costs[i][j];
                }
            }

            preColor = currColor;
            preSecondMin = currSecondMin;
            preMin = currMin;
        }

        return preMin;
    }


}
