package com.hui.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/26 1:17
 *
 *
 * 962. Maximum Width Ramp
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an array A of integers, a ramp is a tuple (i, j) for which i < j and A[i] <= A[j].  The width of such a ramp is j - i.
 *
 * Find the maximum width of a ramp in A.  If one doesn't exist, return 0.
 *
 *
 *
 * Example 1:
 *
 * Input: [6,0,8,2,1,5]
 * Output: 4
 * Explanation:
 * The maximum width ramp is achieved at (i, j) = (1, 5): A[1] = 0 and A[5] = 5.
 * Example 2:
 *
 * Input: [9,8,1,0,1,9,4,0,4,1]
 * Output: 7
 * Explanation:
 * The maximum width ramp is achieved at (i, j) = (2, 9): A[2] = 1 and A[9] = 1.
 *
 *
 * Note:
 *
 * 2 <= A.length <= 50000
 * 0 <= A[i] <= 50000
 *
 */
public class MaximumWidthRamp {

    public int maxWidthRamp(int[] A) {

        int max = 0;
        Deque<Integer> deStack = new ArrayDeque<>();
        for (int i = 0; i < A.length; i++) {
            if(deStack.isEmpty() || A[deStack.peekFirst()] > A[i])
            {
                deStack.addFirst(i); // decreasing stack
            }
        }

        for (int i = A.length -1; i >=0; i--) {
            while (!deStack.isEmpty() && A[deStack.peekFirst()] <= A[i])
            {
                max = Math.max(max, i - deStack.pollFirst());
            }
        }
        return max;

    }

    public static void main(String[] args) {
        MaximumWidthRamp maximumWidthRamp = new MaximumWidthRamp();
      //  System.out.println(maximumWidthRamp.maxWidthRamp(new int[]{6,0,1,8,0,5}));
        System.out.println(maximumWidthRamp.maxWidthRamp(new int[]{9,8,1,0,1,9,4,0,4,1}));
    }
}
