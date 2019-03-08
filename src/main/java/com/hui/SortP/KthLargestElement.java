package com.hui.SortP;

import java.util.PriorityQueue;

/**
 *
 *
 * 215. Kth Largest Element in an Array
 * DescriptionHintsSubmissionsDiscussSolution
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Example 1:
 *
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 * Example 2:
 *
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 *
 *
 * https://leetcode.com/problems/kth-largest-element-in-an-array/discuss/60294/Solution-explained
 *
 *
 * @author: shenhaizhilong
 * @date: 2018/8/23 9:02
 */
public class KthLargestElement {
    private int size;
    PriorityQueue<Integer> minHeap;
    public int add(int val)
    {
        if(minHeap.size() < this.size)
        {
            minHeap.offer(val);
        }else if(val > minHeap.peek())
        {
            minHeap.poll();
            minHeap.offer(val);
        }
        return minHeap.peek();
    }

    public int findKthLargest(int[] nums, int k) {
        this.size = k;
        minHeap = new PriorityQueue<>(k);
        int val = 0;
        for (int i :
                nums) {
           val = add(i);
        }

        return val;
    }


    public int findKthLargest2(int[] nums, int k) {
        pivot(nums, 0, nums.length - 1, k);
        return nums[k - 1];
    }

    public void pivot(int[] nums, int start, int end, int k){
        if(start >= end){
            return;
        }
        int i = start;
        int j = end;
        int mid = (end - start)/2 + start;
        int pivot = nums[mid];
        while(i <= j) {
            while(i <= j && nums[i] > pivot ){
                i++;
            }
            while(i <= j && nums[j] < pivot) {
                j--;
            }
            if(i <= j){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j--;
            }
        }

        if(i > k - 1){
            pivot(nums, start, j, k);
        }else{
            pivot(nums, i, end, k);
        }
    }

    public static void main(String[] args) {
        int[] a = {3,2,1,5,6,4 };
        int[] b = {3,2,3,1,2,4,5,5,6};
        KthLargestElement kthLargestElement = new KthLargestElement();
        System.out.println(kthLargestElement.findKthLargest(a,2));
        System.out.println(kthLargestElement.findKthLargest(b,4));
        System.out.println(kthLargestElement.findKthLargest(b,1));
        System.out.println(Sort.findKthLargest(a,2));
        System.out.println(Sort.findKthLargest(b,1));
    }

}
