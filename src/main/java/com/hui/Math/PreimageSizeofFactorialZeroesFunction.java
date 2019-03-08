package com.hui.Math;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/22 17:56
 *
 *
 * 793. Preimage Size of Factorial Zeroes Function
 * DescriptionHintsSubmissionsDiscussSolution
 * Let f(x) be the number of zeroes at the end of x!. (Recall that x! = 1 * 2 * 3 * ... * x, and by convention, 0! = 1.)
 *
 * For example, f(3) = 0 because 3! = 6 has no zeroes at the end, while f(11) = 2 because 11! = 39916800 has 2 zeroes at the end. Given K, find how many non-negative integers x have the property that f(x) = K.
 *
 * Example 1:
 * Input: K = 0
 * Output: 5
 * Explanation: 0!, 1!, 2!, 3!, and 4! end with K = 0 zeroes.
 *
 * Example 2:
 * Input: K = 5
 * Output: 0
 * Explanation: There is no x such that x! ends in K = 5 zeroes.
 * Note:
 *
 * K will be an integer in the range [0, 10^9].
 *
 */
public class PreimageSizeofFactorialZeroesFunction {


    public int preimageSizeFZF(int K) {
        int countFive = 0;
        long start = 0;
        while (countFive <= K)
        {
            if(countFive == K)return 5;
            long number = start;
            while (number % 5 == 0 && number != 0)
            {
                countFive++;
                number /= 5;
            }

            start += 5;
        }
        return 0;

    }

    // the answer is either 0 or 5
    // https://leetcode.com/problems/preimage-size-of-factorial-zeroes-function/discuss/117631/C++-O(logn)-math-solution-with-explanation
    public int preimageSizeFZF2(int K) {


        // num = {1,6, 31, 156,781,  3906,19531,97656,  488281,2441406,12207031, 61035156, 305175781}
        int[] num = new int[13];
        num[0] = 1;
        for (int i = 1; i < num.length; i++) {
            num[i] = num[i -1]*5 +1;
        }

        //   x: 5 10 15 20 25 30 35 40 45 50 55 60 65 70 75 80 85 90 95 100 105 110 115 120 125 ...
        //g(x): 1 1   1 1   2 1   1 1   1 2   1 1  1  1   2 1  1   1  1  2   1   1   1   1   3 ...
        //k                 6             12              18             24                  31
        //
        // missing k'       5             11              17             23                  29,30

        // when k' =  5(=5), 11(=6*1+5), 17(=6*2+5), 23(=6*3+5), 29(=6*4+5), 30(=6*5), 36(=31+5), 42(=31+6+5), 48(=31+6*2+5), ... are skipped.
        // skipped when k = 5,11,17,23,29,30,36,42,48,54,60,61,67,73,79,85,91,92,98,... f(k) == 0
        for (int i = num.length -1; i >= 0; i--) {
            if(K/ num[i] == 5)return 0;
            K = K % num[i];
        }

        return 5;


    }

    public static void main(String[] args) {
        PreimageSizeofFactorialZeroesFunction preimageSizeofFactorialZeroesFunction = new PreimageSizeofFactorialZeroesFunction();
         for (int i = 0; i < 100; i++) {
            if(preimageSizeofFactorialZeroesFunction.preimageSizeFZF2(i) == 0)
            {
                System.out.print(i +",");
            }
//             System.out.println(i + ", " + preimageSizeofFactorialZeroesFunction.preimageSizeFZF(i));
//             System.out.println(i + ", " + preimageSizeofFactorialZeroesFunction.preimageSizeFZF2(i));
            }
    }

}
