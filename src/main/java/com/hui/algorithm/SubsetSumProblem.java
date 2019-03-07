package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/18 21:11
 *
 *https://www.geeksforgeeks.org/subset-sum-problem-dp-25/
 * Subset Sum Problem | DP-25
 * Given a set of non-negative integers, and a value sum, determine if there is a subset of the given set with sum equal to given sum.
 * Example:
 *
 * Input:  set[] = {3, 34, 4, 12, 5, 2}, sum = 9
 * Output:  True  //There is a subset (4, 5) with sum 9.
 * Recommended: Please solve it on “PRACTICE ” first, before moving on to the solution.
 * Let isSubSetSum(int set[], int n, int sum) be the function to find whether there is a subset of set[] with sum equal to sum. n is the number of elements in set[].
 *
 * The isSubsetSum problem can be divided into two subproblems
 * …a) Include the last element, recur for n = n-1, sum = sum – set[n-1]
 * …b) Exclude the last element, recur for n = n-1.
 * If any of the above the above subproblems return true, then return true.
 *
 */
public class SubsetSumProblem {

    public  boolean isSubsetSum(int set[], int n, int sum)
    {
        int[][] memo = new int[sum +1][n +1];

        // if sum is 0, the answer is true
        for (int i = 0; i <= n; i++) {
            memo[0][i] = 1;
        }

        for (int i = 1; i <= sum ; i++) {
            memo[i][0] =  -1;
        }
        boolean ans = dfs(set, n, sum, memo);
        Matrix.print(memo);
        return ans;
    }

    private boolean dfs(int set[], int n, int sum, int[][] memo)
    {
        if(memo[sum][n] != 0)return memo[sum][n] > 0;
        if(sum == 0)return true;
        if(n == 0 && sum != 0)return false;

        boolean flag = false;
        if(sum < set[n -1])
        {
           flag =  dfs(set, n -1, sum, memo);
        }else {
            // didn't use last element  or use last element
            if(dfs(set, n -1, sum, memo) || dfs(set, n -1, sum - set[n -1], memo))
            {
               flag = true;
            }
        }
        memo[sum][n] = flag ? 1: -1;
        return memo[sum][n] > 0;

    }


    public  boolean isSubsetSum2(int set[], int n, int sum)
    {
        boolean[][] dp = new boolean[sum +1][n +1];

        // if sum is 0, the answer is true
        for (int i = 0; i <= n; i++) {
            dp[0][i] = true;
        }

        for (int i = 1; i <= sum ; i++) {
            dp[i][0] =  false;
        }

        // bottom up manner
        for (int i = 1; i <= sum; i++) {
            for (int j = 1; j <= n; j++) {

                dp[i][j] = dp[i][j -1];
               if(i >= set[j -1])
               {
                   dp[i][j] = dp[i][j -1] || dp[i - set[j -1]][j -1];
               }
            }
        }
        Matrix.print(dp );
        return dp[sum][n];
    }


    // similar  with knapsack problem
    public  boolean isSubsetSum3(int set[], int n, int sum)
    {
        boolean[] dp = new boolean[sum +1];

        // if sum is 0, the answer is true
        dp[0] = true;
        // bottom up manner
        for (int i = 1; i <= n; i++) {
            for (int j = sum; j >=1 ; j--) {
                if( j >= set[i -1])
                {
                    dp[j] = dp[j] || dp[j - set[i -1]];
                }
            }
        }
        Matrix.print(dp );
        return dp[sum];
    }


    public static void main(String[] args) {
        int set[] = {3, 34, 4, 12, 5, 2};
        SubsetSumProblem subsetSumProblem =  new SubsetSumProblem();
        System.out.println(subsetSumProblem.isSubsetSum(set, 6, 10));
        System.out.println("******************");
        System.out.println(subsetSumProblem.isSubsetSum2(set, 6, 10));
        System.out.println(subsetSumProblem.isSubsetSum3(set, 6, 10));
//        for (int i = 1; i < 30; i++) {
//            System.out.println(subsetSumProblem.isSubsetSum3(set,6,i));
//            System.out.println("All");
//            System.out.println(subsetSumProblem.isSubsetSum(set,6,i) == subsetSumProblem.isSubsetSum2(set,6,i) &&
//            subsetSumProblem.isSubsetSum2(set,6,i) == subsetSumProblem.isSubsetSum3(set,6,i));
//        }
    }
}
