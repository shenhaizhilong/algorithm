package com.hui.algorithm;

/**
 *
 * https://leetcode.com/problems/transpose-matrix/description/
 *Transpose Matrix
 * Given a matrix A, return the transpose of A.
 *
 * The transpose of a matrix is the matrix flipped over it's main diagonal, switching the row and column indices of the matrix.
 *
 *
 *
 * Example 1:
 *
 * Input: [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [[1,4,7],[2,5,8],[3,6,9]]
 * Example 2:
 *
 * Input: [[1,2,3],[4,5,6]]
 * Output: [[1,4],[2,5],[3,6]]
 *
 *
 * Note:
 *
 * 1 <= A.length <= 1000
 * 1 <= A[0].length <= 1000
 *
 * @author: shenhaizhilong
 * @date: 2018/8/16 9:52
 */
public class TransposeMatrix {
    public static int[][] transpose(int[][] A) {
        int R = A.length;
        int C = A[0].length;
        int[][] res = new int[C][R];
        for (int i = 0; i < C; i++) {
            for (int j = 0; j < R; j++) {
                res[i][j] = A[j][i];
            }
        }

        return res;
    }

    public static void main(String[] args) {

    }
}
