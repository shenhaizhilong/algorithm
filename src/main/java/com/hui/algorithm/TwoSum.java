package com.hui.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: shenhaizhilong
 * @date: 2018/6/15 14:44
 */
public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {

        int[] results = {0,0};
        int diff;
        for (int i = 0; i < nums.length -1; i++) {

            diff = target- nums[i];
            for (int j = i + 1; j < nums.length ; j++) {
                if(diff == nums[j])
                {
                    results[0] = i;
                    results[1] = j;
                    return results;
                }
            }


        }

        return results;
    }

    public static int[] twoSum2(int[] numbers, int target)
    {

       Map<Integer, Integer> map = new HashMap<>();
       int diff;
        for (int i = 0; i < numbers.length; i++) {
            diff = target - numbers[i];
            if(map.containsKey(diff))
            {
                return new int[]{map.get(diff), i};
            }
            map.put(numbers[i], i);
        }

        throw new IllegalArgumentException("No such two sum solution");
    }
    public static void main(String[] args) {
        int[] a = {2,0,11,9,15};
        int[] b = {3,2,4};
        System.out.println(Arrays.toString(twoSum(a, 9)));
        System.out.println(Arrays.toString(twoSum2(b, 6)));
    }
}
