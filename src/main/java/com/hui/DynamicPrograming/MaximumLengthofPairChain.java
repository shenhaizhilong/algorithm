package com.hui.DynamicPrograming;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/13 21:12
 *
 *
 * 646. Maximum Length of Pair Chain
 * DescriptionHintsSubmissionsDiscussSolution
 * You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.
 *
 * Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.
 *
 * Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.
 *
 * Example 1:
 * Input: [[1,2], [2,3], [3,4]]
 * Output: 2
 * Explanation: The longest chain is [1,2] -> [3,4]
 * Note:
 * The number of given pairs will be in the range [1, 1000]
 *
 *
 */
public class MaximumLengthofPairChain {
    public int findLongestChain(int[][] pairs) {
        if(pairs == null ||  pairs.length == 0)return 0;
        if(pairs.length == 1)return 1;

        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] != o2[0])return o1[0] - o2[0];
                else return o1[1] - o2[1];
            }
        });

        int N = pairs.length;
        int[] dp = new int[N];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < N; i++) {

            int currMax = 0;
            for (int j = 0; j < i; j++) {
                if(pairs[i][0] > pairs[j][1])
                {
                    currMax = Math.max(currMax, dp[j]);
                }
            }
            dp[i] = currMax +1;
            max = Math.max(dp[i], max);
        }
        return max;

    }

    public int findLongestChain2(int[][] pairs) {
        if(pairs == null ||  pairs.length == 0)return 0;
        if(pairs.length == 1)return 1;

        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                 return o1[1] - o2[1];
            }
        });

     //   printArr(pairs);

        int N = pairs.length;
        int ans = 1;
        int end = pairs[0][1];
        for (int i = 1; i < N; i++) {
            if(pairs[i][0] > end)
            {
                ans++;
                end = pairs[i][1];
            }
        }

        return ans;
    }

    private void printArr(int[][] arr)
    {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
    }

    public static void main(String[] args) {

        int[][] pairs = {{-6,9},{1,6},{8,10},{-1,4},{-6,-2},{-9,8},{-5,3},{0,3}};
        MaximumLengthofPairChain maximumLengthofPairChain = new MaximumLengthofPairChain();
        System.out.println(maximumLengthofPairChain.findLongestChain(pairs));
        System.out.println(maximumLengthofPairChain.findLongestChain2(pairs));
    }
}
