package com.hui.HashMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/9 16:24
 *
 *
 * 792. Number of Matching Subsequences
 * DescriptionHintsSubmissionsDiscussSolution
 * Given string S and a dictionary of words words, find the number of words[i] that is a subsequence of S.
 *
 * Example :
 * Input:
 * S = "abcde"
 * words = ["a", "bb", "acd", "ace"]
 * Output: 3
 * Explanation: There are three words in words that are a subsequence of S: "a", "acd", "ace".
 * Note:
 *
 * All words in words and S will only consists of lowercase letters.
 * The length of S will be in the range of [1, 50000].
 * The length of words will be in the range of [1, 5000].
 * The length of words[i] will be in the range of [1, 50].
 *
 *
 *
 */
public class NumberofMatchingSubsequences {

    public int numMatchingSubseq(String S, String[] words) {
        HashMap<String,Boolean> map = new HashMap<>();
        int ans = 0;
        for (int i = 0; i < words.length; i++) {
            if(map.containsKey(words[i]))
            {
               ans += map.get(words[i]).booleanValue() == true ? 1:0;
               continue;
            }
            if(isSubseq(words[i], S))
            {
                ans++;
                map.put(words[i], true);
            }else {
                map.put(words[i], false);
            }

        }
        return ans;
    }


    public int numMatchingSubseq2(String S, String[] words) {
        Set<String> yesSet=new HashSet<>();
        Set<String> noSet=new HashSet<>();
        int count=0;
        for(String word:words){
            if(yesSet.contains(word)){
                count++;
                continue;
            }
            if(noSet.contains(word)) continue;
            boolean isSub=isSubseq(S,word);
            if(isSub){
                yesSet.add(word);
                count++;
            }else{
                noSet.add(word);
            }
        }
        return count;

    }

    /**
     *  whether s is a sub seq of t.
     * @param s
     * @param t
     * @return
     */
    private boolean isSubseq(String s, String t)
    {
        if(s.length() > t.length())return false;
        int prev = 0;
        for (int i = 0; i < s.length(); i++) {
            prev = t.indexOf(s.charAt(i), prev);
            if(prev < 0)return false;
            prev++;
        }
        return true;
    }
}
