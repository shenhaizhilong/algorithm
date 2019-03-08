package com.hui.UnionFindD;

import com.hui.Array.Matrix;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/2 20:44
 *
 *803. Bricks Falling When Hit
 * DescriptionHintsSubmissionsDiscussSolution
 * We have a grid of 1s and 0s; the 1s in a cell represent bricks.  A brick will not drop if and only if it is directly connected to the top of the grid, or at least one of its (4-way) adjacent bricks will not drop.
 *
 * We will do some erasures sequentially. Each time we want to do the erasure at the location (i, j), the brick (if it exists) on that location will disappear, and then some other bricks may drop because of that erasure.
 *
 * Return an array representing the number of bricks that will drop after each erasure in sequence.
 *
 * Example 1:
 * Input:
 * grid = [[1,0,0,0],[1,1,1,0]]
 * hits = [[1,0]]
 * Output: [2]
 * Explanation:
 * If we erase the brick at (1, 0), the brick at (1, 1) and (1, 2) will drop. So we should return 2.
 * Example 2:
 * Input:
 * grid = [[1,0,0,0],[1,1,0,0]]
 * hits = [[1,1],[1,0]]
 * Output: [0,0]
 * Explanation:
 * When we erase the brick at (1, 0), the brick at (1, 1) has already disappeared due to the last move. So each erasure will cause no bricks dropping.  Note that the erased brick (1, 0) will not be counted as a dropped brick.
 *
 *
 * Note:
 *
 * The number of rows and columns in the grid will be in the range [1, 200].
 * The number of erasures will not exceed the area of the grid.
 * It is guaranteed that each erasure will be different from any other erasure, and located inside the grid.
 * An erasure may refer to a location with no brick - if it does, no bricks drop.
 *
 *
 *
 *
 * reversely union
 *If no bircks drop, then after all operations. The grid will be look like a pool with multi islands.
 * for example:
 * 0010000100
 * 0111001110
 * 1111111111
 * after operations: [0,2], [2,4], [1,2], [0,7]
 * 0000000000
 * 0101001110
 * 1111011111
 * so total 2 islands.
 *
 * Then add bricks back reversely.
 * [0,7]
 * 0000000100
 * 0101001110
 * 1111011111
 * the right island attaches top, and its size is 9, which means 8 bricks drop in this operation.
 *
 * [1,2]
 * 0000000100
 * 0111001110
 * 1111011111
 * the left island does not reach the top, so no brick drops.
 *
 * [2,4]
 * 0000000100
 * 0111001110
 * 1111111111
 * the left island connects to right island and acttaches top, and left island is original 7, which means 7 bricks drop in this operation.
 *
 * [0,2]
 * 0010000100
 * 0111001110
 * 1111111111
 * the island size is just enlarged by 1, which means no brick drops.
 *
 */
public class BricksFallingWhenHit {


    private static final int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
    public int[] hitBricks(int[][] grid, int[][] hits) {

        int M = grid.length;
        int N = grid[0].length;
        int capacity = M*N +1;
        UnionFindII unionFindII = new UnionFindII(capacity);


        /**
         *
         * mark hit as -1
         */
        for(int[] hit: hits)
        {
            int x = hit[0];
            int y = hit[1];
            if(grid[x][y] == 1)
            {
                grid[x][y] = -1;
            }
        }

        // union all bricks in 4 directions.
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if(grid[i][j] == 1)
                {
                    union4way(grid, unionFindII, i, j);
                }
            }
        }


        int topRowIslandsSize = unionFindII.getSize(0);
        int[] ans = new int[hits.length];
        int endIDx = hits.length -1;

        while (endIDx >= 0)
        {
            int x = hits[endIDx][0];
            int y = hits[endIDx][1];
            if(grid[x][y] == -1)
            {
                grid[x][y] = 1;
                union4way(grid, unionFindII, x,y);
                int newTopRowIslandsSize = unionFindII.getSize(0);
                int dropSize = newTopRowIslandsSize > topRowIslandsSize ? newTopRowIslandsSize - topRowIslandsSize -1:0;
                ans[endIDx] = dropSize;
                topRowIslandsSize = newTopRowIslandsSize;
            }
            endIDx--;
        }
        return ans;



    }

    private void union4way(int[][] grid, UnionFindII unionFindII, int i, int j)
    {
        int M = grid.length;
        int N = grid[0].length;
        int idx = i*N + j +1;
        for(int[] dir: dirs)
        {
            int x = i + dir[0];
            int y = j + dir[1];
            if(x >= 0 && x < M && y >= 0 && y < N && grid[x][y] == 1)
            {
                unionFindII.union(idx, x*N + y +1);
            }
        }

        // the islands with top row
        if(i == 0)
        {
            unionFindII.union(0, idx);
        }
    }


    public static void main(String[] args) {

        BricksFallingWhenHit bricksFallingWhenHit = new BricksFallingWhenHit();
     //   Matrix.print(bricksFallingWhenHit.hitBricks(new int[][]{{1,0,0,0},{1,1,1,0}}, new int[][]{{1,0}}));

        Matrix.print(bricksFallingWhenHit.hitBricks(new int[][]{{1,0,0,0},{1,1,0,0}}, new int[][]{{1,1},{1,0}}));
    }
}
