package com.hui.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 22. Generate Parentheses
 * DescriptionHintsSubmissionsDiscussSolution
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * For example, given n = 3, a solution set is:
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 * @author: shenhaizhilong
 * @date: 2018/9/10 18:31
 */
public class GenerateParentheses {


    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backTracking(res,new StringBuilder(), 0,0,n);
        return res;

    }

    private void backTracking(List<String> res, StringBuilder sb, int left, int right, int max)
    {
        if(sb.length() == max*2) {
            res.add(sb.toString());
            return;
        }


        if(left < max)
        {
            sb.append('(');
            backTracking(res,sb,left+1,right,max);
            sb.deleteCharAt(sb.length() -1);

        }
        if(right < left)
        {
            sb.append(')');
            backTracking(res,sb,left,right +1, max);
            sb.deleteCharAt(sb.length() -1);
        }


    }

    public static void main(String[] args) {


        GenerateParentheses generateParentheses = new GenerateParentheses();
        List<String> res = generateParentheses.generateParenthesis(3);
        System.out.println(res);
    }
}
