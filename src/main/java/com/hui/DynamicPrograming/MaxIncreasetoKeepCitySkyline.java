package com.hui.DynamicPrograming;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/23 22:59
 *
 *807. Max Increase to Keep City Skyline
 * DescriptionHintsSubmissionsDiscussSolution
 * In a 2 dimensional array grid, each value grid[i][j] represents the height of a building located there. We are allowed to increase the height of any number of buildings, by any amount (the amounts can be different for different buildings). Height 0 is considered to be a building as well.
 *
 * At the end, the "skyline" when viewed from all four directions of the grid, i.e. top, bottom, left, and right, must be the same as the skyline of the original grid. A city's skyline is the outer contour of the rectangles formed by all the buildings when viewed from a distance. See the following example.
 *
 * What is the maximum total sum that the height of the buildings can be increased?
 *
 * Example:
 * Input: grid = [[3,0,8,4],[2,4,5,7],[9,2,6,3],[0,3,1,0]]
 * Output: 35
 * Explanation:
 * The grid is:
 * [ [3, 0, 8, 4],
 *   [2, 4, 5, 7],
 *   [9, 2, 6, 3],
 *   [0, 3, 1, 0] ]
 *
 * The skyline viewed from top or bottom is: [9, 4, 8, 7]
 * The skyline viewed from left or right is: [8, 7, 9, 3]
 *
 * The grid after increasing the height of buildings without affecting skylines is:
 *
 * gridNew = [ [8, 4, 8, 7],
 *             [7, 4, 7, 7],
 *             [9, 4, 8, 7],
 *             [3, 3, 3, 3] ]
 *
 * Notes:
 *
 * 1 < grid.length = grid[0].length <= 50.
 * All heights grid[i][j] are in the range [0, 100].
 * All buildings in grid[i][j] occupy the entire grid cell: that is, they are a 1 x 1 x grid[i][j] rectangular prism.
 *
 */
public class MaxIncreasetoKeepCitySkyline {

    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int R = grid.length;
        int[] rowMax = new int[R];
        int[] colMax = new int[R];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < R; j++) {
                rowMax[i] = Math.max(rowMax[i], grid[i][j]);
                colMax[i] = Math.max(colMax[i], grid[j][i]);
            }
        }
        int ans = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < R; j++) {
                ans += Math.min(colMax[j],rowMax[i]) - grid[i][j];
            }
        }

        return ans;

    }

    public static void main(String[] args) {
        MaxIncreasetoKeepCitySkyline maxIncreasetoKeepCitySkyline = new MaxIncreasetoKeepCitySkyline();
        System.out.println(maxIncreasetoKeepCitySkyline.maxIncreaseKeepingSkyline(new int[][]
                {
                        {3,0,8,4},
                        {2,4,5,7},
                        {9,2,6,3},
                        {0,3,1,0}
                }));
    }
}
