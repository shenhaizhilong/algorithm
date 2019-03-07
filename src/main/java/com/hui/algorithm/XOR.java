package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/6/13 22:21
 */
public class XOR {
    public static void main(String[] args) {
        int a, b;
        a = 10;
        b = 100;
        swap(a, b);
    }

    public static void swap(int a, int b)
    {
        a = a^b;
        b = a^b;
        a = b^a;
        System.out.println("a is:" + a +" , b is:" + b);
    }
}
