package com.hui.Greedy;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/13 14:34
 *
 *948. Bag of Tokens
 * DescriptionHintsSubmissionsDiscussSolution
 * You have an initial power P, an initial score of 0 points, and a bag of tokens.
 *
 * Each token can be used at most once, has a value token[i], and has potentially two ways to use it.
 *
 * If we have at least token[i] power, we may play the token face up, losing token[i] power, and gaining 1 point.
 * If we have at least 1 point, we may play the token face down, gaining token[i] power, and losing 1 point.
 * Return the largest number of points we can have after playing any number of tokens.
 *
 *
 *
 * Example 1:
 *
 * Input: tokens = [100], P = 50
 * Output: 0
 * Example 2:
 *
 * Input: tokens = [100,200], P = 150
 * Output: 1
 * Example 3:
 *
 * Input: tokens = [100,200,300,400], P = 200
 * Output: 2
 *
 *
 * Note:
 *
 * tokens.length <= 1000
 * 0 <= tokens[i] < 10000
 * 0 <= P < 10000
 *
 */
public class BagofTokens {


    public int bagOfTokensScore(int[] tokens, int P) {
        if(P <= 0 || tokens == null || tokens.length == 0)return 0;
        Arrays.sort(tokens);
        if(P < tokens[0])return 0;
        int left = 0;
        int right = tokens.length -1;
        int score = 0;
        while (left <= right)
        {

           // P < tokens[left], and score > 0, we can add the max power and score minus one.
            if(P < tokens[left] && score > 0)
            {
                P += tokens[right--];
                score--;
            }else if (P < tokens[left])
                return score;

            // greedy solution, if P >= tokens[left], just add one score and minus tokens[left], left pointer right shift
            P -= tokens[left++];
            score++;
        }
        return score;

    }
}
