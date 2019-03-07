package com.hui.algorithm;

import java.util.HashMap;

/**
 * @author: shenhaizhilong
 * @date: 2018/7/5 18:09
 */
public class LengthOfLongestSubstring {

    public static int lengthOfLongestSubstring(String s) {

        int maxLength = 0;
        int currentMaxLength = 0;
        HashMap<Character, Integer> map = new HashMap<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            if(!map.containsKey(s.charAt(i)))
            {
                map.put(s.charAt(i), i);
                currentMaxLength++;
            }else {
                i =  map.get(s.charAt(i));
                currentMaxLength = 0;
                map.clear();
            }

            if(currentMaxLength > maxLength)
            {
                maxLength = currentMaxLength;
            }
        }

        return maxLength;
    }
}
