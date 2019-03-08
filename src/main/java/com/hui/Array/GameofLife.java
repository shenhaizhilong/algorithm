package com.hui.Array;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/27 12:40
 *289. Game of Life
 * DescriptionHintsSubmissionsDiscussSolution
 * According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
 *
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
 *
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * Write a function to compute the next state (after one update) of the board given its current state. The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.
 *
 * Example:
 *
 * Input:
 * [
 *   [0,1,0],
 *   [0,0,1],
 *   [1,1,1],
 *   [0,0,0]
 * ]
 * Output:
 * [
 *   [0,0,0],
 *   [1,0,1],
 *   [0,1,1],
 *   [0,1,0]
 * ]
 * Follow up:
 *
 * Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
 * In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?
 *
 *
 */
public class GameofLife {

    private int R;
    private int C;
    public void gameOfLife(int[][] board) {
        this.R  = board.length;
        this.C  = board[0].length;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int nbour = neighbour(board,i,j);
                if(board[i][j] == 1)
                {
                    if(nbour < 2 || nbour > 3)board[i][j] = 2;  // change it to 2, later change it to 0
                }else {
                    if(nbour == 3)board[i][j] = -1;  // change it to -1, later change it to 1
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(board[i][j] == 2)
                {
                    board[i][j] = 0;
                }else if(board[i][j] == -1) {
                    board[i][j] = 1;
                }
            }
        }

    }

    private static final int[][] dirs = {{0,-1},{0,1},{-1,0},{1,0},{-1,-1},{-1,1},{1,-1},{1,1}};
    private int neighbour(int[][] board, int i, int j)
    {
        int ans = 0;
        for(int[] dir:dirs)
        {
            int x = i + dir[0];
            int y = j + dir[1];
            if(x >= 0 && y >= 0 && x < R  && y < C && (board[x][y] >= 1))ans++;
        }
        return ans;
    }

    public static void main(String[] args) {

        int[][] board = {
                {0,1,0},
                {0,0,1},
                {1,1,1},
                {0,0,0}};
        GameofLife gameofLife = new GameofLife();
        gameofLife.gameOfLife(board);
        Matrix.print(board);
    }
}
