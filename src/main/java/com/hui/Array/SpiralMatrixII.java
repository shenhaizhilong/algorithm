package com.hui.Array;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/6 22:42
 */
public class SpiralMatrixII {

    /**
     *59. Spiral Matrix II
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
     *
     * Example:
     *
     * Input: 3
     * Output:
     * [
     *  [ 1, 2, 3 ],
     *  [ 8, 9, 4 ],
     *  [ 7, 6, 5 ]
     * ]
     *
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int value = 0;
        int r1 = 0, r2 = matrix.length -1;
        int c1 = 0, c2 = matrix[0].length -1;
        while (r1 <= r2 && c1 <= c2)
        {
            for(int c = c1; c <= c2; c++)
            {
                matrix[r1][c] = ++value; //top
            }
            for(int r = r1 +1; r <= r2; r++)
            {
               matrix[r][c2] = ++value; // right
            }
            if(r1 < r2 && c1 < c2)
            {
                for (int c = c2 -1; c > c1; c--)
                {
                   matrix[r2][c] = ++value; // bottom
                }
                for(int r = r2; r > r1; r--)
                {
                    matrix[r][c1] = ++value; // left
                }
            }
            r1++;
            c1++;
            r2--;
            c2--;
        }
        return matrix;
    }

    public static void main(String[] args) {


        SpiralMatrixII spiralMatrixII = new SpiralMatrixII();
        int[][] matrix = spiralMatrixII.generateMatrix(3);
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
}
