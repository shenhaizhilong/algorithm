package com.hui.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/16 20:15
 *
 *282. Expression Add Operators
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.
 *
 * Example 1:
 *
 * Input: num = "123", target = 6
 * Output: ["1+2+3", "1*2*3"]
 * Example 2:
 *
 * Input: num = "232", target = 8
 * Output: ["2*3+2", "2+3*2"]
 * Example 3:
 *
 * Input: num = "105", target = 5
 * Output: ["1*0+5","10-5"]
 * Example 4:
 *
 * Input: num = "00", target = 0
 * Output: ["0+0", "0-0", "0*0"]
 * Example 5:
 *
 * Input: num = "3456237490", target = 9191
 * Output: []
 *
 */
public class ExpressionAddOperators {

    public List<String> addOperators(String num, int target) {
        if(num ==  null || num.length() == 0)return new ArrayList<>();
        List<String> ans = new ArrayList<>();
        dfs(num, "", 0, target, 0, 0, ans);
        return ans;

    }

    private void dfs(String num,String expression, int idx,int target, long value, long mult, List<String> ans)
    {
        if(idx == num.length())
        {
            if(target == value)
            {
                ans.add(expression);
            }
            return;
        }

        for (int i = idx; i < num.length(); i++) {
            if(i != idx && num.charAt(idx) == '0')break; // invalid sequence like: 00,03 , 033, etc;
            long curr = Long.valueOf(num.substring(idx, i +1));
            if(idx == 0)
            {
                dfs(num, expression + curr, i +1,target, value + curr, curr, ans);
            }else {
                dfs(num, expression + "+" + curr, i +1, target, value + curr, curr, ans);
                dfs(num, expression + "-" + curr, i +1, target, value - curr, -curr, ans);
                dfs(num, expression + "*" + curr, i +1, target, value - mult + mult*curr, mult*curr, ans);
            }
        }
    }

    public static void main(String[] args) {

    }
}
