package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/29 15:14
 *
 *
 *926. Flip String to Monotone Increasing
 * DescriptionHintsSubmissionsDiscussSolution
 * A string of '0's and '1's is monotone increasing if it consists of some number of '0's (possibly 0), followed by some number of '1's (also possibly 0.)
 *
 * We are given a string S of '0's and '1's, and we may flip any '0' to a '1' or a '1' to a '0'.
 *
 * Return the minimum number of flips to make S monotone increasing.
 *
 *
 *
 * Example 1:
 *
 * Input: "00110"
 * Output: 1
 * Explanation: We flip the last digit to get 00111.
 * Example 2:
 *
 * Input: "010110"
 * Output: 2
 * Explanation: We flip to get 011111, or alternatively 000111.
 * Example 3:
 *
 * Input: "00011000"
 * Output: 2
 * Explanation: We flip to get 00000000.
 *
 *
 * Note:
 *
 * 1 <= S.length <= 20000
 * S only consists of '0' and '1' characters.
 *
 *
 */
public class FlipStringtoMonotoneIncreasing {

    public int minFlipsMonoIncr(String S) {
        if(S == null | S.length() <= 1)return 0;
        char[] vals = S.toCharArray();
        int len = S.length();
        int[] dp0 = new int[len + 1];   //dp0[n] the total cost to set it current bit to zero
        int[] dp1 = new int[len + 1];  // dp1[n] the total cost to set it current bit to one
        for (int i = 1; i <= len; i++) {
            dp1[i] = Math.min(dp0[i -1], dp1[i -1]) + (vals[i -1] == '1' ? 0: 1);
            dp0[i] = dp0[i-1] + (vals[i -1] - '0');

        }

        return Math.min(dp1[len], dp0[len]);


    }


    /**
     *
     * The idea is, each bit could have two cases, either 0 or 1. It could be a 0 only when the previous bit is also 0. However, a bit could be 1 regardless if the previous bit is 0 or 1, but instead, all the following bits must be set to 1.
     * Thus, we consider two cases, the total cost needed to set bit n to 0, and the total cost needed to set bit n to 1.
     * f_0[n] = f_0[n-1] + (cost to set the current bit to 0)
     * f_1[n] = min(f_0[n-1] + f_1[n-1]) + (cost to set the current bit to 1)
     *
     * @param S
     * @return
     */
    public int minFlipsMonoIncr2(String S) {
        if(S == null | S.length() <= 1)return 0;
        char[] vals = S.toCharArray();
        int len = S.length();
        int dp0 = 0;   //dp0[n] the total cost to set it current bit to zero
        int dp1 = 0;  // dp1[n] the total cost to set it current bit to one
        for (int i = 0; i < len; i++) {
            dp1 = Math.min(dp0, dp1) + (vals[i] == '1' ? 0: 1);
            dp0 = dp0 + (vals[i] - '0');

        }

        return Math.min(dp1, dp0);


    }


}
