package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/27 10:05
 */
public class ValidPalindrome {
    /**
     *
     * 680. Valid Palindrome II
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
     *
     * Example 1:
     * Input: "aba"
     * Output: True
     * Example 2:
     * Input: "abca"
     * Output: True
     * Explanation: You could delete the character 'c'.
     * Note:
     * The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
     *
     * @param s
     * @return
     */
    public static boolean validPalindrome(String s) {
        if(s == null || s.length() <=1)return true;
        char[] data = s.toCharArray();
        int start = 0;
        int end = data.length -1;
       while (start < end)
       {
           if(data[start] == data[end])
           {
               start++;
               end--;
           }else {
               return helper(data, start +1, end) || helper(data, start, end -1);
           }
       }

        return true;
    }

    private static boolean helper(char[] data, int start, int end)
    {
        while ( start < end)
        {
            if(data[start++] != data[end--])return false;
        }

        return true;
    }

    public static void main(String[] args) {

        String[] strs = {"cupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupucu", "a", "ab", "abc", "accba", "abcca", "abca"};
        for (int i = 0; i < strs.length; i++) {
            System.out.println(validPalindrome(strs[i]));
        }
    }
}
