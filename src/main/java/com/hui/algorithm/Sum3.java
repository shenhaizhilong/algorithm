package com.hui.algorithm;

import java.util.*;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/19 20:38
 *
 *
 *
 * 15. 3Sum
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
 */
public class Sum3 {


    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        if(nums == null || nums.length < 3)return lists;
        Arrays.sort(nums);
        dfs2(nums, 0,nums.length -1,3, lists, new ArrayList<>(), 0);
        return lists;
    }

    private void dfs2(int[] nums, int start, int end, int k, List<List<Integer>> lists , List<Integer> list, int target)
    {
        // 0 sum
        if(k == 0 || end < start)return;
        // 2 sum
        if(k == 2)
        {
            Set<Integer> set = new HashSet<>();
            Set<Integer> used = new HashSet<>();
            set.add(nums[start]);
            for (int i = start +1; i <= end; i++) {
                if(set.contains(target - nums[i]) && !used.contains(nums[i]))
                {
                    used.add(nums[i]);
                    used.add(target - nums[i]);
                    list.add(nums[i]);
                    list.add(target - nums[i]);
                    lists.add(new ArrayList<>(list));
                    list.remove(list.size() -1);
                    list.remove(list.size() -1);
                }
                set.add(nums[i]);
            }
            return;
        }

        // remove some unused case.
        if(k*nums[start] > target || k*nums[end] < target)return;

        // k > 2 , chose k elements from nums[start,end]
        for (int i = start; i <= end - k +1; i++) {
            // remove duplicate
            if(i > start && nums[i] == nums[i -1])continue;
            if(k*nums[i] <= target)
            {
                list.add(nums[i]);
                dfs2(nums, i +1,end, k -1, lists, list, target - nums[i]);
                list.remove(list.size() -1);
            }
        }
    }

    public List<List<Integer>> threeSum2(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> res = new ArrayList<>();
        int target = 0;
        for (int i = 0; i < num.length -2; i++) {

            if( i == 0 || (i > 0 && num[i] != num[i -1]))
            {
                int lo = i +1;
                int hi = num.length -1;
                int sum = target -  num[i];
                while (lo < hi)
                {
                    if(num[lo] + num[hi] < sum)
                    {
                        lo++;
                    }
                    else if(num[lo] + num[hi] > sum){
                        hi--;
                    }
                    else {
                        res.add(Arrays.asList(num[lo], num[i], num[hi]));

                        // remove duplicate
                        while (lo < hi && num[lo] == num[lo +1])lo++;
                        while (lo < hi && num[hi] == num[hi -1])hi--;
                        lo++;
                        hi--;
                    }
                }
            }
        }

        return res;

    }




        public static void main(String[] args) {

        Sum3 sum3 = new Sum3();
        List<List<Integer>> lists = sum3.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        for(List<Integer> list: lists)
        {
            System.out.println(list);
        }

            lists = sum3.threeSum2(new int[]{-1, 0, 1, 2, -1, -4});

            for(List<Integer> list: lists)
            {
                System.out.println(list);
            }

    }

}
