package com.hui.TwoPointer;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/20 17:02
 *
 *
 * 942. DI String Match
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a string S that only contains "I" (increase) or "D" (decrease), let N = S.length.
 *
 * Return any permutation A of [0, 1, ..., N] such that for all i = 0, ..., N-1:
 *
 * If S[i] == "I", then A[i] < A[i+1]
 * If S[i] == "D", then A[i] > A[i+1]
 *
 *
 * Example 1:
 *
 * Input: "IDID"
 * Output: [0,4,1,3,2]
 * Example 2:
 *
 * Input: "III"
 * Output: [0,1,2,3]
 * Example 3:
 *
 * Input: "DDI"
 * Output: [3,2,0,1]
 *
 *
 * Note:
 *
 * 1 <= S.length <= 10000
 * S only contains characters "I" or "D".
 *
 *
 *
 */
public class DIStringMatch {

    public int[] diStringMatch(String S) {
        int N = S.length();
        int left = 0;
        int right = N;
        int[] res = new int[N +1];
        for (int i = 0; i < S.length(); i++) {
            if(S.charAt(i) == 'I')
            {
                res[i] = left++;
            }else {
                res[i] = right--;
            }
        }
        res[N] = left;
        return res;
    }

    public static void main(String[] args) {

        DIStringMatch diStringMatch = new DIStringMatch();
        System.out.println(Arrays.toString(diStringMatch.diStringMatch("IDID")));
        System.out.println(Arrays.toString(diStringMatch.diStringMatch("III")));
        System.out.println(Arrays.toString(diStringMatch.diStringMatch("DDI")));
    }

}
