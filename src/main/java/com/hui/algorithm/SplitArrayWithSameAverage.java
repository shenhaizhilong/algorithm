package com.hui.algorithm;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/6 13:32
 *
 * 805. Split Array With Same Average
 * DescriptionHintsSubmissionsDiscussSolution
 * In a given integer array A, we must move every element of A to either list B or list C. (B and C initially start empty.)
 *
 * Return true if and only if after such a move, it is possible that the average value of B is equal to the average value of C, and B and C are both non-empty.
 *
 * Example :
 * Input:
 * [1,2,3,4,5,6,7,8]
 * Output: true
 * Explanation: We can split the array into [1,4,5,8] and [2,3,6,7], and both of them have the average of 4.5.
 * Note:
 *
 * The length of A will be in the range [1, 30].
 * A[i] will be in the range of [0, 10000].
 *
 *
 *
 *
 * If the array of size n can be splitted into group A and B with same mean, assuming A is the smaller group, then
 *
 *   totalSum/n = Asum/k = Bsum/(n-k), where k = A.size() and 1 <= k <= n/2;
 *   Asum = totalSum*k/n, which is an integer. So we have totalSum*k%n == 0;
 *
 *
 *
 */
public class SplitArrayWithSameAverage {

    public boolean splitArraySameAverage(int[] A) {
        int totalSum = 0;
        for(int i: A) totalSum += i; // sum[A]
        int N = A.length;  // total length
        int M = N/2;
        Arrays.sort(A);
        for (int i = 1; i <= M; i++) {
            if(totalSum*i % N == 0 && combinationSum(A, 0, i, totalSum*i/N))
            {
                return true;
            }
        }
        return false;



    }

    /**
     *
     * candidates is sorted array, started index is idx,  sum of left k number in candidates pool  is the target.
     * @param candidates
     * @param idx
     * @param k
     * @param target
     * @return
     */
    public boolean combinationSum(int[] candidates,int idx, int k, int target) {
        if(k == 0)return target == 0;
        if(target < k*candidates[idx])return false;   // current min value in candidates pool is  candidates[idx], so if k*min > target then return false.
        for (int i = idx; i < candidates.length - k + 1 ; i++) {   // candidates pool still have k elements to join A group
            if (i > idx && candidates[i] == candidates[i - 1])   // chose previous element can't find the target, so the current  element also fail
                continue;
            if(candidates[i] <= target && combinationSum(candidates, i+1, k -1, target - candidates[i])){
                return true;
            }
        }

        return false;

    }

    public boolean combinationSum2(int[] candidates,int idx, int k, int target) {
        if(k == 0)return target == 0;
        if(target < k*candidates[idx])return false;   // current min value in candidates pool is  candidates[idx], so if k*min > target then return false.
        for (int i = idx; i < candidates.length - k + 1 ; i++) {   // candidates pool still have k elements to join A group
            if (i > idx && candidates[i] == candidates[i - 1])   // chose previous element can't find the target, so the current  element also fail
                continue;
            if(candidates[i] > target)return false;
            if(combinationSum2(candidates, i+1, k -1, target - candidates[i])){
                return true;
            }
        }

        return false;

    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8};

        SplitArrayWithSameAverage splitArrayWithSameAverage = new SplitArrayWithSameAverage();
       // System.out.println(splitArrayWithSameAverage.splitArraySameAverage(arr));
      //  System.out.println(splitArrayWithSameAverage.combinationSum(arr, 0, 3, 6));
        System.out.println(splitArrayWithSameAverage.combinationSum(arr, 0, 8, 36));

        int[] arr2 = {2,3,12,16,18,19};
        System.out.println(splitArrayWithSameAverage.splitArraySameAverage(arr2));

        System.out.println(splitArrayWithSameAverage.combinationSum2(arr, 0, 8, 10));


    }
}
