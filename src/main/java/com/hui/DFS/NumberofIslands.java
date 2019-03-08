package com.hui.DFS;

/**
 *
 * 200. Number of Islands
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 *
 * Input:
 * 11110
 * 11010
 * 11000
 * 00000
 *
 * Output: 1
 * Example 2:
 *
 * Input:
 * 11000
 * 11000
 * 00100
 * 00011
 *
 * Output: 3
 *
 * @author: shenhaizhilong
 * @date: 2018/9/19 20:21
 */
public class NumberofIslands {

    public int numIslands(char[][] grid) {

        if(grid == null || grid.length == 0)return 0;
        int R = grid.length;
        int C = grid[0].length;


        int count = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(grid[i][j] =='1' )  //if it's 1, then set it to zero, then it's left, right, up, down
                {
                    dfs(grid,i,j);
                    count++;
                }
            }
        }


        return count;
    }


    private void dfs(char[][] grid, int r, int c)
    {
        if(grid[r][c] != '1')return;
        grid[r][c] = '0';
        if(r >= 1)
            dfs(grid, r -1, c);// up
        if(c >= 1)
            dfs(grid, r, c -1);//left
        if(r < grid.length -1)
            dfs(grid, r + 1, c); // down
        if(c < grid[0].length -1)
            dfs(grid, r, c + 1);  // right
    }

    public static void main(String[] args) {
        NumberofIslands numberofIslands = new NumberofIslands();
//        System.out.println(numberofIslands.numIslands(new char[][]{ {'1','1','1','1','0'},
//                                                                    {'1','1','0','1','0'},
//                                                                    {'1','1','0','0','0'},
//                                                                    {'0','0','0','0','0'}}));
//
//        System.out.println(numberofIslands.numIslands(new char[][]{
//                {'1','1','0','0','0'},
//                {'1','1','0','0','0'},
//                {'0','0','1','0','0'},
//                {'0','0','0','1','1'}}));

        System.out.println(numberofIslands.numIslands(new char[][]{
                {'1','1','1'},
                {'0','1','0'},
                {'1','1','1'}}));
    }
}
