package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/20 11:20
 */
public class AdditiveNumber {


    /**
     *
     * 306. Additive Number
     * DescriptionHintsSubmissionsDiscussSolution
     * Additive number is a string whose digits can form additive sequence.
     *
     * A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.
     *
     * Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.
     *
     * Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.
     *
     * Example 1:
     *
     * Input: "112358"
     * Output: true
     * Explanation: The digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
     *              1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
     * Example 2:
     *
     * Input: "199100199"
     * Output: true
     * Explanation: The additive sequence is: 1, 99, 100, 199.
     *              1 + 99 = 100, 99 + 100 = 199
     * Follow up:
     * How would you handle overflow for very large input integers?
     * @param num
     * @return
     */
    public boolean isAdditiveNumber(String num) {
        if(num == null || num.length() < 3)return false;
        int len = num.length();
        for (int i = 1; i <= len/2; i++) {
            for (int j = 1; j <= len - i - Math.max(i,j); j++) {
                if(isValid(num, i, j))return true;// i means the first number's length, j means the second number's length
            }
        }
        return false;
    }

    private boolean isValid(String num, int i, int j)
    {
        if(num.charAt(0) == '0' && i > 1)return false; // first number have leading zeros
        if(num.charAt(i) == '0' && j > 1)return false; // second number have leading zeros.
        long a = Long.parseLong(num.substring(0,i));
        long b = Long.parseLong(num.substring(i,i + j));
        // a + b = c
        // b + c = d
        //  a = b, b = c

        String sum;
        for (int start = i + j; start < num.length(); start += sum.length() ) {
            long c = a + b;
            a = b;
            b = c;
            sum = String.valueOf(c);
            if(!num.startsWith(sum, start))return false;
        }

        return true;

    }

    public static void main(String[] args) {

        AdditiveNumber additiveNumber = new AdditiveNumber();
//        System.out.println(additiveNumber.isAdditiveNumber("199100199"));
//        System.out.println(additiveNumber.isAdditiveNumber("112358"));
        System.out.println(additiveNumber.isAdditiveNumber("0235813"));
    }
}
