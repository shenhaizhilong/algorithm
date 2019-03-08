package com.hui.DFS;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/24 20:37
 *
 *
 * 473. Matchsticks to Square
 * DescriptionHintsSubmissionsDiscussSolution
 * Remember the story of Little Match Girl? By now, you know exactly what matchsticks the little match girl has, please find out a way you can make one square by using up all those matchsticks. You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.
 *
 * Your input will be several matchsticks the girl has, represented with their stick length. Your output will either be true or false, to represent whether you could make one square using all the matchsticks the little match girl has.
 *
 * Example 1:
 * Input: [1,1,2,2,2]
 * Output: true
 *
 * Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
 * Example 2:
 * Input: [3,3,3,3,4]
 * Output: false
 *
 * Explanation: You cannot find a way to form a square with all the matchsticks.
 * Note:
 * The length sum of the given matchsticks is in the range of 0 to 10^9.
 * The length of the given matchstick array will not exceed 15.
 *
 *
 */
public class MatchstickstoSquare {

    private int edge;
    public boolean makesquare(int[] nums) {
        int totalSum = 0;
        for(int n:nums)totalSum += n;
        if(totalSum % 4 != 0)return false;
        this.edge = totalSum / 4;  // the square edge length
        Arrays.sort(nums);

        int remainCount = nums.length;
        boolean[] used = new boolean[nums.length];

        return dfs(nums, 0, 0,used, remainCount);
    }

    private boolean dfs(int[] nums, int currSum, int count,boolean[] used, int remainCount)
    {
        if(currSum == edge)
        {
            count++;  // number of the length
            if(count == 4)return remainCount == 0;
            currSum = 0;
        }
        if(currSum > edge)return false;

        // backTracking method.
        for (int i = nums.length -1; i >= 0; i--) {
            if(used[i])continue;
            if(currSum +nums[i] > edge)break;  // very important, since currSum <= edge, if currSum > edge, we need return false
            used[i] = true;
            if(dfs(nums, currSum + nums[i], count, used, remainCount -1))return true;
            used[i] = false;
        }
        return false;

    }


    public static void main(String[] args) {
        MatchstickstoSquare matchstickstoSquare = new MatchstickstoSquare();
     //   System.out.println(matchstickstoSquare.makesquare(new int[]{1,1,2,2,2}));
     //   System.out.println(matchstickstoSquare.makesquare(new int[]{3,3,3,3,4}));
     //   System.out.println(matchstickstoSquare.makesquare(new int[]{3,3,3,3,4,4,4,4,4,4,5,5,5,5,8}));
        System.out.println(matchstickstoSquare.makesquare(new int[]{5,5,5,5,16,4,4,4,4,4,3,3,3,3,4}));
    }
}
