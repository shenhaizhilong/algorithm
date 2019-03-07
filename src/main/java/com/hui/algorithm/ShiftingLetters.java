package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/17 15:28
 */
public class ShiftingLetters {


    /**
     *
     * 848. Shifting Letters
     * DescriptionHintsSubmissionsDiscussSolution
     * We have a string S of lowercase letters, and an integer array shifts.
     *
     * Call the shift of a letter, the next letter in the alphabet, (wrapping around so that 'z' becomes 'a').
     *
     * For example, shift('a') = 'b', shift('t') = 'u', and shift('z') = 'a'.
     *
     * Now for each shifts[i] = x, we want to shift the first i+1 letters of S, x times.
     *
     * Return the final string after all such shifts to S are applied.
     *
     * Example 1:
     *
     * Input: S = "abc", shifts = [3,5,9]
     * Output: "rpl"
     * Explanation:
     * We start with "abc".
     * After shifting the first 1 letters of S by 3, we have "dbc".
     * After shifting the first 2 letters of S by 5, we have "igc".
     * After shifting the first 3 letters of S by 9, we have "rpl", the answer.
     * Note:
     *
     * 1 <= S.length = shifts.length <= 20000
     * 0 <= shifts[i] <= 10 ^ 9
     * @param S
     * @param shifts
     * @return
     */
    public String shiftingLetters(String S, int[] shifts) {
        long[] postSum = new long[shifts.length];

        // compute postfix sum.
        postSum[shifts.length -1] = shifts[shifts.length -1];
        for (int i = shifts.length -2; i >=0 ; i--) {
            postSum[i] = postSum[i +1] + shifts[i];
        }

        char[] vals = S.toCharArray();
        for (int i = 0; i < vals.length; i++) {
            vals[i] = (char) ((vals[i] - 'a' + postSum[i])%26 + 'a');
        }
        return String.valueOf(vals);
    }

    public String shiftingLetters2(String S, int[] shifts) {
        int N = S.length();
        int postSum = 0;
        char [] chArr = S.toCharArray();
        for (int i = N-1; i >= 0; i--) {
            postSum = (postSum + shifts[i]) % 26;
            int offset = (chArr[i] - 'a' + postSum) % 26;
            chArr[i] = (char) (offset + 'a');
        }

        return String.valueOf(chArr);
    }


    public static void main(String[] args) {

        ShiftingLetters shiftingLetters = new ShiftingLetters();
        System.out.println(shiftingLetters.shiftingLetters("abc", new int[]{3,5,9}));
        System.out.println(shiftingLetters.shiftingLetters2("abc", new int[]{3,5,9}));
    }
}
