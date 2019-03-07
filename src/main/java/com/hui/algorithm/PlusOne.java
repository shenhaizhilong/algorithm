package com.hui.algorithm;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/6/13 23:29
 */
public class PlusOne {
    public static int[] plusOne(int[] digits) {

        if(digits.length ==0) return digits;
        if(digits[digits.length -1] < 9)
        {
            digits[digits.length -1] +=1;

        }else {

            int i = digits.length -1;
            while (i>=0)
            {
                if(digits[i]==9)
                {
                    digits[i] = 0;
                    i--;
                }else {
                    break;
                }


            }

            System.out.println("i =" + i + "," + Arrays.toString(digits));

            if(i==-1) {
                digits = new int[digits.length + 1];
                digits[0] = 1;
            }else
            {
                digits[i] +=1;
            }


        }

        return digits;

    }

    public static void main(String[] args) {
        int[] a = {1,0,9,9,0,9};
        a= plusOne(a);
        System.out.println(Arrays.toString(a));
    }
}
