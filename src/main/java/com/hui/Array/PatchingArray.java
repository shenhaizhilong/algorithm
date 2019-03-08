package com.hui.Array;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/7 23:58
 *
 *
 * 330. Patching Array
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a sorted positive integer array nums and an integer n, add/patch elements to the array such that any number in range [1, n] inclusive can be formed by the sum of some elements in the array. Return the minimum number of patches required.
 *
 * Example 1:
 *
 * Input: nums = [1,3], n = 6
 * Output: 1
 * Explanation:
 * Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
 * Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
 * Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
 * So we only need 1 patch.
 * Example 2:
 *
 * Input: nums = [1,5,10], n = 20
 * Output: 2
 * Explanation: The two patches can be [2, 4].
 * Example 3:
 *
 * Input: nums = [1,2,2], n = 5
 * Output: 0
 *
 *
 * greed solution.
 *
 */
public class PatchingArray {

    public int minPatches(int[] nums, int n) {
        int count = 0;
        long patched = 0; // already patched to patched. range is [1,patched]
        int i = 0;
        while (patched < n)
        {
            // range is [1,patched]
            if(i < nums.length && nums[i] <= patched + 1)
            {
                // nums[i] <= pathed + 1, then patched + nums[i], new range is [1, patched + nums[i]], for an example
                // [1,2,2], when i = 2, current range is [1, 1 + 2], since 2 < 3 so, just update the range to [1, 3 + 2]
                patched += nums[i];
                i++;
            }else {
                // patch once
                patched += patched + 1;   // if we have covered range [1 -> num], then adding num + 1 can extend the range to [1..2*num + 1].
                count++;
            }
        }
        return count;
    }

}
