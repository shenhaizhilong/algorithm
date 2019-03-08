package com.hui.HashMap;

import java.util.HashMap;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/12 9:48
 */
public class SubarraySumEqualsK {


    /**
     *
     * 560. Subarray Sum Equals K
     * DescriptionHintsSubmissionsDiscussSolution
     * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
     *
     * Example 1:
     * Input:nums = [1,1,1], k = 2
     * Output: 2
     * Note:
     * The length of the array is in range [1, 20,000].
     * The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
     *
     *
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
       int count = 0;
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        hashMap.put(0,1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if(hashMap.containsKey(sum - k))
            {
                count += hashMap.get(sum -k);
            }
            hashMap.put(sum, hashMap.getOrDefault(sum, 0) +1);
        }

        return count;
    }

    public int bruteForce(int[] nums, int k)
    {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j <= nums.length ; j++) {
                int sum = 0;
                for (int l = i; l <j ; l++) {
                    sum += nums[l];
                }
                if(sum == k)
                {
                    count++;
                }
            }
        }
        return count;
    }



    public static void main(String[] args) {

        SubarraySumEqualsK subarraySumEqualsK = new SubarraySumEqualsK();
        System.out.println(subarraySumEqualsK.bruteForce(new int[]{3,4,7,2,-3,1,4,2},7));
        System.out.println(subarraySumEqualsK.subarraySum(new int[]{3,4,0,7},7));
    }
}
