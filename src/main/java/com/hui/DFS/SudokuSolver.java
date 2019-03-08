package com.hui.DFS;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/17 11:57
 *
 *
 * 37. Sudoku Solver
 * DescriptionHintsSubmissionsDiscussSolution
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 *
 * A sudoku solution must satisfy all of the following rules:
 *
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * Empty cells are indicated by the character '.'.
 *
 */
public class SudokuSolver {

    public void solveSudoku(char[][] board) {
        int R = board.length;
        int C = board[0].length;
        // if number n is used then set number -1 to true at row[number-1][i], col[number-1][j], block[number -1][i/3][j/3]
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][][] block = new boolean[9][3][3];

        for (int i = 0; i < R ; i++) {
            for (int j = 0; j < C; j++) {
                    if(board[i][j] != '.')
                    {
                        int number = board[i][j] - '1'; // index
                        row[number][i] = true;
                        col[number][j] = true;
                        block[number][i/3][j/3] = true;
                    }
            }
        }

        dfs(board, 0, row, col, block);

    }

    private boolean dfs(char[][] board, int pos, boolean[][] row, boolean[][] col, boolean[][][] block)
    {
        if(pos >= 81)return true;
        int i = pos/9;
        int j = pos %9;
        if(board[i][j] != '.')
        {
            return dfs(board, pos +1, row, col, block);
        }

        for (int k = 0; k < 9; k++) {
            if(!row[k][i]  && !col[k][j] && !block[k][i/3][j/3])
            {
                board[i][j] =(char) ('1' + k);
                row[k][i] = true;
                col[k][j] = true;
                block[k][i/3][j/3] = true;
                if(dfs(board, pos +1, row, col, block))
                {
                    return true;
                }

                board[i][j] = '.';
                row[k][i] = false;
                col[k][j] = false;
                block[k][i/3][j/3] = false;

            }
        }

        return false;

    }
}
