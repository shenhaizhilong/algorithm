package com.hui.Array;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/21 23:55
 *
 *
 * 738. Monotone Increasing Digits
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a non-negative integer N, find the largest number that is less than or equal to N with monotone increasing digits.
 *
 * (Recall that an integer has monotone increasing digits if and only if each pair of adjacent digits x and y satisfy x <= y.)
 *
 * Example 1:
 * Input: N = 10
 * Output: 9
 * Example 2:
 * Input: N = 1234
 * Output: 1234
 * Example 3:
 * Input: N = 332
 * Output: 299
 * Note: N is an integer in the range [0, 10^9].
 */
public class MonotoneIncreasingDigits {

    public int monotoneIncreasingDigits(int N) {
        if(N <= 9)return N;
        char[] vals = Integer.toString(N).toCharArray();
        int mark = vals.length;
        for (int i = vals.length -1; i >0; i--) {
            if(vals[i -1] > vals[i])
            {
                vals[i -1]--;
                mark = i;
            }
        }

        for (int i = mark; i < vals.length; i++) {
            vals[i] = '9';
        }
        return Integer.valueOf(new String(vals));


    }
}
