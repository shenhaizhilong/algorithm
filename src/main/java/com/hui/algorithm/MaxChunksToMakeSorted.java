package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/3 22:48
 *
 * 769. Max Chunks To Make Sorted
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an array arr that is a permutation of [0, 1, ..., arr.length - 1], we split the array into some number of "chunks" (partitions), and individually sort each chunk.  After concatenating them, the result equals the sorted array.
 *
 * What is the most number of chunks we could have made?
 *
 * Example 1:
 *
 * Input: arr = [4,3,2,1,0]
 * Output: 1
 * Explanation:
 * Splitting into two or more chunks will not return the required result.
 * For example, splitting into [4, 3], [2, 1, 0] will result in [3, 4, 0, 1, 2], which isn't sorted.
 * Example 2:
 *
 * Input: arr = [1,0,2,3,4]
 * Output: 4
 * Explanation:
 * We can split into two chunks, such as [1, 0], [2, 3, 4].
 * However, splitting into [1, 0], [2], [3], [4] is the highest number of chunks possible.
 * Note:
 *
 * arr will have length in range [1, 10].
 * arr[i] will be a permutation of [0, 1, ..., arr.length - 1].
 *
 *
 *
 * For example,
 *
 * original: 0, 2, 1, 4, 3, 5, 7, 6
 * max:      0, 2, 2, 4, 4, 5, 7, 7
 * index:    0, 1, 2, 3, 4, 5, 6, 7
 * The chunks are: 0 | 2, 1 | 4, 3 | 5 | 7, 6
 *
 *
 */
public class MaxChunksToMakeSorted {

    public int maxChunksToSorted(int[] arr) {
        if(arr == null || arr.length < 1)return 0;
        int currMax = 0;
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            currMax = currMax > arr[i] ? currMax: arr[i];// the maxValue from a[0] to a[i];
            if(currMax == i)res++;
        }
        return res;

    }
}
