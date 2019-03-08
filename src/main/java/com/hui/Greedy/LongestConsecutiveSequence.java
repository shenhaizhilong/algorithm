package com.hui.Greedy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 *
 * @author: shenhaizhilong
 * @date: 2018/9/21 16:45
 */
public class LongestConsecutiveSequence {


    /**
     *128. Longest Consecutive Sequence
     * DescriptionHintsSubmissionsDiscussSolution
     * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
     *
     * Your algorithm should run in O(n) complexity.
     *
     * Example:
     *
     * Input: [100, 4, 200, 1, 3, 2]
     * Output: 4
     * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        int maxLen = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i];

            if(!set.contains(curr -1))
            {
                int currLen = 1;
                while (set.contains(curr + 1))
                {
                    curr++;
                    currLen++;
                }
                maxLen = Math.max(maxLen, currLen);
            }


        }
        

        return maxLen;
    }

    public int longestConsecutive2(int[] nums) {
        if(nums == null || nums.length == 0)return 0;
        Arrays.sort(nums);
        int maxLen = 1;
        int currentLen = 1;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] != nums[i -1])
            {
                if(nums[i-1] + 1 != nums[i])
                {
                    currentLen = 0;
                }
                currentLen++;
                maxLen = Math.max(currentLen, maxLen);
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {

        LongestConsecutiveSequence longestConsecutiveSequence = new LongestConsecutiveSequence();
        System.out.println(longestConsecutiveSequence.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
        System.out.println(longestConsecutiveSequence.longestConsecutive2(new int[]{100, 4, 200, 1, 3, 2}));
    }
}
