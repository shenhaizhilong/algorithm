package com.hui.TrieAlg;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/8 23:03
 *
 *
 *677. Map Sum Pairs
 * DescriptionHintsSubmissionsDiscussSolution
 * Implement a MapSum class with insert, and sum methods.
 *
 * For the method insert, you'll be given a pair of (string, integer). The string represents the key and the integer represents the value. If the key already existed, then the original key-value pair will be overridden to the new one.
 *
 * For the method sum, you'll be given a string representing the prefix, and you need to return the sum of all the pairs' value whose key starts with the prefix.
 *
 * Example 1:
 * Input: insert("apple", 3), Output: Null
 * Input: sum("ap"), Output: 3
 * Input: insert("app", 2), Output: Null
 * Input: sum("ap"), Output: 5
 *
 *
 */
public class MapSumPairs {

    TrieNode root;
    /** Initialize your data structure here. */
    public MapSumPairs() {
        root = new TrieNode();
    }

    public void insert(String key, int val) {

        int value = getValue(key);
        if(value < 0)value = 0;
        TrieNode curr = root;
        for(char c: key.toCharArray())
        {
            int idx = c - 'a';
            if(curr.children[idx] == null)
            {
                curr.children[idx] = new TrieNode();
            }
            curr = curr.children[idx];
            curr.value += val - value;

        }
        curr.isWord = true;
    }

    public int sum(String prefix) {
        return  Math.abs(getValue(prefix));
    }

    private static class TrieNode{
        int value;
        TrieNode[] children;
        boolean isWord;
        public TrieNode()
        {
            children = new TrieNode[26];
        }
    }

    private int getValue(String key)
    {
        TrieNode curr = root;
        for(char c: key.toCharArray())
        {
            int idx = c - 'a';
            if(curr.children[idx] == null)return 0;
            curr = curr.children[idx];
        }
        return curr.isWord ? curr.value: -curr.value;
    }

    public static void main(String[] args) {

        MapSumPairs mapSumPairs = new MapSumPairs();
        mapSumPairs.insert("aa",3);
        System.out.println(mapSumPairs.sum("a"));
        mapSumPairs.insert("ab",2);
        System.out.println(mapSumPairs.sum("a"));
    }
}
