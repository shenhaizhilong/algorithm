package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/2 16:55
 */
public class BestTimetoBuyandSellStock {

    /**
     *
     * 121. Best Time to Buy and Sell Stock
     * DescriptionHintsSubmissionsDiscussSolution
     * Say you have an array for which the ith element is the price of a given stock on day i.
     *
     * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
     *
     * Note that you cannot sell a stock before you buy one.
     *
     * Example 1:
     *
     * Input: [7,1,5,3,6,4]
     * Output: 5
     * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
     *              Not 7-1 = 6, as selling price needs to be larger than buying price.
     * Example 2:
     *
     * Input: [7,6,4,3,1]
     * Output: 0
     * Explanation: In this case, no transaction is done, i.e. max profit = 0.
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int mProfit = 0;
        for (int i = 0; i < prices.length ; i++) {
            if(prices[i] < minPrice)
            {
                minPrice = prices[i];
            }else {
                int v = prices[i] - minPrice;
                if(mProfit < v)
                {
                    mProfit = v;
                }
            }
        }
        return mProfit;
    }

    public static void main(String[] args) {

        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit(prices));
        int[] prices2 = {7,6,4,3,1};
        System.out.println(maxProfit(prices2));
    }
}
