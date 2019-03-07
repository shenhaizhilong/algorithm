package com.hui.algorithm;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/15 16:31
 */
public class Matrix01 {


    /**
     *542. 01 Matrix
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
     *
     * The distance between two adjacent cells is 1.
     * Example 1:
     * Input:
     *
     * 0 0 0
     * 0 1 0
     * 0 0 0
     * Output:
     * 0 0 0
     * 0 1 0
     * 0 0 0
     * Example 2:
     * Input:
     *
     * 0 0 0
     * 0 1 0
     * 1 1 1
     * Output:
     * 0 0 0
     * 0 1 0
     * 1 2 1
     * Note:
     * The number of elements of the given matrix will not exceed 10,000.
     * There are at least one 0 in the given matrix.
     * The cells are adjacent in only four directions: up, down, left and right.
     *
     * @param matrix
     * @return
     */
    public int[][] updateMatrix(int[][] matrix) {
        if(matrix == null || matrix.length == 0)return matrix;
        int R = matrix.length;
        int C = matrix[0].length;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if(matrix[r][c] == 1 && !hasNeighborZero(matrix, r,c))
                {
                    matrix[r][c] = R + C ;  // if it's 1 and no neighbors are zero, we update it's value to R + C ;
                }
            }
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if(matrix[r][c] == 1)      // Just explore where matrix[r][c] = 1
                {
                    dfs(matrix,r,c,1);
                }
            }
        }

        return matrix;

    }

    private boolean hasNeighborZero(int[][] matrix, int r, int c)
    {
        if(r > 0 && matrix[r-1][c] == 0)return true;
        if(c > 0 && matrix[r][c-1] == 0)return true;
        if(r < matrix.length -1 && matrix[r +1][c] == 0)return true;
        if(c < matrix[0].length -1 && matrix[r][c + 1] == 0)return true;
        return false;
    }

    private void dfs(int[][] matrix, int r, int c, int currVal)
    {
        // if currVal > matrix[r][c] we don't need to explore further.
        if(r < 0 || c < 0 || r > matrix.length -1 || c > matrix[0].length -1 || matrix[r][c] <= currVal && currVal != 1)return;
        matrix[r][c] = currVal;
        dfs(matrix, r + 1, c, currVal + 1); // down
        dfs(matrix, r - 1, c, currVal + 1); // up
        dfs(matrix, r, c + 1, currVal + 1); // right
        dfs(matrix, r, c - 1, currVal + 1); // left
    }



    public int[][] updateMatrixDP(int[][] matrix) {
        if(matrix == null || matrix.length == 0)return matrix;
        int R = matrix.length;
        int C = matrix[0].length;
        int[][] dp = new int[R][C];
        int max = R + C;
        for (int i = 0; i < R ; i++) {
            Arrays.fill(dp[i],max);
        }


        // from top- bottom, left -> right.
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(matrix[i][j] == 0)
                {
                    dp[i][j] = 0;
                }else {
                    if( i > 0)
                    {
                        dp[i][j] = Math.min(dp[i][j], dp[i -1][j] + 1); // check with it's up neighbor
                    }
                    if(j > 0)
                    {
                        dp[i][j] = Math.min(dp[i][j], dp[i][j -1] + 1); // check with it's left neighbor
                    }
                }
            }
        }

        for (int i = R -1; i >=0 ; i--) {
            for (int j = C -1; j >=0 ; j--) {
                if(i < R -1)
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1);  // check with it's down neighbor
                if(j < C -1)
                    dp[i][j] = Math.min(dp[i][j], dp[i][j + 1] + 1);//check with it's right neighbor
            }
        }

        return dp;

    }
    public static void main(String[] args) {
        int[][] matrix = {{0,0,0},{0,1,0},{1,1,1}};
        int[][] matrix2 = {{0,0,0},{0,1,0},{0,0,0}};
        Matrix01 matrix01 = new Matrix01();
        int[][] res = matrix01.updateMatrix(matrix);
        for (int[] r :
                res) {
            System.out.println(Arrays.toString(r));
        }

        int[][] res2 = matrix01.updateMatrixDP(matrix);
        for (int[] r :
                res2) {
            System.out.println(Arrays.toString(r));
        }
    }

}
