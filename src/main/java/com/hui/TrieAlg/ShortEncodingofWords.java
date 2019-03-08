package com.hui.TrieAlg;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/7 12:35
 *
 * 820. Short Encoding of Words
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a list of words, we may encode it by writing a reference string S and a list of indexes A.
 *
 * For example, if the list of words is ["time", "me", "bell"], we can write it as S = "time#bell#" and indexes = [0, 2, 5].
 *
 * Then for each index, we will recover the word by reading from the reference string from that index until we reach a "#" character.
 *
 * What is the length of the shortest reference string S possible that encodes the given words?
 *
 * Example:
 *
 * Input: words = ["time", "me", "bell"]
 * Output: 10
 * Explanation: S = "time#bell#" and indexes = [0, 2, 5].
 *
 *
 * Note:
 *
 * 1 <= words.length <= 2000.
 * 1 <= words[i].length <= 7.
 * Each word has only lowercase letters.
 *
 *
 */
public class ShortEncodingofWords {

    public int minimumLengthEncoding(String[] words) {
        if(words == null || words.length < 1)return 0;
        TrieNode root = new TrieNode();
        Map<TrieNode, Integer> trieNodeMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            TrieNode curr = root;
            for (int j = word.length() -1; j >=0; j--) {
                curr = curr.get(word.charAt(j));
            }
            trieNodeMap.put(curr, i);

        }

        int ans = 0;
        for(Map.Entry<TrieNode, Integer> entry: trieNodeMap.entrySet())
        {
            if(entry.getKey().isLeaf)
            {
                ans += words[entry.getValue().intValue()].length() + 1;
            }
        }

        return ans;

    }


    private class TrieNode{
        TrieNode[] children;
        boolean isLeaf;

        public TrieNode()
        {
            children = new TrieNode[26];
            isLeaf = true;
        }

        public TrieNode get(char c)
        {
            int index = c - 'a';
            if(children[index] == null)
            {
                children[index] = new TrieNode();
                isLeaf = false;
            }
            return children[index];
        }
    }

    public static void main(String[] args) {

        ShortEncodingofWords shortEncodingofWords = new ShortEncodingofWords();
        System.out.println(shortEncodingofWords.minimumLengthEncoding(new String[]{"time","me", "bell"}));

    }
}
