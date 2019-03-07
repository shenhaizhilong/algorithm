package com.hui.algorithm;

import java.util.HashMap;

/**
 *
 *
 * @author: shenhaizhilong
 * @date: 2018/8/17 14:31
 */
public class MajorityElement {
    public static int majorityElement(int[] nums) {
        if(nums == null || nums.length == 0)return -1;
        HashMap<Integer,Integer> counter = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int count = counter.getOrDefault(nums[i],0) +1;
            if(count > nums.length/2)
            {
                return nums[i];
            }
            counter.put(nums[i],count);
        }
        return -1;
    }

    /**
     *  方法二
     *
     *http://www.cs.utexas.edu/~moore/best-ideas/mjrty/index.html
     * http://www.cs.utexas.edu/~moore/best-ideas/mjrty/example.html
     *
     *  We will sweep down the sequence starting at the pointer position shown above.
     *
     * As we sweep we maintain a pair consisting of a current candidate and a counter. Initially, the current candidate is unknown and the counter is 0.
     *
     * When we move the pointer forward over an element e:
     *
     * If the counter is 0, we set the current candidate to e and we set the counter to 1.
     * If the counter is not 0, we increment or decrement the counter according to whether e is the current candidate.
     * When we are done, the current candidate is the majority element, if there is a majority.
     *
     * @param nums
     * @return
     */
    public static int majorityElement2(int[] nums) {
        int major = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++){
            if (count == 0){
                count++;
                major=nums[i];
            } else if (major == nums[i]){
                count ++;
            }else count--;
        }
        return major;
    }

    public static void main(String[] args) {
       int[] a =  {2,2,1,1,1,2,2};
        System.out.println(majorityElement2(a));

    }
}
