package com.hui.DFS;

/**
 * @author: shenhaizhilong
 * @date: 2019/1/17 12:45
 *
 *
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。如果一条路径经过了矩阵中的某一个格子，则之后不能再次进入这个格子。 例如 a b c e s f c s a d e e 这样的3 X 4 矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
 *
 *
 */
public class PathInTheMatrix {

    private static final int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
    private  int R,C;
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str)
    {
        R = rows;
        C = cols;
        boolean[][] visited = new boolean[rows][cols];
        char[][] grid = buildGrid(matrix, rows, cols);

        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                if(grid[i][j] == str[0] && dfs(grid, str, 0, i,j, visited))return true;
            }
        }
        return false;
    }

    private boolean dfs(char[][] grid, char[] str, int idx, int r, int c, boolean[][] visited)
    {
        if(r < 0 || r > R -1 || c < 0 || c > C -1 || visited[r][c] )return false;
        if(grid[r][c] != str[idx])return false;
        visited[r][c] = true;
        if(idx == str.length -1)return true;
        for(int[] dir: dirs)
        {
            if(dfs(grid, str, idx +1, r + dir[0], c + dir[1], visited))return true;
        }
        visited[r][c] = false;
        return false;
    }

    private char[][] buildGrid(char[] matrix, int rows, int cols)
    {
        char[][] grid = new char[rows][cols];
        int idx = 0;
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                grid[i][j] = matrix[idx++];
            }
        }
        return grid;
    }


    public boolean hasPath2(char[] matrix, int rows, int cols, char[] str)
    {
        R = rows;
        C = cols;
        boolean[] visited = new boolean[matrix.length];

        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                if(dfs(matrix, str, 0, i,j, visited))return true;
            }
        }
        return false;
    }

    private boolean dfs(char[] matrix, char[] str, int idx, int r, int c, boolean[] visited)
    {
        int index = r*C + c;
        if(r < 0 || r > R -1 || c < 0 || c > C -1 || visited[index] )return false;
        if(matrix[index] != str[idx])return false;
        visited[index] = true;
        if(idx == str.length -1)return true;
        if(dfs(matrix,str, idx +1, r +1, c, visited) ||
                dfs(matrix, str, idx +1, r-1, c, visited) ||
                dfs(matrix, str, idx +1, r, c +1, visited) ||
                dfs(matrix, str, idx +1, r, c -1, visited))return true;
        visited[index] = false;
        return false;
    }



}
