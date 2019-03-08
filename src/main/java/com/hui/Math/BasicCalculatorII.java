package com.hui.Math;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 *
 * 227. Basic Calculator II
 * DescriptionHintsSubmissionsDiscussSolution
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.
 *
 * Example 1:
 *
 * Input: "3+2*2"
 * Output: 7
 * Example 2:
 *
 * Input: " 3/2 "
 * Output: 1
 * Example 3:
 *
 * Input: " 3+5 / 2 "
 * Output: 5
 * Note:
 *
 * You may assume that the given expression is always valid.
 * Do not use the eval built-in library function.
 *
 * @author: shenhaizhilong
 * @date: 2018/9/17 19:11
 */
public class BasicCalculatorII {

    public int calculate(String s) {

        if(s == null || s.length() == 0)return 0;
        Deque<Integer> stack = new ArrayDeque<>();
        int result = 0;
        int num = 0;
        char op = '+';
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch >= '0' && ch <= '9')
            {
                num = num*10 + ch - '0';
            }

            if(!Character.isDigit(ch) && ch != ' '  || i == s.length() -1)
            {
                if(op == '+')
                {
                    stack.push(num);
                }else if(op == '-')
                {
                    stack.push(-num);
                }else if(op == '*')
                {
                    stack.push(stack.pop()*num);
                }else if(op == '/')
                {
                    stack.push(stack.pop()/num);
                }
                op = ch;  // reassign operator to op
                num = 0; // reset to zero
            }
        }

        for (int i :
                stack) {
            result += i;
        }

        return result;
    }


    public int calculate2(String s) {
        if(s == null || s.length() == 0)return 0;
        int res = 0;
        int num = 0;
        int prev = 0;
        char op = '+';
        for (int i = 0; i < s.length(); i++)
        {
            char ch = s.charAt(i);
            if(ch >= '0' && ch <='9')
            {
                num = num *10 + ch - '0';
            }else if(ch != ' ')
            {
                prev = calc(prev,num, op);
                if(ch == '+' || ch == '-')
                {
                    res += prev;
                    prev = 0;
                }
                num = 0;
                op   = ch;
            }

        }
        return res + calc(prev, num, op);
    }

    private int calc(int prev, int curr, char op)
    {
        switch (op)
        {
            case '-':return prev - curr;
            case '+':return prev + curr;
            case '*':return prev*curr;
            default:return prev/curr;
        }
    }
    public static void main(String[] args) {
            BasicCalculatorII basicCalculatorII = new BasicCalculatorII();
        System.out.println(basicCalculatorII.calculate2("10 + 10*11/10"));
    }
}
