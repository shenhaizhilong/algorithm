package com.hui.String;

/**
 *https://leetcode.com/problems/ransom-note/description/
 * 383. Ransom Note
 *
 * Given an arbitrary ransom note string and another string containing letters from all the magazines, write a function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will return false.
 *
 * Each letter in the magazine string can only be used once in your ransom note.
 *
 * Note:
 * You may assume that both strings contain only lowercase letters.
 *
 * canConstruct("a", "b") -> false
 * canConstruct("aa", "ab") -> false
 * canConstruct("aa", "aab") -> true
 * @author: shenhaizhilong
 * @date: 2018/8/18 9:22
 */
public class RansomNote {
    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] dict = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            int index = (magazine.charAt(i) - 'a');
            dict[index] += 1;
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            int index = (ransomNote.charAt(i) -'a');
            dict[index] -= 1;
            if(dict[index] < 0)return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(canConstruct("a","b"));
        System.out.println(canConstruct("aa","ab"));
        System.out.println(canConstruct("aa","aab"));
    }
}
