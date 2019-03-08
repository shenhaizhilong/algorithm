package com.hui.TrieAlg;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/7 13:03
 *
 *  * 820. Short Encoding of Words
 *  * DescriptionHintsSubmissionsDiscussSolution
 *  * Given a list of words, we may encode it by writing a reference string S and a list of indexes A.
 *  *
 *  * For example, if the list of words is ["time", "me", "bell"], we can write it as S = "time#bell#" and indexes = [0, 2, 5].
 *  *
 *  * Then for each index, we will recover the word by reading from the reference string from that index until we reach a "#" character.
 *  *
 *  * What is the length of the shortest reference string S possible that encodes the given words?
 *  *
 *  * Example:
 *  *
 *  * Input: words = ["time", "me", "bell"]
 *  * Output: 10
 *  * Explanation: S = "time#bell#" and indexes = [0, 2, 5].
 *  *
 *  *
 *  * Note:
 *  *
 *  * 1 <= words.length <= 2000.
 *  * 1 <= words[i].length <= 7.
 *  * Each word has only lowercase letters.
 */
public class ShortEncodingofWordsII {

    public int minimumLengthEncoding(String[] words) {
        Trie trie = new Trie();
        trie.insertAllReverse(words);
        return trie.getLength();
    }

    private class Trie{
        private static final int Default_Size = 26;
        private final   Node root;
        private int length = 0;

        public Trie()
        {
            root = new Node();
        }

        public void insertAllReverse(String[] words)
        {
            for(String word: words)
            {
                insertReverse(word);
            }
        }

        public void insertReverse(String word)
        {
            Node curr = root;
            for (int i = word.length() -1; i >=0; i--) {
                int idx = word.charAt(i) - 'a';
                if(curr.children[idx] == null)
                {
                    curr.children[idx] = new Node();
                    curr.isLeaf = false;
                }
                curr = curr.children[idx];
                if(curr.word != null)
                {
                    length -= curr.word.length() + 1;
                    curr.word = null;
                }
            }

            if(curr.isLeaf)
            {
                curr.word = word;
                length += curr.word.length() + 1;
            }
        }


        public int getLength() {
            return length;
        }

        private class Node{
            Node[] children = new Node[Default_Size];
            boolean isLeaf = true;
            String word = null;
        }
    }

    public static void main(String[] args) {

        ShortEncodingofWordsII shortEncodingofWords = new ShortEncodingofWordsII();
        System.out.println(shortEncodingofWords.minimumLengthEncoding(new String[]{"time","me", "bell","me"}));
    }
}
