package com.hui.String;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/16 0:45
 *
 * 916. Word Subsets
 * DescriptionHintsSubmissionsDiscussSolution
 * We are given two arrays A and B of words.  Each word is a string of lowercase letters.
 *
 * Now, say that word b is a subset of word a if every letter in b occurs in a, including multiplicity.  For example, "wrr" is a subset of "warrior", but is not a subset of "world".
 *
 * Now say a word a from A is universal if for every b in B, b is a subset of a.
 *
 * Return a list of all universal words in A.  You can return the words in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","o"]
 * Output: ["facebook","google","leetcode"]
 * Example 2:
 *
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["l","e"]
 * Output: ["apple","google","leetcode"]
 * Example 3:
 *
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","oo"]
 * Output: ["facebook","google"]
 * Example 4:
 *
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["lo","eo"]
 * Output: ["google","leetcode"]
 * Example 5:
 *
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["ec","oc","ceo"]
 * Output: ["facebook","leetcode"]
 *
 *
 * Note:
 *
 * 1 <= A.length, B.length <= 10000
 * 1 <= A[i].length, B[i].length <= 10
 * A[i] and B[i] consist only of lowercase letters.
 * All words in A[i] are unique: there isn't i != j with A[i] == A[j].
 *
 *
 */


public class WordSubsets {
    public List<String> wordSubsets(String[] A, String[] B) {

        int[] counterB = new int[26];
        for(String s:B)
        {
            int[] temp = getCounter(s);
            for (int j = 0; j < 26; j++) {
                if(temp[j] > counterB[j])counterB[j] = temp[j];
            }
        }


        List<String> list = new ArrayList<>();
        for(String str:A)
        {
            int[] counter = getCounter(str);
            boolean isUniversal = true;
            for (int i = 0; i < 26 && isUniversal; i++) {
                if(counter[i] < counterB[i]){
                    isUniversal = false;
                    break;
                }
            }
            if(isUniversal)
                list.add(str);
        }

        return list;
    }

    private int[] getCounter(String str)
    {
        int[] counter = new int[26];
        for (int i = 0; i < str.length(); i++) {
            int idx = str.charAt(i) - 'a';
            counter[idx]++;
        }
        return counter;
    }

    public static void main(String[] args) {

        WordSubsets wordSubsets = new WordSubsets();
        String[] A = {"amazon","apple","facebook","google","leetcode"};
        String[] B = {"ec","oc","ceo"};
        System.out.println(wordSubsets.wordSubsets(A,B));
        System.out.println(wordSubsets.wordSubsets(A,new String[]{"lo","eo"}));
        System.out.println(wordSubsets.wordSubsets(A,new String[]{"e","oo"}));
        System.out.println(wordSubsets.wordSubsets(A,new String[]{"e","l"}));
        System.out.println(wordSubsets.wordSubsets(A,new String[]{"e","o"}));

    }
}
