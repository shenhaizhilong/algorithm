package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/25 10:10
 */
public class MagicSquaresInGrid {

    /**
     * https://leetcode.com/problems/magic-squares-in-grid/description/
     * 840. Magic Squares In Grid
     * DescriptionHintsSubmissionsDiscussSolution
     * A 3 x 3 magic square is a 3 x 3 grid filled with distinct numbers from 1 to 9 such that each row, column, and both diagonals all have the same sum.
     *
     * Given an grid of integers, how many 3 x 3 "magic square" subgrids are there?  (Each subgrid is contiguous).
     *
     *
     *
     * Example 1:
     *
     * Input: [[4,3,8,4],
     *         [9,5,1,9],
     *         [2,7,6,2]]
     * Output: 1
     * Explanation:
     * The following subgrid is a 3 x 3 magic square:
     * 438
     * 951
     * 276
     *
     * while this one is not:
     * 384
     * 519
     * 762
     *
     * In total, there is only one magic square inside the given grid.
     * Note:
     *
     * 1 <= grid.length <= 10
     * 1 <= grid[0].length <= 10
     * 0 <= grid[i][j] <= 15
     *
     * @param grid
     * @return
     */
    public int numMagicSquaresInside(int[][] grid) {
        int R = grid.length;
        int C = grid[0].length;
        int sum = 0;
        for (int i = 0; i < R - 2; i++) {
            for (int j = 0; j < C -2; j++) {
                if(grid[i +1][j +1] == 5)
                {
                    if(isMagicSquare(grid[i][j], grid[i][j +1], grid[i][j +2],
                                            grid[i+1][j], grid[i+1][j +1],grid[i+1][j +2],
                                            grid[i+2][j], grid[i+2][j +1], grid[i+2][j +2]))
                    {
                        sum++;
                    }
                }
            }
        }

        return sum;
    }

    private boolean isMagicSquare(int ... vals)
    {
       int[] count = new int[16];
       for(int v: vals){
           count[v]++;
            if(v > 9 || v <1)return false;
       }
        for (int i = 1; i < 10; i++) {
            if(count[i] != 1)return false;
        }
        return (vals[0] + vals[1] + vals[2] == 15 &&
                vals[3] + vals[4] + vals[5] == 15 &&
                vals[6] + vals[7] + vals[8] == 15 &&
                vals[0] + vals[3] + vals[6] == 15 &&
                vals[1] + vals[4] + vals[7] == 15 &&
                vals[2] + vals[5] + vals[8] == 15 &&
                vals[0] + vals[4] + vals[8] == 15 &&
                vals[2] + vals[4] + vals[6] == 15
        );

    }

}
