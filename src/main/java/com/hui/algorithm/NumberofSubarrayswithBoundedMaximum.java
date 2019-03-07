package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/1 13:10
 *
 *795. Number of Subarrays with Bounded Maximum
 * DescriptionHintsSubmissionsDiscussSolution
 * We are given an array A of positive integers, and two positive integers L and R (L <= R).
 *
 * Return the number of (contiguous, non-empty) subarrays such that the value of the maximum array element in that subarray is at least L and at most R.
 *
 * Example :
 * Input:
 * A = [2, 1, 4, 3]
 * L = 2
 * R = 3
 * Output: 3
 * Explanation: There are three subarrays that meet the requirements: [2], [2, 1], [3].
 * Note:
 *
 * L, R  and A[i] will be an integer in the range [0, 10^9].
 * The length of A will be in the range of [1, 50000].
 *
 *
 */
public class NumberofSubarrayswithBoundedMaximum {

    //  dp[i] means the max number of valid sub-array ending with A[i].
    public int numSubarrayBoundedMax(int[] A, int L, int R) {

        int N = A.length;
        int res = 0;
        int[] dp = new int[N];
        int prev = -1;
        for (int i = 0; i < A.length; i++) {
            if(A[i] >= L && A[i] <= R)
            {
                dp[i] =  i - prev;

            }else if (i > 0 && A[i] < L){

                dp[i] =  dp[i -1];
            }else if(A[i] > R) {
                dp[i] = 0;
                prev = i;

            }

            res += dp[i];
        }

        return res;
    }


    public int numSubarrayBoundedMax2(int[] A, int L, int R) {

        int res = 0;
        int dp = 0;
        int prev = -1;
        for (int i = 0; i < A.length; i++) {
            if(A[i] >= L && A[i] <= R)
            {
                dp =  i - prev;
            }else if (A[i] < L){
                dp = dp;  // we can remove this line, but maybe hard to understand, so i didn't delete it.
            }else {
                dp = 0;
                prev = i;
            }
            res += dp;
        }

        return res;
    }

    public static void main(String[] args) {

        NumberofSubarrayswithBoundedMaximum numberofSubarrayswithBoundedMaximum = new NumberofSubarrayswithBoundedMaximum();
        System.out.println(numberofSubarrayswithBoundedMaximum.numSubarrayBoundedMax(new int[]{2, 1, 4, 3},2, 3));
        System.out.println(numberofSubarrayswithBoundedMaximum.numSubarrayBoundedMax(new int[]{2, 9, 2, 5,6},2, 8));
        System.out.println(numberofSubarrayswithBoundedMaximum.numSubarrayBoundedMax(new int[]{3,4,1},2, 3));

        System.out.println(numberofSubarrayswithBoundedMaximum.numSubarrayBoundedMax2(new int[]{2, 1, 4, 3},2, 3));
        System.out.println(numberofSubarrayswithBoundedMaximum.numSubarrayBoundedMax2(new int[]{2, 9, 2, 5,6},2, 8));
        System.out.println(numberofSubarrayswithBoundedMaximum.numSubarrayBoundedMax2(new int[]{3,4,1},2, 3));

    }
}
