package com.hui.DynamicPrograming;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/13 18:43
 */
public class MaximumLengthofRepeatedSubarray {

    /**
     *
     * O(MN) space
     * @param A
     * @param B
     * @return
     */
    public int findLength(int[] A, int[] B) {
        if(A == null || B == null || A.length == 0 || B.length == 0)return 0;
        int Rows = A.length;
        int Cols = B.length;

        // dp[i][j] is the length of longest common subarray ending with A[i-1], B[j-1]
        int[][] dp = new int[Rows + 1][Cols + 1];
        int maxLen = 0;
        for (int i = 1; i <=Rows ; i++) {
            for (int j = 1; j <= Cols; j++) {
                if(A[i -1] == B[j-1])
                {
                    dp[i][j] = 1 + dp[i-1][j-1];
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
            }
        }
        return maxLen;
    }


    /**
     *
     * O(M) space
     * @param A
     * @param B
     * @return
     */
    public int findLength2(int[] A, int[] B) {
        if(A == null || B == null || A.length == 0 || B.length == 0)return 0;
        int Rows = A.length;
        int Cols = B.length;

        // dp[j] is the length of longest common subarray ending with A[i-1], B[j-1]
        int[] dp = new int[Cols + 1];
        int maxLen = 0;
        for (int i = 1; i <=Rows ; i++) {
            for (int j =  Cols; j > 0 ; j--) {
                if(A[i -1] == B[j-1])
                {
                    dp[j] = 1 + dp[j-1] ;
                    maxLen = Math.max(maxLen, dp[j]);
                }else {
                    dp[j] = 0;
                }
            }
        }
        return maxLen;
    }


    public static void main(String[] args) {

        MaximumLengthofRepeatedSubarray maximumLengthofRepeatedSubarray = new MaximumLengthofRepeatedSubarray();
       // System.out.println(maximumLengthofRepeatedSubarray.findLength(new int[]{1,2,3,2,1,5},new int[]{3,2,1,5,4}));
      //  System.out.println(maximumLengthofRepeatedSubarray.findLength2(new int[]{1,2,3,2,1},new int[]{3,2,1,4,7}));
        System.out.println(maximumLengthofRepeatedSubarray.findLength2(new int[]{1,0,0,0,1},new int[]{1,0,0,1,1}));
    }
}
