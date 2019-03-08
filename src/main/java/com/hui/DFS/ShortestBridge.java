package com.hui.DFS;

import java.util.ArrayDeque;
import java.util.Deque;


/**
 * @author: shenhaizhilong
 * @date: 2018/12/4 23:20
 *
 *934. Shortest Bridge
 * DescriptionHintsSubmissionsDiscussSolution
 * In a given 2D binary array A, there are two islands.  (An island is a 4-directionally connected group of 1s not connected to any other 1s.)
 *
 * Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.
 *
 * Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)
 *
 *
 *
 * Example 1:
 *
 * Input: [[0,1],[1,0]]
 * Output: 1
 * Example 2:
 *
 * Input: [[0,1,0],[0,0,0],[0,0,1]]
 * Output: 2
 * Example 3:
 *
 * Input: [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 * Output: 1
 *
 *
 * Note:
 *
 * 1 <= A.length = A[0].length <= 100
 * A[i][j] == 0 or A[i][j] == 1
 */
public class ShortestBridge {

    private static final int River = 0;
    private static final int Unknown = 1;  // unknown island
    private static final int Red = 2;  // island A set it to red
    private static final int Black = 3;  // island B set it to black
    private static final int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    private int R;  // rows
    private int C;  // cols

    public int shortestBridge(int[][] A) {
        R = A.length;
        C = A[0].length;
        Deque<int[]> queue = new ArrayDeque<>();
        boolean visitedA = false;
        boolean visitedB = false;
        for (int i = 0; i < R && !visitedB; i++) {
            for (int j = 0; j < C ; j++) {
                if(A[i][j] == Unknown && !visitedA)
                {
                    dfs(A,i,j); // visit island A and mark it as Red color
                    visitedA = true; // already visited island A
                    continue;
                }

                if(visitedA && A[i][j] == Unknown)
                {
                    dfs2(A,i,j,queue);  // visit island B and mark it as Black color
                    visitedB = true;
                    break;
                }
            }
        }

        // bfs

        int ans = 0;
        while (!queue.isEmpty())
        {
            int size = queue.size();
            for (int i = 0; i < size; i++) {

                int[] curr = queue.pollFirst();

                // visit all it's neighbours
                for(int[] dir:dirs)
                {
                    int x = curr[0] + dir[0];
                    int y = curr[1] + dir[1];
                    if(x >=0 && x < R && y >=0 && y < C)
                    {
                        if (A[x][y] == Black)continue;  // already visited, ignore it
                        else if(A[x][y] == River)  // zero position, add it to queue.
                        {
                            A[x][y] = Black;   // expand Black island
                            queue.addLast(new int[]{x,y});
                        }else return ans;  // Black island meet  Red island
                    }
                }
            }
            ans++;  // expand island B

        }

        // no answer.
        return -1;


    }

    private void dfs(int[][] A, int i, int j)
    {
        if(A[i][j] != Unknown)return;
        A[i][j] = Red;
        for(int[] dir: dirs)
        {
            int x = i + dir[0];
            int y = j + dir[1];
            if(x >=0 && x < R && y >=0 && y < C && A[x][y] == Unknown)
            {
                dfs(A,x,y);
            }
        }

    }

    private void dfs2(int[][] A, int i, int j, Deque<int[]> queue)
    {
        if(A[i][j] != Unknown)return;
        A[i][j] = Black;
        queue.addLast(new int[]{i,j});
        for(int[] dir: dirs)
        {
            int x = i + dir[0];
            int y = j + dir[1];
            if(x >=0 && x < R && y >=0 && y < C && A[x][y] == Unknown)
            {
                dfs2(A,x,y, queue);
            }
        }

    }


    public int shortestBridge2(int[][] A) {
        R = A.length;
        C = A[0].length;
        Deque<int[]> queue = new ArrayDeque<>();
        boolean visitedA = false;

        for (int i = 0; i < R && !visitedA; i++) {
            for (int j = 0; j < C ; j++) {
                if(A[i][j] == Unknown && !visitedA)
                {
                    dfs2(A,i,j, queue); // visit island A and mark it as Black color
                    visitedA = true; // already visited island A
                    break;
                }
            }
        }

        // bfs

        int ans = 0;
        while (!queue.isEmpty())
        {
            int size = queue.size();
            while (size-- > 0){
                int[] curr = queue.pollFirst();
                // visit all it's neighbours
                for(int[] dir:dirs)
                {
                    int x = curr[0] + dir[0];
                    int y = curr[1] + dir[1];
                    if(x < 0 || x > R -1 || y < 0 || y > C -1)continue;
                    if (A[x][y] == Black)continue;  // already visited, ignore it
                    else if(A[x][y] == River)  // zero position, add it to queue.
                    {
                        A[x][y] = Black;   // expand Black island
                        queue.addLast(new int[]{x,y});
                    }else return ans;  // Black island meet  Red island

                }
            }
            ans++;  // expand island B

        }

        // no answer.
        return -1;


    }


    public static void main(String[] args) {
        ShortestBridge shortestBridge = new ShortestBridge();
        System.out.println(shortestBridge.shortestBridge(new int[][]{
                {0,1},
                {1,0}
        }));

        System.out.println(shortestBridge.shortestBridge2(new int[][]{
                {0,1},
                {1,0}
        }));
    }

}
