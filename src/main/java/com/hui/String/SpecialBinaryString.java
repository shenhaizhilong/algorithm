package com.hui.String;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/24 16:32
 *
 *761. Special Binary String
 * DescriptionHintsSubmissionsDiscussSolution
 * Special binary strings are binary strings with the following two properties:
 *
 * The number of 0's is equal to the number of 1's.
 * Every prefix of the binary string has at least as many 1's as 0's.
 * Given a special string S, a move consists of choosing two consecutive, non-empty, special substrings of S, and swapping them. (Two strings are consecutive if the last character of the first string is exactly one index before the first character of the second string.)
 *
 * At the end of any number of moves, what is the lexicographically largest resulting string possible?
 *
 * Example 1:
 * Input: S = "11011000"
 * Output: "11100100"
 * Explanation:
 * The strings "10" [occuring at S[1]] and "1100" [at S[3]] are swapped.
 * This is the lexicographically largest string possible after some number of swaps.
 * Note:
 *
 * S has length at most 50.
 * S is guaranteed to be a special binary string as defined above.
 *
 *
 *https://leetcode.com/problems/special-binary-string/discuss/113211/Easy-and-Concise-Solution-with-Explanation-C++JavaPython
 *
 * Just 4 steps:
 *
 * Split S into several special strings (as many as possible).
 * Special string starts with 1 and ends with 0. Recursion on the middle part.
 * Sort all special strings in lexicographically largest order.
 * Join and output all strings.
 * Updated:
 *
 * The middle part of a special string may not be another special string. But in my recursion it is.
 * For example, 1M0 is a splitted special string. M is its middle part and it must be another special string.
 *
 * Because:
 *
 * The number of 0's is equal to the number of 1's in M
 * If there is a prefix P of Mwhich has one less 1's than 0's, 1P will make up a special string. 1P will be found as special string before 1M0 in my solution.
 * It means that every prefix of M has at least as many 1's as 0's.
 *
 *
 */
public class SpecialBinaryString {

    public String makeLargestSpecial(String S) {
        // think 1 as "(", 0 as ")", diff = count(1) - count(0)
        int diff = 0;
        int start = 0;

        ArrayList<String> list = new ArrayList<>();
        for (int end = 0; end < S.length(); end++) {
            if(S.charAt(end) == '1')diff++;
            else diff--;
            if(diff == 0)
            {
                list.add(new StringBuilder("1")
                        .append(makeLargestSpecial(S.substring(start +1, end)))
                        .append('0').toString());
                start = end +1;
            }
        }

        Collections.sort(list, Comparator.reverseOrder());
        StringBuilder sb = new StringBuilder();
        for(String str:list){
            sb.append(str);
        }
        return sb.toString();

    }

    public static void main(String[] args) {

        SpecialBinaryString specialBinaryString = new SpecialBinaryString();
        System.out.println(specialBinaryString.makeLargestSpecial("11011000"));
    }
}
