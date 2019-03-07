package com.hui.algorithm;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/31 12:46
 *
 * 828. Unique Letter String
 * DescriptionHintsSubmissionsDiscussSolution
 * A character is unique in string S if it occurs exactly once in it.
 *
 * For example, in string S = "LETTER", the only unique characters are "L" and "R".
 *
 * Let's define UNIQ(S) as the number of unique characters in string S.
 *
 * For example, UNIQ("LETTER") =  2.
 *
 * Given a string S with only uppercases, calculate the sum of UNIQ(substring) over all non-empty substrings of S.
 *
 * If there are two or more equal substrings at different positions in S, we consider them different.
 *
 * Since the answer can be very large, return the answer modulo 10 ^ 9 + 7.
 * Example 1:
 *
 * Input: "ABC"
 * Output: 10
 * Explanation: All possible substrings are: "A","B","C","AB","BC" and "ABC".
 * Evey substring is composed with only unique letters.
 * Sum of lengths of all substring is 1 + 1 + 1 + 2 + 2 + 3 = 10
 * Example 2:
 *
 * Input: "ABA"
 * Output: 8
 * Explanation: The same as example 1, except uni("ABA") = 1.
 *
 *
 * Note: 0 <= S.length <= 10000.
 *
 *
 * dp[i] = sum(uniq(S[j,i])), j in [0,i] , i in [0, n -1], n is the length of String
 *
 * for S = ABCD, index is i, before i there is no duplicate S[i]
 * dp[0] = uniq(0) = 1
 * dp[1]  uniq(AB) + uniq(B) = 1 + 2 = dp[0] + 1 + 1
 * dp[2] = uniq(ABC) + uniq(BC) +uniq(C) = 1 + 2 + 3 = dp[1] + 2 + 1
 * dp[i] = dp[i-1] + i + 1
 *
 *  so the contribution of S[i] is ( i + 1)
 *
 *
 * for S = ABCDB, index is i, before i maybe have  duplicate S[i]
 *
 *  dp[i] = dp[i -1] - contributionOfPrev(S[i]) + contributionOfS[i]
 *
 *  contributionOfS[i] = i - prevIndex(S[i])
 *
 */
public class UniqueLetterString {

    public int uniqueLetterString(String S) {
        if(S == null || S.isEmpty())return 0;
        long Mod = 1_000_000_007;
        int N = S.length();
        int[] prevIndex = new int[26];
        int[] contributionOfS_i = new int[26];
        int dp = 0;
        Arrays.fill(prevIndex, -1);
        long ans = 0;
        for (int i = 0; i < N; i++) {
            int idx = S.charAt(i) - 'A';
            dp = dp - contributionOfS_i[idx] + i - prevIndex[idx];
            ans = (ans + dp) % Mod;
            contributionOfS_i[idx] = i - prevIndex[idx];
            prevIndex[idx] = i;
        }
        return (int) ans;
    }


    public int uniqueLetterString2(String S) {
        if(S == null || S.isEmpty())return 0;
        long Mod = 1_000_000_007;
        int N = S.length();
        int[] prevIndex = new int[26];
        int[] contributionOfS_i = new int[26];
        int dp = 0;

        long ans = 0;
        for (int i = 0; i < N; i++) {
            int idx = S.charAt(i) - 'A';
            dp = dp - contributionOfS_i[idx] + i - prevIndex[idx] +1;
            ans = (ans + dp) % Mod;
            contributionOfS_i[idx] = i - (prevIndex[idx] - 1);
            prevIndex[idx] = i + 1; // right shift 1, change index -1, 0, 1, 2, ... i, to 0,1,2,3,i + 1
        }
        return (int) ans;
    }

    public static void main(String[] args) {
        UniqueLetterString uniqueLetterString = new UniqueLetterString();
        System.out.println(uniqueLetterString.uniqueLetterString2("ABCDD"));
    }
}
