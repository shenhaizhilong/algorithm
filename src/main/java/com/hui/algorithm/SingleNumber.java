package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/6/14 17:11
 */
public class SingleNumber {
    public static int singleNumber(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[0] = nums[0]^nums[i];
        }

        return nums[0];

    }

    public static int singleNumber2(int[] nums) {

        //we need to implement a 2-time counter(base 2) that if a bit appears 2 time ,it will be zero.
        //#curent  income  output
        //# a       c/c       a
        //# 0       1/0       1/0
        //# 1       1/0       0/1

        // a=~ac+a~c;

        int a = 0;
        for(int c:nums)
        {
            a = (~a&c)|(a&~c);
        }
        return a;
    }

    public static void main(String[] args) {
        int[]a = {2,1,2};
        int[] b = {4,1,2,1,2};
        System.out.println(singleNumber2(a));
        System.out.println(singleNumber2(b));
        System.out.println(singleNumber2(new int[]{2,2,8,3,3,}));

      
    }
}
