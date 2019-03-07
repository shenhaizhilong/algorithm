package com.hui.algorithm;

import java.util.Arrays;

/**
 *
 *
 * 322. Coin Change
 * DescriptionHintsSubmissionsDiscussSolution
 * You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * Example 1:
 *
 * Input: coins = [1, 2, 5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * Example 2:
 *
 * Input: coins = [2], amount = 3
 * Output: -1
 * Note:
 * You may assume that you have an infinite number of each kind of coin.
 *
 *
 * @author: shenhaizhilong
 * @date: 2018/9/12 18:59
 */
public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        if(amount < 1)return 0;
        return coinChangeDP(coins, amount, new int[amount +1]);
    }

    private int coinChangeDP(int[] coins, int remain, int[] dp)
    {
        if(remain == 0)return 0;
        if(remain < 0)return -1;
        if(dp[remain] != 0)return dp[remain];
        int Min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int count = coinChangeDP(coins, remain - coins[i], dp);
            if(count >= 0 && count < Min)
            {
                Min = count + 1;
            }
        }
        dp[remain] = (Min == Integer.MAX_VALUE) ? -1: Min;
        return dp[remain];
    }

    public int coinChange2(int[] coins, int amount) {
        if(amount < 1)return 0;
        int[] dp = new int[amount + 1];
    //    Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount ; i++) {
            int min = amount +1;
            for (int j = 0; j < coins.length; j++) {
                if(coins[j] <= i)
                {
                   min = Math.min(min, dp[i - coins[j]]) + 1;
                }
            }
            dp[i] = min;
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public int coinChange3(int[] coins, int amount) {
        if(amount < 1)return 0;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if(coins[i] <= j)
                {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] +1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }


    public static void main(String[] args) {

        CoinChange coinChange = new CoinChange();
        System.out.println(coinChange.coinChange(new int[]{1,2,5},11));
        System.out.println(coinChange.coinChange(new int[]{2},3));

        System.out.println(coinChange.coinChange2(new int[]{1,2,5},11));
        System.out.println(coinChange.coinChange2(new int[]{2},3));

        System.out.println(coinChange.coinChange3(new int[]{1,2,5},11));
        System.out.println(coinChange.coinChange3(new int[]{2},3));

    }
}
