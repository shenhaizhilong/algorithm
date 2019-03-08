package com.hui.SlidingWindow;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 *
 * *480. Sliding Window Median
 *      * DescriptionHintsSubmissionsDiscussSolution
 *      * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 *      *
 *      * Examples:
 *      * [2,3,4] , the median is 3
 *      *
 *      * [2,3], the median is (2 + 3) / 2 = 2.5
 *      *
 *      * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.
 *      *
 *      * For example,
 *      * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 *      *
 *      * Window position                Median
 *      * ---------------               -----
 *      * [1  3  -1] -3  5  3  6  7       1
 *      *  1 [3  -1  -3] 5  3  6  7       -1
 *      *  1  3 [-1  -3  5] 3  6  7       -1
 *      *  1  3  -1 [-3  5  3] 6  7       3
 *      *  1  3  -1  -3 [5  3  6] 7       5
 *      *  1  3  -1  -3  5 [3  6  7]      6
 *      * Therefore, return the median sliding window as [1,-1,-1,3,5,6].
 *      *
 *      * Note:
 *      * You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.
 *
 * @author: shenhaizhilong
 * @date: 2018/10/17 10:54
 */
public class SlidingWindowMedian {

    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();


    /**
     *https://leetcode.com/problems/sliding-window-median/discuss/96348/Java-solution-using-two-PriorityQueues
     * Almost the same idea of Find Median from Data Stream https://leetcode.com/problems/find-median-from-data-stream/
     *
     * Use two Heaps to store numbers. maxHeap for numbers smaller than current median, minHeap for numbers bigger than and equal to current median. A small trick I used is always make size of minHeap equal (when there are even numbers) or 1 element more (when there are odd numbers) than the size of maxHeap. Then it will become very easy to calculate current median.
     * Keep adding number from the right side of the sliding window and remove number from left side of the sliding window. And keep adding current median to the result.
     *
     * @param nums
     * @param k
     * @return
     */
    public double[] medianSlidingWindow(int[] nums, int k) {

        if(nums == null || nums.length == 0 || k < 1)return null;
        int len = nums.length;
        double[] results = new double[len -k + 1];  // store results.
        int position = 0;
        for (int i = 0; i <= len ; i++) {

            if(i >= k)
            {
                // update results when the sliding window size is k.
                results[position++] = getMedian();
                // i -k element move out of this sliding window
               removeNum(nums[i - k]);
            }
            if(i < len)
            {
                // incoming element into this sliding window
                addNum(nums[i]);

            }

        }
        return results;
    }


    public void addNum(int n)
    {
        if(n < getMedian())
        {
            maxHeap.offer(n);
        }else {
            minHeap.offer(n);
        }
        rebalance();

    }


    public void removeNum(int n)
    {
        if(n < getMedian())
        {
            maxHeap.remove(n);
        }else {
            minHeap.remove(n);
        }

        rebalance();
    }
    public void rebalance()
    {
        // always let minHeap's size >= maxHeap's size
        if(maxHeap.size() > minHeap.size())
        {
            minHeap.offer(maxHeap.poll());
        }
        // re-balance process
        if(minHeap.size() - maxHeap.size() > 1)
        {
            maxHeap.offer(minHeap.poll());
        }

    }

    public double getMedian()
    {
        if(maxHeap.isEmpty() && minHeap.isEmpty())return 0;
        return maxHeap.size() == minHeap.size() ? ((double)maxHeap.peek() + minHeap.peek())/2.0:(double)minHeap.peek();
    }


    public static void main(String[] args) {


        SlidingWindowMedian slidingWindowMedian = new SlidingWindowMedian();
        System.out.println(Arrays.toString(slidingWindowMedian.medianSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3)));

    }
}
