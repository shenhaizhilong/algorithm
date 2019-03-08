package com.hui.DFS;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/1 20:45
 */
public class IslandPerimeter {


    /**
     * 463. Island Perimeter
     * DescriptionHintsSubmissionsDiscussSolution
     * You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water. Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells). The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.
     *
     * Example:
     *
     * [[0,1,0,0],
     *  [1,1,1,0],
     *  [0,1,0,0],
     *  [1,1,0,0]]
     *
     * Answer: 16
     * Explanation: The perimeter is the 16 yellow stripes in the image below:
     *
     * @param grid
     * @return
     */
    public static int islandPerimeter(int[][] grid) {
        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] ==1)
                {
                    //top  and bottom bound
                   if(i == 0)sum++;
                   if(i == grid.length -1)sum++;

                    //up and down diff
                    if(i > 0 && grid[i-1][j] == 0)sum++;
                    if(i < grid.length -1 && grid[i +1][j] == 0)sum++;

                    //left and right diff
                    if(j > 0 && grid[i][j-1] == 0)sum++;
                    if(j < grid[0].length -1 && grid[i][j +1] == 0)sum++;

                    if(j == 0)sum++;
                    if(j == grid[0].length -1)sum++;

                }
            }
        }

        return sum;
    }

    public static int islandPerimeter2(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int sum = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(grid[i][j] == 1)
                {
                    sum +=4;
                    if(i >=1 && grid[i-1][j] == 1)sum -=2;
                    if(j >=1 && grid[i][j-1] == 1)sum -=2;
                }
            }
        }
        return sum;
    }



    public static void main(String[] args) {
        int[][] nums = {{1}};
        System.out.println(islandPerimeter(nums));
        System.out.println(islandPerimeter2(nums));

    }
}
