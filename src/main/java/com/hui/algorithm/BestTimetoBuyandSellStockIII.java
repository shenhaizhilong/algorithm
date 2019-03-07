package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/3 11:13
 */
public class BestTimetoBuyandSellStockIII {


    /**
     *
     * 123. Best Time to Buy and Sell Stock III
     * DescriptionHintsSubmissionsDiscussSolution
     * Say you have an array for which the ith element is the price of a given stock on day i.
     *
     * Design an algorithm to find the maximum profit. You may complete at most two transactions.
     *
     * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
     *
     * Example 1:
     *
     * Input: [3,3,5,0,0,3,1,4]
     * Output: 6
     * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
     *              Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
     * Example 2:
     *
     * Input: [1,2,3,4,5]
     * Output: 4
     * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
     *              Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
     *              engaging multiple transactions at the same time. You must sell before buying again.
     * Example 3:
     *
     * Input: [7,6,4,3,1]
     * Output: 0
     * Explanation: In this case, no transaction is done, i.e. max profit = 0.
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length <=1)return 0;
        int Max = 0;
        for (int i = 1; i < prices.length; i++) {
            if(prices[i] > prices[i-1])
            {
                int left = maxProfitRange(prices,0,i);
                int right = maxProfitRange(prices,i + 1, prices.length -1);
                Max = Math.max(Max, left + right);
            }
        }
        return Max;

    }

    public static int maxProfit2(int[] prices) {
        if (prices == null || prices.length <=1)return 0;
        int Max = 0;
        int i =1;
        boolean hasPeak = false;
        for (; i < prices.length -1; i++) {
            if (prices[i] >= prices[i-1] && prices[i] > prices[i+1])
            {
                hasPeak = true;
                int left = maxProfitRange(prices,0,i);
                int right = maxProfitRange(prices,i + 1, prices.length -1);
                Max = Math.max(Max, left + right);
            }
        }

        if(!hasPeak)
        {
            Max = Math.max(Max, maxProfitRange(prices,0,prices.length -1));
        }
        return Max;

    }

    private static int maxProfitRange(int[] prices, int start, int end)
    {
        int minPrice = Integer.MAX_VALUE;
        int mProfit = 0;
        for (int i = start; i <= end ; i++) {
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

//        System.out.println(maxProfit2(new int[]{7,6,5,4,3,2,1}));
//        System.out.println(maxProfit2(new int[]{1,2,3,4,5}));
//        System.out.println(maxProfit2(new int[]{3,3,5,0,0,3,1,4}));
//        System.out.println(maxProfit2(new int[]{2,4,1}));
//        System.out.println(maxProfit2(new int[]{6,1,3,2,4,7}));
        System.out.println(maxProfit2(new int[]{8,3,6,2,8,8,8,4,2,0,7,2,9,4,9}));

    }
}
