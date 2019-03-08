package com.hui.DynamicPrograming;


/**
 *
 *
 * 62. Unique Paths
 * DescriptionHintsSubmissionsDiscussSolution
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *
 * How many possible unique paths are there?
 *
 *
 * Above is a 7 x 3 grid. How many possible unique paths are there?
 *
 * Note: m and n will be at most 100.
 *
 * Example 1:
 *
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation:
 * From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down
 * 2. Right -> Down -> Right
 * 3. Down -> Right -> Right
 * Example 2:
 *
 * Input: m = 7, n = 3
 * Output: 28
 * @author: shenhaizhilong
 * @date: 2018/9/7 13:19
 */
public class UniquePaths {


    /**
     * 递归
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths1(int m, int n) {
        if(m == 0|| n == 0)return 0;
        if(m == 1 || n == 1)return 1;
        return uniquePaths1(m-1, n) + uniquePaths1(m,n -1);
    }


    /**
     *
     * DP
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        for (int i = 0; i < m ; i++) {
            for (int j = 0; j < n ; j++) {
              if(i == 0 || j == 0)
              {
                  dp[i][j] = 1;
              }else{
                  dp[i][j] = dp[i-1][j] + dp[i][j-1];
              }

            }
        }
        return dp[m-1][n-1];
    }


    /**
     *
     *
     * This is a combinatorial problem and can be solved without DP. For mxn grid, robot has to move exactly m-1 steps down and n-1 steps right and these can be done in any order.
     *
     * For the eg., given in question, 3x7 matrix, robot needs to take 2+6 = 8 steps with 2 down and 6 right in any order. That is nothing but a permutation problem. Denote down as 'D' and right as 'R', following is one of the path :-
     *
     * D R R R D R R R
     *
     * We have to tell the total number of permutations of the above given word. So, decrease both m & n by 1 and apply following formula:-
     *
     * Total permutations = (m+n)! / (m! * n!)
     *
     *
     * https://leetcode.com/problems/unique-paths/discuss/22958/Math-solution-O(1)-space
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths3(int m, int n) {
        if(m == 1 || n == 1)
            return 1;
        m--;
        n--;
        if(m < n) {              // Swap, so that m is the bigger number
          m = m^n;
          n = m^n;
          m = n^m;
        }
        long res = 1;
        int j = 1;
        for(int i = m+1; i <= m+n; i++, j++){       // Instead of taking factorial, keep on multiply & divide
            res *= i;
            res /= j;
        }

        return (int)res;
    }


    /**
     *
     * time O(m*n) space O(n)
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths4(int m, int n) {

        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j <=n ; j++) {
                dp[j] += dp[j -1];
            }
        }

        return dp[n];
    }



    public static void main(String[] args) {
        UniquePaths uniquePaths = new UniquePaths();
        System.out.println(uniquePaths.uniquePaths(3,3));
        System.out.println(uniquePaths.uniquePaths(7,3));
        System.out.println(uniquePaths.uniquePaths3(7,3));

        System.out.println(uniquePaths.uniquePaths4(3,3));
        System.out.println(uniquePaths.uniquePaths4(7,3));
        System.out.println(uniquePaths.uniquePaths4(7,3));
    }
}
