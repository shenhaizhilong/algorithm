package com.hui.algorithm;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/9 7:28
 */
public class SetMatrixZeroes {


    /**
     *
     * 73. Set Matrix Zeroes
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.
     *
     * Example 1:
     *
     * Input:
     * [
     *   [1,1,1],
     *   [1,0,1],
     *   [1,1,1]
     * ]
     * Output:
     * [
     *   [1,0,1],
     *   [0,0,0],
     *   [1,0,1]
     * ]
     * Example 2:
     *
     * Input:
     * [
     *   [0,1,2,0],
     *   [3,4,5,2],
     *   [1,3,1,5]
     * ]
     * Output:
     * [
     *   [0,0,0,0],
     *   [0,4,5,0],
     *   [0,3,1,0]
     * ]
     * Follow up:
     *
     * A straight forward solution using O(mn) space is probably a bad idea.
     * A simple improvement uses O(m + n) space, but still not the best solution.
     * Could you devise a constant space solution?
     *
     *
     * we use first row and first col save the zero states.
     *
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;
        boolean firstRowZero = false;
        boolean firstColZero = false;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(matrix[i][j] ==0)
                {
                   if(i == 0)firstRowZero = true;
                   if(j == 0)firstColZero = true;
                   matrix[i][0] = 0;
                   matrix[0][j] = 0;
                }

            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if(matrix[i][0] == 0 || matrix[0][j] == 0)
                {
                    matrix[i][j] = 0;
                }
            }
        }

        if(firstRowZero)
        {
            for (int i = 0; i < n ; i++) {
                matrix[0][i] = 0;
            }
        }

        if(firstColZero)
        {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    public static void main(String[] args) {

        SetMatrixZeroes  setMatrixZeroes = new SetMatrixZeroes();
        int[][] matrix = {{1,2,0},{0,1,2},{3,4,5}};
        setMatrixZeroes.setZeroes(matrix);
        for (int[] m: matrix)
        {
            System.out.println(Arrays.toString(m));
        }
    }
}
