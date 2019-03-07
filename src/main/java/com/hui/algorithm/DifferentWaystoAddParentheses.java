package com.hui.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/16 19:45
 *
 *
 * 241. Different Ways to Add Parentheses
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.
 *
 * Example 1:
 *
 * Input: "2-1-1"
 * Output: [0, 2]
 * Explanation:
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * Example 2:
 *
 * Input: "2*3-4*5"
 * Output: [-34, -14, -10, -10, 10]
 * Explanation:
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 *
 */
public class DifferentWaystoAddParentheses {

    public List<Integer> diffWaysToCompute(String input) {
            if(input == null || input.length() == 0)return new ArrayList<>();
            return dfs(input,new HashMap<>());
    }

    private List<Integer> dfs(String input, Map<String,List<Integer>> memo)
    {
        if(memo.containsKey(input))return memo.get(input);
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if(c == '*' || c == '-' || c == '+')
            {
                String left = input.substring(0,i);
                String right = input.substring(i +1, input.length());
                List<Integer> leftSide = dfs(left, memo);
                List<Integer> rightSide = dfs(right, memo);
                for(int L: leftSide)
                {
                    for(int R : rightSide)
                    {
                        switch (c)
                        {
                            case '+':
                                ans.add(L +R);
                                break;
                            case '-':
                                ans.add(L - R);
                                break;
                            default:
                                ans.add(L*R);
                                break;
                        }
                    }
                }
            }
        }

        if(ans.size() == 0)
        {
            ans.add(Integer.valueOf(input));
        }
        memo.put(input,ans);
        return ans;
    }

    public static void main(String[] args) {

        DifferentWaystoAddParentheses differentWaystoAddParentheses = new DifferentWaystoAddParentheses();
        System.out.println(differentWaystoAddParentheses.diffWaysToCompute("2*3-4*5"));
        System.out.println(differentWaystoAddParentheses.diffWaysToCompute("2-1-1"));
    }
}
