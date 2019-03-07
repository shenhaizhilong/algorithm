package com.hui.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/4 22:12
 */
public class PositionsofLargeGroups {

    /**
     *
     * In a string S of lowercase letters, these letters form consecutive groups of the same character.
     *
     * For example, a string like S = "abbxxxxzyy" has the groups "a", "bb", "xxxx", "z" and "yy".
     *
     * Call a group large if it has 3 or more characters.  We would like the starting and ending positions of every large group.
     *
     * The final answer should be in lexicographic order.
     *
     *
     *
     * Example 1:
     *
     * Input: "abbxxxxzzy"
     * Output: [[3,6]]
     * Explanation: "xxxx" is the single large group with starting  3 and ending positions 6.
     * Example 2:
     *
     * Input: "abc"
     * Output: []
     * Explanation: We have "a","b" and "c" but no large group.
     * Example 3:
     *
     * Input: "abcdddeeeeaabbbcd"
     * Output: [[3,5],[6,9],[12,14]]
     *
     *
     * Note:  1 <= S.length <= 1000
     *
     * @param S
     * @return
     */
    public static List<List<Integer>> largeGroupPositions(String S) {
        List<List<Integer>> res = new ArrayList<>();
        int position = 0;
        int largest = 3;
        int i = 1;
        for (; i <= S.length(); i++) {
            if( i== S.length() || S.charAt(i) != S.charAt(i -1))
            {
                int len = i  - position;
                if (len >= largest)
                {
                    List<Integer> tempList = new ArrayList<>(2);
                    tempList.add(position);
                    tempList.add(i -1);
                    res.add(tempList);

                }
                position = i;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        List<List<Integer>> res = largeGroupPositions("abc");
        print(res);
        res = largeGroupPositions("abbxxxxzzy");
        print(res);
        res = largeGroupPositions("abcdddeeeeaabbbcdffff");
        print(res);
    }

    public static void print(List<List<Integer>> lists)
    {
        for(List<Integer> list: lists)
        {
            System.out.println(list);
        }
    }

}
