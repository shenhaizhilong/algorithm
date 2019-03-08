package com.hui.BackTracking;

import java.util.*;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/6 17:10
 */
public class WordSearchII {

    /**
     *212. Word Search II
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a 2D board and a list of words from the dictionary, find all words in the board.
     *
     * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
     *
     * Example:
     *
     * Input:
     * words = ["oath","pea","eat","rain"] and board =
     * [
     *   ['o','a','a','n'],
     *   ['e','t','a','e'],
     *   ['i','h','k','r'],
     *   ['i','f','l','v']
     * ]
     *
     * Output: ["eat","oath"]
     * Note:
     * You may assume that all inputs are consist of lowercase letters a-z.
     *
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        Set<Character> set = new HashSet<>();
        Set<String> resSet = new HashSet<>();
        hash(board,set);
        for(String word: words)
        {
            if(exist(board,word,set))
                resSet.add(word);
        }
        res.addAll(resSet);
        return res;

    }

    public boolean exist(char[][] board, String word, Set<Character> set) {
        int R = board.length;
        int C = board[0].length;
        for (int i = 0; i < word.length(); i++) {
            if(!set.contains(word.charAt(i)))
                return false;
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(backTracking(board,word,0,i,j))
                    return true;
            }
        }

        return false;
    }

    private void hash(char[][] board, Set<Character> set)
    {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                set.add(board[i][j]);
            }
        }
    }

    private boolean backTracking(char[][] board,String word, int wordIndex, int row, int col)
    {
        if(wordIndex == word.length())return true;
        if(row < 0 || col < 0 || row > board.length -1 || col > board[0].length -1)return false;
        if(word.charAt(wordIndex) != board[row][col])return false;
        else if(wordIndex == word.length() -1)return true;

        board[row][col] ^= 256;
        boolean isExist = backTracking(board,word, wordIndex + 1, row + 1, col) ||  //go up
                          backTracking(board,word, wordIndex + 1, row -1, col) ||   //go down
                          backTracking(board,word, wordIndex + 1, row, col +1) ||   // go right
                          backTracking(board,word, wordIndex + 1, row, col -1);     // go left
        board[row][col] ^= 256;
        return isExist;
    }

    /**
     * ****************************************************************************************************************
     *
     *
     *  method 2:
     *https://leetcode.com/problems/word-search-ii/discuss/59780/Java-15ms-Easiest-Solution-(100.00)
     */

    public List<String> findWords2(char[][] board, String[] words)
    {
        List<String> res = new ArrayList<>();
        Trienode root = buildTree(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board,i,j,root,res);
            }
        }
        return res;
    }

    public void dfs(char[][] board, int i, int j, Trienode curr, List<String> res)
    {
        char c = board[i][j];
        int index = c - 'a';
        if(c == '#' || curr.children[index] == null)return;
        curr = curr.children[index];

        // found this word
        if(curr.word != null)
        {
            res.add(curr.word);
            curr.word = null; // remove deplicate
        }

        //visited this point, change it state
        board[i][j] = '#';
        if( i > 0)dfs(board,i-1, j, curr, res);
        if(j  > 0)dfs(board, i, j -1, curr, res);
        if(i < board.length -1) dfs(board, i + 1, j, curr, res);
        if(j < board[0].length -1)dfs(board,i, j + 1, curr, res);

        // change it back
        board[i][j] = c;

    }

    private Trienode buildTree(String[] words)
    {
        Trienode root = new Trienode();
        for(String word: words)
        {
            Trienode curr = root;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) -'a';
                if(curr.children[index] == null)
                {
                    curr.children[index] = new Trienode();
                }
                curr = curr.children[index];
            }
            curr.word = word;
        }

        return root;
    }

    private static class Trienode
    {
        Trienode[] children = new Trienode[26];
        String word;
    }


    public static void main(String[] args) {

       String[] words = {"oath","pea","eat","rain"};
       char[][] board = {{'o','a','a','n'},
                         {'e','t','a','e'},
                         {'i','h','k','r'},
                         {'i','f','l','v'}};

        WordSearchII wordSearchII = new WordSearchII();
        List<String> list =  wordSearchII.findWords2(board,words);
        System.out.println(list);



    }

}
