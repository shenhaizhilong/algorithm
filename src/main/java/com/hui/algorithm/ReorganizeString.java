package com.hui.algorithm;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/13 13:07
 *
 * 767. Reorganize String
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
 *
 * If possible, output any possible result.  If not possible, return the empty string.
 *
 * Example 1:
 *
 * Input: S = "aab"
 * Output: "aba"
 * Example 2:
 *
 * Input: S = "aaab"
 * Output: ""
 * Note:
 *
 * S will consist of lowercase letters and have length in range [1, 500].
 *
 *
 *
 */
public class ReorganizeString {

    public String reorganizeString(String S) {
        if(S == null || S.isEmpty())return S;
        char[] value = S.toCharArray();
        StringBuilder sb = new StringBuilder(S.length());
        int[] counter = new int[26];
        for(char c: value)
        {
            counter[c - 'a']++;
        }

        // int[] item, item[0] is char, item[1] is the freq, order by frequency
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return  o2[1] - o1[1];
            }
        });

        for (int i = 0; i < 26; i++) {
            if(counter[i] != 0)
            {
                if(counter[i] > (S.length() +1)/2)return "";
                maxHeap.offer(new int[]{i,counter[i]});
            }
        }

        int[] prev = {-1,0};
        while (!maxHeap.isEmpty())
        {
            int[] curr = maxHeap.poll();
            if(prev[1] > 0)
            {
                maxHeap.offer(new int[]{prev[0],prev[1]});
            }
            // use this char
            sb.append((char) (curr[0] + 'a'));
            curr[1]--;
            if(maxHeap.isEmpty() && curr[1] != 0)
            {
                return "";
            }
            prev = curr;
        }

        return sb.toString();

    }

    public static void main(String[] args) {
        ReorganizeString reorganizeString = new ReorganizeString();
        System.out.println(reorganizeString.reorganizeString("aaab"));
        System.out.println(reorganizeString.reorganizeString("aab"));
    }

}
