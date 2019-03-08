package com.hui.MajorityVote;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/17 11:45
 */
public class MajorityElementII {

    public List<Integer> majorityElement(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int count1 = 0, count2 = 0, major1 = 0, major2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == major1)
            {
                count1++;
            }else if(nums[i] == major2)
            {
                count2++;
            }else if(count1 == 0)
            {
                count1 = 1;
                major1 = nums[i];
            }else if(count2 == 0)
            {
                count2 = 1;
                major2 = nums[i];
            }else {
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == major1)
            {
                count1++;
            }else if(nums[i] == major2)
            {
                count2++;
            }
        }

        if(count1 > nums.length/3)list.add(major1);
        if(count2 > nums.length/3)list.add(major2);
        return list;


    }

    public static void main(String[] args) {

        MajorityElementII majorityElementII = new MajorityElementII();
        System.out.println(majorityElementII.majorityElement(new int[]{1,3,3}));
        System.out.println(majorityElementII.majorityElement(new int[]{1,2,3}));
        System.out.println(majorityElementII.majorityElement(new int[]{1,2,3,1,2,1,3,2}));
    }

}
