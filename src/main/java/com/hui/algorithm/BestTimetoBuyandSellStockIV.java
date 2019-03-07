package com.hui.algorithm;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/19 10:38
 */
public class BestTimetoBuyandSellStockIV {

    /**
     *
     * 188. Best Time to Buy and Sell Stock IV
     * DescriptionHintsSubmissionsDiscussSolution
     * Say you have an array for which the ith element is the price of a given stock on day i.
     *
     * Design an algorithm to find the maximum profit. You may complete at most k transactions.
     *
     * Note:
     * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
     *
     * Example 1:
     *
     * Input: [2,4,1], k = 2
     * Output: 2
     * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
     * Example 2:
     *
     * Input: [3,2,6,5,0,3], k = 2
     * Output: 7
     * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
     *              Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
     *、
     *
     *
     * dp[k,i] = max(dp[k,i-1], p[i] - p[j] + dp[k-1,j-1]) , j = 0,1,2,... i -1, i; k = 1, 2, 3 ...K
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0) return 0;
        int[][] dp = new int[k+1][prices.length];
        for (int i = 1; i <=k; i++) {
            int min = prices[0];
            for (int j = 1; j < prices.length ; j++) {
                min = Math.min(min, prices[j] - dp[i-1][j-1]);
                dp[i][j] = Math.max(dp[i][j-1],  prices[j] - min);
            }
        }
        return dp[k][prices.length -1];
    }

    public int maxProfit2(int k, int[] prices) {
        if (prices.length == 0) return 0;
        if(k >= prices.length/2)return quickSolve(prices);
        int[] dp = new int[k+1];
        int[] min = new int[k + 1];
        Arrays.fill(min , prices[0]);
        for (int i = 0; i < prices.length; i++) {
            for (int j = 1; j <=k ; j++) {
                min[j] = Math.min(min[j], prices[i] - dp[j-1]);
                dp[j] = Math.max(dp[j], prices[i] - min[j]);
            }
        }

        return dp[k];
    }

    /**
     *
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/discuss/54113/A-Concise-DP-Solution-in-Java
     *
     * @param prices
     * @return
     */
    private int quickSolve(int[] prices)
    {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            // as long as there is a price gap, we gain a profix.
            if(prices[i] > prices[i-1])maxProfit += prices[i] - prices[i-1];
        }

        return maxProfit;
    }




    public static void main(String[] args) {

        BestTimetoBuyandSellStockIV bestTimetoBuyandSellStockIV = new BestTimetoBuyandSellStockIV();
        System.out.println(bestTimetoBuyandSellStockIV.maxProfit(2, new int[]{3,2,6,5,0,3}));
        System.out.println(bestTimetoBuyandSellStockIV.maxProfit2(1000, new int[]{3,2,6,5,0,3}));


    }
}
