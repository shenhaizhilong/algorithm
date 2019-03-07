package com.hui.algorithm;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/21 0:23
 *
 * 910. Smallest Range II
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an array A of integers, for each integer A[i] we need to choose either x = -K or x = K, and add x to A[i] (only once).
 *
 * After this process, we have some array B.
 *
 * Return the smallest possible difference between the maximum value of B and the minimum value of B.
 *
 *
 *
 * Example 1:
 *
 * Input: A = [1], K = 0
 * Output: 0
 * Explanation: B = [1]
 * Example 2:
 *
 * Input: A = [0,10], K = 2
 * Output: 6
 * Explanation: B = [2,8]
 * Example 3:
 *
 * Input: A = [1,3,6], K = 3
 * Output: 3
 * Explanation: B = [4,6,3]
 *
 *
 * Note:
 *
 * 1 <= A.length <= 10000
 * 0 <= A[i] <= 10000
 * 0 <= K <= 10000
 *
 *
 */
public class SmallestRangeII {


    public int smallestRangeII(int[] A, int K) {
        Arrays.sort(A);
        int len = A.length;
        int min = A[0];
        int max = A[len -1];
        int res = max - min;
        // all number  less or equal to A[i]  will add 2*k, others add zero
        // after add 2*k, the max value may change to A[i] + 2*k or last max
        // the min value may change to A[0] + 2*k or A[i +1]
        for (int i = 0; i < len - 1; i++) {
            max = Math.max(max, A[i] + 2 * K);
            min = Math.min(A[i + 1], A[0] + 2 * K);
            res = Math.min(res, max - min);
        }

        return res;
    }

    public static void main(String[] args) {
        SmallestRangeII smallestRangeII = new SmallestRangeII();
        System.out.println(smallestRangeII.smallestRangeII(new int[]{1,3,6}, 3));
    }
}
