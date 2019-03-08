package com.hui.Math;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/15 14:36
 *
 *
 *
 * 16. 3Sum Closest
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
 *
 * Example:
 *
 * Given array nums = [-1, 2, 1, -4], and target = 1.
 *
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 *
 */
public class Sum3Closest {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = 0;
        int diff = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length -2; i++) {
            if(i > 0 && nums[i] == nums[i -1])continue;
            for (int j = i +1,k = nums.length -1;j < k ; ) {

                int sum = nums[i] + nums[j] + nums[k];
                if(sum > target)
                {
                    k--;
                }else if(sum < target){
                    j++;
                }else return sum;

               int tempDiff = Math.abs(target - sum);
               if(tempDiff < diff)
               {
                   diff = tempDiff;
                   ans = sum;
               }

            }
        }
        return ans;
    }

    public static void main(String[] args) {

        Sum3Closest sum3Closest = new Sum3Closest();
        System.out.println(sum3Closest.threeSumClosest(new int[]{-1,2,1,-4},1));
    }

}
