package com.hui.algorithm;


import static com.hui.algorithm.Matrix.print;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/21 11:02
 *
 *132. Palindrome Partitioning II
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 *
 * Return the minimum cuts needed for a palindrome partitioning of s.
 *
 * Example:
 *
 * Input: "aab"
 * Output: 1
 * Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
 *
 */
public class PalindromePartitioningII {


    public int minCut(String s) {

        int N = s.length();
        int[] cut = new int[N];  // 分割长度为N 的字符串所需要的最小切割数

        // isPalin[j][i]  means   whether s.substring(j,i +1) is palindrome
        boolean[][] isPalin = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            int Min = N;
            for (int j = 0; j <= i; j++) {
                if(s.charAt(j) == s.charAt(i) && ((j +1 > i -1) || isPalin[j+1][i -1]))
                {
                    isPalin[j][i] = true;
                    Min = j == 0 ? 0: Math.min(Min, cut[j -1] +1);
                }
            }

            cut[i] = Min;
            print(cut);
        }

        print(isPalin);

        return cut[N -1];
    }



    public static void main(String[] args) {

        PalindromePartitioningII palindromePartitioningII = new PalindromePartitioningII();
        System.out.println(palindromePartitioningII.minCut("abacdcdd"));
    }

}
