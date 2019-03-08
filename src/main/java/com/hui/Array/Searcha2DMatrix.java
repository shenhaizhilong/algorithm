package com.hui.Array;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/7 17:44
 */
public class Searcha2DMatrix {


    /**
     *74. Search a 2D Matrix
     * DescriptionHintsSubmissionsDiscussSolution
     * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
     *
     * Integers in each row are sorted from left to right.
     * The first integer of each row is greater than the last integer of the previous row.
     * Example 1:
     *
     * Input:
     * matrix = [
     *   [1,   3,  5,  7],
     *   [10, 11, 16, 20],
     *   [23, 30, 34, 50]
     * ]
     * target = 3
     * Output: true
     * Example 2:
     *
     * Input:
     * matrix = [
     *   [1,   3,  5,  7],
     *   [10, 11, 16, 20],
     *   [23, 30, 34, 50]
     * ]
     * target = 13
     * Output: false
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0)return false;

        int R = matrix.length;
        int C = matrix[0].length;
        int start = 0;
        int end = R*C -1;

        while (start <= end)
        {
            int middle = (start + end)>>>1;

            // convert 2D array index to  1D array index = r*C + c, r is 0...R-1, c is 0....C-1
            int c = middle %C;
            int r = middle/C;
            if(matrix[r][c] > target)
            {
                end = middle -1;
            }else if(matrix[r][c] < target)
            {
                start = middle + 1;
            }else if(matrix[r][c] == target)
            {
                return true;
            }
        }

        return false;

    }


    public static void main(String[] args) {
        Searcha2DMatrix searcha2DMatrix = new Searcha2DMatrix();
        int[][] matrix1 = {{1,3,5,7},{10,11,16,20},{23,30,34,50}};
        System.out.println(searcha2DMatrix.searchMatrix(matrix1,3));
        System.out.println(searcha2DMatrix.searchMatrix(matrix1,13));
    }
}
