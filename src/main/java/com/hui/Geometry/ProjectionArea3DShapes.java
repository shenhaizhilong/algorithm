package com.hui.Geometry;

/**
 *https://leetcode.com/problems/projection-area-of-3d-shapes/description/
 *
 * 883. Projection Area of 3D Shapes
 *
 * On a N * N grid, we place some 1 * 1 * 1 cubes that are axis-aligned with the x, y, and z axes.
 *
 * Each value v = grid[i][j] represents a tower of v cubes placed on top of grid cell (i, j).
 *
 * Now we view the projection of these cubes onto the xy, yz, and zx planes.
 *
 * A projection is like a shadow, that maps our 3 dimensional figure to a 2 dimensional plane.
 *
 * Here, we are viewing the "shadow" when looking at the cubes from the top, the front, and the side.
 *
 * Return the total area of all three projections.
 *
 *
 *
 * Example 1:
 *
 * Input: [[2]]
 * Output: 5
 * Example 2:
 *
 * Input: [[1,2],[3,4]]
 * Output: 17
 * Explanation:
 * Here are the three projections ("shadows") of the shape made with each axis-aligned plane.
 *
 * Example 3:
 *
 * Input: [[1,0],[0,2]]
 * Output: 8
 * Example 4:
 *
 * Input: [[1,1,1],[1,0,1],[1,1,1]]
 * Output: 14
 * Example 5:
 *
 * Input: [[2,2,2],[2,1,2],[2,2,2]]
 * Output: 21
 *
 *
 * Note:
 *
 * 1 <= grid.length = grid[0].length <= 50
 * 0 <= grid[i][j] <= 50
 * @author: shenhaizhilong
 * @date: 2018/8/17 22:33
 */
public class ProjectionArea3DShapes {

    public int projectionArea(int[][] grid) {
            int xyShadow = xyShadow(grid);
            int xzShadow = xzShadow(grid);
            int yzShadow = yzShadow(grid);
            return xyShadow + xzShadow + yzShadow;
    }

    private int xyShadow(int[][] grid)
    {
        int shadow = 0;
        for (int i = 0; i < grid.length ; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                shadow += (grid[i][j] == 0)? 0: 1;
            }
        }
        return shadow;
    }

    private int xzShadow(int[][] grid)
    {
        int shadow = 0;
        for (int i = 0; i < grid.length; i++) {
            int max = grid[i][0];
            for (int j = 1; j < grid[0].length; j++) {
                if(max < grid[i][j])max = grid[i][j];
            }
            shadow += max;
        }
        return shadow;
    }

    private int yzShadow(int[][] grid)
    {
        int shadow = 0;
        for (int i = 0; i < grid[0].length; i++) {
            int max = grid[0][i];
            for (int j = 1; j < grid.length; j++) {
                if(max < grid[j][i])max = grid[j][i];
            }
            shadow += max;
        }
        return shadow;
    }

    public int projectionArea2(int[][] grid) {
        int sum = 0;
        for(int i = 0; i < grid.length; i++){
            int max1 = 0;
            int max2 = 0;
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] > 0){
                    sum++;
                }
                if(grid[i][j] > max1){
                    max1 = grid[i][j];
                }
                if(grid[j][i] > max2){
                    max2 = grid[j][i];
                }
            }
            sum += (max1 + max2);
        }

        return sum;
    }

    public static void main(String[] args) {

        ProjectionArea3DShapes shadow = new ProjectionArea3DShapes();
        int[][] a = {{2}};
        int[][] b = {{1,2},{3,4}};
        int[][] c = {{1,0}, {0,2}};
        int[][] d = {{1,1,1},{1,0,1},{1,1,1}};
        int[][] e = {{2,2,2},{2,1,2},{2,2,2}};
        System.out.println(shadow.projectionArea2(a));
        System.out.println(shadow.projectionArea2(b));
        System.out.println(shadow.projectionArea2(c));
        System.out.println(shadow.projectionArea2(d));
        System.out.println(shadow.projectionArea2(e));


    }
}
