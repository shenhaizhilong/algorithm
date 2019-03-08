package com.hui.DFS;

import java.util.*;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/12 10:52
 */
public class WordBreak {


    /**
     *
     *139. Word Break
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
     *
     * Note:
     *
     * The same word in the dictionary may be reused multiple times in the segmentation.
     * You may assume the dictionary does not contain duplicate words.
     * Example 1:
     *
     * Input: s = "leetcode", wordDict = ["leet", "code"]
     * Output: true
     * Explanation: Return true because "leetcode" can be segmented as "leet code".
     * Example 2:
     *
     * Input: s = "applepenapple", wordDict = ["apple", "pen"]
     * Output: true
     * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
     *              Note that you are allowed to reuse a dictionary word.
     * Example 3:
     *
     * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
     * Output: false
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>();
        set.addAll(wordDict);

        //dp[i] represents whether index i is word breakable.
        // dp[i] is True if there is a word in the dictionary that ends at ith index of s AND d is also True at the beginning of the word
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 0; j < i ; j++) {
                if(dp[j] && set.contains(s.substring(j,i)))
                {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }


    TrieNode root = new TrieNode();

    public boolean wordBreak2(String s, List<String> wordDict) {
       addAllWords(wordDict);
       return dfs(s,0);
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

    private void addAllWords(List<String> words)
    {
        for(String word: words)
        {
            addWord(word);
        }
    }

    private boolean dfs(String target, int startIdx)
    {
       TrieNode curr = root;
        for (int i = startIdx; i < target.length(); i++) {
            int idx = target.charAt(i) - 'a';
            if(curr.children[idx] == null)return false;
            curr = curr.children[idx];
            if(curr.isWord)
            {
                if(i == target.length() -1)return true;
                if(dfs(target, i +1))
                {
                    return true;
                }

            }

        }

        return false;
    }


    /**
     * 1. to save the memory, we can cache the <Index,bool> rather than <String,bool>
     * 2.  i <=  min(s.length(), startIdx + maxLen)
     *
     */
    int maxLen;
    public boolean wordBreak3(String s, List<String> wordDict) {
        Map<Integer,Boolean> cache = new HashMap<>();
        Set<String> set = new HashSet<>();
        maxLen = 0;
        for(String word:wordDict)
        {
            maxLen = Math.max(word.length(), maxLen);
            set.add(word);
        }

        cache.put(s.length(), true);
        return dfs2(s,0, cache, set);
    }
    private boolean dfs2(String target,int startIdx, Map<Integer,Boolean> cache, Set<String> wordDict)
    {
        if(cache.containsKey(startIdx))return cache.get(startIdx);

        for (int endIdx = startIdx +1; endIdx <= Math.min(target.length(),startIdx + maxLen); endIdx++) {
            String word = target.substring(startIdx, endIdx);
            if(!wordDict.contains(word))continue;
            if(dfs2(target, endIdx, cache, wordDict))
            {
                cache.put(startIdx, true);
                return true;
            }
        }

        cache.put(startIdx, false);
        return false;
    }

    public static void main(String[] args) {
        WordBreak wordBreak = new WordBreak();
        List<String> list = Arrays.asList("leet","code");
        List<String> list2 = Arrays.asList("apple", "pen");
        List<String> list3 = Arrays.asList("cats", "dog", "sand", "and", "cat");
        List<String> list4 = Arrays.asList("cats", "dog", "cat");
        System.out.println("**************************");
        System.out.println(wordBreak.wordBreak("leetcode", list));
        System.out.println(wordBreak.wordBreak("applepenapple", list2));
        System.out.println(wordBreak.wordBreak("catsandog", list3));
        System.out.println(wordBreak.wordBreak("rat", list4));

        System.out.println("**************************");
        System.out.println(wordBreak.wordBreak2("leetcode", list));
        System.out.println(wordBreak.wordBreak2("applepenapple", list2));
        System.out.println(wordBreak.wordBreak2("catsandog", list3));
        System.out.println(wordBreak.wordBreak2("rat", list4));
        System.out.println("**************************");
        System.out.println(wordBreak.wordBreak3("leetcode", list));
        System.out.println(wordBreak.wordBreak3("applepenapple", list2));
        System.out.println(wordBreak.wordBreak3("catsandog", list3));
        System.out.println(wordBreak.wordBreak3("rat", list4));

    }
}
