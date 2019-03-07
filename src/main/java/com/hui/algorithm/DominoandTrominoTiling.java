package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/18 18:51
 *
 *790. Domino and Tromino Tiling
 * DescriptionHintsSubmissionsDiscussSolution
 * We have two types of tiles: a 2x1 domino shape, and an "L" tromino shape. These shapes may be rotated.
 *
 * XX  <- domino
 *
 * XX  <- "L" tromino
 * X
 * Given N, how many ways are there to tile a 2 x N board? Return your answer modulo 10^9 + 7.
 *
 * (In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.)
 *
 * Example:
 * Input: 3
 * Output: 5
 * Explanation:
 * The five different ways are listed below, different letters indicates different tiles:
 * XYZ XXZ XYY XXY XYY
 * XYZ YYZ XZZ XYY XXY
 * Note:
 *
 * N  will be in range [1, 1000].
 *
 *
 * ----------
 *
 * for L type:
 * g(n) = g(n -1) + f(n -1)
 * for x type:
 * f(n) = f(n -1) + f(n - 2) + 2g(n - 2)
 *
 * f(n -1) = f(n - 2) + f(n - 3) + 2g(n - 3)
 *
 * f(n) - f(n -1) = f(n -1) + 2g(n - 2) - 2g(n - 3) - f(n -3)
 *
 * g(n -2) = g(n - 3) + f(n - 3), then
 *
 * f(n) - f(n -1) = f(n -1) + f(n -3)
 * then f(n) = 2*f(n -1) + f(n - 3)
 * -----------
 *
 * f(1) = 1;
 * f(2) = 2;
 * f(3) = 5;
 *
 */
public class DominoandTrominoTiling {

    private static final int[] consts = {0,1,2,5};
    public int numTilings(int N) {

        // boundary condition
        if(N < 0)return 0;
        if(N <= 3)return consts[N];

        int mod = 1_000_000_007;
        int[] dp = new int[N +1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= N; i++) {
            dp[i] = (int) ((2L*dp[i -1] + dp[i -3]) % mod);
        }
        return dp[N];


    }


    public int numTilings2(int N) {

        // boundary condition
        if(N < 0)return 0;
        if(N <= 3)return consts[N];
        int mod = 1_000_000_007;

        int pre3 = 1;
        int pre2 = 1;
        int pre1 = 2;
        int curr = -1;

        for (int i = 3; i <= N; i++) {
           curr  = (int) ((2L*pre1 + pre3) % mod);
           pre3 = pre2;
           pre2 = pre1;
           pre1 = curr;
        }
        return pre1;


    }


}
