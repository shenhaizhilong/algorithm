package com.hui.Array;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/3 15:45
 */
public class SpiralMatrixIII {

    /**
     *
     * 885. Spiral Matrix III
     * DescriptionHintsSubmissionsDiscussSolution
     * On a 2 dimensional grid with R rows and C columns, we start at (r0, c0) facing east.
     *
     * Here, the north-west corner of the grid is at the first row and column, and the south-east corner of the grid is at the last row and column.
     *
     * Now, we walk in a clockwise spiral shape to visit every position in this grid.
     *
     * Whenever we would move outside the boundary of the grid, we continue our walk outside the grid (but may return to the grid boundary later.)
     *
     * Eventually, we reach all R * C spaces of the grid.
     *
     * Return a list of coordinates representing the positions of the grid in the order they were visited.
     *
     *
     *
     * Example 1:
     *
     * Input: R = 1, C = 4, r0 = 0, c0 = 0
     * Output: [[0,0],[0,1],[0,2],[0,3]]
     *
     *
     *
     *
     * Example 2:
     *
     * Input: R = 5, C = 6, r0 = 1, c0 = 4
     * Output: [[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],[3,4],[3,3],[3,2],[2,2],[1,2],[0,2],[4,5],[4,4],[4,3],[4,2],[4,1],[3,1],[2,1],[1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]
     *
     *
     * @param R
     * @param C
     * @param r0
     * @param c0
     * @return
     */
    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {

        int[][] results = new int[R*C][2];

        // right, r didn't change, c increase 1;
        // down, r increase 1, c didn't change;
        // left, r didn't change, c decrease 1;
        // up, r decrease 1, c didn't change
        // increase or decrease in 4 directions.
        int[] dr = new int[]{0, 1, 0, -1};
        int[] dc = new int[]{1, 0, -1, 0};

        int position = 0;
        results[position++] = new int[]{r0, c0};
        if(R*C == 1)return results;
        int k = 1;
        while (position < R*C)
        {
            for (int i = 0; i < 4; i++) {  // i is the  direction index
                int dk = k + i/2;        // numbers of steps in this direction: steps == { k,k,k +1,k +1}, k = 1, 3,5,7,...., so steps == {1,1,2,2,3,3,4,4,5,5,6,6,.....}
                for (int j = 0; j < dk; j++) {  // for each step in this direction
                    r0 += dr[i];
                    c0 += dc[i];
                    if(0 <= r0 && 0 <= c0 && r0 < R && c0 < C)
                    {
                        results[position++] = new int[]{r0, c0};
                        if(position == R*C)return results;
                    }

                }
            }


            k += 2;
        }


        return results;


    }

    public static void main(String[] args) {
        SpiralMatrixIII spiralMatrixIII = new SpiralMatrixIII();
//        int[][] res = spiralMatrixIII.spiralMatrixIII(5,6, 1, 4);
//
//        for(int[] r:res)
//        {
//            System.out.println(Arrays.toString(r));
//        }

        int[][] res =  spiralMatrixIII.spiralMatrixIII(1,4, 0, 0);
        for(int[] r:res)
        {
            System.out.println(Arrays.toString(r));
        }
    }
}
