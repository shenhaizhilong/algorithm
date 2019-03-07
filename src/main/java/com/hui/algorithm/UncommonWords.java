package com.hui.algorithm;

import java.util.*;

/**
 *https://leetcode.com/problems/uncommon-words-from-two-sentences/
 * 884. Uncommon Words from Two Sentences
 * DescriptionHintsSubmissionsDiscussSolution
 * We are given two sentences A and B.  (A sentence is a string of space separated words.  Each word consists only of lowercase letters.)
 *
 * A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.
 *
 * Return a list of all uncommon words.
 *
 * You may return the list in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: A = "this apple is sweet", B = "this apple is sour"
 * Output: ["sweet","sour"]
 * Example 2:
 *
 * Input: A = "apple apple", B = "banana"
 * Output: ["banana"]
 *
 *
 * Note:
 *
 * 0 <= A.length <= 200
 * 0 <= B.length <= 200
 * A and B both contain only spaces and lowercase letters.
 * @author: shenhaizhilong
 * @date: 2018/8/25 19:25
 */
public class UncommonWords {
    public String[] uncommonFromSentences(String A, String B) {
        String allWords = A + " " + B;
        String[] words = allWords.split(" ");
        HashMap<String,Integer> counter = new HashMap<>();
        for(String w: words)
        {
            counter.put(w, counter.getOrDefault(w,0) + 1);
        }

        List<String> res  = new ArrayList<>();
        for (String w : counter.keySet()){
            if( counter.get(w).intValue() == 1)
            {
                res.add(w);
            }
        }
       words = new String[res.size()];
       res.toArray(words);
        return words;
    }

    public static void main(String[] args) {
        UncommonWords uncommonWords = new UncommonWords();
        String str1 = "this apple is sweet";
        String str2 = "this apple is sour";
        String str3 = "apple apple";
        String str4 = "banana";
        System.out.println(Arrays.toString(uncommonWords.uncommonFromSentences(str1,str2)));
        System.out.println(Arrays.toString(uncommonWords.uncommonFromSentences(str3,str4)));
    }
}
