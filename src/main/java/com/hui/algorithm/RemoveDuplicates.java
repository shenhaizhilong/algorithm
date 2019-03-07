package com.hui.algorithm;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/6/13 14:03
 * remove duplicate elements from a sorted array
 * for an example int[] a = {1,2,2,3,3,3,4,4,5,6,6},
 * after executed this function, it will return 6
 * int[] a= {1,2,3,4,5,6, etc}
 * but after the 6 elements will be ignored.
 */
public class RemoveDuplicates {


    public static int removeDuplicates(int[] numbers)
    {
        if(numbers.length ==0)return 0;
        int i=0;
        for (int j = 1; j < numbers.length ; j++) {
            if(numbers[i] != numbers[j])
            {
                i++;
                numbers[i] = numbers[j];
            }
        }

        return i+1;
    }


    public static int removeDuplicates2(int[] numbers)
    {
        if(numbers.length ==0)return 0;
        int i=0;
        int count = 0;
        for (int j = 1; j < numbers.length ; j++) {
            if(numbers[i] == numbers[j])
            {
                count++;
                if(count<2)
                {
                    i++;
                    numbers[i] = numbers[j];
                }

            }else
            {
                i++;
                numbers[i] = numbers[j];
                count = 0;
            }
        }

        return i+1;
    }

    public static void main(String[] args) {

        int[] b = {0,0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates(b));
        System.out.println(Arrays.toString(b));

    }
}
