package com.hui.BitOperation;

/**
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/26/others/69/
 * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
 *
 * 示例 1:
 *
 * 输入: [3,0,1]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [9,6,4,2,3,5,7,0,1]
 * 输出: 8
 * 说明:
 * 你的算法应具有线性时间复杂度。你能否仅使用额外常数空间来实现?
 * @author: shenhaizhilong
 * @date: 2018/6/29 10:51
 */
public class MissingNumber {
    public static int missingNumber(int[] nums)
    {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res = (res ^ nums[i])^i;
        }
        res = res^nums.length;
        return res;
    }

    public static void main(String[] args) {
        int[] a = {9,6,4,2,3,5,7,0,1};
        System.out.println(missingNumber(a));

    }
}
