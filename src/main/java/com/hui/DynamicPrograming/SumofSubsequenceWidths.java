package com.hui.DynamicPrograming;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/31 11:55
 */
public class SumofSubsequenceWidths {


    /**
     *
     * For A[i]:
     * There are i smaller numbers,
     * so there are 2 ^ i sequences in which A[i] is maximum.
     * we should do res += A[i] * (2 ^ i)
     *
     * There are n - i - 1 bigger numbers,
     * so there are 2 ^ (n - i - 1) sequences in which A[i] is minimum.
     * we should do res -= A[i] * 2 ^ (n - i - 1)
     *res = sum((2^i - 2^(n -i -1))* A[i]) i = 0,1,2,... n -1
     * @param A
     * @return
     */
    public int sumSubseqWidths(int[] A) {

        Arrays.sort(A);
        long Mod = 1_000_000_007;
        int N = A.length;
        long[] pow2 = new long[N];
        pow2[0] = 1;
        for (int i = 1; i < N; i++) {
            pow2[i] = (pow2[i -1] << 1) % Mod;
        }

        long ans = 0;
        for (int i = 0; i < N; i++) {
            ans  = (ans + (pow2[i] - pow2[N - i - 1])*A[i] ) % Mod;
        }

        return (int) ans;

    }

    public int sumSubseqWidths2(int[] A) {
        Arrays.sort(A);
        long c = 1, res = 0, mod = (long)1e9 + 7;
        for (int i = 0; i < A.length; ++i, c = (c << 1) % mod)
            res = (res + A[i] * c - A[A.length - i - 1] * c) % mod;
        return (int)((res + mod) % mod);

    }


    public static void main(String[] args) {

        int[] arr = {1,4,3,5,2,9,10,12,22,1,2,3,4,0,2,20,29,89,0,1,2,3,4,8,74,29,3,5,0,9,3,8,7,3};
        SumofSubsequenceWidths sumofSubsequenceWidths = new SumofSubsequenceWidths();
        System.out.println(sumofSubsequenceWidths.sumSubseqWidths(arr));

    }
}
