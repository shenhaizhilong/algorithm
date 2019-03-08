package com.hui.String;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/3 13:55
 */
public class EvaluateReversePolishNotation {


    /**
     *
     * 150. Evaluate Reverse Polish Notation
     * DescriptionHintsSubmissionsDiscussSolution
     * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
     *
     * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
     *
     * Note:
     *
     * Division between two integers should truncate toward zero.
     * The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
     * Example 1:
     *
     * Input: ["2", "1", "+", "3", "*"]
     * Output: 9
     * Explanation: ((2 + 1) * 3) = 9
     * Example 2:
     *
     * Input: ["4", "13", "5", "/", "+"]
     * Output: 6
     * Explanation: (4 + (13 / 5)) = 6
     * Example 3:
     *
     * Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
     * Output: 22
     * Explanation:
     *   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
     * = ((10 * (6 / (12 * -11))) + 17) + 5
     * = ((10 * (6 / -132)) + 17) + 5
     * = ((10 * 0) + 17) + 5
     * = (0 + 17) + 5
     * = 17 + 5
     * = 22
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        if(tokens == null || tokens.length == 0)return 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
          if(token.equals("+"))
          {
              int b = stack.pop();
              int a = stack.pop();
              stack.push(a + b);
          }else if(token.equals("-"))
          {
              int b = stack.pop();
              int a = stack.pop();
              stack.push(a - b);
          } else if(token.equals("*"))
          {
              int b = stack.pop();
              int a = stack.pop();
              stack.push(a * b);
          } else if(token.equals("/"))
          {
              int b = stack.pop();
              int a = stack.pop();
              stack.push(a / b);
          }else {
              stack.push(Integer.parseInt(token));
          }
        }

        return stack.pollFirst();
    }

    public static void main(String[] args) {
        EvaluateReversePolishNotation evaluateReversePolishNotation = new EvaluateReversePolishNotation();
        System.out.println(evaluateReversePolishNotation.evalRPN(new String[] {"4", "13", "5", "/", "+"}));
        System.out.println(evaluateReversePolishNotation.evalRPN(new String[] {"2", "1", "+", "3", "*"}));
        System.out.println(evaluateReversePolishNotation.evalRPN(new String[] {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));

    }
}
