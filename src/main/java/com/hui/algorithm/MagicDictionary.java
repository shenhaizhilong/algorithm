package com.hui.algorithm;

import java.util.*;

/**
 *
 *
 * https://leetcode.com/problems/implement-magic-dictionary/description/
 *676. Implement Magic Dictionary
 * Implement a magic directory with buildDict, and search methods.
 *
 * For the method buildDict, you'll be given a list of non-repetitive words to build a dictionary.
 *
 * For the method search, you'll be given a word, and judge whether if you modify exactly one character into another character in this word, the modified word is in the dictionary you just built.
 *
 * Example 1:
 * Input: buildDict(["hello", "leetcode"]), Output: Null
 * Input: search("hello"), Output: False
 * Input: search("hhllo"), Output: True
 * Input: search("hell"), Output: False
 * Input: search("leetcoded"), Output: False
 * Note:
 * You may assume that all the inputs are consist of lowercase letters a-z.
 * For contest purpose, the test data is rather small by now. You could think about highly efficient algorithm after the contest.
 * Please remember to RESET your class variables declared in class MagicDictionary, as static/class variables are persisted across multiple test cases. Please see here for more details.
 *
 * @author: shenhaizhilong
 * @date: 2018/8/16 11:31
 */
public class MagicDictionary {
    /** Initialize your data structure here. */
    Map<Integer,List<String>> lenWordMap;

    public MagicDictionary() {
        lenWordMap = new HashMap<>();
    }

    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        for (String w :
                dict) {
            List<String> list = lenWordMap.get(w.length());
            if(list == null)
            {
                list = new ArrayList<>();
            }
            list.add(w);
            lenWordMap.put(w.length(),list);
        }

    }

    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        if(word == null)return false;
        if(!lenWordMap.containsKey(word.length()))return false;
        List<String> wordList = lenWordMap.get(word.length());
        for (String w :
                wordList) {
          if(diffCount(w, word) == 1)return true;
        }
        return false;

    }

    private int diffCount(String w1, String w2)
    {
        int count = 0;
        for (int i = 0; i < w1.length(); i++) {
            if(w1.charAt(i) != w2.charAt(i))
            {
                count++;
                if(count >1)return 2;
            }
        }

        return count;
    }

    public static void main(String[] args) {

        String[] dict = {"hello", "leetcode"};
        MagicDictionary magicDictionary = new MagicDictionary();
        magicDictionary.buildDict(dict);
        System.out.println(magicDictionary.search("hello"));
        System.out.println(magicDictionary.search("hhllo"));
        System.out.println(magicDictionary.search("hell"));
        System.out.println(magicDictionary.search("leetcoded"));

    }
}
