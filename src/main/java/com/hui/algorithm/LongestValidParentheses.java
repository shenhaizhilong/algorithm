package com.hui.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/27 19:18
 */
public class LongestValidParentheses {

    /**
     *
     * 32. Longest Valid Parentheses
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
     *
     * Example 1:
     *
     * Input: "(()"
     * Output: 2
     * Explanation: The longest valid parentheses substring is "()"
     * Example 2:
     *
     * Input: ")()())"
     * Output: 4
     * Explanation: The longest valid parentheses substring is "()()"
     * @param s
     * @return
     */


    public int longestValidParentheses(String s) {

        if(s == null || s.length() < 1)return 0;
        int[] max = new int[]{0,0};
        for (int i = 0; i < s.length(); ) {
            int id = max[1];
            if(s.charAt(i) == '(')
            {
                dfs(s, i, max, 0, 0, 0);
            }

            if(id != max[1])
            {
                i = max[1];
            }else {
                i++;
            }
        }
        return max[0];
    }

    private void dfs(String s, int id, int[] max,int left, int right, int open )
    {
        if(id > s.length() || open < 0 || right > left)return;
        if(open == 0 && right == left && 2*right > max[0])
        {
            max[0] = 2*right;
            max[1] = id;
        }

        if (id == s.length())return;

        char c = s.charAt(id);
        if(c == '(')
        {
            dfs(s, id + 1, max, left + 1, right, open + 1);
        }else{
            dfs(s, id + 1, max, left , right + 1, open - 1);
        }


    }

    public int longestValidParentheses2(String s) {
        int left = 0, right = 0, maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(')
            {
                left++;
            }else {
                right++;
            }
            if(left == right)
            {
                maxLen = Math.max(maxLen, 2*left);
            }else if(right > left)
            {
                left = 0;
                right = 0;
            }
        }

        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxLen = Math.max(maxLen, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return maxLen;

    }


    public int longestValidParentheses3(String s) {
        int maxLen = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(')
            {
                stack.push(i);
            }else {
                stack.pop();
                if(stack.isEmpty())
                {
                    stack.push(i);
                }else {
                    maxLen = Math.max(maxLen, i - stack.peekFirst());
                }
            }
        }

        return maxLen;
    }
    public static void main(String[] args) {
        LongestValidParentheses longestValidParentheses = new LongestValidParentheses();
        System.out.println(longestValidParentheses.longestValidParentheses2(")()())"));
        System.out.println(longestValidParentheses.longestValidParentheses2(")()"));
        System.out.println(longestValidParentheses.longestValidParentheses2("()"));
        System.out.println(longestValidParentheses.longestValidParentheses2("()(()"));
        System.out.println(longestValidParentheses.longestValidParentheses2(")(()(()(((())(((((()()))((((()()(()()())())())()))()()()())(())()()(((()))))()((()))(((())()((()()())((())))(())))())((()())()()((()((())))))((()(((((()((()))(()()(())))((()))()))())"));
        System.out.println(longestValidParentheses.longestValidParentheses3(")()())"));
        System.out.println(longestValidParentheses.longestValidParentheses3(")()"));
        System.out.println(longestValidParentheses.longestValidParentheses3("()"));
        System.out.println(longestValidParentheses.longestValidParentheses3("()(()"));
        System.out.println(longestValidParentheses.longestValidParentheses3(")(()(()(((())(((((()()))((((()()(()()())())())()))()()()())(())()()(((()))))()((()))(((())()((()()())((())))(())))())((()())()()((()((())))))((()(((((()((()))(()()(())))((()))()))())"));

    }
}
