package com.hui.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/11 17:30
 *
 *
 * 472. Concatenated Words
 *  * DescriptionHintsSubmissionsDiscussSolution
 *  * Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
 *  * A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.
 *  *
 *  * Example:
 *  * Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 *  *
 *  * Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 *  *
 *  * Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 *  *  "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
 *  * "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
 *  * Note:
 *  * The number of elements of the given array will not exceed 10,000
 *  * The length sum of elements in the given array will not exceed 600,000.
 *  * All the input string will only include lower case letters.
 *  * The returned elements order does not matter.
 *
 *
 */
public class ConcatenatedWordsII {
    TrieNode root = new TrieNode();
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        if (words == null || words.length < 1) return res;
        addAllWords(words);
        for (int i = 0; i < words.length; i++) {
            if(dfs(words[i], 0,0))
            {
                res.add(words[i]);
            }
        }

        return res;
    }

    private static class TrieNode{
        TrieNode[] children;
        boolean isWord;
        public TrieNode()
        {
            children = new TrieNode[26];
            isWord = false;
        }
    }

    private void addWord(String word)
    {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if(curr.children[idx] == null)
            {
                curr.children[idx] = new TrieNode();
            }
            curr = curr.children[idx];
        }
        curr.isWord = true;
    }

    private void addAllWords(String[] words)
    {
        for(String word: words)
        {
            addWord(word);
        }
    }

    private boolean dfs(String target, int startIdx, int count)
    {
        TrieNode curr = root;
        for (int i = startIdx; i < target.length(); i++) {
            int idx = target.charAt(i) - 'a';
            if(curr.children[idx] == null)return false;
            curr = curr.children[idx];
            if(curr.isWord)
            {
                if(i == target.length() -1)return count >=1;
                if(dfs(target, i +1, count +1))
                {
                    return true;
                }

            }

        }

        return false;
    }

    public static void main(String[] args) {
        String[] words = {"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
        ConcatenatedWordsII concatenatedWords = new ConcatenatedWordsII();
        System.out.println(concatenatedWords.findAllConcatenatedWordsInADict(words));
    }
}
