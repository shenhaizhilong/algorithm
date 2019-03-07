package com.hui.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * https://leetcode-cn.com/problems/first-unique-character-in-a-string/description/
 * 字符串中的第一个唯一字符
 *
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 * 案例:
 *
 * s = "leetcode"
 * 返回 0.
 *
 * s = "loveleetcode",
 * 返回 2.
 *
 *
 * 注意事项：您可以假定该字符串只包含小写字母。
 * @author: shenhaizhilong
 * @date: 2018/7/1 11:17
 */
public class FirstUniqChar {

    // 如果是任意字符可以用以下方法
    public static int firstUniqChar(String s) {

        Map<Character,Integer> map = new HashMap<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            if(map.containsKey(s.charAt(i)))
            {
                map.put(s.charAt(i), (map.get(s.charAt(i)) +1));
            }else {
                map.put(s.charAt(i),1 );
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if(map.get(s.charAt(i)).intValue() == 1 )
                return i;
        }

        return -1;

    }


    /**
     * 该字符串只包含小写字母 a-z
     * @param s
     * @return
     */
    public static int firstUniqChar2(String s) {
        int[] charactersFreq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            charactersFreq[s.charAt(i) - 97] +=1;
        }

        for (int i = 0; i < s.length(); i++) {
            if(charactersFreq[s.charAt(i) -97] ==1)
                return i;
        }

        return -1;
    }


    public static int firstUniqChar3(String s) {

        int firstIndex;
        int lastIndex;

        for (int i = 0; i < s.length(); i++) {
            firstIndex = s.indexOf(s.charAt(i));
            lastIndex = s.lastIndexOf(s.charAt(i));
            if(firstIndex == lastIndex)
                return i;
        }

        return -1;
    }

    public static void main(String[] args) {
       String s = "leetcode";
        System.out.println(firstUniqChar3(s));
        String ss = "loveleetcode";
        System.out.println(firstUniqChar3(ss));

    }
}
