package com.hui.Math;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/18 10:08
 */
public class ReconstructOriginalDigitsfromEnglish {


    /**
     *
     * 423. Reconstruct Original Digits from English
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a non-empty string containing an out-of-order English representation of digits 0-9, output the digits in ascending order.
     *
     * Note:
     * Input contains only lowercase English letters.
     * Input is guaranteed to be valid and can be transformed to its original digits. That means invalid inputs such as "abc" or "zerone" are not permitted.
     * Input length is less than 50,000.
     * Example 1:
     * Input: "owoztneoer"
     *
     * Output: "012"
     * Example 2:
     * Input: "fviefuro"
     *
     * Output: "45"
     *
     * @param s
     * @return
     */
    public String originalDigits(String s) {
        if(s == null || s.isEmpty())return "";
        String[] digits = {"zero","one","two","three","four","five","six","seven","eight","nine"};
        int[] counter = new int[26];
        int[] res = new int[10];
        for(char ch:s.toCharArray()){counter[ch - 'a']++;}

        StringBuilder sb = new StringBuilder();
        res[0] = counter['z' - 'a'];  // zero, z is unique in digits.
        res[2] = counter['w' - 'a'];  // zero, w is unique in digits.
        res[4] = counter['u' - 'a'];
        res[6] = counter['x' - 'a'];
        res[8] = counter['g' - 'a'];

        for (int i = 0; i < 10; i = i +2) {
            for(char c: digits[i].toCharArray())
            {
                counter[c-'a'] -= res[i];
            }
        }

        //after remove 0,2,4,6,8
        res[1] = counter['o' - 'a'];  // one, o is unique in digits.
        res[3] = counter['t' - 'a'];  // three, t is unique in digits.
        res[5] = counter['f' - 'a'];  // five, f is unique in digits.
        res[7] = counter['s' - 'a'];  // seven, s is unique in digits.


        for (int i = 1; i < 9; i = i +2) {
            for(char c: digits[i].toCharArray())
            {
                counter[c-'a'] -= res[i];
            }
        }


        //now only 9 left
        res[9] = counter['e' - 'a'];  // nine, e/i is unique in digits.
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < res[i]; j++) {
                sb.append(i);
            }
        }
    return sb.toString();
    }

    public String originalDigits2(String s) {
        if(s == null || s.isEmpty())return "";
        int[] counter = new int[26];
        int[] res = new int[10];
        for(char ch:s.toCharArray()){counter[ch - 'a']++;}

        StringBuilder sb = new StringBuilder();
        res[0] = counter['z' - 'a'];  // zero, z is unique in digits.
        res[2] = counter['w' - 'a'];  // zero, w is unique in digits.
        res[4] = counter['u' - 'a'];
        res[6] = counter['x' - 'a'];
        res[8] = counter['g' - 'a'];

        res[1] = counter['o' - 'a'] - res[0] - res[2] - res[4];
        res[3] = counter['r' - 'a'] - res[0] - res[4];
        res[5] = counter['f' - 'a'] - res[4];
        res[7] = counter['s' - 'a'] - res[6];
        res[9] = counter['i' - 'a'] - res[8] - res[6] - res[5];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < res[i]; j++) {
                sb.append(i);
            }
        }

        return sb.toString();

    }

    public static void main(String[] args) {
        ReconstructOriginalDigitsfromEnglish reconstructOriginalDigitsfromEnglish = new ReconstructOriginalDigitsfromEnglish();
        System.out.println(reconstructOriginalDigitsfromEnglish.originalDigits2("owoztneoer"));
    }
}
