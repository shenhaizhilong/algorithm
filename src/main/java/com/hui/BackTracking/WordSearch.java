package com.hui.BackTracking;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/6 12:50
 */
public class WordSearch {


    /**
     *
     *
     * 79. Word Search
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a 2D board and a word, find if the word exists in the grid.
     *
     * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
     *
     * Example:
     *
     * board =
     * [
     *   ['A','B','C','E'],
     *   ['S','F','C','S'],
     *   ['A','D','E','E']
     * ]
     *
     * Given word = "ABCCED", return true.
     * Given word = "SEE", return true.
     * Given word = "ABCB", return false.
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        int R = board.length;
        int C = board[0].length;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(backTracking(board,word,0,i,j))
                    return true;
            }
        }

        return false;
    }

    private boolean backTracking(char[][] board, String word, int wordIndex, int row, int col)
    {
        if(wordIndex == word.length())return true;
        if(row < 0 || col < 0 || row > board.length -1 || col > board[0].length -1)return false;
        if(board[row][col] != word.charAt(wordIndex))return false;
        else if(wordIndex == word.length() -1)return true;
        //find  word.charAt(wordIndex)
        board[row][col] ^= 256;  // change it's state
        boolean nextSearch = backTracking(board, word,wordIndex + 1, row  -1, col) || // go down
                             backTracking(board, word,wordIndex + 1, row + 1, col) ||  // go up
                             backTracking(board, word,wordIndex + 1,   row,col + 1) || //go right
                             backTracking(board, word,wordIndex + 1,   row, col -1);  // go left

        board[row][col]  ^= 256;  // change it back

        return nextSearch;
    }





    public static void main(String[] args) {
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};

        WordSearch wordSearch = new WordSearch();
        System.out.println(wordSearch.exist(board,"ABCCED"));
        System.out.println(wordSearch.exist(board,"SEE"));
        System.out.println(wordSearch.exist(board,"ABCB"));
    }
}
