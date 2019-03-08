package com.hui.TrieAlg;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/30 14:05
 *
 *
 * 966. Vowel Spellchecker
 * Medium
 *
 * 5
 *
 * 12
 *
 * Favorite
 *
 * Share
 * Given a wordlist, we want to implement a spellchecker that converts a query word into a correct word.
 *
 * For a given query word, the spell checker handles two categories of spelling mistakes:
 *
 * Capitalization: If the query matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the case in the wordlist.
 * Example: wordlist = ["yellow"], query = "YellOw": correct = "yellow"
 * Example: wordlist = ["Yellow"], query = "yellow": correct = "Yellow"
 * Example: wordlist = ["yellow"], query = "yellow": correct = "yellow"
 * Vowel Errors: If after replacing the vowels ('a', 'e', 'i', 'o', 'u') of the query word with any vowel individually, it matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the match in the wordlist.
 * Example: wordlist = ["YellOw"], query = "yollow": correct = "YellOw"
 * Example: wordlist = ["YellOw"], query = "yeellow": correct = "" (no match)
 * Example: wordlist = ["YellOw"], query = "yllw": correct = "" (no match)
 * In addition, the spell checker operates under the following precedence rules:
 *
 * When the query exactly matches a word in the wordlist (case-sensitive), you should return the same word back.
 * When the query matches a word up to capitlization, you should return the first such match in the wordlist.
 * When the query matches a word up to vowel errors, you should return the first such match in the wordlist.
 * If the query has no matches in the wordlist, you should return the empty string.
 * Given some queries, return a list of words answer, where answer[i] is the correct word for query = queries[i].
 *
 *
 *
 * Example 1:
 *
 * Input: wordlist = ["KiTe","kite","hare","Hare"], queries = ["kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"]
 * Output: ["kite","KiTe","KiTe","Hare","hare","","","KiTe","","KiTe"]
 *
 *
 * Note:
 *
 * 1 <= wordlist.length <= 5000
 * 1 <= queries.length <= 5000
 * 1 <= wordlist[i].length <= 7
 * 1 <= queries[i].length <= 7
 * All strings in wordlist and queries consist only of english letters.
 *
 */
public class VowelSpellchecker {
    TrieNode root = new TrieNode();
    public String[] spellchecker(String[] wordlist, String[] queries) {
        String[] ans = new String[queries.length];
        int idx = 0;
        Set<String> wordDict = new HashSet<>();
        for(String word: wordlist)
        {
            insert(word);
            wordDict.add(word);
        }

        for(String q: queries)
        {
            if(wordDict.contains(q))
            {
                ans[idx++] = q;
            }else {
                ans[idx++] = query(q);
            }
        }
        return ans;
    }

    private String query(String word)
    {
        TrieNode curr = root;
        char[] data = word.toLowerCase().toCharArray();
        for (int i = 0; i < data.length; i++) {
            char c = data[i];
            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
            {
                if(curr.children[0] == null)return "";
                curr = curr.children[0];
            }else {
                int idx = c - 'a';
                if(curr.children[idx] == null) return "";
                curr = curr.children[idx];
            }
        }
        if(!curr.isWord)return "";
        String target = findWord(curr.list, word);
        if(!target.isEmpty())return target;
        return curr.list.get(0);

    }

    private String findWord(List<String> list, String word)
    {
        for(String w:list)
        {
            if(w.equalsIgnoreCase(word))return w;
        }
        return "";
    }

    private void insert(String word)
    {
        TrieNode curr = root;
        char[] data = word.toLowerCase().toCharArray();
        for (int i = 0; i < data.length; i++) {
            char c = data[i];
            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
            {
                if(curr.children[0] == null)
                {
                    curr.children[0]= new TrieNode();
                }
                curr = curr.children[0];
            }else {
                int idx = c - 'a';
                if(curr.children[idx] == null)
                {
                    curr.children[idx]= new TrieNode();
                }
                curr = curr.children[idx];
            }
        }
        curr.isWord = true;
        curr.list.add(word);
    }

    private static class TrieNode{
        boolean isWord;
        TrieNode[] children;
        List<String> list;
        public TrieNode()
        {
            children = new TrieNode[26];
            list = new ArrayList<>();
        }
    }
}
