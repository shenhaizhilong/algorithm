package com.hui.algorithm;

/**
 *
 * 208. Implement Trie (Prefix Tree)
 * DescriptionHintsSubmissionsDiscussSolution
 * Implement a trie with insert, search, and startsWith methods.
 *
 * Example:
 *
 * Trie trie = new Trie();
 *
 * trie.insert("apple");
 * trie.search("apple");   // returns true
 * trie.search("app");     // returns false
 * trie.startsWith("app"); // returns true
 * trie.insert("app");
 * trie.search("app");     // returns true
 * Note:
 *
 * You may assume that all inputs are consist of lowercase letters a-z.
 * All inputs are guaranteed to be non-empty strings.
 * @author: shenhaizhilong
 * @date: 2018/9/7 19:28
 */
public class Trie {

    TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if(curr.next[index] == null)
            {
                curr.next[index] = new TrieNode();
            }
            curr = curr.next[index];

        }
        curr.isWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if(curr.next[index] == null)return false;
            curr = curr.next[index];
        }
        return curr.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            int idx = prefix.charAt(i) - 'a';
            if (curr.next[idx] == null) return false;
            curr = curr.next[idx];
        }
        return true;
    }

    public boolean match(String word)
    {
        return match(word.toCharArray(), 0, root);
    }

    private boolean match(char[] data, int k, TrieNode curr)
    {
        if(k == data.length)return curr.isWord;
        char c = data[k];
        int index = c - 'a';
        if(c != '.')
        {
            if(curr.next[index] == null)return false;
            curr = curr.next[index];
            return match(data,k + 1, curr);
        }else {
            for (int i = 0; i < curr.next.length; i++) {
                if(curr.next[i] != null)
                {
                    if(match(data,k +1, curr.next[i]))
                        return true;
                }
            }
        }
        return false;
    }

    private static class TrieNode{

        private boolean isWord;
        private TrieNode[] next;

        public TrieNode()
        {
            next = new TrieNode[26];
        }
    }

    public static void main(String[] args) {

        Trie trie = new Trie();
        trie.insert("");
        System.out.println(trie.search(""));
        trie.insert("apple");
        // returns true
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));     // returns false
        System.out.println(trie.startsWith("apple")); // returns true
        trie.insert("app");
        System.out.println(trie.search("app"));     // returns true


    }
}
