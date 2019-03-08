package com.hui.Array;

import java.util.PriorityQueue;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/20 12:40
 */
public class KthSmallestElementinaSortedMatrix {


    /**
     *378. Kth Smallest Element in a Sorted Matrix
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
     *
     * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
     *
     * Example:
     *
     * matrix = [
     *    [ 1,  5,  9],
     *    [10, 11, 13],
     *    [12, 13, 15]
     * ],
     * k = 8,
     *
     * return 13.
     * Note:
     * You may assume k is always valid, 1 ≤ k ≤ n2.
     *
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest(int[][] matrix, int k) {
        int R = matrix.length;
        int C = R;
        if(k == 1)return matrix[0][0];
        if(k == R*R)return matrix[R-1][C-1];
        PriorityQueue<Tuple> minHeap = new PriorityQueue<>();
        for (int i = 0; i < R; i++) {
            minHeap.add(new Tuple(0,i, matrix[0][i]));
        }

        // remove k-1 smallest val.
        for (int i = 0; i < k - 1; i++) {
            Tuple t = minHeap.poll();
            if(t.x == R -1)continue;
            minHeap.offer(new Tuple(t.x + 1, t.y, matrix[t.x +1][t.y]));
        }

        return minHeap.poll().val;


    }

    private static class Tuple implements Comparable<Tuple>{
        int x,y,val;
        Tuple(int x, int y, int val)
        {
            this.x = x;
            this.y = y;
            this.val =val;
        }

        @Override
        public int compareTo(Tuple o) {
            return this.val - o.val;
        }
    }


    /**
     *
     *  Binary Search + Sliding Window
     *
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest2(int[][] matrix, int k) {
        int R = matrix.length;
        int C = R;
        if(k == 1)return matrix[0][0];
        if(k == R*C)return matrix[R-1][C-1];
        int low = matrix[0][0];
        int high = matrix[R-1][C-1];
        while (low < high)
        {
            int mid = (low + high)>>> 1;
            int count = 0;   // count is the number of elements that are less than or equal to mid;
            int end = C -1;
            for (int i = 0; i < R; i++) {
                while (end >=0 && matrix[i][end] > mid)end--;
                count += (end +1);
                if(end < 0)break;
            }

            if(count < k)low = mid +1;
            else high = mid;
        }

        return low;

    }
    public static void main(String[] args) {

        KthSmallestElementinaSortedMatrix kthSmallestElementinaSortedMatrix = new KthSmallestElementinaSortedMatrix();
//        System.out.println(kthSmallestElementinaSortedMatrix.kthSmallest2(
//                new int[][]{
//                        {1,  5,  9},
//                        {10, 11, 13},
//                        {12, 13, 15}},5));

        System.out.println(kthSmallestElementinaSortedMatrix.kthSmallest2(
                new int[][]{
                        {1, 2, 2},
                        {4, 5, 7},
                        {6, 8, 9}},8));

    }
}
