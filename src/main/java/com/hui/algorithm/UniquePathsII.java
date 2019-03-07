package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/8 12:31
 */
public class UniquePathsII {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m + 1][n +1];

        dp[0][1] =1;
        for (int i = 1; i <= m ; i++) {
            for (int j = 1; j <= n ; j++) {
                if(obstacleGrid[i -1][j -1] != 1)
                {
                        dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }

            }
        }
        return dp[m][n];
    }


    /**
     * obstacleGrid =  0,0,0
     *                 0,1,0
     *                 0,0,0
     *    index of dp 0,1,2,3
     *   first time   0,1,1,1
     *   sec   time   0,1,0,1
     *   third time   0,1,1,2
     *
     *   ******************
     * obstacleGrid =  0,0,0
     *                 0,0,0
     *                 0,0,0
     *    index of dp 0,1,2,3
     *   first time   0,1,1,1
     *   sec   time   0,1,2,3
     *   third time   0,1,3,6
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 0; i < m ; i++) {
            for (int j = 1; j <=n; j++) {
                if(obstacleGrid[i][j-1] == 1){
                    dp[j] = 0;
                }else {
                    dp[j] += dp[j -1];
                }
            }
        }
        return dp[n];
    }
    public static void main(String[] args) {


        int[][] obstacleGrid = {{0,0,0},{0,0,0},{0,0,0}};
        UniquePathsII uniquePathsII = new UniquePathsII();
        System.out.println(uniquePathsII.uniquePathsWithObstacles2(obstacleGrid));
    }
}
