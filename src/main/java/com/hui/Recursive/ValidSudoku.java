package com.hui.Recursive;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/1/array/30/
 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * @author: shenhaizhilong
 * @date: 2018/6/30 13:47
 */
public class ValidSudoku {
    public static boolean isValidSudoku(char[][] board) {

        Set<Character> rowSet = new HashSet<>();
        Set<Character> colSet = new HashSet<>();
        for (int i = 0; i <9; i++) {
            rowSet.clear();
            colSet.clear();
            for (int j = 0; j < 9; j++) {

                //  for a row
                if(board[i][j] !='.') {
                    if (rowSet.contains(board[i][j]))return false;
                    rowSet.add(board[i][j]);
                }

                // for a column
                if(board[j][i] !='.')
                {
                    if(colSet.contains(board[j][i]))return false;
                    colSet.add(board[j][i]);
                }

                //check a 3*3 block
                if(i %3 == 0 && j %3==0)
                {
                    if(!checkBlock(board, i, j))
                        return false;
                }


            }
        }

        return true;
    }

    public static boolean checkBlock(char[][] block, int row, int col)
    {
        Set<Character> blockSet = new HashSet<>();
        for (int i = row; i < row +3 ; i++) {
            for (int j = col; j < col +3; j++) {
                if(block[i][j] !='.')
                {
                    if(blockSet.contains(block[i][j]))
                        return false;
                    blockSet.add(block[i][j]);
                }

            }
        }
        return true;
    }


    public static void main(String[] args) {

       char[][] board   = {

               {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
               {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
               {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
               {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
               {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
               {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
               {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
               {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
               {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
       };

        System.out.println(isValidSudoku(board));
        char[][] board2 = {
               {'8','3','.','.','7','.','.','.','.'},
               {'6','.','.','1','9','5','.','.','.'},
               {'.','9','8','.','.','.','.','6','.'},
               {'8','.','.','.','6','.','.','.','3'},
               {'4','.','.','8','.','3','.','.','1'},
               {'7','.','.','.','2','.','.','.','6'},
               {'.','6','.','.','.','.','2','8','.'},
               {'.','.','.','4','1','9','.','.','5'},
               {'.','.','.','.','8','.','.','7','9'}
       };

        System.out.println(isValidSudoku(board2));

        char[][] board3 = {
                {'.','.','.','.','5','.','.','1','.'},
                {'.','4','.','3','.','.','.','.','.'},
                {'.','.','.','.','.','3','.','.','1'},
                {'8','.','.','.','.','.','.','2','.'},
                {'.','.','2','.','7','.','.','.','.'},
                {'.','1','5','.','.','.','.','.','.'},
                {'.','.','.','.','.','2','.','.','.'},
                {'.','2','.','9','.','.','.','.','.'},
                {'.','.','4','.','.','.','.','.','.'}
        };

        System.out.println(isValidSudoku(board3));

    }
}
