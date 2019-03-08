package com.hui.Array;

/**
 * @author: shenhaizhilong
 * @date: 2018/7/5 15:42
 */
public class Rotate {
    public void rotate(int[][] matrix) {

        shiftUp(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                swap(matrix,i,j);
            }
        }
    }

    public void shiftUp(int[][] matrix)
    {
        for (int i = 0; i < matrix.length/2; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[matrix.length -i-1][j];
                matrix[matrix.length -i-1][j] = temp;
            }
        }
    }

    public void swap(int[][] a, int i, int j)
    {
        int temp = a[i][j];
        a[i][j] = a[j][i];
        a[j][i] = temp;
    }


}
