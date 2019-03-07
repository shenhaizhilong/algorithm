package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/19 9:26
 */
public class FirstMissingPositive {


    /**
     *
     * 41. First Missing Positive
     * DescriptionHintsSubmissionsDiscussSolution
     * Given an unsorted integer array, find the smallest missing positive integer.
     *
     * Example 1:
     *
     * Input: [1,2,0]
     * Output: 3
     * Example 2:
     *
     * Input: [3,4,-1,1]
     * Output: 2
     * Example 3:
     *
     * Input: [7,8,9,11,12]
     * Output: 1
     * Note:
     *
     * Your algorithm should run in O(n) time and uses constant extra space.
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        if(nums == null || nums.length == 0)return 1;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == i + 1 || nums[i] <=0 || nums[i] > nums.length)continue;
            else if(nums[nums[i] -1] != nums[i]){
                  swap(nums, i, nums[i] -1);  // place nums[i]  to index nums[i] -1, so k + 1 = nums[k]
                  i--;
            }
        }

        int i = 0;
        while (i < nums.length && nums[i] == i + 1)i++;
        return i+1;
    }

    private void swap(int[] nums, int i, int j)
    {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        FirstMissingPositive missingPositive = new FirstMissingPositive();
        System.out.println(missingPositive.firstMissingPositive(new int[]{3,4,-1,1}));
      //  System.out.println(missingPositive.firstMissingPositive(new int[]{1,5,4,2}));
    }
}
