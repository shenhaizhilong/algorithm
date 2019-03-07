package com.hui.algorithm;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/2 15:07
 *
 *778. Swim in Rising Water
 * DescriptionHintsSubmissionsDiscussSolution
 * On an N x N grid, each square grid[i][j] represents the elevation at that point (i,j).
 *
 * Now rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distance in zero time. Of course, you must stay within the boundaries of the grid during your swim.
 *
 * You start at the top left square (0, 0). What is the least time until you can reach the bottom right square (N-1, N-1)?
 *
 * Example 1:
 *
 * Input: [[0,2],[1,3]]
 * Output: 3
 * Explanation:
 * At time 0, you are in grid location (0, 0).
 * You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
 *
 * You cannot reach point (1, 1) until time 3.
 * When the depth of water is 3, we can swim anywhere inside the grid.
 * Example 2:
 *
 * Input: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
 * Output: 16
 * Explanation:
 *  0  1  2  3  4
 * 24 23 22 21  5
 * 12 13 14 15 16
 * 11 17 18 19 20
 * 10  9  8  7  6
 *
 * The final route is marked in bold.
 * We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
 * Note:
 *
 * 2 <= N <= 50.
 * grid[i][j] is a permutation of [0, ..., N*N - 1].
 *
 */
public class SwiminRisingWater {

    private int[][] dirs = {{0,-1}, {0,1},{1,0},{-1,0}};
    public int swimInWater(int[][] grid) {

        int N = grid.length;
        Set<Integer> visited = new HashSet<>();
        int time = 0;
        while (!visited.contains(N*N -1))
        {
            visited.clear();
            dfs(grid,0,0,time,visited);
            time++;
        }
        return time -1;
    }

    private void dfs(int[][] grid, int i, int j, int time, Set<Integer> visited)
    {
        if( i< 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] > time || visited.contains(i* grid.length + j))return;
        visited.add(i*grid.length + j);
        for(int[] dir: dirs)
        {
            dfs(grid, i + dir[0], j + dir[1], time, visited);
        }
    }

    private void dfs(int[][] grid, int i, int j, int time, boolean[][] visited)
    {
        if( i< 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] > time || visited[i][j])return;
        visited[i][j] = true;
        for(int[] dir: dirs)
        {
            dfs(grid, i + dir[0], j + dir[1], time, visited);
        }
    }



    public int swimInWater2(int[][] grid) {

        int N = grid.length;
        int end = N*N;
        UnionFind unionFind = new UnionFind(N*N);
        int time = 0;
        while (!unionFind.isConnected(0,end -1))
        {
            for (int i = 0; i < N; i++) {
                boolean noUnion = true;
                for (int j = 0; j < N; j++) {
                    if(grid[i][j] > time)continue;
                    // right
                    if(j < N -1 && grid[i][j +1] <= time)
                    {
                        noUnion = false;
                        unionFind.union(i*N + j, i*N + j +1);
                    }
                    // down
                    if(i < N -1 && grid[i +1][j] <= time)
                    {
                        noUnion = false;
                        unionFind.union(i*N +j, (i+1)*N + j);
                    }
                }
                if(noUnion)break;
            }
            time++;
        }
        return time -1;
    }



    /**
     * greedy algorithom.
     *
     * @param grid
     * @return
     */
    public int swimInWater3(int[][] grid) {

        int N = grid.length;

        boolean[][] visited = new boolean[N][N];
        PriorityQueue<int[]> heap = new PriorityQueue<>((a,b) -> a[2] - b[2]);
        heap.offer(new int[]{0,0,grid[0][0]});
        while (!heap.isEmpty())
        {
            int[] curr = heap.poll();

            for(int[] dir:dirs)
            {
                int i = dir[0] + curr[0];
                int j = dir[1] + curr[1];
                if(i < 0 || j < 0 || i > N -1 || j > N -1)continue;
                if (!visited[i][j])
                {
                    visited[i][j] = true;
                    int time = Math.max(grid[i][j], curr[2]);
                    if(i == N -1 && j == N -1)return time;
                    heap.offer(new int[]{i,j,time});
                }
            }

        }

        return 0;
    }


    public int swimInWater4(int[][] grid) {

        int N = grid.length;

       int lo = 0;
       int hi = N*N -1;
       while (lo < hi)
       {
           int middle = (lo + hi) >>>1;
           boolean[][] visited = new boolean[N][N];
           if(hasPath(grid, 0, 0, middle, visited))
           {
               hi = middle;
           }else {
               lo = middle +1;
           }
       }

       return lo;
    }

    private boolean hasPath(int[][] grid, int i, int j, int time, boolean[][] visited)
    {
        int N = grid.length;
        if(i == N -1 && j == N -1)return true;
        visited[i][j] = true;
        for(int[] dir: dirs)
        {
            int newI = i + dir[0];
            int newJ = j + dir[1];
            if(newI >= 0 && newJ >=0 && newI < N && newJ < N && !visited[newI][newJ] && grid[i][j] <= time && grid[newI][newJ] <= time)
            {
                if(hasPath(grid, newI, newJ, time, visited))return true;
            }
        }

        return false;
    }


    public int swimInWater5(int[][] grid) {

        int N = grid.length;


        int lo = 0;
        int hi = N*N -1;
        int end = hi;
        while (lo < hi)
        {
            int middle = (lo + hi) >>>1;
            UnionFind unionFind = new UnionFind(N*N);
            for (int i = 0; i < N; i++) {
                boolean noUnion = true;
                for (int j = 0; j < N; j++) {
                    if(grid[i][j] > middle)continue;
                    // right
                    if(j < N -1 && grid[i][j +1] <= middle)
                    {
                        noUnion = false;
                        unionFind.union(i*N + j, i*N + j +1);
                    }
                    // down
                    if(i < N -1 && grid[i +1][j] <= middle)
                    {
                        noUnion = false;
                        unionFind.union(i*N +j, (i+1)*N + j);
                    }
                }
                if(noUnion)break;
            }
            if(unionFind.isConnected(0, end))
            {
                hi = middle;
            }else {
                lo = middle +1;
            }
        }
        return lo;

    }




    public static void main(String[] args) {

        int[][] grid = {{0,1,2,3,4},{24,23,22,21,5},{12,13,14,15,16},{11,17,18,19,20},{10,9,8,7,6}};
        int[][] grid2 = {{0,2},{1,3}};
        SwiminRisingWater swiminRisingWater = new SwiminRisingWater();
//        System.out.println(swiminRisingWater.swimInWater(grid));
//        System.out.println(swiminRisingWater.swimInWater2(grid));
//        System.out.println(swiminRisingWater.swimInWater3(grid));
//        System.out.println(swiminRisingWater.swimInWater4(grid));
//        System.out.println(swiminRisingWater.swimInWater5(grid));
//        System.out.println("********************");
//        System.out.println(swiminRisingWater.swimInWater(grid2));
//        System.out.println(swiminRisingWater.swimInWater2(grid2));
//        System.out.println(swiminRisingWater.swimInWater3(grid2));
//        System.out.println(swiminRisingWater.swimInWater4(grid2));
        System.out.println(swiminRisingWater.swimInWater5(grid2));


    }

}
