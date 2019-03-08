package com.hui.Math;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/19 12:07
 *
 *228. Summary Ranges
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 *
 * Example 1:
 *
 * Input:  [0,1,2,4,5,7]
 * Output: ["0->2","4->5","7"]
 * Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.
 * Example 2:
 *
 * Input:  [0,2,3,4,6,8,9]
 * Output: ["0","2->4","6","8->9"]
 * Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.
 *
 */
public class SummaryRanges {



    public List<String> summaryRanges(int[] nums) {
        List<String> results = new ArrayList<>();
        if(nums == null || nums.length == 0)return results;
        int start = nums[0];
        for (int i = 1; i <= nums.length; i++) {
            while ( i < nums.length && nums[i -1] + 1 == nums[i])i++;
            int end = nums[i -1];
            if(start == end)
            {
                results.add(Integer.toString(start));
            }else {
                results.add(start + "->" + end);
            }
            if(i == nums.length)break;
            start = nums[i];

        }

        return results;

    }

    public static void main(String[] args) {


        SummaryRanges summaryRanges = new SummaryRanges();
        System.out.println(summaryRanges.summaryRanges(new int[]{0,1,2,4,5,7}));
        System.out.println(summaryRanges.summaryRanges(new int[]{0,2,3,4,6,8,9}));
    }
}
