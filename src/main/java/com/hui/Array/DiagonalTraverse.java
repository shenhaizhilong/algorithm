package com.hui.Array;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/26 9:00
 */
public class DiagonalTraverse {

    /**
     *
     * 498. Diagonal Traverse
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.
     *
     * Example:
     * Input:
     * [
     *  [ 1, 2, 3 ],
     *  [ 4, 5, 6 ],
     *  [ 7, 8, 9 ]
     * ]
     * Output:  [1,2,4,7,5,3,6,8,9]
     * Explanation:
     *
     * Note:
     * The total number of elements of the given matrix will not exceed 10,000.
     *
     *
     *
     *  [ 1, 2, 3 ],
     *  [ 4, 5, 6 ],
     *  [ 7, 8, 9 ]
     *
     *
     *
     *  [0,0] ,    0   up
     *  [0,1],     1   down
     *  [1,0],     2   down
     *  [2,0],     3   up
     *  [1,1],     4   up
     *  [0,2],     5   up
     *  [1,2],     6   down
     *  [2,1],     7   down
     *  [2,2]      8    up
     *
     * @param matrix
     * @return
     */
    public int[] findDiagonalOrder(int[][] matrix) {

        if(matrix.length == 0)return new int[0];
        int r =0, c = 0;
        int R = matrix.length;
        int C = matrix[0].length;
        int[] res = new int[R*C];
        for (int i = 0; i < res.length; i++) {
            res[i] = matrix[r][c];
            if(((r + c ) & 0x01) == 0) // moving up
            {
                if( c == C -1){   // at the right side position, moving down
                    r++;
                }else if(r == 0){  // at the top position, moving right
                    c++;
                }else {
                    r--;   // moving up
                    c++;
                }
            }else { // moving down

                if( r == R -1){   // at the bottom most position, moving right
                    c++;
                }else if(c == 0){  // at the left side position, moving down
                    r++;
                }else {
                    c--;  // moving down
                    r++;
                }
            }
        }

        return res;


    }


    public static void main(String[] args) {
        int[][] matrix = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };
        DiagonalTraverse diagonalTraverse = new DiagonalTraverse();
        System.out.println(Arrays.toString(diagonalTraverse.findDiagonalOrder(matrix)));

    }

}
