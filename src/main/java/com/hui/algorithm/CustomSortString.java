package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/5 11:16
 */
public class CustomSortString {


    /**
     *
     * 91. Custom Sort String
     * DescriptionHintsSubmissionsDiscussSolution
     * S and T are strings composed of lowercase letters. In S, no letter occurs more than once.
     *
     * S was sorted in some custom order previously. We want to permute the characters of T so that they match the order that S was sorted. More specifically, if x occurs before y in S, then x should occur before y in the returned string.
     *
     * Return any permutation of T (as a string) that satisfies this property.
     *
     * Example :
     * Input:
     * S = "cba"
     * T = "abcd"
     * Output: "cbad"
     * Explanation:
     * "a", "b", "c" appear in S, so the order of "a", "b", "c" should be "c", "b", and "a".
     * Since "d" does not appear in S, it can be at any position in T. "dcba", "cdba", "cbda" are also valid outputs.
     *
     *
     * Note:
     *
     * S has length at most 26, and no character is repeated in S.
     * T has length at most 200.
     * S and T consist of lowercase letters only.
     *
     * @param S
     * @param T
     * @return
     */
    public static String customSortString(String S, String T) {
        StringBuilder sb = new StringBuilder(T.length());
        int[] dict = new int[26];
        for (int i = 0; i < S.length(); i++) {
            int v = S.charAt(i) -'a';
            dict[i] = v;
        }

        int[] counter = new int[26];
        for(char c:T.toCharArray())
        {
            counter[c -'a']++;
        }

        for (int i = 0; i < dict.length; i++) {
            int index = dict[i];
            while (counter[index] >0)
            {
                sb.append((char) (index + 'a'));
                counter[index]--;
            }
        }

        for (int i = 0; i < counter.length; i++) {

            while (counter[i] >0)
            {
                sb.append((char) (i + 'a'));
                counter[i]--;
            }
        }
        return sb.toString();

    }


    /**
     *
     * 别人的解法
     * @param S
     * @param T
     * @return
     */
    public String customSortString2(String S, String T) {
        int[] count = new int[26];
        for (char c : T.toCharArray()) { ++count[c - 'a']; }  // count each char in T.
        StringBuilder sb = new StringBuilder();
        for (char c : S.toCharArray()) {
            while (count[c - 'a']-- > 0) { sb.append(c); }    // sort chars both in T and S by the order of S.
        }
        for (char c = 'a'; c <= 'z'; ++c) {
            while (count[c - 'a']-- > 0) { sb.append(c); }   // group chars in T but not in S.
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        System.out.println(customSortString("cba","abcd"));
    }


}
