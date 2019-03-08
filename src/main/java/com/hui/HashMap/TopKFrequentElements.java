package com.hui.HashMap;

import java.util.*;

/**
 *
 *
 * 347. Top K Frequent Elements
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a non-empty array of integers, return the k most frequent elements.
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 * Note:
 *
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 * @author: shenhaizhilong
 * @date: 2018/8/23 17:27
 */
public class TopKFrequentElements {
    private PriorityQueue<int[]> freqHeap;
    private int size;

    public void init(int size) {
        this.size = size;
        freqHeap = new PriorityQueue<>(size, (int[] o1, int[] o2) -> o1[1] - o2[1]);
    }

    private int[] add(int[] val)
    {
        if(freqHeap.size() < size)
        {
            freqHeap.offer(val);
        }else if(val[1] > freqHeap.peek()[1])
        {
            freqHeap.offer(val);
            freqHeap.poll();
        }
        return freqHeap.peek();
    }
    public List<Integer> topKFrequent(int[] nums, int k) {
        init(k);
        HashMap<Integer, int[]> map = new HashMap<>();
        for (int i :
                nums) {
            if (!map.containsKey(i))
            {
                int[] val = new int[]{i,1};
                map.put(i, val);
            }else {
                int[] val = map.get(i);
                val[1] +=1;
            }
        }

        //O(nlogk)
        for (int[] val :
                map.values()) {
            add(val);
        }
        List<Integer> list = new LinkedList<>();
        while (!freqHeap.isEmpty())
        {
           ((LinkedList<Integer>) list).addFirst(freqHeap.poll()[0]);
        }
        return list;
    }

    public static void main(String[] args) {

        int[] nums = {5,9,1,1,1,1,2,2,2,2,1,3,3,3,4,4,5,5,5,5,5,5,5,5,5,5,5,5,0,0,0,0,0,0,0};
        int k = 3;
        TopKFrequentElements topKFrequentElements = new TopKFrequentElements();
        List<Integer> list =  topKFrequentElements.topKFrequent(nums, k);
        System.out.println(list);
    }
}
