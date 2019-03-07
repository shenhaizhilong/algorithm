package com.hui.algorithm;

/**
 *
 * @author: shenhaizhilong
 * @date: 2018/9/30 12:36
 */
public class ShortestPalindrome {


    /**
     *
     * 214. Shortest Palindrome
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a string s, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.
     *
     * Example 1:
     *
     * Input: "aacecaaa"
     * Output: "aaacecaaa"
     * Example 2:
     *
     * Input: "abcd"
     * Output: "dcbabcd"
     * 
     * @param s
     * @return
     */
    public String shortestPalindrome(String s) {
        if(isPalindrome(s))return s;

        String res = s + s ;
        for (int k = 1; k < s.length(); k++) {
            String left = s.substring(0, k);
            String right = s.substring(k);
            String reversedRight = new StringBuilder(right).reverse().toString();
            String left2 = reversedRight.substring(0, reversedRight.length() -1);
            if(left2.endsWith(left))
            {
                String temp = left2 + right;
                if(temp.length() < res.length())
                {
                    res = temp;
                }
            }else {
                String temp = reversedRight + s;
                if(isPalindrome(temp) && temp.length() <= res.length())
                {
                    res = temp;
                }
            }
        }
        return res;
        
    }

    private boolean isPalindrome(String str)
    {
        int start = 0;
        int end = str.length() - 1;
        while (start < end)
        {
            if(str.charAt(start++) != str.charAt(end--))
                return false;
        }
        return true;
    }


    public String shortestPalindrome2(String s)
    {
        int len = s.length();
        String reversedStr = new StringBuilder(s).reverse().toString();
        for (int i = 0; i < len; i++) {
            if(s.substring(0, len - i).equals(reversedStr.substring(i)))  // find the longest palindrome from the beginning of s
            {
                return reversedStr.substring(0, i) + s;
            }
        }
        return "";
    }


    /**
     *
     * we use  KMP algorithm to get the partial match table
     * PMT[k] means the  length of longest common prefix and suffix from P[0...k]
     * @param s
     * @return
     */
    public String shortestPalindrome3(String s) {

        String reversed = new StringBuilder(s).reverse().toString();
        String pattern = s + "#" + reversed;
        int n = s.length();
        int len = pattern.length();
        int[] PMT = new int[len];
        PMT[0] = 0;
        int i = 1;
        int k = 0;
        while (i < len)
        {
            if(pattern.charAt(i) == pattern.charAt(k))
            {
                k++;
                PMT[i] = k;
                i++;
            }else{
                if(k != 0)
                {
                    k = PMT[k -1];
                }else {
                    i++;
                }
            }
        }

        return reversed.substring(0, n - PMT[ len -1]) + s;
    }


    public static void main(String[] args) {

        ShortestPalindrome shortestPalindrome = new ShortestPalindrome();
//        System.out.println(shortestPalindrome.shortestPalindrome("aacecaaa"));
//        System.out.println(shortestPalindrome.shortestPalindrome("abcd"));
//        System.out.println(shortestPalindrome.shortestPalindrome("ba"));
//        System.out.println(shortestPalindrome.shortestPalindrome2("aacecaaa"));
//        System.out.println(shortestPalindrome.shortestPalindrome2("abcd"));
//        System.out.println(shortestPalindrome.shortestPalindrome2("ba"));
        System.out.println(shortestPalindrome.shortestPalindrome3("aacecaaa"));
    }
}
