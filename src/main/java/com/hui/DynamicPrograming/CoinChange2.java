package com.hui.DynamicPrograming;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/18 16:06
 *
 * 518. Coin Change 2
 * DescriptionHintsSubmissionsDiscussSolution
 * You are given coins of different denominations and a total amount of money. Write a function to compute the number of combinations that make up that amount. You may assume that you have infinite number of each kind of coin.
 *
 * Note: You can assume that
 *
 * 0 <= amount <= 5000
 * 1 <= coin <= 5000
 * the number of coins is less than 500
 * the answer is guaranteed to fit into signed 32-bit integer
 *
 *
 * Example 1:
 *
 * Input: amount = 5, coins = [1, 2, 5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 *
 *
 * Example 2:
 *
 * Input: amount = 3, coins = [2]
 * Output: 0
 * Explanation: the amount of 3 cannot be made up just with coins of 2.
 *
 *
 * Example 3:
 *
 * Input: amount = 10, coins = [10]
 * Output: 1
 *
 */
public class CoinChange2 {

    public int change(int amount, int[] coins) {

        // dp[i][j] : the number of ways to make up amount j by using the first i types of coins.
        // states:
        //1. not using the ith coin, only using the first i-1 coins to make up amount j, then we have dp[i-1][j] ways.  i in range[1,coins.length]
        //2. using the ith coin, we need to know how many ways to make up amount j - coins[i-1]
        // by using first i coins(including ith), which is dp[i][j-coins[i-1]]

        int[][] dp = new int[coins.length + 1][amount + 1];
        dp[0][0] = 1;
        for (int i = 1; i <=coins.length ; i++) {
            dp[i][0] = 1;  //init
            for (int j = 1; j <= amount ; j++) {
               if(j >= coins[i -1])
               {
                   dp[i][j] = dp[i-1][j] + dp[i][j- coins[i -1]];
               } else {
                     dp[i][j] = dp[i -1][j];
               }
            }
        }

     //   printMatrix(dp);
        return dp[coins.length][amount];

    }

    public int change1(int amount, int[] coins) {

        // dp[i][j] : the number of ways to make up amount j by using the first i types of coins.
        // states:
        //1. not using the ith coin, only using the first i-1 coins to make up amount j, then we have dp[i-1][j] ways.  i in range[1,coins.length]
        //2. using the ith coin, we need to know how many ways to make up amount j - coins[i-1]
        // by using first i coins(including ith), which is dp[i][j-coins[i-1]]

        int[][] dp = new int[coins.length + 1][amount + 1];
        dp[0][0] = 1;
        for (int i = 1; i <=coins.length ; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= amount ; j++) {
                dp[i][j] = dp[i -1][j] + (j >= coins[i -1] ? dp[i][j - coins[i-1]]: 0);
            }
        }

        printMatrix(dp);
        return dp[coins.length][amount];

    }

    public int change3(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 1; i <= coins.length; i++) {

            for (int j = coins[i-1]; j <= amount ; j++) {
                dp[j] += dp[j - coins[i-1]];
            }
        }




        return dp[amount];
    }

    public int change2(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for(int coin: coins)
        {
            for (int i = coin; i <= amount ; i++) {
                dp[i] += dp[i - coin];
            }
        }

        return dp[amount];
    }




    private void  printMatrix(int[][] matrix)
    {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    public static void main(String[] args) {
        CoinChange2 coinChange2 = new CoinChange2();
        System.out.println(coinChange2.change(5, new int[]{1,2,5}));
        System.out.println(coinChange2.change1(5, new int[]{1,2,5}));
        System.out.println(coinChange2.change2(5, new int[]{1,2,5}));
        System.out.println(coinChange2.change3(5, new int[]{1,2,5}));


      //  System.out.println(coinChange2.change3(5, new int[]{1,2,5}));
    }
}
