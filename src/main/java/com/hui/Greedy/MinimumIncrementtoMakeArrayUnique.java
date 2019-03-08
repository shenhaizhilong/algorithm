package com.hui.Greedy;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/5 18:18
 *
 * 945. Minimum Increment to Make Array Unique
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an array of integers A, a move consists of choosing any A[i], and incrementing it by 1.
 *
 * Return the least number of moves to make every value in A unique.
 *
 *
 *
 * Example 1:
 *
 * Input: [1,2,2]
 * Output: 1
 * Explanation:  After 1 move, the array could be [1, 2, 3].
 * Example 2:
 *
 * Input: [3,2,1,2,1,7]
 * Output: 6
 * Explanation:  After 6 moves, the array could be [3, 4, 1, 2, 5, 7].
 * It can be shown with 5 or less moves that it is impossible for the array to have all unique values.
 *
 *
 * Note:
 *
 * 0 <= A.length <= 40000
 * 0 <= A[i] < 40000
 *
 */
public class MinimumIncrementtoMakeArrayUnique {

    public int minIncrementForUnique(int[] A) {
        Arrays.sort(A);
        int next = A[0];
        int ans = 0;
        for(int i:A)
        {
            ans += Math.max(0, next - i);
            next = Math.max(next, i) + 1; // next need to be bigger than pre
        }

        return ans;
    }
}
