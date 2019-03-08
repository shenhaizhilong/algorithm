package com.hui.PriorityQueue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 *
 * @author: shenhaizhilong
 * @date: 2018/9/20 22:10
 */
public class FindKthSmallestPairDistance {


    /**
     *
     * 719. Find K-th Smallest Pair Distance
     * DescriptionHintsSubmissionsDiscussSolution
     * Given an integer array, return the k-th smallest distance among all the pairs. The distance of a pair (A, B) is defined as the absolute difference between A and B.
     *
     * Example 1:
     * Input:
     * nums = [1,3,1]
     * k = 1
     * Output: 0
     * Explanation:
     * Here are all the pairs:
     * (1,3) -> 2
     * (1,1) -> 0
     * (3,1) -> 2
     * Then the 1st smallest distance pair is (1,1), and its distance is 0.
     * Note:
     * 2 <= len(nums) <= 10000.
     * 0 <= nums[i] < 1000000.
     * 1 <= k <= len(nums) * (len(nums) - 1) / 2.
     *
     * @param nums
     * @param k
     * @return
     */
    public int smallestDistancePair(int[] nums, int k) {

       PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < nums.length -1; i++) {
            for (int j = i + 1; j < nums.length ; j++) {
                int distance = Math.abs(nums[i] - nums[j]);
                if(maxHeap.size() < k)
                {
                    maxHeap.offer(distance);
                }else {
                    if(maxHeap.peek().intValue() > distance)
                    {
                        maxHeap.poll();
                        maxHeap.offer(distance);
                    }
                }

            }
        }

        return maxHeap.poll().intValue();
    }


    /**
     * Binary Search + Sliding Window
     * As in Approach #2, let's binary search for the answer, and we will focus on evaluating our possible function quickly.
     *
     * Algorithm
     *
     * We will use a sliding window approach to count the number of pairs with distance <= guess.
     *
     * For every possible right, we maintain the loop invariant: left is the smallest value such that nums[right] - nums[left] <= guess. Then, the number of pairs with right as it's right-most endpoint is right - left, and we add all of these up.
     * Complexity Analysis
     *
     * Time Complexity: O(N \log{W} + N \log{N})O(NlogW+NlogN), where NN is the length of nums, and WW is equal to nums[nums.length - 1] - nums[0]. The \log WlogW factor comes from our binary search, and we do O(N)O(N) work inside our call to possible (or to calculate count in Java). The final O(N\log N)O(NlogN) factor comes from sorting.
     *
     * Space Complexity: O(1)O(1). No additional space is used except for integer variables.
     *
     * @param nums
     * @param k
     * @return
     */
    public int smallestDistancePair2(int[] nums, int k) {
        Arrays.sort(nums);
        int len = nums.length;
        int lo = 0; // min distance
        int hi = nums[len -1] - nums[0]; // max distance
        while (lo < hi)
        {
            int mi = (lo + hi) >>>1;  // middle distance
            int count = 0, left = 0;
            for (int right = 0; right < nums.length; right++) {
                while (nums[right] - nums[left] > mi)left++;
                count += right - left;
            }

            // count is the number of pairs with distance <= mi
            if(count < k)
            {
                lo = mi + 1;
            }else hi = mi;
        }

        return lo;
    }

    public static void main(String[] args) {

    }

}
