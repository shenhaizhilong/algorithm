package com.hui.DynamicPrograming;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/14 20:08
 *
 *
 *
 * 474. Ones and Zeroes
 * DescriptionHintsSubmissionsDiscussSolution
 * In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.
 *
 * For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings consisting of only 0s and 1s.
 *
 * Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.
 *
 * Note:
 * The given numbers of 0s and 1s will both not exceed 100
 * The size of given string array won't exceed 600.
 * Example 1:
 * Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
 * Output: 4
 *
 * Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”
 * Example 2:
 * Input: Array = {"10", "0", "1"}, m = 1, n = 1
 * Output: 2
 *
 * Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".
 *
 *
 *
 * knapsack problems
 */
public class OnesandZeroes {

    public int findMaxForm(String[] strs, int m, int n) {
        int L = strs.length;
        int[][][] dp = new int[L +1][m +1][n +1];
        for (int i = 1; i <= strs.length; i++) {
            String str = strs[i -1];
            // counter[0] is the number of zeros in str
            // counter[1] is the number of ones in str
            int[] counter = new int[2];
            for(char c:str.toCharArray())
            {
                counter[c-'0']++;
            }

            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    if(j >= counter[0] && k >= counter[1])
                    {
                        dp[i][j][k] = Math.max(dp[i -1][j][k], dp[i -1][j - counter[0]][k - counter[1]] +1);
                    }else {
                        dp[i][j][k] = dp[i-1][j][k];
                    }
                }
            }
        }

        return dp[L][m][n];
    }

    public int findMaxForm2(String[] strs, int m, int n) {
        int L = strs.length;
        int[][][] dp = new int[L +1][m +1][n +1];
        for (int i = 1; i <= strs.length; i++) {
            String str = strs[i -1];
            // counter[0] is the number of zeros in str
            // counter[1] is the number of ones in str
            int[] counter = new int[2];
            for(char c:str.toCharArray())
            {
                counter[c-'0']++;
            }

            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    dp[i][j][k] = dp[i-1][j][k];
                    if(j >= counter[0] && k >= counter[1])
                    {
                        dp[i][j][k] = Math.max(dp[i -1][j][k], dp[i -1][j - counter[0]][k - counter[1]] +1);
                    }
                }
            }
        }

        return dp[L][m][n];
    }


    public int findMaxForm3(String[] strs, int m, int n) {
        int L = strs.length;
        int[][] dp = new int[m +1][n +1];
        for (String str: strs) {

            // counter[0] is the number of zeros in str
            // counter[1] is the number of ones in str
            int[] counter = new int[2];
            for(char c:str.toCharArray())
            {
                counter[c-'0']++;
            }

            for (int j = m; j >=0; j--) {
                for (int k = n; k >=0; k--) {
                    dp[j][k] = dp[j][k];
                    if(j >= counter[0] && k >= counter[1])
                    {
                        dp[j][k] = Math.max(dp[j][k], dp[j - counter[0]][k - counter[1]] +1);
                    }
                }
            }
        }

        return dp[m][n];
    }

    public int findMaxForm4(String[] strs, int m, int n) {
        int[][] dp = new int[m +1][n +1];
        for (String str: strs) {

            // counter[0] is the number of zeros in str
            // counter[1] is the number of ones in str
            int[] counter = new int[2];
            for(char c:str.toCharArray())
            {
                counter[c-'0']++;
            }

            for (int j = m; j >=counter[0]; j--) {
                for (int k = n; k >=counter[1]; k--) {
                        dp[j][k] = Math.max(dp[j][k], dp[j - counter[0]][k - counter[1]] +1);
                }
            }
        }

        return dp[m][n];
    }







    public static void main(String[] args) {

        OnesandZeroes onesandZeroes = new OnesandZeroes();
        System.out.println(onesandZeroes.findMaxForm(new String[]{"10","0001","111001","1","0"},5,3));
        System.out.println(onesandZeroes.findMaxForm(new String[]{"10","1","0"},1,1));
        System.out.println(onesandZeroes.findMaxForm2(new String[]{"10","0001","111001","1","0"},5,3));
        System.out.println(onesandZeroes.findMaxForm2(new String[]{"10","1","0"},1,1));

        System.out.println(onesandZeroes.findMaxForm3(new String[]{"10","0001","111001","1","0"},5,3));
        System.out.println(onesandZeroes.findMaxForm3(new String[]{"10","1","0"},1,1));

        System.out.println(onesandZeroes.findMaxForm4(new String[]{"10","0001","111001","1","0"},5,3));
        System.out.println(onesandZeroes.findMaxForm4(new String[]{"10","1","0"},1,1));


    }
}
