package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/22 8:18
 */
public class Base7 {
    public static String convertToBase7(int num) {
        StringBuilder sb = new StringBuilder();
        String sign = num >= 0 ? "":"-";
        num = Math.abs(num);
        while (num >= 7)
        {
            int r = num % 7;
            num /= 7;
            sb.append(r);
        }
        sb.append(num);
        sb.append(sign);
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        for (int i = -7; i < 101; i++) {
            System.out.println(convertToBase7(i));
        }
    }
}
