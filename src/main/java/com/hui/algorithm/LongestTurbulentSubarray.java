package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2019/1/20 21:34
 *
 *978. Longest Turbulent Subarray
 * DescriptionHintsSubmissionsDiscussSolution
 * A subarray A[i], A[i+1], ..., A[j] of A is said to be turbulent if and only if:
 *
 * For i <= k < j, A[k] > A[k+1] when k is odd, and A[k] < A[k+1] when k is even;
 * OR, for i <= k < j, A[k] > A[k+1] when k is even, and A[k] < A[k+1] when k is odd.
 * That is, the subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.
 *
 * Return the length of a maximum size turbulent subarray of A.
 *
 *
 *
 * Example 1:
 *
 * Input: [9,4,2,10,7,8,8,1,9]
 * Output: 5
 * Explanation: (A[1] > A[2] < A[3] > A[4] < A[5])
 * Example 2:
 *
 * Input: [4,8,12,16]
 * Output: 2
 * Example 3:
 *
 * Input: [100]
 * Output: 1
 *
 *
 * Note:
 *
 * 1 <= A.length <= 40000
 * 0 <= A[i] <= 10^9
 *
 */
public class LongestTurbulentSubarray {
    // s means start idx, e means end idx
    //idx  0,1, 2,3, 4,5, 6, 7,8
    // arr[9,4, 2,10,7,8, 8, 1,9]
    // dp [1,1,-1,1,-1,0, 1,-1,0]
    //     s e
    //       s         e
    //                 s  e
    //                    s     e
    public int maxTurbulenceSize(int[] A) {
        if(A == null || A.length == 0)return 0;
        if(A.length == 1)return 1;
        int max = 1;
        int start = 0;
        int[] dp = new int[A.length];
        for (int i = 0; i < A.length -1; i++) {
            dp[i] = Integer.compare(A[i],A[i +1]);
        }
        for (int i = 1; i < A.length; i++) {
            if(dp[i -1]*dp[i] != -1)
            {
                max = Math.max(max, i - start +1);
                start = i;
            }
        }
        return max;
    }


    public int maxTurbulenceSize2(int[] A) {
        if(A == null || A.length == 0)return 0;
        if(A.length == 1)return 1;
        int start = 0;
        int max = 1;
        int sign = -2;
        for (int i = 1; i < A.length; i++) {
             sign = Integer.compare(A[i -1],A[i]);
            if(i == A.length -1 || sign*Integer.compare(A[i],A[i +1]) != -1)
            {
                max = Math.max(max, i - start + 1);
                start = i;
            }
        }
        return max;
    }
}
