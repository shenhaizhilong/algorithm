package com.hui.DynamicPrograming;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/27 13:55
 *
 *
 * 413. Arithmetic Slices
 * DescriptionHintsSubmissionsDiscussSolution
 * A sequence of number is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
 *
 * For example, these are arithmetic sequence:
 *
 * 1, 3, 5, 7, 9
 * 7, 7, 7, 7
 * 3, -1, -5, -9
 * The following sequence is not arithmetic.
 *
 * 1, 1, 2, 5, 7
 *
 * A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.
 *
 * A slice (P, Q) of array A is called arithmetic if the sequence:
 * A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.
 *
 * The function should return the number of arithmetic slices in the array A.
 *
 *
 * Example:
 *
 * A = [1, 2, 3, 4]
 *
 * return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.
 *
 */
public class ArithmeticSlices {
    public int numberOfArithmeticSlices(int[] A) {
        if(A == null || A.length < 3)return 0;
        int N = A.length;
        int ans = 0;
        int[] dp = new int[N];// dp[i] means the number of Arithmetic Slices ending with A[i];
        if(A[2] - A[1] == A[1] - A[0])dp[2] = 1;
        ans +=dp[2];
        for (int i = 3; i < N; i++) {
            if(A[i] - A[i -1] == A[i - 1] - A[i -2])
            {
                dp[i] = dp[i -1] + 1;
            }
            ans += dp[i];
        }
        return ans;

    }

    public int numberOfArithmeticSlices2(int[] A) {
        if(A == null || A.length < 3)return 0;
        int N = A.length;
        int ans = 0;
        int prev = 0;// dp[i] means the number of Arithmetic Slices ending with A[i];
        int curr = 0;
        for (int i = 2; i < N; i++) {
            curr = 0;
            if(A[i] - A[i -1] == A[i - 1] - A[i -2])
            {
                curr = prev + 1;
            }
            prev = curr;
            ans += curr;
        }
        return ans;

    }
}
