package com.hui.Queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/8 11:07
 *
 *
 * 856. Score of Parentheses
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a balanced parentheses string S, compute the score of the string based on the following rule:
 *
 * () has score 1
 * AB has score A + B, where A and B are balanced parentheses strings.
 * (A) has score 2 * A, where A is a balanced parentheses string.
 *
 *
 * Example 1:
 *
 * Input: "()"
 * Output: 1
 * Example 2:
 *
 * Input: "(())"
 * Output: 2
 * Example 3:
 *
 * Input: "()()"
 * Output: 2
 * Example 4:
 *
 * Input: "(()(()))"
 * Output: 6
 *
 *
 * Note:
 *
 * S is a balanced parentheses string, containing only ( and ).
 * 2 <= S.length <= 50
 *
 *
 */
public class ScoreofParentheses {

    public int scoreOfParentheses(String S) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);  // the score of current position
        for (char c: S.toCharArray())
        {
            if(c == '(')
            {
                stack.push(0);
            }else {
                int t = stack.pop();
                int prev = stack.pop(); // prev score
                stack.push(prev + Math.max(1, 2*t));
            }
        }

        return stack.pop();
    }


    public int scoreOfParentheses2(String S) {
        int[] stack = new int[32];
        int i = 0;
        for (char c: S.toCharArray())
        {
            if(c == '(')
            {
                stack[++i] = 0;
            }else {
                int t = stack[i--];
                int prev = stack[i--]; // prev score
                stack[++i] = prev + Math.max(1,2*t);
            }
        }

        return stack[0];
    }

    public int scoreOfParentheses3(String S) {
       int layer = 0;
       int ans = 0;
       char[] vals = S.toCharArray();
        for (int i = 0; i < vals.length; i++) {
            layer = vals[i] == '('? layer+1: layer -1;
            if(vals[i] == '(' && vals[i +1] == ')')
            {
                ans += 1<<(layer -1);
            }

        }

        return ans;
    }

    public static void main(String[] args) {

        ScoreofParentheses scoreofParentheses = new ScoreofParentheses();
        System.out.println(scoreofParentheses.scoreOfParentheses2("()"));
        System.out.println(scoreofParentheses.scoreOfParentheses2("(())"));
        System.out.println(scoreofParentheses.scoreOfParentheses2("(()(()))"));

        System.out.println(scoreofParentheses.scoreOfParentheses3("()"));
        System.out.println(scoreofParentheses.scoreOfParentheses3("(())"));
        System.out.println(scoreofParentheses.scoreOfParentheses3("(()(()))"));


    }


}
