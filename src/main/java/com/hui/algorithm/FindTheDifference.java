package com.hui.algorithm;

/**
 *
 *https://leetcode.com/problems/find-the-difference/description/
 * 389. Find the Difference
 * DescriptionHintsSubmissionsDiscussSolution
 * Given two strings s and t which consist of only lowercase letters.
 *
 * String t is generated by random shuffling string s and then add one more letter at a random position.
 *
 * Find the letter that was added in t.
 *
 * Example:
 *
 * Input:
 * s = "abcd"
 * t = "abcde"
 *
 * Output:
 * e
 *
 * Explanation:
 * 'e' is the letter that was added.
 *
 * @author: shenhaizhilong
 * @date: 2018/8/19 17:44
 */
public class FindTheDifference {
    public static char findTheDifference(String s, String t) {
        int[] buckets = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) -'a';
            buckets[index] +=1;
        }
        char res = 0;
        for (int i = 0; i < t.length(); i++) {
            int index = t.charAt(i) -'a';
            buckets[index] -=1;
            if(buckets[index] <0)
            {
                return (char) (index +'a');
            }
        }
        return res;

    }


    public static char findTheDifference2(String s, String t) {
        char res = 0;
        for (int i = 0; i < s.length(); i++) {
            res ^= s.charAt(i);
        }
        for (int i = 0; i < t.length(); i++) {
            res ^= t.charAt(i);
        }

        return res;

    }

    public static char findTheDifference3(String s, String t) {
        char res = 0;
        for (int i = 0; i < s.length(); i++) {
            res -= s.charAt(i);
        }
        for (int i = 0; i < t.length(); i++) {
           res += t.charAt(i);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(findTheDifference3("abcd","abced"));
    }

}
