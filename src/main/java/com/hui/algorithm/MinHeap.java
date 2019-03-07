package com.hui.algorithm;

import java.util.Random;

/**
 * @author: shenhaizhilong
 * @date: 2019/1/23 10:13
 */
public class MinHeap {

    public void heapSort(int[] nums)
    {
        // build heap
        int n = nums.length;
        for (int i = n/2 -1; i >= 0 ; i--) {
            siftDown(nums, i, n);
        }

        // move max to the end
        for (int i = n -1; i > 0 ; i--) {
            int temp = nums[i];
            nums[i] = nums[0];
            nums[0] = temp;
            siftDown(nums, 0, i);
        }

    }

    private void siftDown(int[] nums, int idx, int length)
    {
        int child;
        int hole = nums[idx];
        for(; idx*2 < length; idx = child)
        {
           child = idx << 1;
           if(child < length -1 && nums[child] < nums[child +1])
           {
               child++;
           }
           if(nums[child] > hole)
           {
               nums[idx] = nums[child];
           }else break;
        }
        nums[idx] = hole;
    }

    public static void main(String[] args) {
        int[] nums = new int[10];
        Random random = new Random();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(100);
        }
        Matrix.print(nums);
        MinHeap minHeap = new MinHeap();
        minHeap.heapSort(nums);
        Matrix.print(nums);
    }
}
