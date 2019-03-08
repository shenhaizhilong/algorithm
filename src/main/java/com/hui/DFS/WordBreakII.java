package com.hui.DFS;

import java.util.*;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/11 21:47
 *
 *
 * 140. Word Break II
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
 *
 * Note:
 *
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 *
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 * Example 2:
 *
 * Input:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * Output:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output:
 * []
 *
 */
public class WordBreakII {

    public List<String> wordBreak(String s, List<String> wordDict) {
        Map<String,List<String>> map = new HashMap<>();
        return dfs(s, map, wordDict);
    }

    private List<String> dfs(String target, Map<String,List<String>> cache, List<String> wordDict)
    {
        if (cache.containsKey(target))return cache.get(target);

        List<String> list = new ArrayList<>();
        if(target.length() == 0)
        {
            list.add("");
            return list;
        }

       for(String word:wordDict)
       {
           if(target.startsWith(word))
           {
               List<String> temp = dfs(target.substring(word.length()), cache, wordDict);
               for(String w: temp)
               {
                    if(w.isEmpty())
                    {
                        list.add(word);
                    }else {
                        list.add(word + " " + w);
                    }
               }
           }
       }

        cache.put(target, list);
        return list;
    }


    int maxLen;
    public List<String> wordBreak2(String s, List<String> wordDict) {
        Map<Integer,List<String>> cache = new HashMap<>();
        Set<String> set = new HashSet<>();
        maxLen = 0;
        for(String word:wordDict)
        {
            maxLen = Math.max(word.length(), maxLen);
            set.add(word);
        }
        List<String> last = new ArrayList<>();
        last.add("");
        cache.put(s.length(), last );
        return dfs2(s,0, cache, set);
    }
    private List<String> dfs2(String target,int startIdx, Map<Integer,List<String>> cache, Set<String> wordDict)
    {
        if(cache.containsKey(startIdx))return cache.get(startIdx);
        List<String> res = new ArrayList<>();
        for (int endIdx = startIdx +1; endIdx <= Math.min(target.length(),startIdx + maxLen); endIdx++) {
            String word = target.substring(startIdx, endIdx);
            if(!wordDict.contains(word))continue;
            List<String> temp = dfs2(target, endIdx, cache, wordDict);
            for(String w: temp)
            {
                if(w.isEmpty())
                {
                    res.add(word);
                }else {
                    res.add(word + " " + w);
                }
            }
        }

        cache.put(startIdx, res);
        return res;
    }



    public static void main(String[] args) {

        WordBreakII wordBreakII = new WordBreakII();
//        System.out.println(wordBreakII.wordBreak("catsanddog", Arrays.asList(new String[]{"cat", "cats", "and", "sand", "dog"})));
//        System.out.println(wordBreakII.wordBreak("pineapplepenapple", Arrays.asList(new String[]{"apple", "pen", "applepen", "pine", "pineapple"})));
//        System.out.println(wordBreakII.wordBreak("catsandog", Arrays.asList(new String[]{"cats", "dog", "sand", "and", "cat"})));


        System.out.println(wordBreakII.wordBreak2("catsanddog", Arrays.asList(new String[]{"cat", "cats", "and", "sand", "dog"})));
        System.out.println(wordBreakII.wordBreak2("pineapplepenapple", Arrays.asList(new String[]{"apple", "pen", "applepen", "pine", "pineapple"})));
        System.out.println(wordBreakII.wordBreak2("catsandog", Arrays.asList(new String[]{"cats", "dog", "sand", "and", "cat"})));

    }
}
