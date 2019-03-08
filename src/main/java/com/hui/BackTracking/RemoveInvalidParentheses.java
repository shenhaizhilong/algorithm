package com.hui.BackTracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/27 12:16
 */
public class RemoveInvalidParentheses {


    /**
     *
     *
     *
     *301. Remove Invalid Parentheses
     * DescriptionHintsSubmissionsDiscussSolution
     * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
     *
     * Note: The input string may contain letters other than the parentheses ( and ).
     *
     * Example 1:
     *
     * Input: "()())()"
     * Output: ["()()()", "(())()"]
     * Example 2:
     *
     * Input: "(a)())()"
     * Output: ["(a)()()", "(a())()"]
     * Example 3:
     *
     * Input: ")("
     * Output: [""]
     *
     *
     *
     *https://leetcode.com/problems/remove-invalid-parentheses/discuss/75034/Easiest-9ms-Java-Solution
     *
     * Limit max removal rmL and rmR for backtracking boundary. Otherwise it will exhaust all possible valid substrings, not shortest ones.
     * Scan from left to right, avoiding invalid strs (on the fly) by checking num of open parens.
     * If it's '(', either use it, or remove it.
     * If it's '(', either use it, or remove it.
     * Otherwise just append it.
     * Lastly set StringBuilder to the last decision point.
     * In each step, make sure:
     *
     * i does not exceed s.length().
     * Max removal rmL rmR and num of open parens are non negative.
     * De-duplicate by adding to a HashSet.
     * @param s
     * @return
     */
    public List<String> removeInvalidParentheses(String s) {
        int maxRemoveLeft =0, maxRemoveRight = 0;
        char[] data = s.toCharArray();
        Set<String> set = new HashSet<>();
        for(char c: data)
        {
            if(c == '(')
            {
                maxRemoveLeft++;
            }else if(c == ')')
            {
                if(maxRemoveLeft != 0)maxRemoveLeft--;
                else maxRemoveRight++;
            }
        }
        backTracking(data, 0, maxRemoveLeft, maxRemoveRight, 0, set, new StringBuilder());

        return new ArrayList<>(set);
    }

    private void backTracking(char[] data, int index, int maxRemoveLeft, int maxRemoveRight, int open, Set<String> set, StringBuilder sb)
    {
        if(maxRemoveLeft < 0 || maxRemoveRight < 0 || open < 0)return;
        if(index == data.length)
        {
            if(maxRemoveLeft == 0 && maxRemoveRight == 0 && open == 0)
            {
                set.add(sb.toString());
            }
            return;
        }

        char c = data[index];

        if(c == '(')
        {

            backTracking(data, index + 1, maxRemoveLeft -1, maxRemoveRight, open, set, sb);  // not use (
            backTracking(data, index + 1, maxRemoveLeft, maxRemoveRight, open + 1, set, sb.append('('));                  // use (
        }else if(c == ')')
        {
            backTracking(data, index + 1, maxRemoveLeft, maxRemoveRight - 1, open , set, sb); // not use )
            backTracking(data, index + 1, maxRemoveLeft, maxRemoveRight, open - 1, set, sb.append(')')); // use )

        }else {
            backTracking(data, index + 1, maxRemoveLeft, maxRemoveRight, open, set, sb.append(c));  // other char
        }

        sb.deleteCharAt(sb.length() -1);

    }


    public static void main(String[] args) {

        RemoveInvalidParentheses removeInvalidParentheses = new RemoveInvalidParentheses();
        System.out.println(removeInvalidParentheses.removeInvalidParentheses("(a)())()"));

    }
}
