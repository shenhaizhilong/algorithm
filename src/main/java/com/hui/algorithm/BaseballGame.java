package com.hui.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/1 18:39
 */
public class BaseballGame {

    /**
     *
     * 682. Baseball Game
     * DescriptionHintsSubmissionsDiscussSolution
     * You're now a baseball game point recorder.
     *
     * Given a list of strings, each string can be one of the 4 following types:
     *
     * Integer (one round's score): Directly represents the number of points you get in this round.
     * "+" (one round's score): Represents that the points you get in this round are the sum of the last two valid round's points.
     * "D" (one round's score): Represents that the points you get in this round are the doubled data of the last valid round's points.
     * "C" (an operation, which isn't a round's score): Represents the last valid round's points you get were invalid and should be removed.
     * Each round's operation is permanent and could have an impact on the round before and the round after.
     *
     * You need to return the sum of the points you could get in all the rounds.
     *
     * Example 1:
     * Input: ["5","2","C","D","+"]
     * Output: 30
     * Explanation:
     * Round 1: You could get 5 points. The sum is: 5.
     * Round 2: You could get 2 points. The sum is: 7.
     * Operation 1: The round 2's data was invalid. The sum is: 5.
     * Round 3: You could get 10 points (the round 2's data has been removed). The sum is: 15.
     * Round 4: You could get 5 + 10 = 15 points. The sum is: 30.
     * Example 2:
     * Input: ["5","-2","4","C","D","9","+","+"]
     * Output: 27
     * Explanation:
     * Round 1: You could get 5 points. The sum is: 5.
     * Round 2: You could get -2 points. The sum is: 3.
     * Round 3: You could get 4 points. The sum is: 7.
     * Operation 1: The round 3's data is invalid. The sum is: 3.
     * Round 4: You could get -4 points (the round 3's data has been removed). The sum is: -1.
     * Round 5: You could get 9 points. The sum is: 8.
     * Round 6: You could get -4 + 9 = 5 points. The sum is 13.
     * Round 7: You could get 9 + 5 = 14 points. The sum is 27.
     * Note:
     * The size of the input list will be between 1 and 1000.
     * Every integer represented in the list will be between -30000 and 30000.
     * @param ops
     * @return
     */
    public static int calPoints(String[] ops) {
        int sum = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < ops.length; i++) {
            if(ops[i].equals("C"))
            {
                if(!stack.isEmpty())
                    sum -= stack.pop();
            }else if(ops[i].equals("D"))
            {
                int pre = stack.peek();
                stack.push(2*pre);
                sum +=2*pre;
            }else if(ops[i].equals("+"))
            {
                int pre = stack.pop();
                int prepre = stack.peek();
                int v = pre + prepre;
                stack.push(pre);
                stack.push(v);
                sum +=v;
            }else {
                int v = Integer.valueOf(ops[i]);
                sum +=v;
                stack.push(v);
            }
        }

        return sum;


    }

    public static int calPoints2(String[] ops) {
        int[] score = new int[ops.length];
        int index = 1;
        score[0] = Integer.parseInt(ops[0]);
        for (int i = 1; i < ops.length; i++) {
            if (ops[i].equals("+")) {
                score[index] = score[index - 1] + score[index - 2];
                index++;
            } else if (ops[i].equals("C")) {
                index--;
                score[index] = 0;
            } else if (ops[i].equals("D")) {
                score[index] = 2 * score[index - 1];
                index++;
            } else {
                score[index] = Integer.parseInt(ops[i]);
                index++;
            }
        }
        int sum = 0;
        for (int i = 0; i < score.length; i++) {
            sum += score[i];
        }
        return sum;
    }

    public static void main(String[] args) {

        String[] str1 = {"5","2","C","D","+"};
        System.out.println(calPoints(str1));
        String[] str2 = {"5","-2","4","C","D","9","+","+"};
        System.out.println(calPoints(str2));

        int head = 0;

        head = (head - 1) & (16 - 1);
        System.out.println(head);
    }
}
