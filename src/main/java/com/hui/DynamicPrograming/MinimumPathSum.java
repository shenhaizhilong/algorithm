package com.hui.DynamicPrograming;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/9 8:14
 */
public class MinimumPathSum {

    private int minSum = Integer.MAX_VALUE;
    public int minPathSum(int[][] grid) {
        backTracing(grid,0,0,0);
        return minSum;
    }

    private void backTracing(int[][] grid, int row, int col, int sum)
    {
        sum += grid[row][col];
        if(row == grid.length -1 && col == grid[0].length -1)
        {
            minSum = Math.min(minSum,sum);
            return;
        }

        if(row < grid.length -1 && col < grid[0].length -1)
        {
            backTracing(grid, row + 1, col, sum);// go down
            backTracing(grid, row, col + 1, sum); // go right
        }else if(row < grid.length -1 && col == grid[0].length -1)
        {
            backTracing(grid,row + 1, col, sum); // go down
        }else if(row == grid.length -1 && col < grid[0].length -1)
        {
            backTracing(grid,row,col +1, sum); // go right
        }else {
            return;
        }

    }

    private int distance(int[][] dist, int row, int col)
    {
        if(row < 0 || col < 0)return Integer.MAX_VALUE;
        return dist[row][col];
    }

    /**
     *
     * dp method:
     * dp[0][0] = grid[0][0];
     *
     * dp[i][j] = = min(dp[i-1][j] ,dp[i][j-1]) + grid[i][j];
     *
     * @param grid
     * @return
     */

    public int minPathSum2(int[][] grid) {
        if(grid == null || grid.length == 0)return 0;
        int R = grid.length;
        int C = grid[0].length;
        int[][] dp = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(i == 0 && j == 0)
                {
                    dp[0][0] = grid[0][0];
                }else
                {
                    dp[i][j] = Math.min(distance(dp,i-1, j), distance(dp,i,j - 1)) + grid[i][j];
                }

            }
        }

        return dp[R-1][C-1];
    }


    /**
     *
     *     dp method:
     *     dp[0][0] = grid[0][0];
     *
     *     dp[i][j] = = min(dp[i-1][j] ,dp[i][j-1]) + grid[i][j];
     *
     *
     *     dp[j +1] min value ending in grid[i][j];
     *     dp[j +1] = min(dp[j +1],dp[j]) + grid[i][j];
     * @param grid
     * @return
     */
    public int minPathSum3(int[][] grid)
    {
        if(grid == null || grid.length == 0)return 0;
        int R = grid.length;
        int C = grid[0].length;
        int[] dp = new int[C +1];
        for (int i = 0; i < C ; i++) {

            dp[i +1] = dp[i] + grid[0][i];
        }

        dp[0] = Integer.MAX_VALUE;
        for (int i = 1; i < R; i++) {
            for (int j = 0; j < C; j++) {
                dp[j +1] = Math.min(dp[j +1],dp[j]) + grid[i][j];
            }
        }

        return dp[C];
    }
    public static void main(String[] args) {
        MinimumPathSum minimumPathSum = new MinimumPathSum();
//       int r = minimumPathSum.minPathSum(
//               new int[][]{ {1,3,1},
//                            {1,5,1},
//                            {4,2,1}});
//        System.out.println(r);


        int[][] matrix = {{5,0,1,1,2,1,0,1,3,6,3,0,7,3,3,3,1},
                {1,4,1,8,5,5,5,6,8,7,0,4,3,9,9,6,0},
                {2,8,3,3,1,6,1,4,9,0,9,2,3,3,3,8,4},
                {3,5,1,9,3,0,8,3,4,3,4,6,9,6,8,9,9},
                {3,0,7,4,6,6,4,6,8,8,9,3,8,3,9,3,4},
                {8,8,6,8,3,3,1,7,9,3,3,9,2,4,3,5,1},
                {7,1,0,4,7,8,4,6,4,2,1,3,7,8,3,5,4},
                {3,0,9,6,7,8,9,2,0,4,6,3,9,7,2,0,7},
                {8,0,8,2,6,4,4,0,9,3,8,4,0,4,7,0,4},
                {3,7,4,5,9,4,9,7,9,8,7,4,0,4,2,0,4},
                {5,9,0,1,9,1,5,9,5,5,3,4,6,9,8,5,6},
                {5,7,2,4,4,4,2,1,8,4,8,0,5,4,7,4,7},
                {9,5,8,6,4,4,3,9,8,1,1,8,7,7,3,6,9},
                {7,2,3,1,6,3,6,6,6,3,2,3,9,9,4,4,8}};
       // System.out.println(minimumPathSum.minPathSum(matrix));
        System.out.println(minimumPathSum.minPathSum2(matrix));
        System.out.println(minimumPathSum.minPathSum3(matrix));
    }
}
