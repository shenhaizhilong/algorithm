package com.hui.BFS;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/7 11:44
 *
 *
 * 733. Flood Fill
 * DescriptionHintsSubmissionsDiscussSolution
 * An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).
 *
 * Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.
 *
 * To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on. Replace the color of all of the aforementioned pixels with the newColor.
 *
 * At the end, return the modified image.
 *
 * Example 1:
 * Input:
 * image = [[1,1,1],[1,1,0],[1,0,1]]
 * sr = 1, sc = 1, newColor = 2
 * Output: [[2,2,2],[2,2,0],[2,0,1]]
 * Explanation:
 * From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected
 * by a path of the same color as the starting pixel are colored with the new color.
 * Note the bottom corner is not colored 2, because it is not 4-directionally connected
 * to the starting pixel.
 * Note:
 *
 * The length of image and image[0] will be in the range [1, 50].
 * The given starting pixel will satisfy 0 <= sr < image.length and 0 <= sc < image[0].length.
 * The value of each color in image[i][j] and newColor will be an integer in [0, 65535].
 *
 */
public class FloodFill {

    private static final int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {

        bfs(image,sr,sc,newColor);
        return image;
    }

    private void bfs(int[][] image, int sr, int sc, int newColor)
    {
        int oldColor = image[sr][sc];
        if(oldColor == newColor)return ;
        Deque<int[]> queue = new ArrayDeque<>();
        queue.addLast(new int[]{sr,sc});
        int R = image.length;
        int C = image[0].length;
        image[sr][sc] = newColor;
        while (!queue.isEmpty())
        {
            int[] curr = queue.pollFirst();

            for(int[] dir: dirs)
            {
                int x = dir[0] + curr[0];
                int y = dir[1] + curr[1];
                if(x > -1 && y > -1 && x < R && y < C && image[x][y] == oldColor)
                {
                    queue.addLast(new int[]{x,y});
                    image[x][y] = newColor;
                }
            }
        }
    }



    public int[][] floodFill2(int[][] image, int sr, int sc, int newColor) {

        dfs(image,sr,sc,newColor);
        return image;
    }
    private void dfs(int[][] image, int sr, int sc, int newColor)
    {
        int oldColor = image[sr][sc];
        if(oldColor == newColor)return ;
        Deque<int[]> queue = new ArrayDeque<>();
        queue.addLast(new int[]{sr,sc});
        int R = image.length;
        int C = image[0].length;
        image[sr][sc] = newColor;
        while (!queue.isEmpty())
        {
            int[] curr = queue.pollLast();

            for(int[] dir: dirs)
            {
                int x = dir[0] + curr[0];
                int y = dir[1] + curr[1];
                if(x > -1 && y > -1 && x < R && y < C && image[x][y] == oldColor)
                {
                    queue.addLast(new int[]{x,y});
                    image[x][y] = newColor;
                }
            }
        }
    }



    private int R;
    private int C;
    private int NewColor;
    private int OldColor;
    public int[][] floodFill3(int[][] image, int sr, int sc, int newColor) {

        OldColor = image[sr][sc];
        if(OldColor == newColor)return  image;
        NewColor = newColor;
        R = image.length;
        C = image[0].length;
        dfs2(image,sr,sc);
        return image;
    }
    private void dfs2(int[][] image, int x, int y)
    {
        if(x < 0 || y < 0 || x > R -1 || y > C -1 || image[x][y] != OldColor)return;
        image[x][y] = NewColor;
        dfs2(image, x, y +1);
        dfs2(image,x, y -1);
        dfs2(image, x +1,y);
        dfs2(image,x -1, y);
    }

}
