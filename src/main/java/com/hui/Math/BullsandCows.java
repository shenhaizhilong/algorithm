package com.hui.Math;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/27 15:50
 */
public class BullsandCows {


    /**
     *
     *
     * 299. Bulls and Cows
     * DescriptionHintsSubmissionsDiscussSolution
     * You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses and hints to eventually derive the secret number.
     *
     * Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows.
     *
     * Please note that both secret number and friend's guess may contain duplicate digits.
     *
     * Example 1:
     *
     * Input: secret = "1807", guess = "7810"
     *
     * Output: "1A3B"
     *
     * Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.
     * Example 2:
     *
     * Input: secret = "1123", guess = "0111"
     *
     * Output: "1A1B"
     *
     * Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.
     * Note: You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.
     * @param secret
     * @param guess
     * @return
     */
    public String getHint(String secret, String guess) {

        int countA = 0, countB = 0;
        int[] counter = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            int s = secret.charAt(i) - '0';
            int g = guess.charAt(i) - '0';
            if(s == g)
            {
                countA++;   // same position and same digit.
            }else {
                if(counter[s] > 0)countB++; // means it has s in guess
                if(counter[g] < 0)countB++; // means it has g in secret
                counter[s]--;
                counter[g]++;
            }
        }

        return countA +"A"+ countB + "B";
    }

    public static void main(String[] args) {


        BullsandCows bullsandCows = new BullsandCows();
       // System.out.println(bullsandCows.getHint("1807", "7810"));
        System.out.println(bullsandCows.getHint("1123", "0111"));

    }
}
