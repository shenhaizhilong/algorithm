package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/10 21:42
 *
 *629. K Inverse Pairs Array
 * DescriptionHintsSubmissionsDiscussSolution
 * Given two integers n and k, find how many different arrays consist of numbers from 1 to n such that there are exactly k inverse pairs.
 *
 * We define an inverse pair as following: For ith and jth element in the array, if i < j and a[i] > a[j] then it's an inverse pair; Otherwise, it's not.
 *
 * Since the answer may be very large, the answer should be modulo 109 + 7.
 *
 * Example 1:
 * Input: n = 3, k = 0
 * Output: 1
 * Explanation:
 * Only the array [1,2,3] which consists of numbers from 1 to 3 has exactly 0 inverse pair.
 * Example 2:
 * Input: n = 3, k = 1
 * Output: 2
 * Explanation:
 * The array [1,3,2] and [2,1,3] have exactly 1 inverse pair.
 * Note:
 * The integer n is in the range [1, 1000] and k is in the range [0, 1000].
 *
 *
 * ``````````
 *
 * dp[n][k] denotes the number of arrays that have k inverse pairs for array composed of 1 to n
 * we can establish the recursive relationship between dp[n][k] and dp[n-1][i]:
 *
 * if we put n as the last number then all the k inverse pair should come from the first n-1 numbers
 * if we put n as the second last number then there's 1 inverse pair involves n so the rest k-1 comes from the first n-1 numbers
 * ...
 * if we put n as the first number then there's n-1 inverse pairs involve n so the rest k-(n-1) comes from the first n-1 numbers
 *
 * dp[n][k] = dp[n-1][k]+dp[n-1][k-1]+dp[n-1][k-2]+...+dp[n-1][k+1-n+1]+dp[n-1][k-n+1]
 *
 * It's possible that some where in the right hand side the second array index become negative, since we cannot generate negative inverse pairs we just treat them as 0, but still leave the item there as a place holder.
 *
 * dp[n][k] = dp[n-1][k]+dp[n-1][k-1]+dp[n-1][k-2]+...+dp[n-1][k+1-n+1]+dp[n-1][k-n+1]
 * dp[n][k+1] = dp[n-1][k+1]+dp[n-1][k]+dp[n-1][k-1]+dp[n-1][k-2]+...+dp[n-1][k+1-n+1]
 *
 * so by deducting the first line from the second line, we have
 *
 * dp[n][k+1] = dp[n][k]+dp[n-1][k+1]-dp[n-1][k+1-n]
 *
 *
 * dp[n][k] = dp[n][k -1]+dp[n-1][k]-dp[n-1][k-n]
 *
 * dp[n][k] = dp[n][k -1]+dp[n-1][k]
 * if k >= n
 * dp[n][k] = dp[n][k] -dp[n-1][k-n]
 *
 *
 * https://leetcode.com/problems/k-inverse-pairs-array/discuss/104825/Shared-my-C++-O(n-*-k)-solution-with-explanation
 *
 *
 */
public class KInversePairsArray {

    public int kInversePairs(int n, int k) {
        int MOD = 1_000_000_007;
        if(k < 0 || k > n*(n-1)/2 || n < 2)return 0;
        if(k == 0 || k == n*(n-1)/2)return 1;
        long[][] dp = new long[n +1][k+1];
        dp[2][0] = 1;
        dp[2][1] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i][0] = 1;
            int min = Math.min(k, i*(i -1)/2);
            for (int j = 1; j <=min; j++) {
                dp[i][j] = dp[i -1][j] + dp[i][j -1];
                if(j >= i)
                {
                    dp[i][j] -= dp[i -1][j - i];
                    if(dp[i][j] < 0)
                        dp[i][j] += MOD;
                }
                dp[i][j] = dp[i][j] % MOD;
            }
        }
        return (int) dp[n][k];

    }

    public int kInversePairs2(int n, int k) {
        int MOD = 1_000_000_007;
        if(k < 0 || k > n*(n-1)/2)return 0;
        if(k == 0 || k == n*(n-1)/2)return 1;
        long[] dp = new long[k+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 3; i <= n; i++) {
            long[] currDp = new long[k +1];
            currDp[0] = 1;  // current  row, last row is dp
            int min = Math.min(k, i*(i -1)/2);
            for (int j = 1; j <= min; j++) {
                currDp[j] = dp[j] + currDp[j -1];
                if(j >= i)
                {
                    currDp[j] -= dp[j - i];
                    if(currDp[j] < 0)
                        currDp[j] += MOD;
                }
                currDp[j] = currDp[j] % MOD;

            }
            dp = currDp;  // got the current row, we need to update the last row.
        }
        //Matrix.print(dp);
        return (int) dp[k];

    }

    public static void main(String[] args) {
        KInversePairsArray kInversePairsArray = new KInversePairsArray();
        System.out.println(kInversePairsArray.kInversePairs(3,0));
        System.out.println(kInversePairsArray.kInversePairs(3,1));
        System.out.println(kInversePairsArray.kInversePairs(4,1));
        System.out.println(kInversePairsArray.kInversePairs(4,2));
        System.out.println(kInversePairsArray.kInversePairs(1000,1000));
        System.out.println("******************");
        System.out.println(kInversePairsArray.kInversePairs2(3,0));
        System.out.println(kInversePairsArray.kInversePairs2(3,1));

        System.out.println(kInversePairsArray.kInversePairs2(4,1));
        System.out.println(kInversePairsArray.kInversePairs2(4,2));
        System.out.println(kInversePairsArray.kInversePairs2(1000,1000));
    }

}
