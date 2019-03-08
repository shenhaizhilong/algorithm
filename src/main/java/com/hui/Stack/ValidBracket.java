package com.hui.Stack;

import java.util.LinkedList;

/**
 *
 * https://leetcode-cn.com/problems/valid-parentheses/description/
 *有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 *
 * 输入: "()"
 * 输出: true
 * 示例 2:
 *
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 *
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 *
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 *
 * 输入: "{[]}"
 * 输出: true
 *
 * @author: shenhaizhilong
 * @date: 2018/7/9 18:18
 */
public class ValidBracket {
    public boolean isValid(String str)
    {
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(c == '{' || c== '[' || c== '(')
            {
                stack.push(c);
                continue;
            }

            if(stack.size()>0)
            {
                Character left = stack.pop();
                if(left == null)return false;

                char cc = left.charValue();
                if(c == '}' && cc !='{')
                {
                    return false;
                } else if(c == ']' && cc !='[')
                {
                    return false;
                }
                else if(c == ')'&& (cc!= '('))
                {
                    return false;
                }
            }else {
                return false;
            }


        }




        return stack.isEmpty();
    }
}
