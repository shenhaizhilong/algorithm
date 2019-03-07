package com.hui.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/3 17:36
 *
 *827. Making A Large Island
 * DescriptionHintsSubmissionsDiscussSolution
 * In a 2D grid of 0s and 1s, we change at most one 0 to a 1.
 *
 * After, what is the size of the largest island? (An island is a 4-directionally connected group of 1s).
 *
 * Example 1:
 *
 * Input: [[1, 0], [0, 1]]
 * Output: 3
 * Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
 * Example 2:
 *
 * Input: [[1, 1], [1, 0]]
 * Output: 4
 * Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
 * Example 3:
 *
 * Input: [[1, 1], [1, 1]]
 * Output: 4
 * Explanation: Can't change any 0 to 1, only one island with area = 4.
 *
 *
 * Notes:
 *
 * 1 <= grid.length = grid[0].length <= 50.
 * 0 <= grid[i][j] <= 1.
 *
 */
public class MakingALargeIsland {

    private static final int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
    public int largestIsland(int[][] grid) {
        int N = grid.length;
        UnionFindII unionFindII = new UnionFindII(N*N);
        int maxArea = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(grid[i][j] == 1)
                {
                    if(j < N -1 && grid[i][j +1] == 1)unionFindII.union(i*N +j, i*N +j +1);
                    if(i < N -1 && grid[i +1][j] == 1)unionFindII.union(i*N +j, (i+1)*N + j);
                    maxArea =  Math.max(maxArea, unionFindII.getSize(i*N +j));  // the max islands
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 0) {
                        maxArea = Math.max(maxArea, aroundIslandsArea(grid,i,j,unionFindII));
                }
            }
        }
        return maxArea;

    }

    private int aroundIslandsArea(int[][] grid, int i, int j, UnionFindII unionFindII)
    {
        Set<Integer> around = new HashSet<>();
        int N = grid.length;
        int area = 1;
        for(int[] dir: dirs)
        {
            int x = i + dir[0];
            int y = j + dir[1];
            if(x >= 0 && x < N && y >= 0 && y < N && grid[x][y] == 1)
            {
                // chose different islands
                int parent = unionFindII.find(x*N +y);
                if(!around.contains(parent))
                {
                    area += unionFindII.getSize(parent);
                    around.add(parent);
                }
            }
        }
        return area;
    }

    public static void main(String[] args) {
        MakingALargeIsland makingALargeIsland = new MakingALargeIsland();
        System.out.println(makingALargeIsland.largestIsland(new int[][]{{1,1},{1,0}}));
    }
}
