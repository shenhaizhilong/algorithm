package com.hui.String;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/10 13:22
 *
 *
 * 953. Verifying an Alien Dictionary
 * DescriptionHintsSubmissionsDiscussSolution
 * In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.
 *
 * Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * Output: true
 * Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
 * Example 2:
 *
 * Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 * Output: false
 * Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
 * Example 3:
 *
 * Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 * Output: false
 * Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
 *
 *
 * Note:
 *
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 20
 * order.length == 26
 * All characters in words[i] and order are english lowercase letters.
 *
 *
 */
public class VerifyinganAlienDictionary {
    public boolean isAlienSorted(String[] words, String order) {
        int[] dict = new int[26];
        for (int i = 0; i < dict.length; i++) {
            int idx = order.charAt(i) - 'a';
            dict[idx] = i;
        }
        for (int i = 0; i < words.length -1; i++) {
            if(compare(words[i], words[i +1], dict) > 0)return false;
        }

        return true;
    }

    // two string compare by dict
    private int compare(String word1, String word2, int[] dict)
    {
        int L1 = word1.length();
        int L2 = word2.length();
        int min = Math.min(L1,L2);
        for (int i = 0; i < min; i++) {
            int c1 = word1.charAt(i) - 'a';
            int c2 = word2.charAt(i) - 'a';
            if(c1 != c2)
                return dict[c1] - dict[c2];
        }
        return L1 == min ? -1 : 1;
    }

}
