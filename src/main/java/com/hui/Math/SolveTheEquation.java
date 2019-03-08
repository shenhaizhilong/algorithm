package com.hui.Math;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/16 23:35
 */
public class SolveTheEquation {

    /**
     *
     * 640. Solve the Equation
     * DescriptionHintsSubmissionsDiscussSolution
     * Solve a given equation and return the value of x in the form of string "x=#value". The equation contains only '+', '-' operation, the variable x and its coefficient.
     *
     * If there is no solution for the equation, return "No solution".
     *
     * If there are infinite solutions for the equation, return "Infinite solutions".
     *
     * If there is exactly one solution for the equation, we ensure that the value of x is an integer.
     *
     * Example 1:
     * Input: "x+5-3+x=6+x-2"
     * Output: "x=2"
     * Example 2:
     * Input: "x=x"
     * Output: "Infinite solutions"
     * Example 3:
     * Input: "2x=x"
     * Output: "x=0"
     * Example 4:
     * Input: "2x+3x-6x=x+2"
     * Output: "x=-1"
     * Example 5:
     * Input: "x=x+2"
     * Output: "No solution"
     * @param equation
     * @return
     */
    public String solveEquation(String equation) {
        String[] expressions = equation.split("=");
        int[] left = solve(expressions[0]); // left part
        int[] right = solve(expressions[1]);// right part.
        left[0] -= right[0];
        left[1] = right[1] - left[1];
        if(left[0] == 0 && left[1] == 0)return "Infinite solutions";
        if(left[0] == 0)return "No solution";
        return "x=" + left[1]/left[0];
    }

    private int[] solve(String expression)
    {
       // String[] tokens = expression.replace("+","#+").replace("-","#-").split("#");
        String[] tokens = expression.split("(?=[+-])");
        int[] res = new int[2]; // int[0] use to store x's coefficient, int[1] use to store the const;
        for(String token: tokens)
        {
            //if(token.isEmpty())continue;
            if(token.equals("x")|| token.equals("+x"))
            {
                res[0]++;
            }else if(token.equals("-x"))
            {
                res[0]--;
            }else if(token.contains("x"))
            {
                res[0] += Integer.valueOf(token.substring(0, token.indexOf("x")));
            }else {
                res[1] += Integer.valueOf(token);
            }

        }

        return res;
    }

    public static void main(String[] args) {

        SolveTheEquation solveTheEquation = new SolveTheEquation();
       // System.out.println(solveTheEquation.solveEquation("x+5-3+x=6+x-2"));
        System.out.println(solveTheEquation.solveEquation("-x=-1"));
    }
}
