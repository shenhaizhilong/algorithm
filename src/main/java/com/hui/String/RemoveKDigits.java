package com.hui.String;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/23 0:03
 *
 *
 *
 * 402. Remove K Digits
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.
 *
 * Note:
 * The length of num is less than 10002 and will be ≥ k.
 * The given num does not contain any leading zero.
 * Example 1:
 *
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 * Example 2:
 *
 * Input: num = "10200", k = 1
 * Output: "200"
 * Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
 * Example 3:
 *
 * Input: num = "10", k = 2
 * Output: "0"
 * Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 *
 *
 */
public class RemoveKDigits {

    public String removeKdigits(String num, int k) {
        if (num == null || num.length() == 0 || k == 0) return num;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            while ( k > 0 && sb.length() > 0 && sb.charAt(sb.length() -1) > c)
            {
                k--;
                sb.deleteCharAt(sb.length() -1);
            }
            if(sb.length() == 0 && c == '0')continue;
            sb.append(c);
        }

        while (k > 0 && sb.length() > 0)
        {
            sb.deleteCharAt(sb.length() -1);
            k--;
        }

        return sb.length() == 0 ? "0":sb.toString();
    }

}
