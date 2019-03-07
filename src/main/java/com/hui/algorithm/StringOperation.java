package com.hui.algorithm;

/**
 * 字符串 a,b 只包含大写字母，a 为长字符串
 * b 为短字符串,请问如何快速判断出短字符串b中的所有字符是否都出现在长字符串a中呢？
 *
 * A-Z 一共有26个字符
 * 'A' - 'A' = 0;
 * 'Z' - 'A' = 25;
 * 用一位代表这个字符出现
 * 0x01代表'A' 出现
 * 0x10代表'B' 出现
 * 0x11代表'AB' 出现
 * 以此类推
 * @author: shenhaizhilong
 * @date: 2018/7/6 9:36
 */
public class StringOperation {
    public static boolean contains(String a, String b)
    {
        int hash = 0;
        for (int i = 0; i < a.length(); i++) {
            hash |= 1<<(a.charAt(i) - 'A');
        }
        for (int i = 0; i < b.length(); i++) {
            if((hash & (1<<(b.charAt(i)-'A'))) ==0)
            {
                return false;
            }
        }

        return true;
    }
}
