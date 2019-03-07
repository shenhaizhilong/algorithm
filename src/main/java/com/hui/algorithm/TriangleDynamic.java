package com.hui.algorithm;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/19 0:01
 *
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 *
 * For example, given the following triangle
 *
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 *
 * The minimum path sum from top to bottom is11(i.e., 2 + 3 + 5 + 1 = 11).
 *
 * Note:
 * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 *
 *
 */
public class TriangleDynamic {

    public int minimumTotal(int[][] triangle) {
        int N = triangle.length;
        int[][] memo = new int[N][N];
        for(int[] row:memo)Arrays.fill(row, Integer.MAX_VALUE);
        int ans = dfs(triangle,0,0,0, memo);
        Matrix.print(memo);
        return ans;
    }

    private int[][] dirs = {{1,0},{1,-1},{1,1}};
    private int dfs(int[][] triangle, int i, int j, int pathSum, int[][] memo)
    {
        if(i == triangle.length -1 && j < triangle[i].length)
        {
            return pathSum + triangle[i][j];
        }
        if(memo[i][j] != Integer.MAX_VALUE)return memo[i][j] + pathSum;
        int ans = Integer.MAX_VALUE;
        for(int[] dir: dirs)
        {
            int x = dir[0] + i;
            int y = dir[1] + j;
            if(x < triangle.length && y >=0 && y < triangle[x].length)
            {
                ans = Math.min(ans, dfs(triangle, x, y, triangle[i][j], memo));
            }
        }

        memo[i][j] = ans;
        if(ans == Integer.MAX_VALUE)
        {
            memo[i][j] = Integer.MAX_VALUE;
            return Integer.MAX_VALUE;
        }

        return pathSum + memo[i][j];
    }

    public static void main(String[] args) {
        int[][] triangle = {
                {2},
                {3,4},
                {6,5,7},
                {4,1,8,3}};
        TriangleDynamic triangleDynamic = new TriangleDynamic();
        System.out.println(triangleDynamic.minimumTotal(triangle));
    }
}
