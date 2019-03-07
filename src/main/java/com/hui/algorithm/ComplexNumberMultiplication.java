package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/5 11:40
 */
public class ComplexNumberMultiplication {

    /**
     *
     * 537. Complex Number Multiplication
     * DescriptionHintsSubmissionsDiscussSolution
     * Given two strings representing two complex numbers.
     *
     * You need to return a string representing their multiplication. Note i2 = -1 according to the definition.
     *
     * Example 1:
     * Input: "1+1i", "1+1i"
     * Output: "0+2i"
     * Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
     * Example 2:
     * Input: "1+-1i", "1+-1i"
     * Output: "0+-2i"
     * Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
     * Note:
     *
     * The input strings will not have extra blank.
     * The input strings will be given in the form of a+bi, where the integer a and b will both belong to the range of [-100, 100]. And the output should be also in this form.
     *
     * @param a
     * @param b
     * @return
     */
    public String complexNumberMultiply(String a, String b) {
        int[] res1 = getNumber(a);
        int[] res2 = getNumber(b);
        return multiply(res1[0],res1[1],res2[0],res2[1]);

    }

    private String multiply(int a, int b, int c, int d)
    {
        int k = (a*c - b*d);
        int m = (a*d + c*b);
        return k +"+" + m +"i";
    }

    private int[] getNumber(String str)
    {
        int[] res = new int[2];
        String[] strs = str.split("\\+");
        res[0] = Integer.valueOf(strs[0]);
        res[1] = Integer.valueOf(strs[1].substring(0,strs[1].indexOf('i')));
        return res;

    }

    public static void main(String[] args) {

        ComplexNumberMultiplication complex = new ComplexNumberMultiplication();
        System.out.println(complex.complexNumberMultiply("1+1i","1+-1i"));
        System.out.println(complex.complexNumberMultiply("1+1i","1+1i"));
        System.out.println(complex.complexNumberMultiply("1+-1i","1+-1i"));
    }
}

