package com.hui.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 *
 *https://leetcode.com/problems/goat-latin/description/
 * 824. Goat Latin
 * DescriptionHintsSubmissionsDiscussSolution
 * A sentence S is given, composed of words separated by spaces. Each word consists of lowercase and uppercase letters only.
 *
 * We would like to convert the sentence to "Goat Latin" (a made-up language similar to Pig Latin.)
 *
 * The rules of Goat Latin are as follows:
 *
 * If a word begins with a vowel (a, e, i, o, or u), append "ma" to the end of the word.
 * For example, the word 'apple' becomes 'applema'.
 *
 * If a word begins with a consonant (i.e. not a vowel), remove the first letter and append it to the end, then add "ma".
 * For example, the word "goat" becomes "oatgma".
 *
 * Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
 * For example, the first word gets "a" added to the end, the second word gets "aa" added to the end and so on.
 * Return the final sentence representing the conversion from S to Goat Latin.
 *
 *
 *
 * Example 1:
 *
 * Input: "I speak Goat Latin"
 * Output: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
 * Example 2:
 *
 * Input: "The quick brown fox jumped over the lazy dog"
 * Output: "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"
 *
 *
 * Notes:
 *
 * S contains only uppercase, lowercase and spaces. Exactly one space between each word.
 * 1 <= S.length <= 150.
 * @author: shenhaizhilong
 * @date: 2018/8/21 16:24
 */
public class GoatLatin {
    private static Set<Character> set;
    static {
        set = new HashSet<>(10);
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');
    }
    public static String toGoatLatin(String S) {
        if(S == null || S.length() == 0)return S;
        StringBuilder postFix = new StringBuilder("a");
        StringBuilder results = new StringBuilder(S.length()*2);
        int start = 0;
        int end;
        String word = "";
        while (start < S.length())
        {
            end = S.indexOf(' ', start);
            char tempChar = S.charAt(start);
            if(end <0)end = S.length();
            if(set.contains(tempChar))
            {
                word = S.substring(start,end);

            }else {
                word = S.substring(start +1, end);
                word = word +S.charAt(start);
            }
            results.append(word);
            results.append("ma");
            results.append(postFix);
            results.append(" ");
            postFix.append("a");

            start = end +1;
        }

        return results.toString().trim();

    }

    public static void main(String[] args) {
        String s = "HZ sg L";
        String r = "ZHmaa gsmaaa Lmaaaa";
        System.out.println(toGoatLatin(s));
    }
}
