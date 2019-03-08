package com.hui.DynamicPrograming;

import java.util.*;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/11 16:15
 *
 * 472. Concatenated Words
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
 * A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.
 *
 * Example:
 * Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 *
 * Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 *
 * Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 *  "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
 * "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
 * Note:
 * The number of elements of the given array will not exceed 10,000
 * The length sum of elements in the given array will not exceed 600,000.
 * All the input string will only include lower case letters.
 * The returned elements order does not matter.
 *
 */
public class ConcatenatedWords {

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        if(words == null || words.length <1)return res;
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
               return o1.length() - o2.length();
            }
        });

        Set<String> set = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            if(canWordBreak(words[i], set))
            {
                res.add(words[i]);
            }
            set.add(words[i]);
        }


        return res;
    }


    private boolean canWordBreak(String str, Set<String> set)
    {
        if(set.isEmpty())return false;

        boolean[] dp = new boolean[str.length() +1];
        dp[0] = true;
        for (int i = 1; i < str.length() +1; i++) {
            for (int j = 0; j < i; j++) {
                if(dp[j] && set.contains(str.substring(j,i)))
                {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[str.length()];
    }




    public static void main(String[] args) {
        String[] words = {"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
        ConcatenatedWords concatenatedWords = new ConcatenatedWords();
        System.out.println(concatenatedWords.findAllConcatenatedWordsInADict(words));
    }
}
