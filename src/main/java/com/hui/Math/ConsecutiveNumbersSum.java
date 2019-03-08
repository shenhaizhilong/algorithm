package com.hui.Math;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/3 9:18
 */
public class ConsecutiveNumbersSum {


    /**
     *
     * 829. Consecutive Numbers Sum
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a positive integer N, how many ways can we write it as a sum of consecutive positive integers?
     *
     * Example 1:
     *
     * Input: 5
     * Output: 2
     * Explanation: 5 = 5 = 2 + 3
     * Example 2:
     *
     * Input: 9
     * Output: 3
     * Explanation: 9 = 9 = 4 + 5 = 2 + 3 + 4
     * Example 3:
     *
     * Input: 15
     * Output: 4
     * Explanation: 15 = 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
     * Note: 1 <= N <= 10 ^ 9.
     *
     *
     *
     * Basic math you should know:
     * Sum of 2k + 1 consecutive numbers = (2*k+1) * (middle number x) = (2 * k + 1) * x
     * Sum of 2k consecutive numbers = k * (sum of two middle number x and x + 1) = k * (2 * x + 1)
     *
     * No matter (2 * k + 1) * x or k * (2 * x + 1), there is an odd factor.
     * If we decompose N into two factors x and i, where i is odd and i = 2 * k + 1.
     *
     * if x - k > 0, then N = (x - k) + ... + x + ... + (x + k)
     * If k - x + 1 > 0, then N = (k - x + 1) + ... + k + (k + 1) + ... + (k + x)
     * It means that, for each odd factor of N, we can find a consecutive numbers solution.
     * Now this problem is only about counting odd numbers!
     * I believe you can find many solutions to do this.
     *
     * Another math about counting factor:
     * If N = 3^a * 5^b * 7*c * 11*d ...., the number of factors N has:
     * N_factors = (a + 1) * (b + 1) * (c + 1) * (d + 1) .....
     *
     * Time complexity:
     * To be more accurate, it's O(biggest prime factor).
     * Because every time I find a odd factor, we do N /= i.
     * This help reduce N faster.
     *
     * Assume P is the biggest prime factor of N .
     * If N = 3^x * 5^y ...* P, Loop on i will stop at P^0.5
     * If N = 3^x * 5^y ...* P^z, Loop on i will stop at P.
     *
     *
     *
     * @param N
     * @return
     */
    public int consecutiveNumbersSum(int N) {
        int res = 1;

        // we count all odd factors, so we don't need even factors, just remove all even factors.
        while ( (N & 1) == 0) N >>>= 1;

        // i stops at N^0.5
        for (int i = 3; i*i <=N ; i+= 2) {
            int exp = 0;
            while (N %i  == 0)
            {
                exp++;
                N = N /i;
            }
            res *= (exp + 1);
        }

        //The loop will stop at N^0.5
        //If N==1, you find all primes and just return res.
        //Otherwise, N will be equal to P and we should do res *= (exp + 1) where exp = 1.
        if( N > 1)res <<=1;
        return res;
    }

    public int consecutiveNumbersSum2(int N) {
        // let N = x + 1 + x + 2 + ..... x + k
        // so N = k*x + k(k + 1)/2;
        // N - k(k+1)/2 = k*x;
        int res = 1;
        for (int i = 2; i*(i+1)/2 <= N; i++) {
            if((N - i*(i+1)/2) % i == 0)
                res++;

        }

        return res;
    }

    public static void main(String[] args) {

        ConsecutiveNumbersSum consecutiveNumbersSum = new ConsecutiveNumbersSum();
        int max = 10000000;
        long t1 = System.nanoTime();

        for (int i = 1; i < max; i++) {
            consecutiveNumbersSum.consecutiveNumbersSum(i);
        }
        long t2 = System.nanoTime();


        for (int i = 1; i < max; i++) {
            consecutiveNumbersSum.consecutiveNumbersSum2(i);
        }

        long t3 = System.nanoTime();
        System.out.println((t3-t2)/(t2-t1));
//        for (int i = 1; i < 20; i++) {
//            System.out.println( i + ", " + consecutiveNumbersSum.consecutiveNumbersSum(i));
//            System.out.println( i + ", " + consecutiveNumbersSum.consecutiveNumbersSum2(i));
//        }
    }
}
