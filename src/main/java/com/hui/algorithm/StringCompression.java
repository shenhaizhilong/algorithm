package com.hui.algorithm;



/**
 *
 *https://leetcode.com/problems/string-compression/description/
 * 443. String Compression
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an array of characters, compress it in-place.
 *
 * The length after compression must always be smaller than or equal to the original array.
 *
 * Every element of the array should be a character (not int) of length 1.
 *
 * After you are done modifying the input array in-place, return the new length of the array.
 *
 *
 * Follow up:
 * Could you solve it using only O(1) extra space?
 *
 *
 * Example 1:
 * Input:
 * ["a","a","b","b","c","c","c"]
 *
 * Output:
 * Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
 *
 * Explanation:
 * "aa" is replaced by "a2". "bb" is replaced by "b2". "ccc" is replaced by "c3".
 * Example 2:
 * Input:
 * ["a"]
 *
 * Output:
 * Return 1, and the first 1 characters of the input array should be: ["a"]
 *
 * Explanation:
 * Nothing is replaced.
 * Example 3:
 * Input:
 * ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 *
 * Output:
 * Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
 *
 * Explanation:
 * Since the character "a" does not repeat, it is not compressed. "bbbbbbbbbbbb" is replaced by "b12".
 * Notice each digit has it's own entry in the array.
 * Note:
 * All characters have an ASCII value in [35, 126].
 * 1 <= len(chars) <= 1000.
 *
 *
 * @author: shenhaizhilong
 * @date: 2018/8/25 8:18
 */
public class StringCompression {
    public int compress(char[] chars) {
        if(chars.length ==1)return 1;
       int slow = 0;
       int fast = 1;
       int position = 0;
       for (; slow < chars.length; )
       {
           if( fast < chars.length && chars[slow] == chars[fast])
           {
               fast++;
           }else {
               int len = fast - slow;
               chars[position] = chars[slow];
               if(len >1)
               {
                   String str = String.valueOf(len);
                   for (int i = 0; i < str.length(); i++) {
                       position++;
                       chars[position] = str.charAt(i);
                   }

               }
               position++;
               slow = fast;
               fast++;
           }

       }
        System.out.println(new String(chars,0,position));
       return position;
    }

    public static void main(String[] args) {
        StringCompression stringCompression = new StringCompression();
        char[] a = new char[]{'a'};
        char[] b = new char[]{'a','b','b','b','b','b','b','b','b','b','b','b','b'};
        char[] c = new char[]{'a','a','b','b','c','c','c'};
        char[] d = new char[]{'a','a'};
        char[] e = new char[]{};
        char[] f = new char[]{'a','a','a','b','b','a','a'};
        System.out.println(stringCompression.compress(a));

        System.out.println(stringCompression.compress(b));

        System.out.println(stringCompression.compress(c));

        System.out.println(stringCompression.compress(d));

        System.out.println(stringCompression.compress(e));
        System.out.println(stringCompression.compress(f));

    }
}
