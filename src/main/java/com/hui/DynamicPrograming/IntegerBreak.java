package com.hui.DynamicPrograming;

import com.hui.Array.Matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 *
 * 343. Integer Break
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.
 *
 * Example 1:
 *
 * Input: 2
 * Output: 1
 * Explanation: 2 = 1 + 1, 1 × 1 = 1.
 * Example 2:
 *
 * Input: 10
 * Output: 36
 * Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
 * Note: You may assume that n is not less than 2 and not larger than 58.
 *
 *
 *
 *  max(n/2*(n -n/2), n/3*n/3 *(n-n/3 -n/3), .....)
 *  n = a + b,
 *  a*b = a*(n-a) = -a^2 +an
 *
 *  -2a +n = 0
 *  a = n/2
 *  个人猜测最大值必定 在把数据等分或者十分接近等分之后所有等分之积的序列里面，然后取最大值max(n/2*(n -n/2), n/3*n/3 *(n-n/3 -n/3), .....)
 *
 * @author: shenhaizhilong
 * @date: 2018/8/23 19:11
 */
public class IntegerBreak {
    public int integerBreak(int num) {
        if(num == 2)return 1;
        if(num == 3)return 2;
        int max = 1;
        for (int i = 2; i <= num -1; i++) {
            max = Math.max(max, breakNPart(num,i));
        }
        return max;
    }

    private int breakNPart(int num, int n)
    {
        int t = (int) Math.round(num*1.0/n) ;
        int last = num - (n -1)*t;
        return (int) (last*Math.pow(t, n -1));
    }


    // dynamic programing
    public int integerBreak2(int n)
    {
        List<Integer> dp1 = Arrays.asList(new Integer[]{0,0,1,2,4,6,9});
        List<Integer> dp = new ArrayList<>(dp1.size());
        dp.addAll(dp1);

        for (int i = 7; i <=58 ; i++) {
            dp.add(3*dp.get(i -3));
        }
        return dp.get(n);
    }


    /**
     *https://leetcode.com/problems/integer-break/discuss/80723/Simple-Java-solution
     * Basic idea is to divide your number into threes unless when the last number is 4
     * Eg :
     *
     * 7 = 3 * 2 * 2
     * 8 = 3 * 3 * 2
     * 9 = 3 * 3 * 3
     * 10 = 3 * 3 * 2 * 2
     * 11 = 3 * 3 * 3 * 2
     * 12 = 3 * 3 * 3 * 3
     * 13 = 3 * 3 * 3 * 2 * 2
     * @param n
     * @return
     */
    public  int integerBreak3(int n) {
        if(n==2||n==3) return n-1;
        if(n==4) return 4;
        int product = 1;
        while(n > 4){
            n = n -3;
            product = product*3;
        }
        return product*n;
    }


    /**
     *https://leetcode.com/problems/integer-break/discuss/80785/O(log(n))-Time-solution-with-explanation
     * Given a number n lets say we have a possible product P = p1 * p2 * ... pk. Then we notice what would happen if we could break pi up into two more terms lets say one of the terms is 2 we would get the terms pi-2 and 2 so if 2(pi-2) > pi we would get a bigger product and this happens if pi > 4. since there is one other possible number less then 4 that is not 2 aka 3. Likewise for 3 if we instead breakup the one of the terms into pi-3 and 3 we would get a bigger product if 3*(pi-3) > pi which happens if pi > 4.5.
     *
     * Hence we see that all of the terms in the product must be 2's and 3's. So we now just need to write n = a3 + b2 such that P = (3^a) * (2^b) is maximized. Hence we should favor more 3's then 2's in the product then 2's if possible.
     *
     * So if n = a*3 then the answer will just be 3^a.
     *
     * if n = a*3 + 2 then the answer will be 2(3^a).
     *
     * and if n = a*3 + 2*2 then the answer will be 2 * 2 * 3^a
     *
     * @param n
     * @return
     */
    public int integerBreak4(int n) {
        if(n == 2)
            return 1;
        else if(n == 3)
            return 2;
        else if(n%3 == 0)
            return (int)Math.pow(3, n/3);
        else if(n%3 == 1)
            return 4 * (int) Math.pow(3, (n - 4) / 3);
        else
            return 2 * (int) Math.pow(3, n/3);
    }

    public int integerBreak5(int n)
    {
        if(n < 1)return 0;
        if(n == 1)return 1;
        if(n == 2 || n == 3)return n -1;
        int[] dp = new int[n +1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j*(i - j), dp[j]*(i -j)));
            }
        }
        Matrix.print(dp);
        return dp[n];
    }
    public static void main(String[] args) {
        IntegerBreak integerBreak = new IntegerBreak();
//        for (int i = 2; i <= 58; i++) {
//            System.out.println(i +", " + integerBreak.integerBreak(i) + ", " + integerBreak.integerBreak2(i) +", " + integerBreak.integerBreak3(i) + ", " + integerBreak.integerBreak4(i) + ", " + integerBreak.integerBreak5(i));
//        }

        integerBreak.integerBreak5(10);

        System.out.println(90.0309D/6.7774D);
    }
}
