package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/5 23:43
 */
public class ScoreAfterFlippingMatrix {

    /**
     *861. Score After Flipping Matrix
     * DescriptionHintsSubmissionsDiscussSolution
     * We have a two dimensional matrix A where each value is 0 or 1.
     *
     * A move consists of choosing any row or column, and toggling each value in that row or column: changing all 0s to 1s, and all 1s to 0s.
     *
     * After making any number of moves, every row of this matrix is interpreted as a binary number, and the score of the matrix is the sum of these numbers.
     *
     * Return the highest possible score.
     *
     *
     *
     * Example 1:
     *
     * Input: [[0,0,1,1],[1,0,1,0],[1,1,0,0]]
     * Output: 39
     * Explanation:
     * Toggled to [[1,1,1,1],[1,0,0,1],[1,1,1,1]].
     * 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
     *
     *
     * Note:
     *
     * 1 <= A.length <= 20
     * 1 <= A[0].length <= 20
     * A[i][j] is 0 or 1.
     *
     * @param A
     * @return
     */
    public int matrixScore(int[][] A) {
        int R = A.length;
        int C = A[0].length;
        int res = 0;
        //for every col
        for (int i = 0; i < C; i++) {

            int colSum = 0;
            //for every row
            for (int j = 0; j < R; j++) {
                colSum += A[j][i]^A[j][0];
            }

            res += Math.max(colSum, R - colSum)*(1<<(C - 1 -i));
        }
        return res;
    }

    public static void main(String[] args) {
        ScoreAfterFlippingMatrix matrix = new ScoreAfterFlippingMatrix();
        System.out.println(matrix.matrixScore(new int[][]{{0,0,1,1},{1,0,1,0},{1,1,0,0}}));
    }
}
