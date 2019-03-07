package com.hui.algorithm;

/**
 *
 * https://leetcode.com/problems/to-lower-case/description/
 *
 * 709. To Lower Case
 *
 * Implement function ToLowerCase() that has a string parameter str, and returns the same string in lowercase.
 *
 *
 *
 * Example 1:
 *
 * Input: "Hello"
 * Output: "hello"
 * Example 2:
 *
 * Input: "here"
 * Output: "here"
 * Example 3:
 *
 * Input: "LOVELY"
 * Output: "lovely"
 *
 * @author: shenhaizhilong
 * @date: 2018/8/16 14:28
 */
public class ToLowerCase {
    public static String toLowerCase(String str) {
        if(str == null || str.length() == 0)return str;
        char[] chars = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(c >= 65 && c <= 90)
            {
                c = (char)(c + 32);
            }

            chars[i] = c;
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        String[] strs = {"LOVELY","here","Hello","09877726AQZWSXEDCRFVTGBYHNUJMIK,OL.P;/"};
        for (String s :
                strs) {
            System.out.println(toLowerCase(s));
        }
    }
}
