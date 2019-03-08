package com.hui.Array;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/18 17:45
 *908. Smallest Range I
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an array A of integers, for each integer A[i] we may choose any x with -K <= x <= K, and add x to A[i].
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
 * Output: 0
 * Explanation: B = [3,3,3] or B = [4,4,4]
 *
 *
 * Note:
 *
 * 1 <= A.length <= 10000
 * 0 <= A[i] <= 10000
 * 0 <= K <= 10000
 *
 */
public class SmallestRangeI {


    /**
     *
     * If min(A) + K < max(A) - K, then return max(A) - min(A) - 2 * K
     * If min(A) + K >= max(A) - K, then return 0
     *
     * Time Complexity:
     * O(N)
     *
     * @param A
     * @param K
     * @return
     */
    public int smallestRangeI(int[] A, int K) {
        int max = A[0];
        int min = A[0];
        for(int i:A)
        {
            if(i < min) min = i;
            if(i > max) max = i;
        }
        return Math.max(0, max - min -2*K);
    }
}
