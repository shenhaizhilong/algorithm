package com.hui.DynamicPrograming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/1 16:54
 */
public class GroupsofSpecialEquivalentStrings {


    /**
     *
     * 893. Groups of Special-Equivalent Strings
     * DescriptionHintsSubmissionsDiscussSolution
     * You are given an array A of strings.
     *
     * Two strings S and T are special-equivalent if after any number of moves, S == T.
     *
     * A move consists of choosing two indices i and j with i % 2 == j % 2, and swapping S[i] with S[j].
     *
     * Now, a group of special-equivalent strings from A is a non-empty subset S of A such that any string not in S is not special-equivalent with any string in S.
     *
     * Return the number of groups of special-equivalent strings from A.
     *
     *
     *
     * Example 1:
     *
     * Input: ["a","b","c","a","c","c"]
     * Output: 3
     * Explanation: 3 groups ["a","a"], ["b"], ["c","c","c"]
     * Example 2:
     *
     * Input: ["aa","bb","ab","ba"]
     * Output: 4
     * Explanation: 4 groups ["aa"], ["bb"], ["ab"], ["ba"]
     * Example 3:
     *
     * Input: ["abc","acb","bac","bca","cab","cba"]
     * Output: 3
     * Explanation: 3 groups ["abc","cba"], ["acb","bca"], ["bac","cab"]
     * Example 4:
     *
     * Input: ["abcd","cdab","adcb","cbad"]
     * Output: 1
     * Explanation: 1 group ["abcd","cdab","adcb","cbad"]
     *
     *
     * Note:
     *
     * 1 <= A.length <= 1000
     * 1 <= A[i].length <= 20
     * All A[i] have the same length.
     * All A[i] consist of only lowercase letters.
     *
     * @param A
     * @return
     */
    public int numSpecialEquivGroups(String[] A) {
        Set<String> set = new HashSet<>();
        for(String s :A)
        {
            int[] odd = new int[26];
            int[] even = new int[26];
            for (int i = 0; i < s.length(); i++) {
                if( (i & 0x01) == 1){
                    odd[s.charAt(i) - 'a']++;
                }
                else {
                    even[s.charAt(i) -'a']++;
                }
            }
            String sig = Arrays.toString(odd) + Arrays.toString(even);
            set.add(sig);
        }
        return set.size();
    }


    /**
     *
     * Approach 1: Counting
     * Intuition and Algorithm
     *
     * Let's try to characterize a special-equivalent string SS, by finding a function \mathcal{C}C so that S≡T⟺C(S)=C(T).
     *
     * Through swapping, we can permute the even indexed letters, and the odd indexed letters. What characterizes these permutations is the count of the letters: all such permutations have the same count, and different counts have different permutations.
     *
     * Thus, the function \mathcal{C}(S) =C(S)= (the count of the even indexed letters in S, followed by the count of the odd indexed letters in S) successfully characterizes the equivalence relation.
     *
     * Afterwards, we count the number of unique \mathcal{C}(S)C(S) for S \in AS∈A.
     *
     * @param A
     * @return
     */
    private int numSpecialEquivGroups2(String[] A) {
        Set<String> seen = new HashSet();
        for (String S: A) {
            int[] count = new int[52];
            for (int i = 0; i < S.length(); ++i)
                count[S.charAt(i) - 'a' + 26 * (i &0x01)]++;
            seen.add(Arrays.toString(count));
        }
        return seen.size();
    }
}
