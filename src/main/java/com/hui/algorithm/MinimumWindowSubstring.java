package com.hui.algorithm;

import java.util.*;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/21 19:00
 */
public class MinimumWindowSubstring {

    /**
     *
     * 76. Minimum Window Substring
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
     *
     * Example:
     *
     * Input: S = "ADOBECODEBANC", T = "ABC"
     * Output: "BANC"
     * Note:
     *
     * If there is no such window in S that covers all characters in T, return the empty string "".
     * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0) {
            return "";
        }
        String res = s;
        Map<Character,Integer> targetMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            targetMap.put(t.charAt(i), targetMap.getOrDefault(t.charAt(i), 0) + 1);
        }
        int left = 0, right = 0;

        // ans list of the form (window length, left, right)
        int[] ans = {-1, 0, 0};


        // formed is used to keep track of how many unique characters in t
        // are present in the current window in its desired frequency.
        // e.g. if t is "AABC" then the window must have two A's, one B and one C.
        // Thus formed would be = 3 when all these conditions are met.
        int formed = 0;

        // Number of unique characters in t, which need to be present in the desired window.
        int required = targetMap.size();


        Map<Character,Integer> map = new HashMap<>();
        while (right < s.length()) {

            char c = s.charAt(right);
            if(!targetMap.containsKey(c))
            {
                right++;
                continue;
            }
            map.put(c, map.getOrDefault(c,0) + 1); // add one character from the right to the window
            if(targetMap.containsKey(c) && map.get(c).equals(targetMap.get(c)))
            {
                formed++;
            }

            while (left <= right && formed == required)
            {
                c = s.charAt(left);
                if(ans[0] == -1| right -left +1 < ans[0])
                {
                    ans[0] = right - left + 1;
                    ans[1] = left;
                    ans[2] = right;
                }

                // The character at the position pointed by the
                // `Left` pointer is no longer a part of the window.
                if(map.containsKey(c))
                {
                    map.put(c, map.get(c) -1);
                    if(targetMap.containsKey(c) && map.get(c).compareTo(targetMap.get(c)) < 0)
                    {
                        formed--;
                    }

                }

                left++; // move the left pointer ahead. and then look for a new window.
            }

            // keep expanding the window
            right++;

        }


        return ans[0] == -1 ? "": s.substring(ans[1], ans[2] + 1);
    }


    /**
     * 不符合题意，已经适用于集合
     * @param s
     * @param t
     * @return
     */
    public String minWindow2(String s, String t) {
        if(s.length() < t.length())return "";
        String res = s;
        Set<Character> targetSet = new HashSet<>();
        for (int i = 0; i < t.length(); i++) {
            targetSet.add(t.charAt(i));
        }

        boolean hasMinWindow = false;
        int left = 0;


        Map<Character,Integer> map = new HashMap<>();
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if(targetSet.contains(c))
            {
                map.put(c, map.getOrDefault(c,0) + 1);
            }

            if(!map.keySet().containsAll(targetSet))continue;
            while (map.keySet().containsAll(targetSet))
            {
                c = s.charAt(left);
                if(map.containsKey(c))
                {
                    int v = map.get(c);
                    if(v == 1)
                    {
                        map.remove(c);
                    }else {
                        map.put(c, v - 1);
                    }
                }
                left++;
            }
            hasMinWindow = true;
            String tmp = s.substring(left -1, right +1);
            if(tmp.length() < res.length())
            {
                res = tmp;
            }

        }

        if(hasMinWindow)return res;
        return "";
    }


    public static void main(String[] args) {
        MinimumWindowSubstring minimumWindowSubstring = new MinimumWindowSubstring();
        System.out.println(minimumWindowSubstring.minWindow("ADOBECODEBANC", "ABC"));
    }
}
