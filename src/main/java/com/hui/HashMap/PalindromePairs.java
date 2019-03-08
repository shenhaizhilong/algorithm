package com.hui.HashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/29 18:55
 */
public class PalindromePairs {


    /**
     *
     *336. Palindrome Pairs
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
     *
     * Example 1:
     *
     * Input: ["abcd","dcba","lls","s","sssll"]
     * Output: [[0,1],[1,0],[3,2],[2,4]]
     * Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
     * Example 2:
     *
     * Input: ["bat","tab","cat"]
     * Output: [[0,1],[1,0]]
     * Explanation: The palindromes are ["battab","tabbat"]
     * @param words
     * @return
     */
    public List<List<Integer>> palindromePairs(String[] words) {

        if(words == null)return new ArrayList<>();
        Map<String,Integer> map = new HashMap<>(words.length);
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }
        List<List<Integer>> results = new ArrayList<>();
        int id = map.getOrDefault("",-1);
        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            // if this word is a palindrome
            if(isPalindrome(word))
            {
                if(id != -1 && id != i)
                {

                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(id);
                    results.add(list);
                    list = new ArrayList<>();
                    list.add(id);
                    list.add(i);
                    results.add(list);

                }
            }

            //if the reversed word exists, it is a palindrome
           addReversedIfExist(word, i, results, map, false);

            for (int j = 1; j < word.length(); j++) {
                String left = word.substring(0, j);
                String right = word.substring(j);

                // if left part is palindrome, find reversed right part's index
                //  [aaab, aa, ba],  for aaab, left part is aa, right part is ab, reversed right is ba
                // the concatenation palindrome is baaaab
                if(isPalindrome(left))
                {
                    addReversedIfExist(right, i, results, map, true);
                }

                //if right part is a palindrome, find reversed left part's index
                if(isPalindrome(right))
                {
                    addReversedIfExist(left, i, results, map, false);
                }
            }


        }

        return results;


    }

    private void addReversedIfExist( String word, int index, List<List<Integer>> results, Map<String,Integer> map, boolean needSwap)
    {
        String reversedWord = new StringBuilder(word).reverse().toString();
        if(map.containsKey(reversedWord))
        {
            int tmpId = map.getOrDefault(reversedWord, -1);
            if(tmpId != -1 && tmpId != index)
            {
                List<Integer> list = new ArrayList<>();
                if(needSwap)
                {
                    int tmp = index;
                    index = tmpId;
                    tmpId = tmp;
                }
                list.add(index);
                list.add(tmpId);
                results.add(list);
            }
        }
    }

    private boolean isPalindrome(String str)
    {
        int left = 0, right = str.length() -1;
        while (left < right)
        {
            if(str.charAt(left++) != str.charAt(right--))
                return false;

        }

        return true;
    }

    public static void main(String[] args) {
        PalindromePairs palindromePairs = new PalindromePairs();
        System.out.println(palindromePairs.isPalindrome("abc"));
        System.out.println(palindromePairs.isPalindrome("aba"));
        List<List<Integer>> lists = palindromePairs.palindromePairs(new String[]{"abcd","dcba","lls","s","sssll"});
        for (List<Integer> list :
                lists) {
            System.out.println(list);
        }

    }
}
