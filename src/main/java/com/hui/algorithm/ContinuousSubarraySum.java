package com.hui.algorithm;

import java.util.HashMap;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/12 10:54
 */
public class ContinuousSubarraySum {


    /**
     *
     * https://leetcode.com/problems/continuous-subarray-sum/description/
     *
     * 523. Continuous Subarray Sum
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to the multiple of k, that is, sums up to n*k where n is also an integer.
     *
     * Example 1:
     * Input: [23, 2, 4, 6, 7],  k=6
     * Output: True
     * Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
     * Example 2:
     * Input: [23, 2, 6, 4, 7],  k=6
     * Output: True
     * Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
     * Note:
     * The length of the array won't exceed 10,000.
     * You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
     *
     *
     * Suppose sum_i represents the running sum starting from index 0 and ending at i,
     * once we find a mod that has been seen, say r, we have:
     * current one: sum_i = m*k + r
     * previous one: sum_j = n*k + r
     * Thus,
     * sum_i - sum_j = (m - n) *k
     *
     * @param nums
     * @param k
     * @return
     */

    public boolean checkSubarraySum(int[] nums, int k) {

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(0,-1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if(k != 0)sum = sum %k;
            Integer prev = hashMap.get(sum);
            if (prev != null) {
                if (i - prev > 1) return true;
            }
            else hashMap.put(sum, i);

        }
        return false;
    }

    public boolean bruteForce(int[] nums, int k)
    {

        for (int start = 0; start < nums.length -1; start++) {
            for (int end = start +1; end <=nums.length ; end++) {
                int sum = 0;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                    if(k !=0)
                    {
                        if(i > start && sum % k == 0)
                        {
                            return true;
                        }
                    }else {
                        if(i > start && sum == 0)return true;
                    }


                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ContinuousSubarraySum continuousSubarraySum = new ContinuousSubarraySum();
       // System.out.println(continuousSubarraySum.bruteForce(new int[]{23,2,4,6,7}, 6));
       // System.out.println(continuousSubarraySum.bruteForce(new int[]{23,2,4,6,7}, -6));
      //  System.out.println(continuousSubarraySum.checkSubarraySum(new int[]{23,2,4,6,7}, -6));
        System.out.println(continuousSubarraySum.bruteForce(new int[]{0,1,0}, 0));
        System.out.println(continuousSubarraySum.checkSubarraySum(new int[]{6,0}, 6));

    }
}
