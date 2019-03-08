package com.hui.Array;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/4 0:17
 *
 * 768. Max Chunks To Make Sorted II
 * DescriptionHintsSubmissionsDiscussSolution
 * This question is the same as "Max Chunks to Make Sorted" except the integers of the given array are not necessarily distinct, the input array could be up to length 2000, and the elements could be up to 10**8.
 *
 * Given an array arr of integers (not necessarily distinct), we split the array into some number of "chunks" (partitions), and individually sort each chunk.  After concatenating them, the result equals the sorted array.
 *
 * What is the most number of chunks we could have made?
 *
 * Example 1:
 *
 * Input: arr = [5,4,3,2,1]
 * Output: 1
 * Explanation:
 * Splitting into two or more chunks will not return the required result.
 * For example, splitting into [5, 4], [3, 2, 1] will result in [4, 5, 1, 2, 3], which isn't sorted.
 * Example 2:
 *
 * Input: arr = [2,1,3,4,4]
 * Output: 4
 * Explanation:
 * We can split into two chunks, such as [2, 1], [3, 4, 4].
 * However, splitting into [2, 1], [3], [4], [4] is the highest number of chunks possible.
 * Note:
 *
 * arr will have length in range [1, 2000].
 * arr[i] will be an integer in range [0, 10**8].
 *
 *
 * * original:   0, 2, 3, 5, 4, 5, 7, 6
 *  * max:       0, 2, 3, 5, 5, 5, 7, 7
 *    min:       0, 2, 3, 4, 4, 5, 6, 6
 *  * index:     0, 1, 2, 3, 4, 5, 6, 7
 *  * The chunks are: 0 | 2 | 3 | 5,4 | 5 | 7, 6
 *
 */
public class MaxChunksToMakeSortedII {

    public int maxChunksToSorted(int[] arr) {
        if(arr == null || arr.length < 1)return 0;
        int N = arr.length;
        int ans = 1;
        int[] currMaxFromLeft = new int[N];
        int[] currMinFromRight = new int[N];
        currMaxFromLeft[0] = arr[0];
        currMinFromRight[N -1] = arr[N -1];
        for (int i = 1; i < N; i++) {
            currMaxFromLeft[i] = arr[i] > currMaxFromLeft[i -1] ? arr[i]: currMaxFromLeft[i-1];
            currMinFromRight[N -i -1] = arr[N -i -1] > currMinFromRight[N - i] ? currMinFromRight[N -i]: arr[N -i -1];
        }

        //Iterate through the array, each time all elements to the left are smaller (or equal) to all elements to the right, there is a new chunk.

        for (int i = 1; i < N; i++) {
            if(currMinFromRight[i] >= currMaxFromLeft[i -1])ans++;
        }
        return ans;

    }


    public int maxChunksToSorted2(int[] arr) {
        if(arr == null || arr.length < 1)return 0;
        int N = arr.length;
        int ans = 1;

        int[] currMinFromRight = new int[N];
        currMinFromRight[N -1] = arr[N -1];
        for (int i = N -2; i >=0; i--) {
            currMinFromRight[i] = arr[i] > currMinFromRight[i + 1] ? currMinFromRight[i + 1]: arr[i];
        }

        //Iterate through the array, each time all elements to the left are smaller (or equal) to all elements to the right, there is a new chunk.

        int max = arr[0];
        for (int i = 1; i < N; i++) {

            max = Math.max(max, arr[i -1]);
            if(currMinFromRight[i] >= max)ans++;
        }
        return ans;

    }

    public int maxChunksToSorted3(int[] arr) {
        if (arr == null || arr.length < 1) return 0;
        int N = arr.length;
        int ans = 0;
        int[] nums = Arrays.copyOf(arr, N);
        Arrays.sort(nums);
        int sumArr = 0;
        int sumNums = 0;
        for (int i = 0; i < N; i++) {
            sumArr += arr[i];
            sumNums += nums[i];
            if(sumArr == sumNums)ans++;
        }
        return ans;

    }

        public static void main(String[] args) {
        MaxChunksToMakeSortedII makeSortedII = new MaxChunksToMakeSortedII();
        System.out.println(makeSortedII.maxChunksToSorted(new int[]{0, 2, 3, 5, 4, 5, 7, 6}));
        System.out.println(makeSortedII.maxChunksToSorted2(new int[]{0, 2, 3, 5, 4, 5, 7, 6}));
            System.out.println(makeSortedII.maxChunksToSorted3(new int[]{0, 2, 3, 5, 4, 5, 7, 6}));
    }
}
