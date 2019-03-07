package com.hui.algorithm;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/9 8:22
 */
public class WiggleSortII {


    /**
     *324. Wiggle Sort II
     * DescriptionHintsSubmissionsDiscussSolution
     * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
     *
     * Example 1:
     *
     * Input: nums = [1, 5, 1, 1, 6, 4]
     * Output: One possible answer is [1, 4, 1, 5, 1, 6].
     * Example 2:
     *
     * Input: nums = [1, 3, 2, 2, 3, 1]
     * Output: One possible answer is [2, 3, 1, 3, 1, 2].
     * Note:
     * You may assume all input has valid answer.
     *
     * Follow Up:
     * Can you do it in O(n) time and/or in-place with O(1) extra space?
     *
     *
     *https://leetcode.com/problems/wiggle-sort-ii/discuss/77677/O(n)+O(1)-after-median-Virtual-Indexing
     *
     * Explanation
     * First I find a median using nth_element. That only guarantees O(n) average time complexity and I don't know about space complexity. I might write this myself using O(n) time and O(1) space, but that's not what I want to show here.
     *
     * This post is about what comes after that. We can use three-way partitioning to arrange the numbers so that those larger than the median come first, then those equal to the median come next, and then those smaller than the median come last.
     *
     * Ordinarily, you'd then use one more phase to bring the numbers to their final positions to reach the overall wiggle-property. But I don't know a nice O(1) space way for this. Instead, I embed this right into the partitioning algorithm. That algorithm simply works with indexes 0 to n-1 as usual, but sneaky as I am, I rewire those indexes where I want the numbers to actually end up. The partitioning-algorithm doesn't even know that I'm doing that, it just works like normal (it just uses A(x) instead of nums[x]).
     *
     * Let's say nums is [10,11,...,19]. Then after nth_element and ordinary partitioning, we might have this (15 is my median):
     *
     * index:     0  1  2  3   4   5  6  7  8  9
     * number:   18 17 19 16  15  11 14 10 13 12
     * I rewire it so that the first spot has index 5, the second spot has index 0, etc, so that I might get this instead:
     *
     * index:     5  0  6  1  7  2  8  3  9  4
     * number:   11 18 14 17 10 19 13 16 12 15
     * And 11 18 14 17 10 19 13 16 12 15 is perfectly wiggly. And the whole partitioning-to-wiggly-arrangement (everything after finding the median) only takes O(n) time and O(1) space.
     *
     *
     * @param nums
     */
    public void wiggleSort(int[] nums) {

        int len = nums.length;
        int middle = Sort.selectKthSmall(nums, len/2 + 1);
        threeWayPartition(nums, middle);
    }


    /**
     *
     * https://en.wikipedia.org/wiki/Dutch_national_flag_problem#Pseudocode
     *
     *
     * If the above description is unclear, maybe this explicit listing helps:
     *
     * Accessing A(0) actually accesses nums[1].
     * Accessing A(1) actually accesses nums[3].
     * Accessing A(2) actually accesses nums[5].
     * Accessing A(3) actually accesses nums[7].
     * Accessing A(4) actually accesses nums[9].
     * Accessing A(5) actually accesses nums[0].
     * Accessing A(6) actually accesses nums[2].
     * Accessing A(7) actually accesses nums[4].
     * Accessing A(8) actually accesses nums[6].
     * Accessing A(9) actually accesses nums[8].
     *
     * @param nums
     * @param middle
     */
    private void threeWayPartition(int[] nums, int middle)
    {
        // i holds the boundary of numbers greater than mid.
        // j is the position of number under consideration.
        // And n is the boundary for the numbers lesser than the mid.
        int i = 0;
        int j = 0;
        int n = nums.length -1;
        int len = nums.length;
        while (j <=n)
        {
            int id = indexRewiring(j,len);
            if(nums[id] > middle)
            {
                swap(nums, indexRewiring(i,len), id);
                i++;
                j++;
            }else if(nums[id] < middle)
            {
                swap(nums, indexRewiring(n,len), id);
                n--;
            }else {
                j++;
            }
        }

    }

    private void swap(int[] nums, int i, int j)
    {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    private int indexRewiring(int index, int length)
    {
        return (2*index + 1)%(length | 1);
    }



    public  int selectKthSmall(int[] array, int k)
    {

        if(k > array.length || k < 1)throw new IllegalArgumentException("k > array.length");
        return selectKthSmall(array, 0, array.length -1, k);
    }

    private  int selectKthSmall(int[] array,  int start, int end, int k)
    {
        if(start == end) return array[start];

        // pivot 所在的索引位置
        int q = selectKthPartition(array, start, end);
        //pivot 在数组里面是第kth 大
        int kth = q - start +1;
        if(k == kth)
        {
            return array[q];
        }
        else if(k < kth)   //k< kth 大,那第k 大元素必在左半部分
        {
            return selectKthSmall(array, start, q-1, k);
        }else {   //k> kth 大,那第k 大元素必在右半部分
            return selectKthSmall(array, q+1, end, k-kth);
        }



    }

    private  int selectKthPartition(int[] array, int start, int end)
    {
        int i = start -1;
        int j = end ;

        int p =(start + end) >>>1;
        swap(array, p, end);
        int pivot = array[end];

        for(;;)
        {
            while (i <=end-1 && array[++i] < pivot){}
            while (j>=1 && array[--j] > pivot){}
            if(i < j)
            {
                swap(array, i,j);
            }else {
                break;
            }

        }
        //restore pivot
        swap(array, i, end);
        return i;
    }

    public static void main(String[] args) {

        int[] nums = new int[]{10,11,12,13,14,15,16,17,18,19};
        WiggleSortII wiggleSortII = new WiggleSortII();
        wiggleSortII.wiggleSort(nums);
        System.out.println(Arrays.toString(nums));

    }

}
