package com.hui.String;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/10 17:47
 *
 *833. Find And Replace in String
 * DescriptionHintsSubmissionsDiscussSolution
 * To some string S, we will perform some replacement operations that replace groups of letters with new ones (not necessarily the same size).
 *
 * Each replacement operation has 3 parameters: a starting index i, a source word x and a target word y.  The rule is that if x starts at position i in the original string S, then we will replace that occurrence of x with y.  If not, we do nothing.
 *
 * For example, if we have S = "abcd" and we have some replacement operation i = 2, x = "cd", y = "ffff", then because "cd" starts at position 2 in the original string S, we will replace it with "ffff".
 *
 * Using another example on S = "abcd", if we have both the replacement operation i = 0, x = "ab", y = "eee", as well as another replacement operation i = 2, x = "ec", y = "ffff", this second operation does nothing because in the original string S[2] = 'c', which doesn't match x[0] = 'e'.
 *
 * All these operations occur simultaneously.  It's guaranteed that there won't be any overlap in replacement: for example, S = "abc", indexes = [0, 1], sources = ["ab","bc"] is not a valid test case.
 *
 * Example 1:
 *
 * Input: S = "abcd", indexes = [0,2], sources = ["a","cd"], targets = ["eee","ffff"]
 * Output: "eeebffff"
 * Explanation: "a" starts at index 0 in S, so it's replaced by "eee".
 * "cd" starts at index 2 in S, so it's replaced by "ffff".
 * Example 2:
 *
 * Input: S = "abcd", indexes = [0,2], sources = ["ab","ec"], targets = ["eee","ffff"]
 * Output: "eeecd"
 * Explanation: "ab" starts at index 0 in S, so it's replaced by "eee".
 * "ec" doesn't starts at index 2 in the original S, so we do nothing.
 * Notes:
 *
 * 0 <= indexes.length = sources.length = targets.length <= 100
 * 0 < indexes[i] < S.length <= 1000
 * All characters in given inputs are lowercase letters.
 *
 *
 */
public class FindAndReplaceinString {

    public String findReplaceString1(String S, int[] indexes, String[] sources, String[] targets) {
        if(indexes == null || indexes.length == 0)return S;
        StringBuilder sb = new StringBuilder();
        int sLen = S.length();
        int[] map = new int[sLen];
        Arrays.fill(map, -1);
        for (int i = 0; i < indexes.length; i++) {
            map[indexes[i]] = i;
        }
        int startIdx = 0;

        while (startIdx < sLen)
        {
            if(map[startIdx] != -1)
            {
                int sourceIdx = map[startIdx];
                if(checkSame(S, startIdx, sources,sourceIdx))
                {
                    sb.append(targets[sourceIdx]);
                    startIdx += sources[sourceIdx].length();
                }else {
                    sb.append(S.charAt(startIdx));
                    startIdx++;
                }


            }else {
                sb.append(S.charAt(startIdx));
                startIdx++;
            }
        }

        return sb.toString();

    }



    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {

        if(indexes == null || indexes.length == 0)return S;
        StringBuilder sb = new StringBuilder();
        int sLen = S.length();
        int[] map = new int[sLen];
        Arrays.fill(map, -1);
        for (int i = 0; i < indexes.length; i++) {
            map[indexes[i]] = i;  // remember it's index to find the source Index
        }
        int startIdx = 0;

        while (startIdx < sLen)
        {
            if(map[startIdx] != -1)
            {
                int sourceIdx = map[startIdx];
                if(checkSame(S, startIdx, sources,sourceIdx))
                {
                    sb.append(targets[sourceIdx]);
                    startIdx += sources[sourceIdx].length();
                    continue;
                }

            }
            sb.append(S.charAt(startIdx));
            startIdx++;

        }

        return sb.toString();


    }

    private boolean checkSame(String S, int startIdx, String[] sources, int sourceIdx)
    {
        String word =sources[sourceIdx];
        for (int i = 0; i < word.length(); i++) {
            if(startIdx < S.length() && S.charAt(startIdx) != word.charAt(i))return false;
            startIdx++;
        }
        return true;
    }

    public static void main(String[] args) {


        FindAndReplaceinString findAndReplaceinString = new FindAndReplaceinString();
        String S = "abcd";
        int[] indexes = new int[]{0,2};
        String[] sources = new String[]{"a","cd"};
        String[] targets =   new String[]{"eee","ffff"};
        System.out.println(findAndReplaceinString.findReplaceString(S, indexes, sources, targets));

        indexes = new int[]{0,2};
        sources = new String[]{"ab","ec"};
        targets = new String[]{"eee","ffff"};
        System.out.println(findAndReplaceinString.findReplaceString(S, indexes, sources, targets));

       String ss = "vmokgggqzp";
        indexes = new int[]{3,5,1};
        sources = new String[]{"kg","ggq","mo"};
        targets = new String[]{"s","so","bfr"};
        System.out.println(findAndReplaceinString.findReplaceString(ss, indexes, sources, targets));

    }
}
