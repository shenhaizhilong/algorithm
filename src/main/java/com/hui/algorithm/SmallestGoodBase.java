package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/22 23:24
 *483. Smallest Good Base
 * DescriptionHintsSubmissionsDiscussSolution
 * For an integer n, we call k>=2 a good base of n, if all digits of n base k are 1.
 *
 * Now given a string representing n, you should return the smallest good base of n in string format.
 *
 * Example 1:
 * Input: "13"
 * Output: "3"
 * Explanation: 13 base 3 is 111.
 * Example 2:
 * Input: "4681"
 * Output: "8"
 * Explanation: 4681 base 8 is 11111.
 * Example 3:
 * Input: "1000000000000000000"
 * Output: "999999999999999999"
 * Explanation: 1000000000000000000 base 999999999999999999 is 11.
 * Note:
 * The range of n is [3, 10^18].
 * The string representing n is always valid and will not have leading zeros.
 *
 *
 */
public class SmallestGoodBase {

    public String smallestGoodBase(String n) {
         long number = 0L;
         for(char c:n.toCharArray())
         {
             number = number*10 + c - '0';
         }


         // f(k,m) = 1 + k + k^2 + k^3 + ..+ k^(m-1) = (k^m -1)/(k -1) == n ; k is the base m is the length
        //  since k >= 2, so 2^m -1 <= n*(2-1), so  2^m <= n +1, so m <= log2(n +1)
        // since the min n is 3, min k is 2,  so m >= 2
         for (int m = (int)(Math.log(number +1)/Math.log(2)); m >= 2; m--)
         {
             //For the upper limit, note that n = f(k, m) = 1 + k + ... + k^(m-1) > k^(m-1) ==> k < n^(1/(m-1)).
             // For the lower limit, note that n = f(k, m) = (k^m - 1)/(k-1) <= (k^m - 1) ==> k >= (n+1)^(1/m).
             // To summarize: (n+1)^(1/m) <= k <= n^(1/(m-1)).

             long lo = (long)Math.pow(number +1, 1.0/m);
             long hi = (long)Math.pow(number, 1.0/(m -1));
             while (lo <= hi)
             {
                 long k = (lo + hi) >>>1;  // curr base;
                 long curr = 0L;
                 for (int i = 0; i < m; i++) {
                     curr = curr*k + 1L;
                 }
                 if(curr == number)return String.valueOf(k);
                 else if(curr > number)
                 {
                     hi = k -1;
                 }else {
                     lo = k +1;
                 }
             }
         }
         return String.valueOf(number -1);

    }
}
