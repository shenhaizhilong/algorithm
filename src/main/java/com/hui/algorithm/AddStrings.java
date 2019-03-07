package com.hui.algorithm;

/**
 *
 * https://leetcode.com/problems/add-strings/description/
 *Add Strings
 * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
 *
 * Note:
 *
 * The length of both num1 and num2 is < 5100.
 * Both num1 and num2 contains only digits 0-9.
 * Both num1 and num2 does not contain any leading zero.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 *
 * @author: shenhaizhilong
 * @date: 2018/8/15 19:04
 */
public class AddStrings{

    public static String addStrings(String num1, String num2) {
        if(num1 == null)return num2;
        if(num2 == null)return num1;
        int len = Math.max(num1.length(), num2.length()) + 1;
        char[] res = new char[len];
        int sum = 0;
        int r = 0;
        int carry = 0;

        for (int i = 0; i < len; i++) {

            int up   = (num1.length() > i) ? (num1.charAt(num1.length() -i -1) - '0'):0;
            int down = (num2.length() > i) ? (num2.charAt(num2.length() -i -1) - '0'):0;
            sum = up + down + carry;
            r =  sum % 10;
            carry = sum/10;
            char c = (char) (r + '0');
            res[len -i -1] = c;

        }
        if(res[0] == '0')
        {
           return new String (res,1,len -1);
        }
        return new String(res);

    }

    public static void main(String[] args) {
        String a = "1111111111111111111111111";
        String b = "999999999999999999999999999";
        System.out.println(addStrings(a,b));
    }
}
