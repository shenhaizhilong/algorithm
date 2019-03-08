package com.hui.SortP;

import java.util.PriorityQueue;

/**
 *
 *
 * 703. Kth Largest Element in a Stream
 * DescriptionHintsSubmissionsDiscussSolution
 * Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Your KthLargest class will have a constructor which accepts an integer k and an integer array nums, which contains initial elements from the stream. For each call to the method KthLargest.add, return the element representing the kth largest element in the stream.
 *
 * Example:
 *
 * int k = 3;
 * int[] arr = [4,5,8,2];
 * KthLargest kthLargest = new KthLargest(3, arr);
 * kthLargest.add(3);   // returns 4
 * kthLargest.add(5);   // returns 5
 * kthLargest.add(10);  // returns 5
 * kthLargest.add(9);   // returns 8
 * kthLargest.add(4);   // returns 8
 * Note:
 * You may assume that nums' length ≥ k-1 and k ≥ 1.
 *
 *
 *
 * We can build a MinHeap that contains only k largest elements.
 * On add:
 *
 * compare a new element x with min to decide if we should pop min and insert x
 * take into account a case when heap_size is less than k
 * Construction is simply calling the add function N times.
 *
 * Time complexity:
 *
 * Construction: O(N * logK)
 * Adding: O(logK)
 * Additional memory:
 *
 * O(K)
 * @author: shenhaizhilong
 * @date: 2018/8/23 8:22
 */
public class KthLargest {
    private   PriorityQueue<Integer> minHeap;
    private int size;
    public KthLargest(int k, int[] nums) {
        minHeap = new PriorityQueue<>(k);
        this.size = k;
        for (int n :
                nums) {
            add(n);
        }
    }

    public int add(int val) {
        if(minHeap.size() < size)
        {
            minHeap.offer(val);
        }
        else if(val > minHeap.peek())
        {
            minHeap.offer(val);
            minHeap.poll();

        }
        return minHeap.peek();
    }


    public static void main(String[] args) {

        int[] nums = {4,5,8,2};
        KthLargest kthLargest = new KthLargest(3, nums);
        System.out.println(kthLargest.add(3));
        System.out.println(kthLargest.add(5));
        System.out.println(kthLargest.add(10));
        System.out.println(kthLargest.add(9));
        System.out.println(kthLargest.add(4));
    }
}
