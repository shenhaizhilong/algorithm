package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/3 20:09
 */
public class ArrangingCoins {

    /**
     *
     * 441. Arranging Coins
     * DescriptionHintsSubmissionsDiscussSolution
     * You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.
     *
     * Given n, find the total number of full staircase rows that can be formed.
     *
     * n is a non-negative integer and fits within the range of a 32-bit signed integer.
     *
     * Example 1:
     *
     * n = 5
     *
     * The coins can form the following rows:
     * ¤
     * ¤ ¤
     * ¤ ¤
     *
     * Because the 3rd row is incomplete, we return 2.
     * Example 2:
     *
     * n = 8
     *
     * The coins can form the following rows:
     * ¤
     * ¤ ¤
     * ¤ ¤ ¤
     * ¤ ¤
     *
     * Because the 4th row is incomplete, we return 3.
     * @param n
     * @return
     */
    public static int arrangeCoins(int n) {
        int k = (int) Math.sqrt(2.0*n);
        int v = (k*(k +1))>>>1;
        while (v > n)
        {
            k--;
            v = (k*(k +1))>>>1;
        }
        return  k;
    }

    public static int arrangeCoins2(int n)
    {
         return (int)((-1 + Math.sqrt(1 + 8.0* n))/2);
    }

    public static void main(String[] args) {
//        System.out.println(arrangeCoins(1));
//        System.out.println(arrangeCoins(5));
//        System.out.println(arrangeCoins(8));
        System.out.println(arrangeCoins2(1804289383));
    }

}
