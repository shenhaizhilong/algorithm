package com.hui.Array;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/28 21:03
 */
public class MountainArray {
    /**
     *852. Peak Index in a Mountain Array
     * DescriptionHintsSubmissionsDiscussSolution
     * Let's call an array A a mountain if the following properties hold:
     *
     * A.length >= 3
     * There exists some 0 < i < A.length - 1 such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]
     * Given an array that is definitely a mountain, return any i such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1].
     *
     * Example 1:
     *
     * Input: [0,1,0]
     * Output: 1
     * Example 2:
     *
     * Input: [0,2,1,0]
     * Output: 1
     * Note:
     *
     * 3 <= A.length <= 10000
     * 0 <= A[i] <= 10^6
     * A is a mountain, as defined above.
     *
     * @param A
     * @return
     */
    public int peakIndexInMountainArray(int[] A) {
        int left = 1, right = A.length -1;
        while (left < right)
        {
            int middle = (left + right)>>>1;
            if(A[middle] < A[middle +1])
            {
                left = middle+1;
            }else {
                right = middle;
            }
        }
        return left;
    }
}
