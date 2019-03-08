package com.hui.SlidingWindow;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/21 17:47
 */
public class MinimumSizeSubarraySum {

    /**
     *
     * 209. Minimum Size Subarray Sum
     * DescriptionHintsSubmissionsDiscussSolution
     * Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
     *
     * Example:
     *
     * Input: s = 7, nums = [2,3,1,2,4,3]
     * Output: 2
     * Explanation: the subarray [4,3] has the minimal length under the problem constraint.
     * Follow up:
     * If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {

        int minLen = Integer.MAX_VALUE;
        int sum = 0;
        int left = 0, right = 0;
        while (right < nums.length)
        {
            while (s > sum && right < nums.length)
            {
                sum += nums[right];
                if(s > sum)
                    right++;
            }
            if(right < nums.length)
            {
                minLen = Math.min(right - left +1, minLen);
                sum = sum - nums[left] -nums[right] ;
            }

            left++;

        }
        return minLen == Integer.MAX_VALUE ? 0: minLen;
    }


    /**
     *sliding window
     *
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen2(int s, int[] nums) {
        int minLen = Integer.MAX_VALUE;
        int sum = 0;
        int left = 0, right = 0;
        while (right < nums.length)
        {
            sum += nums[right];
            if(s > sum)
            {
                // sum less than s, so we need to increasing this sliding window from right side.
                right++;
                continue;
            }

            // now the sum >= s, so we need to decreasing this sliding window from left side.
            while (s <= sum)
            {
                sum -= nums[left++];
            }
            // right - (left -1) + 1 is the window size.
            minLen = Math.min(minLen, right - (left -1) +1);
            right++;
        }

        return minLen == Integer.MAX_VALUE ? 0: minLen;
    }
    public static void main(String[] args) {
        MinimumSizeSubarraySum minimumSizeSubarraySum = new MinimumSizeSubarraySum();
        System.out.println(minimumSizeSubarraySum.minSubArrayLen(7, new int[]{2,3,1,2,4,3}));
        System.out.println(minimumSizeSubarraySum.minSubArrayLen2(7, new int[]{2,3,1,2,4,3}));
    }
}
