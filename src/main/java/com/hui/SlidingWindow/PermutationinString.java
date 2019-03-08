package com.hui.SlidingWindow;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/12 21:19
 *
 * 567. Permutation in String
 * DescriptionHintsSubmissionsDiscussSolution
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.
 * Example 1:
 * Input:s1 = "ab" s2 = "eidbaooo"
 * Output:True
 * Explanation: s2 contains one permutation of s1 ("ba").
 * Example 2:
 * Input:s1= "ab" s2 = "eidboaoo"
 * Output: False
 * Note:
 * The input strings only contain lower case letters.
 * The length of both given strings is in range [1, 10,000].
 *
 */
public class PermutationinString {

    public boolean checkInclusion(String s1, String s2) {
        if(s1.length() > s2.length())return false;
        int s1Len = s1.length();
        int s2Len = s2.length();
        int[] counter1 = getCounter(s1);
        int[] counter2 = new int[26];
        int start = 0;
        int end = 0;
        while (start <= s2Len -s1Len)
        {

            //expand to window size s1Len, and counter for incoming chars
            while (end -start < s1Len)
            {
                int idx = s2.charAt(end) -'a';
                counter2[idx]++;
                end++;
            }
            // check if equal
            if(compareCounter(counter1, counter2))return true;
            // decrease the count of chars which out of the window
            int idx = s2.charAt(start) - 'a';
            counter2[idx]--;
            start++;

        }

        return false;

    }

    private int[] getCounter(String s)
    {
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            counter[idx]++;
        }
        return counter;
    }

    private boolean compareCounter(int[] counter1, int[] counter2)
    {
        for (int i = 0; i < counter1.length; i++) {
            if(counter1[i] != counter2[i])return false;
        }
        return true;
    }

    public static void main(String[] args) {
        PermutationinString permutationinString = new PermutationinString();
        System.out.println(permutationinString.checkInclusion("ab","eidbaooo"));
        System.out.println(permutationinString.checkInclusion("ab","eidboaoo"));
        System.out.println(permutationinString.checkInclusion("ab","eidboaba"));
        System.out.println(permutationinString.checkInclusion("ab","ab"));
    }
}
