package com.hui.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/18 20:24
 */
public class MaximumProductofWordLengths {


    /**
     *318. Maximum Product of Word Lengths
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.
     *
     * Example 1:
     *
     * Input: ["abcw","baz","foo","bar","xtfn","abcdef"]
     * Output: 16
     * Explanation: The two words can be "abcw", "xtfn".
     * Example 2:
     *
     * Input: ["a","ab","abc","d","cd","bcd","abcd"]
     * Output: 4
     * Explanation: The two words can be "ab", "cd".
     * Example 3:
     *
     * Input: ["a","aa","aaa","aaaa"]
     * Output: 0
     * Explanation: No such pair of words.
     * @param words
     * @return
     */
    public int maxProduct(String[] words) {
        if(words == null || words.length == 0)return 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        int result = 0;
        for(String word:words)
        {
            int mask = 0;
            for(char c:word.toCharArray())
            {
                mask |= 1<<(c - 'a');   //if it has letter c, then set (c-'a')th bit to true.
            }
            map.put(mask, Math.max(map.getOrDefault(mask,0), word.length()));  //update mask's mapping value.
            for (Map.Entry<Integer, Integer> entry :
                    map.entrySet()) {
                if ((mask & entry.getKey()) == 0) // checking whether the two strings have common letter.
                {
                    result = Math.max(result, word.length()*entry.getValue());
                }
            }
        }

        return result;
    }


    public int maxProduct2(String[] words) {
        if(words == null || words.length == 0)return 0;
        int[] hash = new int[words.length];
        int result = 0;
        for (int i = 0; i < words.length; i++) {

            for(char c:words[i].toCharArray())
            {
                hash[i] |= 1<<(c - 'a');   //if it has letter c, then set (c-'a')th bit to true.
            }
        }

        for (int j = 0; j < words.length -1; j++) {
            for (int k = j + 1; k < words.length; k++) {
                if((hash[j] & hash[k]) == 0)  // no common letters.
                {

                    result = Math.max(result, words[j].length()*words[k].length());
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] words = {"a","ab","abc","d","cd","bcd","abcd"};
        MaximumProductofWordLengths maximumProductofWordLengths = new MaximumProductofWordLengths();
        System.out.println(maximumProductofWordLengths.maxProduct(words));
        System.out.println(maximumProductofWordLengths.maxProduct2(words));
    }
}
