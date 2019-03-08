package com.hui.String;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/27 11:59
 *
 *524. Longest Word in Dictionary through Deleting
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string. If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.
 *
 * Example 1:
 * Input:
 * s = "abpcplea", d = ["ale","apple","monkey","plea"]
 *
 * Output:
 * "apple"
 * Example 2:
 * Input:
 * s = "abpcplea", d = ["a","b","c"]
 *
 * Output:
 * "a"
 * Note:
 * All the strings in the input will only contain lower-case letters.
 * The size of the dictionary won't exceed 1,000.
 * The length of all the strings in the input won't exceed 1,000.
 *
 */
public class LongestWordinDictionarythroughDeleting {

    public String findLongestWord(String s, List<String> d) {

        Collections.sort(d, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o2.length() != o1.length()) return o2.length() - o1.length();
                return o1.compareTo(o2);
            }
        });
        for(String word: d)
        {
            if(isSubSeq(s, word))
            {
                return word;
            }
        }
        return "";
    }

    public String findLongestWord2(String s, List<String> d) {

        String ans = "";
        int maxLen = 0;
        for(String word: d)
        {
            if(isSubSeq2(s,word))
            {
                if(word.length() > maxLen || (word.length() == maxLen && word.compareTo(ans) < 0))
                {
                    ans = word;
                    maxLen = word.length();
                }
            }
        }
        return ans;
    }

    private boolean isSubSeq(String source, String target)
    {
        if(target.length() > source.length())return false;
        int idx = 0;
        char t = target.charAt(idx);
        for (int i = 0; i < source.length(); i++) {
            if(source.charAt(i) == t)
            {
                idx++;
                t = target.charAt(idx); // 减少chatAt(i) 的执行次数可以提升性能
                if(idx == target.length())return true;
            }
        }
        return false;
    }


    public String findLongestWord3(String s, List<String> d) {

        String ans = "";
        int maxLen = 0;
        for(String word: d)
        {
            if(word.length() > maxLen || (word.length() == maxLen && word.compareTo(ans) < 0))
            {
                if(isSubSeq2(s,word) )
                {
                    ans = word;
                    maxLen = word.length();
                }
            }
        }
        return ans;
    }
    private boolean isSubSeq2(String source, String target)
    {
        if(target.length() > source.length())return false;
        int idx = -1;
        int i = 0;
        while (i < target.length())
        {
            char t = target.charAt(i);
            idx = source.indexOf(t, idx +1);
            if(idx < 0)return false;
            i++;
        }
        return true;
    }
}
