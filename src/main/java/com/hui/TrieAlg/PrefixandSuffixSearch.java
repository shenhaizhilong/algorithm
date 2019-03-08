package com.hui.TrieAlg;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/7 14:25
 *
 *
 *745. Prefix and Suffix Search
 * DescriptionHintsSubmissionsDiscussSolution
 * Given many words, words[i] has weight i.
 *
 * Design a class WordFilter that supports one function, WordFilter.f(String prefix, String suffix). It will return the word with given prefix and suffix with maximum weight. If no word exists, return -1.
 *
 * Examples:
 * Input:
 * WordFilter(["apple"])
 * WordFilter.f("a", "e") // returns 0
 * WordFilter.f("b", "") // returns -1
 * Note:
 * words has length in range [1, 15000].
 * For each test case, up to words.length queries WordFilter.f may be made.
 * words[i] has length in range [1, 10].
 * prefix, suffix have lengths in range [0, 10].
 * words[i] and prefix, suffix queries consist of lowercase letters only.
 *
 *
 * insert apple{apple, pple{apple,ple{apple,le{apple,e{apple,{apple
 *
 */
public class PrefixandSuffixSearch {
    private  final Node root;

    public PrefixandSuffixSearch(String[] words) {

        root = new Node();
        for (int i = 0; i < words.length; i++) {
            String word = words[i]+"{" + words[i];
            for (int j = 0; j <= words[i].length(); j++) {
                Node curr = root;
                curr.weight = i;
                for (int k = j; k <word.length() ; k++) {
                    int idx = word.charAt(k) - 'a';
                    if(curr.children[idx] == null)
                    {
                        curr.children[idx] = new Node();
                    }
                    curr = curr.children[idx];
                    curr.weight = i;
                }
            }
        }
        
    }

    public int f(String prefix, String suffix) {

        Node curr = root;
        String word = suffix + "{" + prefix;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if(curr.children[idx] == null)return -1;
            curr = curr.children[idx];
        }
        return curr.weight;
    }

    private class Node{
        int weight = 0;
        Node[] children = new Node[27];
    }

    public static void main(String[] args) {
        PrefixandSuffixSearch prefixandSuffixSearch = new PrefixandSuffixSearch(new String[]{"apple","bool","been"});
        System.out.println(prefixandSuffixSearch.f("ap", "e"));
        System.out.println(prefixandSuffixSearch.f("b","l"));
        System.out.println(prefixandSuffixSearch.f("b","d"));
    }

}
