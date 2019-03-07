package com.hui.algorithm;

/**
 *
 *https://leetcode.com/problems/zigzag-conversion/description/
 * ZigZag Conversion
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string s, int numRows);
 * Example 1:
 *
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 * Example 2:
 *
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 *
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 *
 * @author: shenhaizhilong
 * @date: 2018/8/15 16:47
 */
public class ZigZag {

    public String convert(String s, int numRows) {

        if(s == null || s.length() ==0 || numRows == 1)return s;
        int rowCount = Math.min(numRows, s.length());
        StringBuilder[] stringBuilders = new StringBuilder[rowCount];
        for (int i = 0; i < stringBuilders.length; i++) {
            stringBuilders[i] = new StringBuilder();
        }

        int cutOff = 0;
        boolean godown = false;
        for (int i = 0; i < s.length(); i++) {
            stringBuilders[cutOff].append(s.charAt(i));
            if(cutOff == 0 || cutOff == rowCount -1)godown = !godown;
            cutOff += (godown ? 1:-1);
        }

        for (int i = 1; i < stringBuilders.length; i++) {
            stringBuilders[0].append(stringBuilders[i].toString());
        }
        return stringBuilders[0].toString();

    }

    public static void main(String[] args) {

        ZigZag zigZag = new ZigZag();
        String a = "PAYPALISHIRING";
        for (int i = 2; i < 5 ; i++) {
            System.out.println(zigZag.convert(a,i));
        }
    }
}
