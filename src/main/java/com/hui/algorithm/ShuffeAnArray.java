package com.hui.algorithm;

import java.util.*;

/**
 *
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/24/design/58/
 * Shuffle an Array
 * 打乱一个没有重复元素的数组。
 *
 * 示例:
 *
 * // 以数字集合 1, 2 和 3 初始化数组。
 * int[] nums = {1,2,3};
 * Solution solution = new Solution(nums);
 *
 * // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。
 * solution.shuffle();
 *
 * // 重设数组到它的初始状态[1,2,3]。
 * solution.reset();
 *
 * // 随机返回数组[1,2,3]打乱后的结果。
 * solution.shuffle();
 *
 *
 * @author: shenhaizhilong
 * @date: 2018/8/14 11:30
 */
public class ShuffeAnArray {
    private final int[] original;

    private Random random;

    /**
     *
     * @param nums can't be null
     */
    public ShuffeAnArray(int[] nums) {
        if(nums == null)throw new IllegalArgumentException("parameter nums can't be null");
        original = nums;
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return original;
    }


    /**
     * O(n), n is the length of original
     */
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        if(original == null || original.length <=1)return original;
        int[] values = Arrays.copyOf(original, original.length);
        random = new Random();
        int rightEnd = values.length -1;
        int r;
        while (rightEnd >0)
        {
            r = random.nextInt(rightEnd + 1);
            if(r != rightEnd)
                swap(values,r,rightEnd);
            rightEnd--;
        }
        return values;
    }

    private void swap(int[] array, int i, int j)
    {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    /**
     *https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/1/array/26/
     * 两个数组的交集 II
     * 给定两个数组，编写一个函数来计算它们的交集。
     *
     * 示例 1:
     *
     * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出: [2,2]
     * 示例 2:
     *
     * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出: [4,9]
     * 说明：
     *
     *    输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
     *    我们可以不考虑输出结果的顺序。
     * 进阶:
     *
     * 如果给定的数组已经排好序呢？你将如何优化你的算法？
     * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
     * 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        if(nums1 == null || nums2 == null)return null;
        int[] smaller,larger;

        smaller = nums1;
        larger = nums2;
        if(nums1.length > nums2.length)
        {
            smaller = nums2;
            larger = nums1;
        }

        Map<Integer,Integer> counter = new HashMap<>();
        for (int i :
                smaller) {
                counter.put(i, counter.getOrDefault(i,0) + 1);
        }

        int temp;
        List<Integer> list = new ArrayList<>();
        for (int i :
                larger) {
            if (counter.containsKey(i) && (temp = counter.get(i).intValue()) > 0)
            {
                counter.put(i, temp -1);
                list.add(i);
            }
        }

       int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }

        return res;


    }



    public static void main(String[] args) {
        // 以数字集合 1, 2 和 3 初始化数组。
        int[] nums = {1,2,3};
        ShuffeAnArray solution = new ShuffeAnArray(nums);

// 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。
        System.out.println(Arrays.toString(solution.shuffle()));



// 重设数组到它的初始状态[1,2,3]。
        solution.reset();

// 随机返回数组[1,2,3]打乱后的结果。
        System.out.println(Arrays.toString(solution.shuffle()));


       int[]  nums1 = {1,2,2,1};
       int[] nums2 = {2,2};
        System.out.println(Arrays.toString(intersect(nums1, nums2)));

        int[] nums3 = {4,9,5};
        int[] nums4= {9,4,9,8,4};
        System.out.println(Arrays.toString(intersect(nums3, nums4)));

    }
}
