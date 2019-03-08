package com.hui.DynamicPrograming;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/7 22:05
 *
 *
 * 915. Partition Array into Disjoint Intervals
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an array A, partition it into two (contiguous) subarrays left and right so that:
 *
 * Every element in left is less than or equal to every element in right.
 * left and right are non-empty.
 * left has the smallest possible size.
 * Return the length of left after such a partitioning.  It is guaranteed that such a partitioning exists.
 *
 *
 *
 * Example 1:
 *
 * Input: [5,0,3,8,6]
 * Output: 3
 * Explanation: left = [5,0,3], right = [8,6]
 * Example 2:
 *
 * Input: [1,1,1,0,6,12]
 * Output: 4
 * Explanation: left = [1,1,1,0], right = [6,12]
 *
 *
 * Note:
 *
 * 2 <= A.length <= 30000
 * 0 <= A[i] <= 10^6
 * It is guaranteed there is at least one way to partition A as described.
 *
 *
 *
 *  array :           [5,0,3,8,6]
 *  index:            [0,1,2,3,4]
 *  maxLeft:          [5,5,5,8,8]
 *  minRight:         [0,0,3,6,6]
 *
 *  array :           [1,1,1,0,6,12]
 *  index:            [0,1,2,3,4,5]
 *  maxLeft:          [1,1,1,1,6,12]
 *  minRight:         [0,0,0,0,6,12]
 *
 *  array :           [1,1,1,1]
 * index:            [0,1,2,3]
 * maxLeft:          [1,1,1,1]
 * minRight:        [1,1,1,1]
 *
 *
 * maxLeft[i] is the maximum value from left side, i = 0, 1, 2, ... N -1.
 * minLeft[i] is the minimum value from right side, i = N -1,   N -2, N -3 ... 0;
 * if maxLeft[i] <= minLeft[i + 1], then all the numbers with index <= i is smaller or equal to the number with index > i.
 *
 *
 */
public class PartitionArrayintoDisjointIntervals {

    public int partitionDisjoint(int[] A) {
        if (A == null || A.length == 0) {
            return -1; // not exist
        }
        int N = A.length;
        int[] maxLeft = new int[N];
        int[] minRight = new int[N];
        maxLeft[0] = A[0];
        minRight[N-1] = A[N -1];
        for (int i = 1; i < N; i++) {
            maxLeft[i] = Math.max(maxLeft[i -1], A[i]);
        }
        for (int i = N -2; i >=0; i--) {
            minRight[i] = Math.min(minRight[i + 1], A[i]);
        }

        for (int i = 0; i < N -1; i++) {
            if (maxLeft[i] <= minRight[i +1])
                return i + 1;
        }
        return -1; // not exist

    }

    public int partitionDisjoint2(int[] A) {
        if (A == null || A.length == 0) {
            return -1; // not exist
        }
        int N = A.length;
        int[] minRight = new int[N];
        int maxLeft = A[0];
        minRight[N-1] = A[N -1];
        for (int i = N -2; i >=0; i--) {
            minRight[i] = Math.min(minRight[i + 1], A[i]);
        }

        for (int i = 0; i < N -1; i++) {
            maxLeft = Math.max(maxLeft, A[i]);
            if (maxLeft <= minRight[i +1])
                return i + 1;
        }
        return -1; // not exist

    }


    public static void main(String[] args) {
        PartitionArrayintoDisjointIntervals disjointIntervals = new PartitionArrayintoDisjointIntervals();
      //  System.out.println(disjointIntervals.partitionDisjoint(new int[]{5,0,3,8,6}));
        System.out.println(disjointIntervals.partitionDisjoint(new int[]{1,1,1,0,6,12}));
        System.out.println(disjointIntervals.partitionDisjoint2(new int[]{1,1,1,0,6,12}));

    }

}
