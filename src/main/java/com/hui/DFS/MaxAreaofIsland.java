package com.hui.DFS;

import com.hui.UnionFindD.UnionFindII;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/3 9:49
 *
 *
 * 695. Max Area of Island
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 *
 * Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)
 *
 * Example 1:
 *
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
 * Example 2:
 *
 * [[0,0,0,0,0,0,0,0]]
 * Given the above grid, return 0.
 * Note: The length of each dimension in the given grid does not exceed 50.
 *
 */
public class MaxAreaofIsland {

    public int maxAreaOfIsland(int[][] grid) {
        if(grid == null)return 0;
        int M = grid.length;
        if(M == 0)return 0;
        int N = grid[0].length;
        if(N == 0)return 0;
        UnionFindII unionFindII = new UnionFindII(M*N);

        int maxArea = -1;
        boolean hasOne = false;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if(grid[i][j] == 1)
                {
                    hasOne = true;
                    if(j < N -1 && grid[i][j +1] == 1)
                    {
                        unionFindII.union(i*N +j, i*N + j +1);
                    }
                    if(i < M -1 && grid[i+1][j] == 1)
                    {
                        unionFindII.union(i*N +j, (i+1)*N +j);
                    }
                    maxArea = Math.max(maxArea, unionFindII.getSize(i*N +j));
                }
            }
        }

        if(maxArea == -1 && hasOne)return 1;
        return maxArea > 0 ? maxArea:0;
    }


    public int maxAreaOfIsland2(int[][] grid) {
        if (grid == null) return 0;
        int M = grid.length;
        if (M == 0) return 0;
        int N = grid[0].length;
        if (N == 0) return 0;

        int maxArea = 0;

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if(grid[i][j] == 1)
                {
                    maxArea = Math.max(maxArea, dfs(grid,i,j,0));
                }
            }
        }

        return maxArea;
    }

    private static final int[][] dirs ={{0,1},{0,-1},{1,0},{-1,0}};
    private int dfs(int[][] grid, int i, int j, int area)
    {
        if(i < 0 || j < 0 || i > grid.length -1 || j > grid[0].length -1 || grid[i][j] != 1)return area;
        area++;
        grid[i][j] +=1;
        for(int[] dir:dirs)
        {
            int x = dir[0] + i;
            int y = dir[1] + j;
            area += dfs(grid,x,y,0);
        }

        return area;
    }


}
