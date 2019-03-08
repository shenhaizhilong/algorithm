package com.hui.String;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/28 8:53
 */
public class ReverseWords {

    /**
     *https://leetcode.com/problems/reverse-words-in-a-string/description/
     * 151. Reverse Words in a String
     * DescriptionHintsSubmissionsDiscussSolution
     * Given an input string, reverse the string word by word.
     *
     * Example:
     *
     * Input: "the sky is blue",
     * Output: "blue is sky the".
     * Note:
     *
     * A word is defined as a sequence of non-space characters.
     * Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
     * You need to reduce multiple spaces between two words to a single space in the reversed string.
     * Follow up: For C programmers, try to solve it in-place in O(1) space.
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        if(s == null || s.length() ==0 )return s;

        char[] data = s.toCharArray();
        int len = removeSpaces(data);
        reverse(data,0, len -1);
        int slow = 0;
        int fast = 0;
        while (slow < len)
        {
            while (fast < len && data[fast] != ' ')fast++;
            reverse(data, slow, fast -1);
            slow = fast;
            slow++;
            fast++;
        }
        return new String(data,0, len);

    }
    private void reverse(char[] data, int start, int end)
    {
       while (start < end)
       {
           char t = data[end];
           data[end] = data[start];
           data[start] = t;
           end--;
           start++;

       }
    }

    private int removeSpaces(char[] data)
    {
        int slow = 0;
        int fast = 0;
        int len = data.length;
        while (fast < len)
        {
           while (fast < len && data[fast] == ' ')fast++;  //skip the space
           while (fast < len && data[fast] != ' ')data[slow++] = data[fast++];  //copy the non-space char
           while (fast < len && data[fast] == ' ')fast++; //skip the space
            if(fast < len )data[slow++] = ' ';  //save only one space
        }
        return slow;
    }

    public static void main(String[] args) {

        String a = " ";
        ReverseWords reverseWords = new ReverseWords();
        String b = reverseWords.reverseWords(a);
        System.out.println(b.length());
        System.out.println(b);
    }
}
