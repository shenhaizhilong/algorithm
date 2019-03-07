package com.hui.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/29 17:27
 *
 *890. Find and Replace Pattern
 * DescriptionHintsSubmissionsDiscussSolution
 * You have a list of words and a pattern, and you want to know which words in words matches the pattern.
 *
 * A word matches the pattern if there exists a permutation of letters p so that after replacing every letter x in the pattern with p(x), we get the desired word.
 *
 * (Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter, and no two letters map to the same letter.)
 *
 * Return a list of the words in words that match the given pattern.
 *
 * You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
 * Output: ["mee","aqq"]
 * Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}.
 * "ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation,
 * since a and b map to the same letter.
 *
 *
 * Note:
 *
 * 1 <= words.length <= 50
 * 1 <= pattern.length = words[i].length <= 20
 *
 */
public class FindandReplacePattern {

    public List<String> findAndReplacePattern(String[] words, String pattern) {
        int[] p = normal(pattern);
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if(words[i].length() == pattern.length() && Arrays.equals(p, normal(words[i])))
            {
                ans.add(words[i]);
            }
        }

        return ans;
    }


    /**
     *
     *  abb => [1,2,2]
     *  cbb=>  [1,2,2]
     *
     *
     * @param word
     * @return
     */
    private int[] normal(String word)
    {
        int[] map = new int[26];
        int N = word.length();
        int[] ans = new int[N];
        int uniq = 1;
        for (int i = 0; i < N; i++) {
            int idx = word.charAt(i) - 'a';
            if(map[idx] == 0)
            {
                map[idx] = uniq;
                uniq++;
            }
            ans[i] = map[idx];
        }
        return ans;
    }


    public List<String> findAndReplacePattern2(String[] words, String pattern) {
        List<String> ans = new ArrayList<>();
        for(String word:words)
        {
            int[] p = new int[26];
            int[] w = new int[26];
            boolean isSame = true;
            for (int i = 0; i < word.length(); i++) {

                if(p[pattern.charAt(i) - 'a'] != w[word.charAt(i) - 'a'])
                {
                    isSame = false;
                    break;
                }else {
                    p[pattern.charAt(i) - 'a'] = i +1;
                    w[word.charAt(i) - 'a'] = i +1;
                }
            }

            if(isSame)
            {
                ans.add(word);
            }

        }

        return ans;
    }

    public static void main(String[] args) {

        String[] word = {"abc","deq","mee","aqq","dkd","ccc"};
        FindandReplacePattern findandReplacePattern = new FindandReplacePattern();
        System.out.println(findandReplacePattern.findAndReplacePattern(word, "abb"));
        System.out.println(findandReplacePattern.findAndReplacePattern2(word, "abb"));

    }
}
