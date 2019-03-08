package com.hui.HashMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/22 11:16
 */
public class WordPattern {

    /**
     *https://leetcode.com/problems/word-pattern/description/
     * 290. Word Pattern
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a pattern and a string str, find if str follows the same pattern.
     *
     * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
     *
     * Example 1:
     *
     * Input: pattern = "abba", str = "dog cat cat dog"
     * Output: true
     * Example 2:
     *
     * Input:pattern = "abba", str = "dog cat cat fish"
     * Output: false
     * Example 3:
     *
     * Input: pattern = "aaaa", str = "dog cat cat dog"
     * Output: false
     * Example 4:
     *
     * Input: pattern = "abba", str = "dog dog dog dog"
     * Output: false
     * Notes:
     * You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.
     *
     * @param pattern
     * @param str
     * @return
     */
    public static boolean wordPattern(String pattern, String str) {

        String[] words = str.split("\\s+");
        if(pattern.length() != words.length)return false;
        HashMap<Character,String> map = new HashMap<>();
        Set<String> banned = new HashSet<>();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if(!map.containsKey(c))
            {
                if(!banned.contains(words[i]))
                {
                    map.put(c, words[i]);
                    banned.add(words[i]);
                }else {
                    return false;
                }

            }else {
                 if(!map.get(c).equals(words[i]))return false;
            }
        }

        return true;

    }

    public static void main(String[] args) {
        String pattern = "abba", str = "dog cat cat dog";
        System.out.println(wordPattern(pattern,str));
        System.out.println(wordPattern(pattern,"dog cat cat fish"));
        System.out.println(wordPattern("aaaa","dog cat cat dog"));
        System.out.println(wordPattern("abba", "dog dog dog dog"));
    }
}
