package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/7 18:11
 */
public class Searcha2DMatrixII {


    /**
     *
     * 240. Search a 2D Matrix II
     * DescriptionHintsSubmissionsDiscussSolution
     * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
     *
     * Integers in each row are sorted in ascending from left to right.
     * Integers in each column are sorted in ascending from top to bottom.
     * Example:
     *
     * Consider the following matrix:
     *
     * [
     *   [1,   4,  7, 11, 15],
     *   [2,   5,  8, 12, 19],
     *   [3,   6,  9, 16, 22],
     *   [10, 13, 14, 17, 24],
     *   [18, 21, 23, 26, 30]
     * ]
     * Given target = 5, return true.
     *
     * Given target = 20, return false.
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)return false;

        int R = matrix.length;
        int C = matrix[0].length;
        if(target < matrix[0][0] || target > matrix[R-1][C-1])return false;
        int min = Math.min(R,C);
        for (int i = 0; i < min ; i++) {
            if(searchMatrixInRow(matrix,i,C,target))return true;
            if(searchMatrixInCol(matrix,R,i,target))return true;
        }
        return false;
    }

    private boolean searchMatrixInCol(int[][] matrix, int R, int col, int target)
    {
        for (int i = 0; i < R; i++) {
            if(matrix[i][col] == target)return true;
            else if(matrix[i][col] > target)return false;
        }
        return false;
    }

    private boolean searchMatrixInRow(int[][] matrix, int row, int C, int target)
    {
        for (int i = 1; i < C; i++) {
            if(matrix[row][i] == target)return true;
            else if(matrix[row][i] > target)return false;
        }
        return false;
    }


    public boolean searchMatrix2(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)return false;
        int row = 0;
        int endCol = matrix[0].length -1;
        while (row < matrix.length && endCol >=0)
        {
            if(target == matrix[row][endCol])return true;
            if(target < matrix[row][endCol])
            {
                endCol--;
            }else{
                row++;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        int[][] matrix = {
                {1,   4,  7, 11, 15},
                {2,   5,  8, 12, 19},
                {3,   6,  9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        Searcha2DMatrixII searcha2DMatrixII = new Searcha2DMatrixII();
   //     System.out.println(searcha2DMatrixII.searchMatrix(matrix,5));
        System.out.println(searcha2DMatrixII.searchMatrix2(matrix,20));
    }
}
