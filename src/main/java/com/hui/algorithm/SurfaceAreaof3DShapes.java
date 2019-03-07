package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/2 10:01
 */
public class SurfaceAreaof3DShapes {

    /**
     *892. Surface Area of 3D Shapes
     * DescriptionHintsSubmissionsDiscussSolution
     * On a N * N grid, we place some 1 * 1 * 1 cubes.
     *
     * Each value v = grid[i][j] represents a tower of v cubes placed on top of grid cell (i, j).
     *
     * Return the total surface area of the resulting shapes.
     *
     *
     *
     * Example 1:
     *
     * Input: [[2]]
     * Output: 10
     * Example 2:
     *
     * Input: [[1,2],[3,4]]
     * Output: 34
     * Example 3:
     *
     * Input: [[1,0],[0,2]]
     * Output: 16
     * Example 4:
     *
     * Input: [[1,1,1],[1,0,1],[1,1,1]]
     * Output: 32
     * Example 5:
     *
     * Input: [[2,2,2],[2,1,2],[2,2,2]]
     * Output: 46
     *
     *
     * Note:
     *
     * 1 <= N <= 50
     * 0 <= grid[i][j] <= 50
     *
     *
     *i is Y axis,j is X axis, height(grid[i][j]) is the Z axis.
     *
     * @param grid
     * @return
     */
    public static int surfaceArea(int[][] grid) {
        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] != 0)
                {
                    sum += grid[i][j]*6;
                    // Z axis
                    sum -= 2*(grid[i][j] -1);

                    // Y axis
                    if(i < grid.length -1)
                    {
                        sum -= 2*Math.min(grid[i][j], grid[i +1][j]);
                    }

                    // X axis
                    if(j < grid[0].length -1)
                    {
                        sum -= 2*Math.min(grid[i][j],grid[i][j+1]);
                    }
                }
            }
        }

        return sum;
    }

    public static void main(String[] args) {

        int[][] nums = {{2}};
        System.out.println(surfaceArea(nums));
        int[][] nums2 = {{1,2},{3,4}};
        System.out.println(surfaceArea(nums2));
        int[][] nums3 = {{1,0},{0,2}};
        int[][] nums4 = {{1,1,1},{1,0,1},{1,1,1}};
        int[][] nums5 = {{2,2,2},{2,1,2},{2,2,2}};
        System.out.println(surfaceArea(nums3));
        System.out.println(surfaceArea(nums4));
        System.out.println(surfaceArea(nums5));

    }
}
