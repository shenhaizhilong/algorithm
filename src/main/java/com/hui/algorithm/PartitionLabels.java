package com.hui.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/7 13:24
 *
 * 763. Partition Labels
 * DescriptionHintsSubmissionsDiscussSolution
 * A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.
 *
 * Example 1:
 * Input: S = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 * Note:
 *
 * S will have length in range [1, 500].
 * S will consist of lowercase letters ('a' to 'z') only.
 *
 * --------------
 *
 * the use of end index
 *
 */
public class PartitionLabels {


    public List<Integer> partitionLabels(String S) {
        if(S == null || S.length() < 1)return new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        int N = S.length();
        int[] endIndex = new int[26]; // remember the last index of char x which in [a,z]
        for (int i = 0; i < S.length(); i++) {
            int idx = S.charAt(i) - 'a';
            endIndex[idx] = i;
        }

        //           start          end
        //           |               |
        // string    a b a b c b a c a   #d e   f   e  g  d  e    # h  i  j  h  k  l  i  j
        // index     0 1 2 3 4 5 6 7 8   #9 10  11  12 13 14 15   # 16 17 18 19 20 21 22 23
        int end = 0;
        int start = 0;
        for (int i = 0; i < N; i++) {
            int idx = S.charAt(i) - 'a';
            end  = Math.max(end, endIndex[idx]);
            if(i == end)
            {
                list.add(end - start + 1);
                start = end + 1;
            }
        }

        return list;
    }

    public static void main(String[] args) {

        PartitionLabels partitionLabels = new PartitionLabels();
        System.out.println(partitionLabels.partitionLabels("ababcbacadefegdehijhklij"));
    }

}
