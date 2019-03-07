package com.hui.algorithm;

import java.util.*;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/1 13:36
 */
public class RelativeRanks {

    /**
     *506. Relative Ranks
     * DescriptionHintsSubmissionsDiscussSolution
     * Given scores of N athletes, find their relative ranks and the people with the top three highest scores, who will be awarded medals: "Gold Medal", "Silver Medal" and "Bronze Medal".
     *
     * Example 1:
     * Input: [5, 4, 3, 2, 1]
     * Output: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
     * Explanation: The first three athletes got the top three highest scores, so they got "Gold Medal", "Silver Medal" and "Bronze Medal".
     * For the left two athletes, you just need to output their relative ranks according to their scores.
     * Note:
     * N is a positive integer and won't exceed 10,000.
     * All the scores of athletes are guaranteed to be unique.
     *
     * @param nums
     * @return
     */
    public static String[] findRelativeRanks(int[] nums) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(nums.length, Comparator.reverseOrder());
        Map<Integer,String> map = new HashMap<>(nums.length);
        for(int i: nums)
            priorityQueue.offer(i);
        int rank = 0;
        while (!priorityQueue.isEmpty())
        {
            int v = priorityQueue.poll();
            if(rank == 0)
            {
                map.put(v, "Gold Medal");
            } else if (rank == 1)
            {
                map.put(v, "Silver Medal");
            }else if(rank == 2)
            {
                map.put(v,"Bronze Medal");
            }else {
                map.put(v, String.valueOf(rank + 1));
            }
            rank++;

        }
       String[] res = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = map.get(nums[i]);
        }
       return res;

    }


    /**
     *
     * method 2, beats 95.6%
     * @param nums
     * @return
     */
    public static String[] findRelativeRanks2(int[] nums) {
        String[] res = new String[nums.length];
        int max = Integer.MIN_VALUE;
        for(int i: nums)
        {
            if(i > max)max = i;
        }


        int[] hash = new int[max + 1];
        for (int i = 0; i < nums.length; i++) {
            hash[nums[i]] = i + 1;  // record nums[i]'s index + 1
        }

        int rank = 1;
        for (int i = hash.length -1; i >=0; i--) {
            if(hash[i] != 0)
            {
                if(rank == 1){
                    res[hash[i] -1] = "Gold Medal";
                }else if(rank == 2)
                {
                    res[hash[i] -1] = "Silver Medal";
                }else if(rank == 3)
                {
                    res[hash[i] -1] = "Bronze Medal";
                }else {
                    res[hash[i] -1] = String.valueOf(rank);
                }
                rank++;
            }
            if(rank == nums.length + 1)break;  //find all the number's rank
        }

        return res;

    }

    public static void main(String[] args) {

        System.out.println(Arrays.toString(findRelativeRanks2(new int[]{5,4,3,2,1})));
    }
}
