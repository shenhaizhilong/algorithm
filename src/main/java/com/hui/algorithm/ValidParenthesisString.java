package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/24 15:37
 *
 *678. Valid Parenthesis String
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid. We define the validity of a string by these rules:
 *
 * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 * Any right parenthesis ')' must have a corresponding left parenthesis '('.
 * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 * '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
 * An empty string is also valid.
 * Example 1:
 * Input: "()"
 * Output: True
 * Example 2:
 * Input: "(*)"
 * Output: True
 * Example 3:
 * Input: "(*))"
 * Output: True
 * Note:
 * The string size will be in the range [1, 100].
 *
 */
public class ValidParenthesisString {

    public boolean checkValidString(String s) {
        if(s == null || s.length() < 1)return true;
        char[] chars = s.toCharArray();
        return dfs2(chars,0, 0);
    }

    private boolean dfs(char[] chars, int idx, int diff)
    {
        if(diff < 0)return false;
        if(idx >= chars.length)return   diff == 0;
        if(chars[idx] == '(')
        {
            return dfs(chars, idx +1, diff +1);
        }else if(chars[idx] == ')')
        {
            return dfs(chars, idx +1, diff -1);
        }else {
            return dfs(chars, idx +1, diff) || dfs(chars, idx +1, diff +1) || dfs(chars, idx +1, diff -1);
        }
    }

    private boolean dfs2(char[] chars, int idx, int diff)
    {
        if(diff < 0)return false;
        if(idx >= chars.length)return   diff == 0;
        if(chars[idx] == '(')
        {
            while (idx < chars.length && chars[idx] == '(')
            {
                idx++;
                diff++;
            }
            return dfs2(chars,idx, diff);
        }else if(chars[idx] == ')')
        {
            while (idx < chars.length && chars[idx] == ')')
            {
                idx++;
                diff--;
                if(diff < 0)return false;
            }
            return dfs2(chars, idx, diff);
        }else {
            return dfs2(chars, idx +1, diff) || dfs2(chars, idx +1, diff +1) || dfs2(chars, idx +1, diff -1);
        }
    }




    public boolean checkValidString2(String s) {
        if(s == null || s.length() < 1)return true;
        int left = 0;
        int right = 0;
        char[] chars = s.toCharArray();

        for(char c: chars)
        {
            if(c == '(' || c == '*')
            {
                left++;
            }else {
                left--;
            }
            if(left < 0)return false;
        }
        if(left == 0)return true;
        for (int i = chars.length -1; i >=0; i--) {
            char c = chars[i];
            if(c != '(')
            {
                right++;
            }else {
                right--;
                if(right < 0)return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        ValidParenthesisString validParenthesisString = new ValidParenthesisString();
        System.out.println(validParenthesisString.checkValidString("()"));
        System.out.println(validParenthesisString.checkValidString("(*)"));
        System.out.println(validParenthesisString.checkValidString("(*))"));
        System.out.println(validParenthesisString.checkValidString("(*)))"));



        System.out.println("*************************");
        System.out.println(validParenthesisString.checkValidString2("()"));
        System.out.println(validParenthesisString.checkValidString2("(*)"));
        System.out.println(validParenthesisString.checkValidString2("(*))"));
        System.out.println(validParenthesisString.checkValidString2("(*)))"));



    }

}
