package com.hui.algorithm;

import java.util.Arrays;

/**
 *https://leetcode.com/problems/shortest-distance-to-a-character/description/
 *
 * 821. Shortest Distance to a Character
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a string S and a character C, return an array of integers representing the shortest distance from the character C in the string.
 *
 * Example 1:
 *
 * Input: S = "loveleetcode", C = 'e'
 * Output: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]
 *
 *
 * Note:
 *
 * S string length is in [1, 10000].
 * C is a single character, and guaranteed to be in string S.
 * All letters in S and C are lowercase.
 * @author: shenhaizhilong
 * @date: 2018/8/26 9:22
 */
public class ShortestDistancetoaCharacter {
    public int[] shortestToChar(String S, char C) {
        int[] res = new int[S.length()];

        int last = Integer.MAX_VALUE;
        int next = S.indexOf(C);
        int current = 0;
        int diff;
        while (current < S.length())
        {
            diff = Math.min(Math.abs(current - last), Math.abs(next - current));
            res[current] = diff;
            if(current == next)
            {
                last = next;
                next = S.indexOf(C,current + 1);
                if(next == -1)
                {
                    next = Integer.MAX_VALUE;
                }
            }
            current++;

        }

        return res;
    }

    public static void main(String[] args) {
        String s = "loveleetcode";
        ShortestDistancetoaCharacter distancetoaCharacter = new ShortestDistancetoaCharacter();
        int[] res = distancetoaCharacter.shortestToChar(s,'l');
        System.out.println(Arrays.toString(res));
    }
}
