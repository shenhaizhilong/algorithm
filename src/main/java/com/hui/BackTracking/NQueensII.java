package com.hui.BackTracking;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/23 18:12
 *
 *
 * 52. N-Queens II
 * DescriptionHintsSubmissionsDiscussSolution
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 *
 *
 *
 * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
 *
 * Example:
 *
 * Input: 4
 * Output: 2
 * Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
 * [
 *  [".Q..",  // Solution 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // Solution 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 *
 */
public class NQueensII {
    public int totalNQueens(int n) {
        char[][] chessBoard = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(chessBoard[i],'.');
        }
        int[] ans = {0};
        backTracking(chessBoard, 0,ans);
        return ans[0];
    }


    private void backTracking(char[][] chessBoard, int row, int[] ans)
    {
        if(row == chessBoard.length)
        {
            ans[0]++;
            return;
        }

        for (int i = 0; i < chessBoard[0].length; i++) {
            if(isValid(chessBoard, row, i))
            {
                chessBoard[row][i] = 'Q';
                backTracking(chessBoard, row +1, ans);
                chessBoard[row][i] = '.';
            }
        }

    }


    // check whether we can place a Queue
    private boolean isValid(char[][] chessBoard, int row, int col)
    {
        //check column
        for (int i = 0; i < row ; i++) {
            if(chessBoard[i][col] == 'Q')return false;
        }

        // check 45'
        for (int i = row -1, j = col +1; i >=0 && j < chessBoard[0].length ; i--, j++) {
            if(chessBoard[i][j] == 'Q')return false;
        }

        // check 135'
        for (int i = row -1, j = col -1; i >= 0 && j >= 0; i--, j--) {
            if(chessBoard[i][j] == 'Q')return false;
        }

        return true;

    }
}
