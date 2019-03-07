package com.hui.algorithm;

import java.util.*;

/**
 *
 *
 * 438. Find All Anagrams in a String
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 *
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 *
 * The order of output does not matter.
 *
 * Example 1:
 *
 * Input:
 * s: "cbaebabacd" p: "abc"
 *
 * Output:
 * [0, 6]
 *
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 *
 * Input:
 * s: "abab" p: "ab"
 *
 * Output:
 * [0, 1, 2]
 *
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 * @author: shenhaizhilong
 * @date: 2018/8/25 10:57
 */
public class FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if(s == null || p == null || s.length() == 0 || s.length() < p.length())return list;
        int len = p.length();

        char[] charP = p.toCharArray();
        int[] countP = new int[26];
        for(char c : charP){countP[c -'a'] +=1;}


        for (int start = 0;start <= s.length() -len; start++) {
            int end = start + len -1;
            if(isAnagram(s, start, end, countP))
            {
                list.add(start);
            }
        }

        return list;

    }


    private boolean isAnagram(String s, int start, int end, int[] countT)
    {
        int[] countS = new int[26];
        for (int i = start; i <= end; i++) {
            int index = s.charAt(i) - 'a';
            countS[index]++;
        }
        for (int i = 0; i < countS.length; i++) {
            if(countS[i] != countT[i])return false;
        }
        return true;

    }


    /**
     * sliding window algorithm
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if(s == null || p == null || s.length() < p.length())return list;


        Map<Character,Integer> hash = new HashMap<>();
        for(char c:p.toCharArray()){
            hash.put(c,hash.getOrDefault(c,0).intValue() + 1);
        }
        int count = hash.size();
        int slow = 0, fast = 0;
        while (fast < s.length())
        {
            char c = s.charAt(fast);
            if(hash.containsKey(c))
            {
                hash.put(c,hash.get(c).intValue() -1);
                if(hash.get(c).intValue() == 0)count--;
            }
            fast++;

            while (count ==0)
            {
                c = s.charAt(slow);
                if(hash.containsKey(c))
                {
                    hash.put(c, hash.get(c).intValue() +1);
                    if(hash.get(c).intValue() >0)count++;
                }
                if(fast - slow == p.length()){
                    list.add(slow);
                }
                slow++;
            }
        }
        return list;

    }


    /**
     * sliding window algorithm ,this time we use int array replace HashMap
     *
     * https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92007/Sliding-Window-algorithm-template-to-solve-all-the-Leetcode-substring-search-problem.
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams3(String s, String p) {

        List<Integer> res = new ArrayList<>();
        if (s == null || p == null) return res;

        // total number of character in p to be contained in s
        int countToBeContained = p.length();
        // calculate the number of each character to be contained in S
        int[] dict = new int[128];
        for (char c : p.toCharArray()) dict[c]++;

        int slow = 0, fast = 0;
        while (fast < s.length()) {
            // if the character needs to be contained, include it and minus the counter
           // if (dict[s.charAt(fast++)]-- > 0) countToBeContained--; //it's very complicated, I use below 3 lines code to replace it.
            if(dict[s.charAt(fast)] > 0) countToBeContained--;
            dict[s.charAt(fast)]--;
            fast++;

            // all included, move the slow pointer to minimize the window
            while (countToBeContained == 0) {
                // current window contains same number of the current character as in p, cannot move forward anymore
                if (dict[s.charAt(slow++)]++ == 0) {
                    // if the window size is same as p, an anagram is found
                    if (fast - (slow - 1) == p.length()) res.add(slow - 1);
                    countToBeContained++;
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {

         String s = "cbaebabacd", p = "abc";
          FindAllAnagramsInAString findAllAnagramsInAString = new FindAllAnagramsInAString();

         List<Integer> list = findAllAnagramsInAString.findAnagrams3(s,p);
        System.out.println(list);
        String ss = "acbab";
        String pp = "ab";
        List<Integer> list2 = findAllAnagramsInAString.findAnagrams3(ss,pp);
        System.out.println(list2);

        int[] arr = new int[]{1,0,0};
        int fast  = 0;
        int count = 3;
         while (fast < arr.length)
         {
             if(arr[fast++]-- > 0)
             {
                 count--;

             }
             System.out.println(fast);
         }
//        for (int i = 0; i < arr.length; i++) {
//            if(arr[i]-- >0)
//            {
//                System.out.println(arr[i]);
//            }
//        }

        System.out.println(Arrays.toString(arr));
    }
}
