package com.hui.Array;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/21 1:00
 *
 * 922. Sort Array By Parity II
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an array A of non-negative integers, half of the integers in A are odd, and half of the integers are even.
 *
 * Sort the array so that whenever A[i] is odd, i is odd; and whenever A[i] is even, i is even.
 *
 * You may return any answer array that satisfies this condition.
 *
 *
 *
 * Example 1:
 *
 * Input: [4,2,5,7]
 * Output: [4,5,2,7]
 * Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.
 *
 *
 * Note:
 *
 * 2 <= A.length <= 20000
 * A.length % 2 == 0
 * 0 <= A[i] <= 1000
 *
 *
 */
public class SortArrayByParityII {

    public int[] sortArrayByParityII(int[] A) {
        int len = A.length;
        int[] val = new int[A.length];
        int even = 0;
        int odd = 1;

        for (int i = 0; i < len; i++) {
            if((A[i] & 1) == 0)
            {
                val[even] = A[i];
                even += 2;
            }else {
                val[odd] = A[i];
                odd += 2;
            }
        }

        return val;

    }

    public int[] sortArrayByParityII2(int[] A) {

        int odd = 1;
        for (int even = 0; even < A.length ; even += 2) {
            if((A[even] & 1) == 1)
            {
                while ( (A[odd] & 1) == 1)
                    odd += 2;

                // swap A[odd] and A[even]
                int t = A[even];
                A[even] = A[odd];
                A[odd] = t;
            }
        }

        return A;
    }

}
