package com.hui.TwoPointer;

import java.util.BitSet;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/31 23:00
 */
public class FindtheDuplicateNumber {


    /**
     *
     * 287. Find the Duplicate Number
     * DescriptionHintsSubmissionsDiscussSolution
     * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
     *
     * Example 1:
     *
     * Input: [1,3,4,2,2]
     * Output: 2
     * Example 2:
     *
     * Input: [3,1,3,4,2]
     * Output: 3
     * Note:
     *
     * You must not modify the array (assume the array is read only).
     * You must use only constant, O(1) extra space.
     * Your runtime complexity should be less than O(n2).
     * There is only one duplicate number in the array, but it could be repeated more than once.
     * @param nums
     * @return
     */
    public static int findDuplicate(int[] nums) {
        BitSet bitSet = new BitSet(32);
        int res = -1;
        for (int i = 0; i < nums.length; i++) {
            if(bitSet.get(nums[i])){
                res = nums[i];
                break;
            }
            bitSet.set(nums[i]);

        }
        return res;
    }


    /**
     *https://leetcode.com/problems/find-the-duplicate-number/solution/
     * Approach #3 Floyd's Tortoise and Hare (Cycle Detection) [Accepted]
     * Intuition
     *
     * If we interpret nums such that for each pair of index ii and value v_iv
     * ​i
     * ​​ , the "next" value v_jv
     * ​j
     * ​​  is at index v_iv
     * ​i
     * ​​ , we can reduce this problem to cycle detection. See the solution to Linked List Cycle II for more details.
     *
     *
     * 对于数组 A = [2,6,4,1,3,1,5]
     * index 0 , 1, 2, 3, 4, 5, 6
     * value:2,  6, 4, 1, 3,  1, 5
     *
     * 索引是什么？ 索引是指针的相对位置/偏移量
     * 那么value 是什么？ 下一个位置的地址
     * 那么这个数组就可以转换为 : 0 - > 2 - > 4 -> 3 -> 1 -> 6 -> 5-> [1- >6-> 5 ->1  链表环] 可以看到这就是一个有环的链表
     * slow = nums[slow] 的含义就是指针向右移动一步 等价于slow = slow.next
     * fast = nums[nums[fast]] 就是移动两步  等价于fast = fast.next.next
     *
     * @param nums
     * @return
     */
    public static int findDuplicate2(int[] nums)
    {
        if(nums == null || nums.length <= 1)return -1;
        int slow = nums[0];
        // fast works same as slow but moves twice fast
        int fast = nums[0];
        fast = nums[fast];
        while(fast != slow){
            slow = nums[slow];
            // fast works same as slow but moves twice fast
            fast = nums[fast];
            fast = nums[fast];
        }
        fast = 0;
        while(fast != slow){
            slow = nums[slow];
            fast = nums[fast];
        }
        return fast;
    }
    public static void main(String[] args) {


//        System.out.println(findDuplicate2(new int[]{1,3,4,2,2}));
//        System.out.println(findDuplicate2(new int[]{3,1,1,2}));
        System.out.println(findDuplicate2(new int[]{2,6,4,1,3,1,5}));

//        BitSet[] bitSets = new BitSet[2];
//        bitSets[0] = new BitSet(32);
//        bitSets[1] = new BitSet(32);
//        long d = (long)(Math.pow(2,31) + 10);
//        long m = (long)(Math.pow(2,31));
//        int bitSetIndex = (int) (d/m);
//        int index =(int) (d %m);
//        bitSets[bitSetIndex].set(index);
//        System.out.println(bitSets[bitSetIndex].get(index));
    }
}
