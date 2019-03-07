package com.hui.algorithm;

import java.util.*;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/6 9:59
 */
public class WordLadder {


    /**
     *
     * https://leetcode.com/problems/word-ladder/discuss/40707/Easy-76ms-C++-Solution-using-BFS
     *
     *
     * 127. Word Ladder
     * DescriptionHintsSubmissionsDiscussSolution
     * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
     *
     * Only one letter can be changed at a time.
     * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
     * Note:
     *
     * Return 0 if there is no such transformation sequence.
     * All words have the same length.
     * All words contain only lowercase alphabetic characters.
     * You may assume no duplicates in the word list.
     * You may assume beginWord and endWord are non-empty and are not the same.
     * Example 1:
     *
     * Input:
     * beginWord = "hit",
     * endWord = "cog",
     * wordList = ["hot","dot","dog","lot","log","cog"]
     *
     * Output: 5
     *
     * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
     * return its length 5.
     * Example 2:
     *
     * Input:
     * beginWord = "hit"
     * endWord = "cog"
     * wordList = ["hot","dot","dog","lot","log"]
     *
     * Output: 0
     *
     * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Deque<String> toVisit = new ArrayDeque<>();
        Set<String> wordSet = new HashSet<>(wordList.size());

        // change to hashset.
        wordSet.addAll(wordList);
        if(!wordSet.contains(endWord))return 0;  // no solution if endword not in wordSet.
        // add adjacent  vertex
        addNextWord(beginWord,wordSet, toVisit);
        // distance from start to this vertex
        int dist = 2;
        while (!toVisit.isEmpty())
        {

            int num = toVisit.size();
            for (int i = 0; i < num; i++) {
                String word = toVisit.pollFirst();
                if(word.equals(endWord))return dist;
                addNextWord(word, wordSet, toVisit);
            }

            // next vertex
            dist++;
        }
        return 0;

    }


    /**
     *
     * add adjacent vertex to the queue.
     * @param word
     * @param wordSet
     * @param toVisit
     */
    private void addNextWord(String word, Set<String> wordSet, Deque<String> toVisit)
    {
        wordSet.remove(word);
        char[] data = word.toCharArray();
        for (int i = 0; i < data.length; i++) {
            char c = data[i];
            for (char j = 'a'; j <= 'z'; j++) {
                if(j == c)continue;
                data[i] = j;
                String temp = String.valueOf(data);
                if(wordSet.contains(temp))
                {
                    toVisit.addLast(temp);
                    wordSet.remove(temp);
                }
            }
            data[i] = c;
        }
    }


    public static void main(String[] args) {

        List<String> worddList = Arrays.asList(new String[]{"hot","dot","dog","lot","log","cog"});


        List<String> worddList2 = new ArrayList<>(Arrays.asList(new String[]{"hot","dot","dog","lot","log"})) ;
        String begin = "hit";
        String end = "cog";
        WordLadder wordLadder = new WordLadder();
        System.out.println(wordLadder.ladderLength(begin,end,worddList));
       // System.out.println(wordLadder.ladderLength(begin,end,worddList2));

    }
}
