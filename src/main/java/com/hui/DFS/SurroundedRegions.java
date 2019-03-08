package com.hui.DFS;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/15 11:10
 */
public class SurroundedRegions {


    /**
     *
     * 130. Surrounded Regions
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
     *
     * A region is captured by flipping all 'O's into 'X's in that surrounded region.
     *
     * Example:
     *
     * X X X X
     * X O O X
     * X X O X
     * X O X X
     * After running your function, the board should be:
     *
     * X X X X
     * X X X X
     * X X X X
     * X O X X
     * Explanation:
     *
     * Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.
     *
     *
     * https://leetcode.com/problems/surrounded-regions/discuss/41612/A-really-simple-and-readable-C++-solutiononly-cost-12ms
     * @param board
     */
    public void solve(char[][] board) {

        if(board == null || board.length == 0)return;
        int R = board.length;
        int C = board[0].length;

        for (int i = 0; i < R; i++) {
            dfs(board,i,0); // check first col
            if(C > 1)
                dfs(board,i,C-1);// check last col
        }

        for (int i = 1; i < C; i++) {
            dfs(board,0,i);// check first row
            if(R > 1)
                dfs(board, R-1, i);// check last row
        }


        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(board[i][j] == '#')
                {
                    board[i][j] = 'O';
                }else if(board[i][j] == 'O')
                {
                    board[i][j] = 'X';
                }
            }
        }


    }

    private void dfs(char[][] board, int r, int c)
    {
        if(board[r][c] != 'O')return;
        board[r][c] = '#';
        if(r > 1)
            dfs(board, r -1, c);

        if(r < board.length -1)
            dfs(board, r + 1, c);
        if(c < board[0].length -1)
            dfs(board, r, c + 1);
        if(c > 1)
            dfs(board, r, c - 1);

    }
}
