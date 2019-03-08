package com.hui.HashMap;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/28 20:41
 */
public class LongestPalindrome {


    /**
     *
     * 409. Longest Palindrome
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.
     *
     * This is case sensitive, for example "Aa" is not considered a palindrome here.
     *
     * Note:
     * Assume the length of given string will not exceed 1,010.
     *
     * Example:
     *
     * Input:
     * "abccccdd"
     *
     * Output:
     * 7
     *
     * Explanation:
     * One longest palindrome that can be built is "dccaccd", whose length is 7.
     *
     * @param s
     * @return
     */
    public static int longestPalindrome(String s) {
        if(s == null || s.length() == 0)return 0;
        if(s.length() == 1)return 1;
        int[] counter = new int[58];
        char[] data = s.toCharArray();
        for (int i = 0; i < data.length; i++) {
            counter[data[i] -'A']++;
        }

        int r = data.length;
        for (int i = 0; i < counter.length; i++) {
            if((counter[i] &0x01) == 1) {
                r--;
            }
        }
        return (r == data.length) ? r:r +1;
    }


    public static void main(String[] args) {

        System.out.println(longestPalindrome("abccccdd"));
        System.out.println(longestPalindrome("civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth"));
    }
}
