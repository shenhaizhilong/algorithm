package com.hui.HashMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/30 0:22
 *
 *
 * 854. K-Similar Strings
 * DescriptionHintsSubmissionsDiscussSolution
 * Strings A and B are K-similar (for some non-negative integer K) if we can swap the positions of two letters in A exactly K times so that the resulting string equals B.
 *
 * Given two anagrams A and B, return the smallest K for which A and B are K-similar.
 *
 * Example 1:
 *
 * Input: A = "ab", B = "ba"
 * Output: 1
 * Example 2:
 *
 * Input: A = "abc", B = "bca"
 * Output: 2
 * Example 3:
 *
 * Input: A = "abac", B = "baca"
 * Output: 2
 * Example 4:
 *
 * Input: A = "aabc", B = "abca"
 * Output: 2
 * Note:
 *
 * 1 <= A.length == B.length <= 20
 * A and B contain only lowercase letters from the set {'a', 'b', 'c', 'd', 'e', 'f'}
 *
 */
public class KSimilarStrings {

    public int kSimilarity(String A, String B) {

        Map<String,Integer> memo = new HashMap<>();
        return dfs(A.toCharArray(), B.toCharArray(), 0, memo);
    }

    private int dfs(char[] A, char[] B, int start, Map<String,Integer> memo)
    {
        boolean same = true;
        for (int i = start; i < B.length; i++) {
            if(A[i] != B[i])
            {
                same = false;
                start = i;
                break;
            }
        }
        if(same)return 0;

        // unmatch
        String unmatch = String.valueOf(A,start, B.length - start);
        if(memo.containsKey(unmatch))return memo.get(unmatch);
        int min = Integer.MAX_VALUE;
        for (int j = start +1; j < B.length; j++) {
            // find all candidates position where A[j] == B[start]
            // aabda
            // adb
            if(A[j] == B[start] && A[j] != B[j])
            {
                // swap  A[j] and A[start]
                swap(A,start, j);
                int next = dfs(A,B, start +1, memo);
                min = Math.min(next +1, min);
                // change it back
                swap(A,start,j);
                // reversed pair
                // A == abcd
                // B == dcba
                //   https://leetcode.com/problems/k-similar-strings/discuss/140871/java-+-backtracking-+-10ms
                if(min == 1 || A[start] == B[j])break;
            }
        }
        memo.put(unmatch, min);
        return min;


    }

    private void swap(char[] vals, int i, int j)
    {
        char t = vals[i];
        vals[i] = vals[j];
        vals[j] = t;
    }


    public static void main(String[] args) {

        KSimilarStrings kSimilarStrings = new KSimilarStrings();
        System.out.println(kSimilarStrings.kSimilarity("aabc","abca"));
        System.out.println(kSimilarStrings.kSimilarity("abac","baca"));
        System.out.println(kSimilarStrings.kSimilarity("ab","ba"));
    }
}
