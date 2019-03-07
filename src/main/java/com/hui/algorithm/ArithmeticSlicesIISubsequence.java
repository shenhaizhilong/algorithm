package com.hui.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/27 14:34
 *
 *
 * 446. Arithmetic Slices II - Subsequence
 * DescriptionHintsSubmissionsDiscussSolution
 * A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
 *
 * For example, these are arithmetic sequences:
 *
 * 1, 3, 5, 7, 9
 * 7, 7, 7, 7
 * 3, -1, -5, -9
 * The following sequence is not arithmetic.
 *
 * 1, 1, 2, 5, 7
 *
 * A zero-indexed array A consisting of N numbers is given. A subsequence slice of that array is any sequence of integers (P0, P1, ..., Pk) such that 0 ≤ P0 < P1 < ... < Pk < N.
 *
 * A subsequence slice (P0, P1, ..., Pk) of array A is called arithmetic if the sequence A[P0], A[P1], ..., A[Pk-1], A[Pk] is arithmetic. In particular, this means that k ≥ 2.
 *
 * The function should return the number of arithmetic subsequence slices in the array A.
 *
 * The input contains N integers. Every integer is in the range of -231 and 231-1 and 0 ≤ N ≤ 1000. The output is guaranteed to be less than 231-1.
 *
 *
 * Example:
 *
 * Input: [2, 4, 6, 8, 10]
 *
 * Output: 7
 *
 * Explanation:
 * All arithmetic subsequence slices are:
 * [2,4,6]
 * [4,6,8]
 * [6,8,10]
 * [2,4,6,8]
 * [4,6,8,10]
 * [2,4,6,8,10]
 * [2,6,10]
 *
 *
 * https://leetcode.com/problems/arithmetic-slices-ii-subsequence/discuss/92822/Detailed-explanation-for-Java-O(n2)-solution
 *
 */
public class ArithmeticSlicesIISubsequence {

    // f(i, diff) = sum(1 + T(j, d)), where  0 <= j < i and diff == A[i] - A[j].
    public int numberOfArithmeticSlices(int[] A) {
        if(A == null || A.length < 3) return 0;
        int N = A.length;
        int ans = 0;
        Map<Integer,Integer>[] maps = new Map[N];
        for (int i = 0; i < A.length; i++) {
            maps[i] = new HashMap<>();
            for (int j = 0; j < i; j++) {
                long diff = (long)A[i] - (long) A[j];
                if(diff > Integer.MAX_VALUE || diff < Integer.MIN_VALUE)continue;
                int d = (int)diff;
                int prev = maps[j].getOrDefault(d,0);
                int curr = maps[i].getOrDefault(d,0);
                ans += prev; // why prev ? for an example:
                //   A = {2,4,6,8,10}
                // index: 0,1,2,3, 4
                //                 when i = 4

                // 如果有相同的diff则在 序列后面 加上A[i]
                //  4,6,8 + 10
                // 2,4,6,8 + 10
                // 6,8 + 10  =>   map[3][2] = 3
                // 2,6 + 10   map[2][4] = 1
                maps[i].put(d, 1 + curr + prev);
            }
        }
        return ans;

    }
}
