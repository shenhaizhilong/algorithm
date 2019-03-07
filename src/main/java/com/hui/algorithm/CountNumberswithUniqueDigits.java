package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/4 12:23
 */
public class CountNumberswithUniqueDigits {

    /**
     *357. Count Numbers with Unique Digits
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.
     *
     * Example:
     *
     * Input: 2
     * Output: 91
     * Explanation: The answer should be the total numbers in the range of 0 ≤ x < 100,
     *              excluding 11,22,33,44,55,66,77,88,99
     *
     *
     *
     * f(1) = 10, 1 digits integer, where j in [0,9]
     * f(2) = 9*9,  2 digits integer, ij, i in [1,9], where j in [0,9] and i != j,
     * f(3) = 9*9*8,  3 digits integer, ijk, i in [1,9], where j in [0,9] and i != j, where k in [0,9] and k !=i and k !=j
     * f(3) = f(2)*8
     * f(4) = f(3)*7,  4digits integer, ijkl, i in [1,9], where j in [0,9] and i != j, where k in [0,9] and k !=i and k !=j, L in [0,9], L !=
     * i, L !=j, L !=k
     * so f(10) = f(9)*1,
     * f(10) = 9*9*8*7*6*5*4*3*2*1
     * f(11) = 0
     * f(12) == f(13) == 0
     * @param n
     * @return
     */
    public int countNumbersWithUniqueDigits(int n) {

        if(n == 0)return 1;
        int availableDigits = 9;
        int k = 9;
        int res = 10;  // 1 digits have 10 integer
        while (availableDigits > 0 && n-- > 1)
        {
            k *= availableDigits;  //  n digits have: f(n) = f(n-1)*availableDigits
            availableDigits--;
            res += k;   // res = f(1) + f(2) + f(3) ...f(n)
        }

        return res;

    }

    public static void main(String[] args) {
        CountNumberswithUniqueDigits countNumberswithUniqueDigits = new CountNumberswithUniqueDigits();
        for (int i = 0; i < 14; i++) {
            System.out.println(countNumberswithUniqueDigits.countNumbersWithUniqueDigits(i));
        }
    }
}
