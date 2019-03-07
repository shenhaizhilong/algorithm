package com.hui.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/16 22:00
 */
public class SplitArrayintoConsecutiveSubsequences {


    /**
     *659. Split Array into Consecutive Subsequences
     * DescriptionHintsSubmissionsDiscussSolution
     * You are given an integer array sorted in ascending order (may contain duplicates), you need to split them into several subsequences, where each subsequences consist of at least 3 consecutive integers. Return whether you can make such a split.
     *
     * Example 1:
     * Input: [1,2,3,3,4,5]
     * Output: True
     * Explanation:
     * You can split them into two consecutive subsequences :
     * 1, 2, 3
     * 3, 4, 5
     * Example 2:
     * Input: [1,2,3,3,4,4,5,5]
     * Output: True
     * Explanation:
     * You can split them into two consecutive subsequences :
     * 1, 2, 3, 4, 5
     * 3, 4, 5
     * Example 3:
     * Input: [1,2,3,4,4,5]
     * Output: False
     * Note:
     * The length of the input is in range of [1, 10000]
     *
     * @param nums
     * @return
     */
    public boolean isPossible(int[] nums) {

        Map<Integer,Integer> counter = new HashMap<>();
        Map<Integer,Integer> need = new HashMap<>();
        for(int n:nums)counter.put(n, counter.getOrDefault(n,0) +1);
        for(int n: nums)
        {
            if(counter.get(n).intValue() == 0)continue;
            else if(need.getOrDefault(n,0).intValue() > 0)
            {
                // can be appended to prev sequence

                need.put(n, need.get(n) -1);
                need.put(n +1, need.getOrDefault(n +1, 0) +1);
            }else if(counter.getOrDefault(n +1, 0) > 0 && counter.getOrDefault(n +2, 0) > 0)
            {
                // can be a new sequence
                counter.put(n +1, counter.get(n +1) -1);
                counter.put(n +2, counter.get(n +2) -1);
                need.put(n +3, need.getOrDefault(n +3, 0) +1);
            }else {
                // can't append to other sequence and can't be a new sequence
                return false;
            }

            counter.put(n, counter.get(n) -1);
        }

        return true;
    }

    public static void main(String[] args) {

        SplitArrayintoConsecutiveSubsequences splitArrayintoConsecutiveSubsequences = new SplitArrayintoConsecutiveSubsequences();
        System.out.println(splitArrayintoConsecutiveSubsequences.isPossible(new int[]{1,2,3,4,5}));
        System.out.println(splitArrayintoConsecutiveSubsequences.isPossible(new int[]{1,2,3,3,4,5}));
        System.out.println(splitArrayintoConsecutiveSubsequences.isPossible(new int[]{1,2,3,3,4,4,5,5}));
        System.out.println(splitArrayintoConsecutiveSubsequences.isPossible(new int[]{1,2,3,4,4,5}));
    }
}
