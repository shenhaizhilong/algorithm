package com.hui.String;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/29 21:15
 */
public class StrStr {

    /**
     *
     * 28. Implement strStr()
     * DescriptionHintsSubmissionsDiscussSolution
     * Implement strStr().
     *
     * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
     *
     * Example 1:
     *
     * Input: haystack = "hello", needle = "ll"
     * Output: 2
     * Example 2:
     *
     * Input: haystack = "aaaaa", needle = "bba"
     * Output: -1
     * Clarification:
     *
     * What should we return when needle is an empty string? This is a great question to ask during an interview.
     *
     * For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
     *
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStr(String haystack, String needle) {
        if(needle == null || needle.length() > haystack.length())return -1;
        if(needle.length() == 0)return 0;
        int res = -1;
        int start = 0;
        boolean hasSame = false;
        for (int i = 0; i <= haystack.length(); i++) {

            if(haystack.charAt(i) == needle.charAt(start))
            {
                start++;
                if(start == needle.length()){
                    res = i - needle.length() + 1;
                    break;
                }
                hasSame = true;
            }else {
                if(hasSame)
                {
                    i = i - start;
                    start = 0;
                    hasSame = false;
                }

            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(strStr("hellllp", "llp"));
    }
}
