package com.hui.Array;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/5 21:02
 */
public class RemoveDuplicatesfromSortedArrayII {


    /**
     *
     * 80. Remove Duplicates from Sorted Array II
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.
     *
     * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
     *
     * Example 1:
     *
     * Given nums = [1,1,1,2,2,3],
     *
     * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
     *
     * It doesn't matter what you leave beyond the returned length.
     * Example 2:
     *
     * Given nums = [0,0,1,1,1,1,2,3,3],
     *
     * Your function should return length = 7, with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.
     *
     * It doesn't matter what values are set beyond the returned length.
     * Clarification:
     *
     * Confused why the returned value is an integer but your answer is an array?
     *
     * Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.
     *
     * Internally you can think of this:
     *
     * // nums is passed in by reference. (i.e., without making a copy)
     * int len = removeDuplicates(nums);
     *
     * // any modification to nums in your function would be known by the caller.
     * // using the length returned by your function, it prints the first len elements.
     * for (int i = 0; i < len; i++) {
     *     print(nums[i]);
     *
     *
     *
     * 80. Remove Duplicates from Sorted Array II
     * DescriptionHintsSubmissionsDiscussSolution
     *
     * 3-6 easy lines, C++, Java, Python, Ruby
     * 3-6 easy lines, C++, Java, Python, Ruby
     * 36.8K
     * VIEWS
     * 531
     * Last Edit: September 1, 2018 2:32 PM
     *
     * StefanPochmann
     * StefanPochmann
     *  24149
     * Same simple solution written in several languages. Just go through the numbers and include those in the result that haven't been included twice already.
     *
     * C++
     *
     * int removeDuplicates(vector<int>& nums) {
     *     int i = 0;
     *     for (int n : nums)
     *         if (i < 2 || n > nums[i-2])
     *             nums[i++] = n;
     *     return i;
     * }
     * Java
     *
     * public int removeDuplicates(int[] nums) {
     *     int i = 0;
     *     for (int n : nums)
     *         if (i < 2 || n > nums[i-2])
     *             nums[i++] = n;
     *     return i;
     * }
     * Python
     *
     * def removeDuplicates(self, nums):
     *     i = 0
     *     for n in nums:
     *         if i < 2 or n > nums[i-2]:
     *             nums[i] = n
     *             i += 1
     *     return i
     * Ruby
     *
     * def remove_duplicates(nums)
     *     i = 0
     *     nums.each { |n| nums[(i+=1)-1] = n if i < 2 || n > nums[i-2] }
     *     i
     * end
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {

        int i = 2;
        for (int j = 2; j < nums.length; j++) {
            if(nums[j] >nums[i -2])
            {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    public static void main(String[] args) {


    }
}
