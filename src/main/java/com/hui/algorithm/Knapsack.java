package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/14 23:36
 *
 *
 * https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
 *
 *
 * 0-1 Knapsack Problem | DP-10
 * Given weights and values of n items, put these items in a knapsack of capacity W to get the maximum total value in the knapsack.
 * In other words, given two integer arrays val[0..n-1] and wt[0..n-1] which represent values and weights associated with n items
 * respectively. Also given an integer W which represents knapsack capacity, find out the maximum value subset of val[] such that
 * sum of the weights of this subset is smaller than or equal to W. You cannot break an item, either pick the complete item,
 * or don’t pick it (0-1 property)
 *
 *
 */
public class Knapsack {

    /**
     *
     * @param Cap the capacity of a knapsack
     * @param weights
     * @param vals
     * @return
     */
    public int knapsack(int Cap, int[] weights, int[] vals)
    {
        int N = weights.length;
        // dp[n][w] means the maximum total value when we  chose n  items, total weights is w
        int[][] dp = new int[N +1][Cap +1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= Cap; j++) {
                if(j >= weights[i -1])
                {
                    dp[i][j] = Math.max(dp[i-1][j - weights[i -1]] + vals[i-1], dp[i -1][j]);
                }else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }


        return dp[N][Cap];

    }

    public int knapsack2(int Cap, int[] weights, int[] vals)
    {
        int N = weights.length;
        // dp[n][w] means the maximum total value when we  chose n  items, total weights is w
        int[][] dp = new int[N +1][Cap +1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= Cap; j++) {
                dp[i][j] = dp[i-1][j]; // don't include this item
                if(j >= weights[i -1])
                {
                    dp[i][j] = Math.max(dp[i-1][j - weights[i -1]] + vals[i-1], dp[i][j]);
                }
            }
        }


        return dp[N][Cap];

    }

    public int knapsack3(int Cap, int[] weights, int[] vals)
    {
        int N = weights.length;
        // dp[n][w] means the maximum total value when we the chose n  items, total weights is w
        int[] dp = new int[Cap +1];
        for (int i = 1; i <= N; i++) {
            for (int j = Cap; j >=1 ; j--) {
                if(j >= weights[i -1])
                {
                    dp[j] = Math.max(dp[j - weights[i -1]] + vals[i-1], dp[j]);
                }
            }
        }


        return dp[Cap];

    }


    /**
     *
     * 有N 个非负元素的数组，组合和为 S 的数量，每个元素最多用一次
     *
     * @param nums
     * @param target
     * @return
     */
    public int NumberOfSubsetSum1( int[] nums, int target) {

        int[][] dp = new int[nums.length + 1][target + 1];
        dp[0][0] = 1;
        for (int i = 1; i <=nums.length ; i++) {
            dp[i][0] = 1;  //init
            for (int j = target; j >=0 ; j--) {
                if(j >= nums[i -1])
                {
                    dp[i][j] = dp[i-1][j] + dp[i -1][j- nums[i -1]];
                }else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

       // Matrix.print(dp);
        return dp[nums.length][target];

    }


    /**
     *
     * 有N 个非负元素的数组，组合和为 S 的数量，每个元素最多用一次
     *
     * @param nums
     * @param target
     * @return
     */
    public int NumberOfSubsetSum2( int[] nums, int target) {

        int[][] dp = new int[nums.length + 1][target + 1];
        dp[0][0] = 1;
        for (int i = 1; i <=nums.length ; i++) {
            dp[i][0] = 1;  //init
            for (int j = 1; j  <= target ; j++) {
                if(j >= nums[i -1])
                {
                    dp[i][j] = dp[i-1][j] + dp[i -1][j- nums[i -1]];
                }else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

       // Matrix.print(dp);
        return dp[nums.length][target];

    }


    /**
     *
     * 有N 个非负元素的数组，组合和为 S 的数量，每个元素最多用一次
     *
     * @param nums
     * @param target
     * @return
     */
    public int NumberOfSubsetSum3( int[] nums, int target) {

        int[][] dp = new int[nums.length + 1][target + 1];
        dp[0][0] = 1;
        for (int i = 1; i <=nums.length ; i++) {
            dp[i][0] = 1;  //init
            for (int j = 1; j  <= target ; j++) {
                dp[i][j] = dp[i-1][j];
                if(j >= nums[i -1])
                {
                    dp[i][j] = dp[i][j] + dp[i -1][j- nums[i -1]];
                }
            }
        }

      //  Matrix.print(dp);
        return dp[nums.length][target];

    }

    /**
     * 有N 个非负元素的数组，组合和为 S 的数量，每个元素最多用一次
     *
     * @param nums
     * @param target
     * @return
     */
    public int NumberOfSubsetSum4( int[] nums, int target) {

        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <=nums.length ; i++) {
            for (int j = target; j  > 0  ; j--) {
                if(j >= nums[i -1])
                {
                    dp[j] = dp[j] + dp[j- nums[i -1]];
                }
            }
        }

       // Matrix.print(dp);
        return dp[target];

    }

    /**
     *
     * 有N 个非负元素的数组，组合和为 S 的数量，每个元素最多用一次
     *
     * @param nums
     * @param s
     * @return
     */
    public int NumberOfSubsetSum5(int[] nums, int s)
    {
        int[] dp = new int[s +1];
        dp[0] = 1;
        for(int n: nums)
        {
            for (int i = s; i >= n; i--)
            {
                dp[i] += dp[i -n];
            }
        }
       // Matrix.print(dp);
        return dp[s];
    }


    /**
     *
     * whether sub set of nums can combination s,, when nums[i] >= 0
     * @param nums
     * @param s
     * @return
     */
    public boolean isSubsetSum(int[] nums, int s)
    {
        boolean[] dp = new boolean[s +1];
        dp[0] = true;
        for(int n: nums)
        {
            for (int i = s; i >= n; i--)
            {
                dp[i] = dp[i] || dp[i -n];
            }
        }
        return dp[s];
    }

    /**
     *
     * 每个元素可以用多次
     * @param nums
     * @param s
     * @return
     */
    public int NumberOfSubsetSum6(int[] nums, int s)
    {
        int[] dp = new int[s +1];
        dp[0] = 1;
        for(int n: nums)
        {
            for (int i = n; i <= s; i++)
            {
                dp[i] += dp[i -n];
            }
        }
       // Matrix.print(dp);
        return dp[s];
    }




    public static void main(String[] args) {
        Knapsack knapsack = new Knapsack();
        System.out.println(knapsack.knapsack(50, new int[]{10,20,30},new int[]{60,100,120}));
        System.out.println(knapsack.knapsack2(50, new int[]{10,20,30},new int[]{60,100,120}));
        System.out.println(knapsack.knapsack3(50, new int[]{10,20,30},new int[]{60,100,120}));

        System.out.println("**********88");

        System.out.println(knapsack.NumberOfSubsetSum2(new int[]{1,5,11,5}, 5));
        System.out.println(knapsack.NumberOfSubsetSum1(new int[]{1,5,11,5}, 5));


        System.out.println("****************");
        System.out.println(knapsack.NumberOfSubsetSum1(new int[]{1,5,4,11,5}, 5));
        System.out.println(knapsack.NumberOfSubsetSum2(new int[]{1,5,4,11,5}, 5));
        System.out.println(knapsack.NumberOfSubsetSum3(new int[]{1,5,4,11,5}, 5));
        System.out.println(knapsack.NumberOfSubsetSum4(new int[]{1,5,4,11,5}, 5));
        System.out.println(knapsack.NumberOfSubsetSum5(new int[]{1,5,4,11,5}, 5));
    }

}
