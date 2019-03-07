package com.hui.algorithm;

import java.util.HashSet;

/**
 *
 *https://leetcode.com/problems/reverse-vowels-of-a-string/description/
 * 345. Reverse Vowels of a String
 * DescriptionHintsSubmissionsDiscussSolution
 * Write a function that takes a string as input and reverse only the vowels of a string.
 *
 * Example 1:
 *
 * Input: "hello"
 * Output: "holle"
 * Example 2:
 *
 * Input: "leetcode"
 * Output: "leotcede"
 * Note:
 * The vowels does not include the letter "y".
 *
 *
 *
 * @author: shenhaizhilong
 * @date: 2018/8/19 12:08
 */
public class ReverseVowels {
    private static HashSet<Character> hashSet;
    static {
        hashSet = new HashSet<>(10);
        hashSet.add('a');
        hashSet.add('e');
        hashSet.add('i');
        hashSet.add('o');
        hashSet.add('u');
        hashSet.add('A');
        hashSet.add('E');
        hashSet.add('I');
        hashSet.add('O');
        hashSet.add('U');
    }

    public static String reverseVowels(String s) {
        if(s == null || s.length() == 0 || s.length() ==1)return s;
        char[] results = s.toCharArray();
        int left = 0;
        int right = results.length -1;
        while (left < right)
        {

            while (left <= results.length -1 && !hashSet.contains(results[left]) )
            {
               left++;
            }

            while (right >=0 && !hashSet.contains(results[right]))
            {
               right--;
            }

            if(left < right)
            {
                char t = results[left];
                results[left] = results[right];
                results[right] = t;
                left++;
                right--;
            }

        }

        return new String(results);
    }

    public static void main(String[] args) {

        System.out.println(reverseVowels("hello"));
        System.out.println(reverseVowels("leetcode"));
    }
}
