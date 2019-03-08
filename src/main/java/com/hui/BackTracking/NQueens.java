package com.hui.BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/23 17:53
 *
 *
 * 51. N-Queens
 * DescriptionHintsSubmissionsDiscussSolution
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 *
 *
 *
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
 *
 * Example:
 *
 * Input: 4
 * Output: [
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
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 *
 *
 */
public class NQueens {

    public List<List<String>> solveNQueens(int n) {
        char[][] chessBoard = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(chessBoard[i],'.');
        }
        List<List<String>> ans = new ArrayList<>(n);
        backTracking(chessBoard, 0,ans);
        return ans;
    }

    private void backTracking(char[][] chessBoard, int row, List<List<String>> ans)
    {
        if(row == chessBoard.length)
        {
            List<String> chess = new ArrayList<>();
            for (int i = 0; i < chessBoard.length; i++) {
               chess.add(String.valueOf(chessBoard[i]));
            }
            ans.add(chess);
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
