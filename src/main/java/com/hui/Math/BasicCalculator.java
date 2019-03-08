package com.hui.Math;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/17 12:59
 */
public class BasicCalculator {

    public int calculate(String s) {
        if(s == null || s.length() == 0)return 0;
        char[] val = s.toCharArray();
        int result = 0;
        int sign = 1;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < val.length; i++) {
            char ch = val[i];
            if(ch >= '0' && ch <='9')
            {
                int sum = ch - '0';
                while (i + 1 < val.length && Character.isDigit(val[i+1]))
                {
                    sum = sum *10 + val[i +1] - '0';
                    i++;
                }
                result += sum*sign;
            }else if(ch == '+')
            {
                sign = 1;
            }else if(ch == '-')
            {
                sign = -1;
            }else if(ch == '(')
            {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            }else if( ch == ')')
            {
                result = result*stack.pop() + stack.pop();
            }
        }
        return result;
    }




    public static void main(String[] args) {

        BasicCalculator basicCalculator = new BasicCalculator();
        System.out.println(basicCalculator.calculate("(1+(4+5+2)-3)+(6+8)"));
        System.out.println(basicCalculator.calculate(" 1 + 1"));
        System.out.println(basicCalculator.calculate(" 2 - 1 + 2"));
    }
}
