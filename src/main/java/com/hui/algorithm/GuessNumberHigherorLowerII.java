package com.hui.algorithm;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/19 12:50
 *375. Guess Number Higher or Lower II
 * DescriptionHintsSubmissionsDiscussSolution
 * We are playing the Guess Game. The game is as follows:
 *
 * I pick a number from 1 to n. You have to guess which number I picked.
 *
 * Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.
 *
 * However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.
 *
 * Example:
 *
 * n = 10, I pick 8.
 *
 * First round:  You guess 5, I tell you that it's higher. You pay $5.
 * Second round: You guess 7, I tell you that it's higher. You pay $7.
 * Third round:  You guess 9, I tell you that it's lower. You pay $9.
 *
 * Game over. 8 is the number I picked.
 *
 * You end up paying $5 + $7 + $9 = $21.
 * Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.
 *
 *
 *
 *
 * https://leetcode.com/problems/guess-number-higher-or-lower-ii/discuss/84766/Clarification-on-the-problem-description.-Problem-description-need-to-be-updated-!!!
 *
 * first introducebest strategyto guess:
 *
 * for one number, like 1, best strategy is 0$
 * for two number, like 3,4, best strategy is 3$, which can be understood in this way: you have two way to guess: a) start by guess 4 is the target, (the worst case is) if wrong, you get charged $4, then immediately you know 3 is the target number, get get charged $0 by guessing that, and finally you get charged $4. b) similarly, if you start by 3, (the worst case is) if wrong, you get charged $3, then you immediately know that 4 is the target number, and get charged $0 for guessing this, and finally you get charged $3. In summary:
 * range ---------> best strategy cost
 * 3, 4 ---------> $3
 * 5, 6 ---------> $5
 * ...
 * for three number, the best strategy is guess the middle number first, and (worst case is) if wrong, you get charged that middle number money, and then you immediately know what target number is by using "lower" or "higher" response, so in summary:
 * range ---------> best strategy cost
 * 3, 4, 5 ---------> $4
 * 7, 8, 9 ---------> $8
 * ...
 *
 *
 * for four numbers, the best strategy is the first number + the third number
 * for five numbers, the best strategy is the second number + the fourth number
 *
 * for more numbers, it can simply be reduced them into smaller ranges, and here is why DP solution make more sense in solving this.
 * suppose the range is [start, end]
 * the strategy here is to iterate through all number possible and select it as the starting point, say for any k between start and end, the worst cost for this is: k+ max(DP( start, k-1 ) , DP(k+1, end )), and the goal is minimize the cost, so you need the minimum one among all those k between start and end
 *
 */
public class GuessNumberHigherorLowerII {

    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n +1];
        int v = helper(dp, 1, n);
        printMatrix(dp);
        return v;

    }

    private void printMatrix(int[][] matrix)
    {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
    private int helper(int[][] dp, int start, int end)
    {
        if(dp[start][end] != 0)return dp[start][end];
        // only one number or no number
        if(end <= start)return 0;
        // twn numbers and three numbers case
        if(end - start <= 2 ){
            dp[start][end] = end -1;
            return dp[start][end];
        }

        // four numbers and five numbers case
        if(end - start <= 4 )
        {
            dp[start][end] = end -1 + end -3;  // remember the value so we don't need to re-compute it again.
            return dp[start][end];
        }
        int middle = (start + end) >>>1; // to avoid overflow
        int min = Integer.MAX_VALUE;
        while (middle < end)
        {
            int left = helper(dp, start, middle -1);
            int right = helper(dp, middle + 1, end);
            int worst = middle + Math.max(left, right); // chose the worst branch
            min = Math.min(min, worst);  // find the min from the worst[i];
            if(right <= left)break;
            middle++;
        }

        dp[start][end] = min;
        return dp[start][end];

    }

    public static void main(String[] args) {

        GuessNumberHigherorLowerII guessNumberHigherorLowerII = new GuessNumberHigherorLowerII();
        System.out.println(guessNumberHigherorLowerII.getMoneyAmount(10));
    }
}
