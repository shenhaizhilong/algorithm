package com.hui.algorithm;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/31 11:51
 */
public class LongestWordInDictionary {

    /**
     *
     * 720. Longest Word in Dictionary
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a list of strings words representing an English Dictionary, find the longest word in words that can be built one character at a time by other words in words. If there is more than one possible answer, return the longest word with the smallest lexicographical order.
     *
     * If there is no answer, return the empty string.
     * Example 1:
     * Input:
     * words = ["w","wo","wor","worl", "world"]
     * Output: "world"
     * Explanation:
     * The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
     * Example 2:
     * Input:
     * words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
     * Output: "apple"
     * Explanation:
     * Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".
     * Note:
     *
     * All the strings in the input will only contain lowercase letters.
     * The length of words will be in the range [1, 1000].
     * The length of words[i] will be in the range [1, 30].
     *
     *
     *
     * @param words
     * @return
     */
    public static String longestWord(String[] words) {

        Trie trie = new Trie();
        for (String word: words)
        {
            trie.insert(word);
        }

        return trie.findLongestWord();

    }

    private static class TrieNode{
        String word;
        boolean isWord;
        TrieNode[] children;

        public TrieNode(){
            children = new TrieNode[26];
        }

    }

    private static class Trie{
        private TrieNode root;
        public Trie()
        {
            root = new TrieNode();
        }

        public void insert(String word)
        {
            TrieNode curr = root;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if(curr.children[index] == null)
                {
                    curr.children[index] = new TrieNode();
                }
                curr = curr.children[index];
            }
            curr.word = word;
            curr.isWord = true;
        }

        /**
         * traverse this trie from top to bottom, one level by one level, in one level from right to left
         * @return
         */
        public String findLongestWord(){
            String res = null;
            Queue<TrieNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty())
            {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TrieNode node = queue.poll();
                    for (int j = 25; j >=0; j--) {
                        if(node.children[j] != null && node.children[j].isWord)
                        {
                            res = node.children[j].word;
                            queue.offer(node.children[j]);
                        }
                    }
                }
            }
            return res;
        }
    }
}
