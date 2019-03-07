package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/12 16:09
 *
 *
 */
public class ScrambleString {

    // s1.len == s2.len
    public boolean isScramble(String s1, String s2) {
        if(s1.equals(s2))return true;
        int len1 = s1.length();
        if(len1 == 2)return s1.charAt(0) == s2.charAt(1) && s1.charAt(1) == s2.charAt(0);
        if(len1 == 1)return s1.equals(s2);
        int[] counterA = new int[26];
        int[] counterB = new int[26];
        for (int i = 0; i < len1; i++) {
            counterA[s1.charAt(i) -'a']++;
            counterB[s2.charAt(i)- 'a']++;
        }

        for (int i = 0; i < counterA.length; i++) {
            if(counterA[i] != counterB[i])return false;
        }
        if(len1 <=3)return true;

        // possible position i
        for (int i = 1; i < len1; i++) {
            if(isScramble(s1.substring(0,i), s2.substring(0,i)) && isScramble(s1.substring(i), s2.substring(i)))return true;
            if(isScramble(s1.substring(0,i), s2.substring(len1 - i)) && isScramble(s1.substring(i), s2.substring(0, len1 -i)))return true;

        }

        return false;

    }

    public static void main(String[] args) {
        ScrambleString scrambleString = new ScrambleString();
//        System.out.println(scrambleString.isScramble("great","taerg"));
//        System.out.println(scrambleString.isScramble("eaa","aea"));
        System.out.println(scrambleString.isScramble("abcde","caebd"));

    }
}
