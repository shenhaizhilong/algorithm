package com.hui.algorithm;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/18 18:09
 *
 * 905. Sort Array By Parity
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an array A of non-negative integers, return an array consisting of all the even elements of A, followed by all the odd elements of A.
 *
 * You may return any answer array that satisfies this condition.
 *
 *
 *
 * Example 1:
 *
 * Input: [3,1,2,4]
 * Output: [2,4,3,1]
 * The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
 *
 *
 * Note:
 *
 * 1 <= A.length <= 5000
 * 0 <= A[i] <= 5000
 *
 */
public class SortArrayByParity {



    public int[] sortArrayByParity(int[] A) {
        int lo = 0, hi = A.length -1;

        while (lo < hi)
        {
            // if A[lo] is even, lo++;
            while (lo < hi && (A[lo] & 1) == 0) lo++;
            // if A[hi] is odd, hi--;
            while (hi > lo && (A[hi] & 1) == 1) hi--;
            int temp = A[lo];
            A[lo++] = A[hi];
            A[hi--] = temp;
        }
        return A;
    }

    public static void main(String[] args) {
        SortArrayByParity sortArrayByParity = new SortArrayByParity();
        System.out.println(Arrays.toString(sortArrayByParity.sortArrayByParity(new int[]{3,1,2,4})));
    }

}
