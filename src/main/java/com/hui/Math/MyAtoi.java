package com.hui.Math;

/**
 * @author: shenhaizhilong
 * @date: 2018/7/1 14:01
 */
public class MyAtoi {

    public static int myAtoi(String str) {

        if(str.isEmpty())return 0;
      //  str = str.replaceAll("\\s","");

        str = str.trim();
        if(str.length() == 0)return 0;
        int sign = 1;
        if(str.matches("^(\\+||\\-)?[0-9]+.*?"))
        {
            System.out.println("str format: " + str);
            char[] chars = str.toCharArray();
            if(chars[0] == '-')
                sign = -1;
            int start=0;
            if(chars[0] == '-' || chars[0] == '+')
                start++;
            return to_num(chars, start, sign);

        }

        return 0;

    }


    public static int to_num(char[] chars, int start, int sign)
    {
        long num = 0;
        for (int i = start; i < chars.length; i++) {
            if(chars[i] >='0' && chars[i] <='9')
            {
                num = num*10 + sign*(chars[i] - '0');
                if(num > 0x7fffffff)
                {
                    return Integer.MAX_VALUE;
                }

                if(num <0x80000000)
                {
                    return Integer.MIN_VALUE;
                }
            }else {
                break;
            }
        }

        return (int)num;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("words "));
        System.out.println(myAtoi("032words "));
        System.out.println(myAtoi("+-words "));
        System.out.println(myAtoi("+words "));
        System.out.println(myAtoi("+036555words"));
        System.out.println(myAtoi("+- 0369words "));
        System.out.println(myAtoi("-91283472332"));
        System.out.println(myAtoi("+0 123"));
    }
}
