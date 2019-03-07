package com.hui.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/7/2 17:48
 */
public class FizzBuzz {
    public static List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            int mod3 = i %3;
            int mod5 = i %5;
            if (mod3== 0 && mod5 ==0)
            {
                list.add("FizzBuzz");
            }else if( mod3 == 0)
            {
                list.add("Fizz");
            }else if( mod5 ==0)
            {
                list.add("Buzz");
            }else {
                list.add(String.valueOf(i));
            }
        }

        return list;

    }

    public static void main(String[] args) {
        List<String> list = fizzBuzz(15);
        System.out.println(list);
    }
}
