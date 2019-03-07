package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/28 11:09
 */
public class ExcelSheetColumnNumber {


    /**
     *
     * 171. Excel Sheet Column Number
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a column title as appear in an Excel sheet, return its corresponding column number.
     *
     * For example:
     *
     *     A -> 1
     *     B -> 2
     *     C -> 3
     *     ...
     *     Z -> 26
     *     AA -> 27
     *     AB -> 28
     *     ...
     * Example 1:
     *
     * Input: "A"
     * Output: 1
     * Example 2:
     *
     * Input: "AB"
     * Output: 28
     * Example 3:
     *
     * Input: "ZY"
     * Output: 701
     *
     * @param s
     * @return
     */
    public static int titleToNumber(String s) {
        if(s == null || s.length() == 0)return 0;
        int number = 0;
        for (int i = 0; i < s.length(); i++) {
            number = s.charAt(i) - 'A' +1 + number*26;
        }
        return number;
    }


    public static   int titleToNumber2(String s) {
        if(s == null || s.length() == 0)return 0;
        int number = 0;
        int p = 0;
        for (int i = s.length() -1; i >=0; i--) {
            int v = s.charAt(i) - 'A' +1;
            v =  (int) (v*Math.pow(26,p));
            p++;
            number += v;
        }
        return number;
    }


    /**
     *
     * 68. Excel Sheet Column Title
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
     *
     * For example:
     *
     *     1 -> A
     *     2 -> B
     *     3 -> C
     *     ...
     *     26 -> Z
     *     27 -> AA
     *     28 -> AB
     *     ...
     * Example 1:
     *
     * Input: 1
     * Output: "A"
     * Example 2:
     *
     * Input: 28
     * Output: "AB"
     * Example 3:
     *
     * Input: 701
     * Output: "ZY"
     *
     * @param n
     * @return
     */
    public static String convertToTitle(int n) {
       StringBuilder sb = new StringBuilder();
       while (n > 0)
       {
            int r = n % 26;
            r = r == 0? 26:r;
            sb.append((char)( r -1 + 'A'));
            n = (n-r)/26;

       }
       return sb.reverse().toString();

    }



    public static void main(String[] args) {
        String[] strs = { "A","X","Z", "ZY", "AB", "XYZ", "XXZZYY"};
        for(String s: strs)
        {
            int n = titleToNumber(s);
            System.out.println(n);
            System.out.println(convertToTitle(n));
        }
    }
}
