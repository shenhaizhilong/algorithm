package com.hui.algorithm;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/9 14:50
 *
 *
 * 392. Is Subsequence
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a string s and a string t, check if s is subsequence of t.
 *
 * You may assume that there is only lower case English letters in both s and t. t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).
 *
 * A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).
 *
 * Example 1:
 * s = "abc", t = "ahbgdc"
 *
 * Return true.
 *
 * Example 2:
 * s = "axc", t = "ahbgdc"
 *
 * Return false.
 *
 * Follow up:
 * If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence. In this scenario, how would you change your code?
 *
 * Credits:
 * Special thanks to @pbrother for adding this problem and creating all test cases.
 *
 *
 *
 */
public class IsSubsequence {

    public boolean isSubsequence1(String s, String t) {
        if(s.length() == 0)return true;
        int idxT = 0;
        int idxS = 0;
        int lenT = t.length();
        int lenS = s.length();
        while (idxT < lenT)
        {
            if(t.charAt(idxT) == s.charAt(idxS))
            {
                idxS++;
                if(idxS == lenS)return true;
            }
            idxT++;

        }

        return false;

    }


    public boolean isSubsequence2(String s, String t) {
        if(s.length() == 0)return true;
        int idxT = 0;
        int idxS = 0;
        int lenT = t.length();
        int lenS = s.length();
        int[] freq = new int[26];
        for (int i = 0; i < lenT; i++) {
            int id = t.charAt(i) - 'a';
            freq[id]++;
        }
        while (idxT < lenT)
        {
            if(freq[s.charAt(idxS) - 'a'] <1)return false;
            if(t.charAt(idxT) == s.charAt(idxS))
            {
                idxS++;
                if(idxS == lenS)return true;
            }
            freq[t.charAt(idxT) - 'a']--;
            idxT++;


        }

        return false;

    }

    public boolean isSubsequence3(String s, String t) {
        ArrayList<Integer>[] map = new ArrayList[26];
        for (int i = 0; i < t.length(); i++) {
            int idx = t.charAt(i) - 'a';
            if(map[idx] == null)
            {
                map[idx] = new ArrayList<>();
            }
            map[idx].add(i);
        }

        int prev = 0;
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            if(map[idx] == null)return false;// didn't exist char idx + 'a';
            int k = Collections.binarySearch(map[idx], prev);
            if(k < 0)k = -k -1;
            if(k == map[idx].size())return false; // can't find
            prev = map[idx].get(k) + 1;// next position
        }

        return true;

    }
    public boolean isSubsequence(String s, String t)
    {
        if(s.length() == 0)return true;
        if(s.length() > t.length())return false;
        int prev = 0;
        for (int i = 0; i < s.length(); i++) {
            prev = t.indexOf(s.charAt(i), prev);
            if(prev == -1)return false;
            prev++;
        }
        return true;
    }


    public static void main(String[] args) {

        IsSubsequence isSubsequence = new IsSubsequence();
        System.out.println(isSubsequence.isSubsequence2("abc","ahbgdc"));
    }
}
