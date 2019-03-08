package com.hui.Math;

import java.util.*;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/7 11:29
 */
public class ThreeSum {


    /**
     *3Sum
     * DescriptionHintsSubmissionsDiscussSolution
     * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
     *
     * Note:
     *
     * The solution set must not contain duplicate triplets.
     *
     * Example:
     *
     * Given array nums = [-1, 0, 1, 2, -1, -4],
     *
     * A solution set is:
     * [
     *   [-1, 0, 1],
     *   [-1, -1, 2]
     * ]
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);
        HashMap<Integer,Integer> map = new HashMap<>(nums.length);
        for(int i = 0; i < nums.length; i++)
        {
            map.put(nums[i],i);
        }


        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i <nums.length ; i++) {
            for (int j = i; j < nums.length; j++) {
                if (i == 0 || ( nums[i] != nums[i-1]))
                {
                    int v = -(nums[i] + nums[j]);
                    int index = map.getOrDefault(v, -1);
                    if(index != -1 && index != i && index != j)
                    {
                        List<Integer> list = Arrays.asList(new Integer[]{nums[i],nums[j],nums[index]});
                        //map.remove(v);
                        map.remove(nums[i]);
                       // map.remove(nums[j]);
                        lists.add(list);
                    }

                }
            }
        }


        return lists;
    }

    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        List<List<Integer>> lists = threeSum.threeSum(new int[]{-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6});
        for(List<Integer> list: lists)
        {
            System.out.println(list);
        }

    }


}
