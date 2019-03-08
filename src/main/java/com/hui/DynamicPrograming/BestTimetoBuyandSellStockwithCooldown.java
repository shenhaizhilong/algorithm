package com.hui.DynamicPrograming;

/**
 *
 * 309. Best Time to Buy and Sell Stock with Cooldown
 * DescriptionHintsSubmissionsDiscussSolution
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
 *
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 * Example:
 *
 * Input: [1,2,3,0,2]
 * Output: 3
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 *
 *
 * dp[i] = max(dp[i-1], p[i] - p[j] + dp[j-2]) , j = 0,1,2,... i -1, i;
 * If we sell the shares on i-th day bought on j-th day, we couldn't trade on (j-1)-th day because of cooldown. So the last one is dp[j-2].
 *
 *
 * @author: shenhaizhilong
 * @date: 2018/9/19 19:17
 */
public class BestTimetoBuyandSellStockwithCooldown {
    public int MaxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        int[] dp = new int[prices.length + 1];
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i] - dp[i-1]);
            dp[i +1] = Math.max(dp[i], prices[i] - min);
        }

        return dp[prices.length];
    }


    public int MaxProfit2(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        int prev = 0;
        int rest = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i] - prev);
            prev = rest;
            rest = Math.max(rest, prices[i] - min);
        }

        return rest;
    }

    public static void main(String[] args) {

        BestTimetoBuyandSellStockwithCooldown bestTimetoBuyandSellStockwithCooldown = new BestTimetoBuyandSellStockwithCooldown();
        System.out.println(bestTimetoBuyandSellStockwithCooldown.MaxProfit(new int[]{1,2,3,0,2}));
        System.out.println(bestTimetoBuyandSellStockwithCooldown.MaxProfit2(new int[]{1,2,3,0,2}));
    }

}
