package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/13 10:04
 *
 *
 *
 * 424. Longest Repeating Character Replacement
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a string that consists of only uppercase English letters, you can replace any letter in the string with another letter at most k times. Find the length of a longest substring containing all repeating letters you can get after performing the above operations.
 *
 * Note:
 * Both the string's length and k will not exceed 104.
 *
 * Example 1:
 *
 * Input:
 * s = "ABAB", k = 2
 *
 * Output:
 * 4
 *
 * Explanation:
 * Replace the two 'A's with two 'B's or vice versa.
 * Example 2:
 *
 * Input:
 * s = "AABABBA", k = 1
 *
 * Output:
 * 4
 *
 * Explanation:
 * Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 *
 *
 */
public class LongestRepeatingCharacterReplacement {

    public int characterReplacement(String s, int k) {

        int start = 0;

        int maxCharCount = 0;
        int maxSize = 0;
        int N = s.length();
        int[] counter = new int[26];
        for (int end = 0; end < N; end++) {

            int idx = s.charAt(end) - 'A';
            counter[idx]++;
            if(counter[idx] > maxCharCount)
            {
                maxCharCount = counter[idx];
            }

            // end - start +1 - maxCharCount <=k ,changing size <=k, expand the sliding window
            // end - start +1 <= maxCharCount +k, we want len = end - start +1 is the max, k is const, so just compute the max Char Count is ok.
            //end - start +1 - maxCharCount > k ,changing size > k, shrink the sliding window,
            while (end - start +1 - maxCharCount > k)
            {
                counter[s.charAt(start) -'A']--;
                start++;

            }

            if(end - start +1 > maxSize)
            {
                maxSize = end - start +1;
            }

        }

        return maxSize;

    }

    public static void main(String[] args) {



    }

}
