package com.hui.algorithm;

import java.util.*;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/29 12:20
 *
 * 126. Word Ladder II
 * DescriptionHintsSubmissionsDiscussSolution
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 *
 * Return an empty list if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * Example 1:
 *
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * Output:
 * [
 *   ["hit","hot","dot","dog","cog"],
 *   ["hit","hot","lot","log","cog"]
 * ]
 * Example 2:
 *
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * Output: []
 *
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 *
 *
 *
 */
public class WordLadderII {

    // 1, using the bfs to construct the graph
    //2, dfs find all the path from begin word to end word

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        List<List<String>> ans = new ArrayList<>();
        Set<String> wordDict = new HashSet<>(wordList);
        if(!wordDict.contains(endWord))return ans;
        Map<String,List<String>> graph = new HashMap<>();
        Set<String> currLevel = new HashSet<>();
        currLevel.add(beginWord);
        boolean isFound = false;
        while (!currLevel.isEmpty() && !isFound)
        {
            // remove all the visited vertex
            wordDict.removeAll(currLevel);
            Set<String> nextLevel = new HashSet<>();
            for(String nbor: currLevel)
            {
                graph.put(nbor, new ArrayList<>());
                char[] data = nbor.toCharArray();
                for (int i = 0; i < data.length; i++) {
                    char curr = data[i];
                    for (char j = 'a'; j <= 'z' ; j++) {
                        if(j == curr)continue;
                        data[i] = j;
                        String temp = String.valueOf(data);
                        if(wordDict.contains(temp))
                        {
                            graph.get(nbor).add(temp);
                            nextLevel.add(temp);
                            if(temp.equals(endWord))isFound = true;
                        }
                    }
                    data[i] = curr;// change it back
                }
            }

            currLevel = nextLevel;
        }

        if(!isFound)return ans;
        List<String> list = new ArrayList<>();
        list.add(beginWord);
        addPath(graph, beginWord, endWord, list, ans);
        return ans;
    }

    // backtracking /dfs find all the path from curr to end
    private void addPath(Map<String,List<String>> graph, String curr,
                         String end, List<String> path, List<List<String>> ans)
    {
        if(curr.equals(end))
        {
            ans.add(new ArrayList<>(path));
            return;
        }
        // no path from current  word
        if(!graph.containsKey(curr))return;

        // visit neighbour word in the right path
        for(String nbor: graph.get(curr))
        {
            path.add(nbor);
            addPath(graph, nbor, end, path, ans);
            path.remove(path.size() -1);
        }

    }


    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> list = Arrays.asList("hot","dot","dog","lot","log","cog");
        WordLadderII wordLadderII = new WordLadderII();
        Matrix.print(wordLadderII.findLadders(beginWord, endWord, list));
    }

}
