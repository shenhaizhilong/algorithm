package com.hui.String;


/**
 *https://leetcode.com/problems/judge-route-circle/description/
 *
 * 657. Judge Route Circle
 * DescriptionHintsSubmissionsDiscussSolution
 * Initially, there is a Robot at position (0, 0). Given a sequence of its moves, judge if this robot makes a circle, which means it moves back to the original place.
 *
 * The move sequence is represented by a string. And each move is represent by a character. The valid robot moves are R (Right), L (Left), U (Up) and D (down). The output should be true or false representing whether the robot makes a circle.
 *
 * Example 1:
 * Input: "UD"
 * Output: true
 * Example 2:
 * Input: "LL"
 * Output: false
 * @author: shenhaizhilong
 * @date: 2018/8/21 20:43
 */
public class JudgeRouteCircle {
    public static boolean judgeCircle(String moves) {

        int[] original = new int[2];
        for (int i = 0; i < moves.length(); i++) {
            char c = moves.charAt(i);
            switch (c)
            {
                case 'R':
                    original[0] +=1;
                    break;
                case 'L':
                    original[0] -=1;
                    break;
                case 'U':
                    original[1] +=1;
                    break;
                case 'D':
                    original[1] -=1;
                    break;
                    default:break;

            }

        }

        return original[0] == 0 && original[1] == 0;

    }

    /**
     * hash method
     * @param moves
     * @return
     */
    public boolean judgeCircle2(String moves) {
        int[] cnt = new int[26];
        for(char c:moves.toCharArray()){
            cnt[c-'A']++;
        }
        return cnt[20]==cnt[3]&&cnt[11]==cnt[17];
    }

    public static void main(String[] args) {
        System.out.println(judgeCircle("UD"));
        System.out.println(judgeCircle("LL"));
    }

}
