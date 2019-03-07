package com.hui.algorithm;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * same idea with 480. Sliding Window Median
 * @author: shenhaizhilong
 * @date: 2018/10/17 12:25
 */
public class FindMedianfromDataStream {

    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    /** initialize your data structure here. */
    public FindMedianfromDataStream() {

    }



    public double findMedian() {
        return getMedian();
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


}
