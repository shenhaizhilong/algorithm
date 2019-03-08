package com.hui.TwoPointer;

import java.util.HashMap;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/25 18:05
 */
public class LongestSubstringWithoutRepeatingCharacters {

    /**
     *
     *3. Longest Substring Without Repeating Characters
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a string, find the length of the longest substring without repeating characters.
     *
     * Example 1:
     *
     * Input: "abcabcbb"
     * Output: 3
     * Explanation: The answer is "abc", which the length is 3.
     * Example 2:
     *
     * Input: "bbbbb"
     * Output: 1
     * Explanation: The answer is "b", with the length of 1.
     * Example 3:
     *
     * Input: "pwwkew"
     * Output: 3
     * Explanation: The answer is "wke", with the length of 3.
     *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0)return 0;
        if(s.length() == 1)return 1;
        HashMap<Character, Integer> map  = new HashMap<>();
        int slow = 0, fast = 0;
        char[] chars = s.toCharArray();

        int maxLength = Integer.MIN_VALUE;
        int len = 0;
        while (fast < s.length())
        {
            char c = chars[fast];
            if (!map.containsKey(c))
            {
                map.put(c, map.getOrDefault(c,0) +1);
                fast++;
            }else {
                len = fast - slow;
                maxLength = maxLength > len ? maxLength : len;
                c = chars[slow];
                map.remove(c);
                slow++;
            }

        }
       len = fast - slow;
        maxLength = maxLength > len ? maxLength: len;

        return maxLength == 0? 1: maxLength;
    }

    public int lengthOfLongestSubstring2(String s) {
        if(s == null || s.length() == 0)return 0;
        if(s.length() == 1)return 1;
        int[] hash = new int[256];
        int slow = 0, fast = 0;
        char[] chars = s.toCharArray();

        int maxLength = Integer.MIN_VALUE;
        int len = 0;
        while (fast < s.length())
        {
            char c = chars[fast];
            if (hash[c] == 0)
            {
                hash[c]++;
                fast++;
            }else {
                len = fast - slow;
                maxLength = maxLength > len ? maxLength : len;
                c = chars[slow];
                hash[c] = 0;
                slow++;
            }

        }
        len = fast - slow;
        maxLength = maxLength > len ? maxLength: len;

        return maxLength;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters longestSubstringWithoutRepeatingCharacters = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println(longestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring2("pwwkew"));
    }
}
