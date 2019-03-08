package com.hui.Array;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/23 18:18
 *
 *
 * 961. N-Repeated Element in Size 2N Array
 * DescriptionHintsSubmissionsDiscussSolution
 * In a array A of size 2N, there are N+1 unique elements, and exactly one of these elements is repeated N times.
 *
 * Return the element repeated N times.
 *
 *
 *
 * Example 1:
 *
 * Input: [1,2,3,3]
 * Output: 3
 * Example 2:
 *
 * Input: [2,1,2,5,3,2]
 * Output: 2
 * Example 3:
 *
 * Input: [5,1,5,2,5,3,5,4]
 * Output: 5
 *
 *
 * Note:
 *
 * 4 <= A.length <= 10000
 * 0 <= A[i] < 10000
 * A.length is even
 *
 *
 */
public class NRepeatedElementinSize2NArray {
    public int repeatedNTimes(int[] A) {
        int[] counter = new int[10001];
        int N = A.length/2;
        for(int a:A)
        {
            counter[a]++;
            if(counter[a] == N)return a;

        }
        return -1;
    }

    public int repeatedNTimes2(int[] A) {
        int[] counter = new int[10001];
        for(int a:A)
        {
            counter[a]++;
            if(counter[a] >1 )return a;

        }
        return -1;
    }

    public int repeatedNTimes3(int[] A) {
        for (int i = 2; i < A.length; i++) {
                if(A[i] == A[i -1] || A[i] == A[i -2])return A[i];
                else if( A[i -1] == A[i -2])return A[i -1];
        }
        return A[A.length -1];
    }
}
