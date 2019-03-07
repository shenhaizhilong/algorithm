package com.hui.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/6 22:15
 */
public class SpiralMatrix {


    /**
     *54. Spiral Matrix
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
     *
     * Example 1:
     *
     * Input:
     * [
     *  [ 1, 2, 3 ],
     *  [ 4, 5, 6 ],
     *  [ 7, 8, 9 ]
     * ]
     * Output: [1,2,3,6,9,8,7,4,5]
     * Example 2:
     *
     * Input:
     * [
     *   [1, 2, 3, 4],
     *   [5, 6, 7, 8],
     *   [9,10,11,12]
     * ]
     * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>(matrix.length*matrix[0].length);
        if(matrix.length == 0)return ans;

        int r1 = 0, r2 = matrix.length -1;
        int c1 = 0, c2 = matrix[0].length -1;
        while (r1 <= r2 && c1 <= c2)
        {
            for(int c = c1; c <= c2; c++)ans.add(matrix[r1][c]); //top
            for(int r = r1 +1; r <= r2; r++)ans.add(matrix[r][c2]); // right
            if(r1 < r2 && c1 < c2)
            {
                for (int c = c2 -1; c > c1; c--) ans.add(matrix[r2][c]); // bottom
                for(int r = r2; r > r1; r--) ans.add(matrix[r][c1]); // left
            }
            r1++;
            c1++;
            r2--;
            c2--;
        }

        return ans;

    }
}
