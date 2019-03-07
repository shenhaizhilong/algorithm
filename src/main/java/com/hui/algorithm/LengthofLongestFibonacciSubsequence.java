package com.hui.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/6 21:46
 *
 *873. Length of Longest Fibonacci Subsequence
 * DescriptionHintsSubmissionsDiscussSolution
 * A sequence X_1, X_2, ..., X_n is fibonacci-like if:
 *
 * n >= 3
 * X_i + X_{i+1} = X_{i+2} for all i + 2 <= n
 * Given a strictly increasing array A of positive integers forming a sequence, find the length of the longest fibonacci-like subsequence of A.  If one does not exist, return 0.
 *
 * (Recall that a subsequence is derived from another sequence A by deleting any number of elements (including none) from A, without changing the order of the remaining elements.  For example, [3, 5, 8] is a subsequence of [3, 4, 5, 6, 7, 8].)
 *
 *
 *
 * Example 1:
 *
 * Input: [1,2,3,4,5,6,7,8]
 * Output: 5
 * Explanation:
 * The longest subsequence that is fibonacci-like: [1,2,3,5,8].
 * Example 2:
 *
 * Input: [1,3,7,11,12,14,18]
 * Output: 3
 * Explanation:
 * The longest subsequence that is fibonacci-like:
 * [1,11,12], [3,11,14] or [7,11,18].
 *
 *
 * Note:
 *
 * 3 <= A.length <= 1000
 * 1 <= A[0] < A[1] < ... < A[A.length - 1] <= 10^9
 * (The time limit has been reduced by 50% for submissions in Java, C, and C++.)
 *
 *
 */
public class LengthofLongestFibonacciSubsequence {


    public int lenLongestFibSubseq(int[] A) {

        if(A == null || A.length < 3)return 0;
        Map<Integer,Integer> map = new HashMap<>(A.length);
        for (int i = 0; i < A.length; i++) {
            map.put(A[i], i);
        }
        int N = A.length;
        int[][] mem = new int[N][N];

        int max = 0;
        for (int i = 2; i < A.length ; i++) {
            for (int j = i -1; j >=0 ; j--) {
                if(!map.containsKey(A[i] - A[j]))continue;
                max = Math.max(max, fibOperation(A, map, i,j, mem) + 2);
            }
        }
        return  max;
    }

    private int fibOperation(int[] arr, Map<Integer, Integer> map, int ida, int idb, int[][] mem)
    {
        int ta = ida;
        int tb = idb;
        int ans = 0;
        while (idb >= 0)
        {

            if(mem[ida][idb] !=0)return ans + mem[ida][idb];
            int idc = map.getOrDefault(arr[ida] - arr[idb], -1);
            if(idc >= idb || idc <0)break;
            ans++;
            ida = idb;
            idb = idc;
        }

        mem[ta][tb] = ans;
        return ans;

    }


    public int lenLongestFibSubseq2(int[] A) {

        if(A == null || A.length < 3)return 0;
        Map<Integer,Integer> map = new HashMap<>(A.length);
        int N = A.length;
        int[][] mem = new int[N][N];
        int max = 0;
        for (int R = 0; R < A.length ; R++) {
            map.put(A[R], R);
            for (int L = 0; L < R ; L++) {
                int newL = map.getOrDefault(A[R] - A[L], -1);
                mem[R][L] = (A[R] - A[L] < A[L] && newL >= 0) ? mem[L][newL] + 1: 0;
                max = Math.max(max, mem[R][L] + 2);
            }
        }
        return  max < 3 ? 0: max;
    }

    public static void main(String[] args) {


        LengthofLongestFibonacciSubsequence fibonacciSubsequence = new LengthofLongestFibonacciSubsequence();
       System.out.println(fibonacciSubsequence.lenLongestFibSubseq(new int[]{1,2,3,4,5,6,7,8}));
        System.out.println(fibonacciSubsequence.lenLongestFibSubseq(new int[]{1,3,7,11,12,14,18}));

        System.out.println(fibonacciSubsequence.lenLongestFibSubseq2(new int[]{1,2,3,4,5,6,7,8}));
        System.out.println(fibonacciSubsequence.lenLongestFibSubseq2(new int[]{1,3,7,11,12,14,18}));
    }
}
