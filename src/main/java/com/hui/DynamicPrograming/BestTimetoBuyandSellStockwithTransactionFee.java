package com.hui.DynamicPrograming;

/**
 *
 * 714. Best Time to Buy and Sell Stock with Transaction Fee
 * DescriptionHintsSubmissionsDiscussSolution
 * Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i; and a non-negative integer fee representing a transaction fee.
 *
 * You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction. You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)
 *
 * Return the maximum profit you can make.
 *
 * Example 1:
 * Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * Output: 8
 * Explanation: The maximum profit can be achieved by:
 * Buying at prices[0] = 1
 * Selling at prices[3] = 8
 * Buying at prices[4] = 4
 * Selling at prices[5] = 9
 * The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * Note:
 *
 * 0 < prices.length <= 50000.
 * 0 < prices[i] < 50000.
 * 0 <= fee < 50000.
 *
 * @author: shenhaizhilong
 * @date: 2018/9/19 20:03
 */
public class BestTimetoBuyandSellStockwithTransactionFee {


    /**
     *
     * dp[i] = max(dp[i-1], p[i] - p[j] -fee + dp[j-1]) , j = 0,1,2,... i -1, i;
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit(int[] prices, int fee) {
        if(prices == null || prices.length <2)return 0;
        int[] dp = new int[prices.length];
        int min = prices[0];
        for (int i = 1; i < prices.length ; i++) {
            min = Math.min(min, prices[i] - dp[i-1]);
            dp[i] = Math.max(dp[i-1], prices[i] - fee - min);
        }

        return dp[prices.length -1];

    }

    public int maxProfit2(int[] prices, int fee) {
        if(prices == null || prices.length <2)return 0;
        int prev = 0;
        int curr = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length ; i++) {
            min = Math.min(min, prices[i] - prev);
            curr = Math.max(prev, prices[i] - fee - min);
            prev = curr;
        }

        return curr;

    }


    public static void main(String[] args) {
        BestTimetoBuyandSellStockwithTransactionFee bestTimetoBuyandSellStockwithTransactionFee = new BestTimetoBuyandSellStockwithTransactionFee();
        System.out.println(bestTimetoBuyandSellStockwithTransactionFee.maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2));
        System.out.println(bestTimetoBuyandSellStockwithTransactionFee.maxProfit2(new int[]{1, 3, 2, 8, 4, 9}, 2));
    }

}
