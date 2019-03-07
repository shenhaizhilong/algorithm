package com.hui.algorithm;
import java.util.ArrayList;
import java.util.List;


/**
 *
 *
 * 886. Possible Bipartition
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.
 *
 * Each person may dislike some other people, and they should not go into the same group.
 *
 * Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.
 *
 * Return true if and only if it is possible to split everyone into two groups in this way.
 *
 *
 *
 * Example 1:
 *
 * Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
 * Output: true
 * Explanation: group1 [1,4], group2 [2,3]
 * Example 2:
 *
 * Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
 * Output: false
 * Example 3:
 *
 * Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
 * Output: false
 *
 * https://leetcode.com/problems/possible-bipartition/solution/
 * Approach 1: Depth-First Search
 * Intuition
 *
 * It's natural to try to assign everyone to a group. Let's say people in the first group are red, and people in the second group are blue.
 *
 * If the first person is red, anyone disliked by this person must be blue. Then, anyone disliked by a blue person is red, then anyone disliked by a red person is blue, and so on.
 *
 * If at any point there is a conflict, the task is impossible, as every step logically follows from the first step. If there isn't a conflict, then the coloring was valid, so the answer would be true.
 *
 * Algorithm
 *
 * Consider the graph on N people formed by the given "dislike" edges. We want to check that each connected component of this graph is bipartite.
 *
 * For each connected component, we can check whether it is bipartite by just trying to coloring it with two colors. How to do this is as follows: color any node red, then all of it's neighbors blue, then all of those neighbors red, and so on. If we ever color a red node blue (or a blue node red), then we've reached a conflict.
 * @author: shenhaizhilong
 * @date: 2018/8/27 8:49
 */
public class PossibleBipartition {


    public boolean possibleBipartition(int N, int[][] dislikes) {

        //N 个顶点，初始化
        ArrayList<Integer>[]  graph = new ArrayList[N];
        int[] color = new int[N];

        for (int i = 0; i < N; ++i)
            graph[i] = new ArrayList();

        //双向图，添加边
        for (int[] edge: dislikes) {
            graph[edge[0] -1].add(edge[1] - 1);
            graph[edge[1] -1].add(edge[0] - 1);
        }

        //if this node is red, color[n] =1,else if it's blue, color[n] = -1, else if it's not visited , color[n] = 0;
        color[0] = 1;
        for (int i = 0; i < N; i++) {

            //check every vertex
            if(!dfs(graph, color, i))return false;
        }

        return true;
    }

    /**
     * check whether we can split those numbers into two parts
     * @param graph
     * @param color
     * @param current
     * @return
     */
    public boolean dfs(List<Integer>[] graph, int[] color, int current) {

        //check node i's next vertex
        for (int i = 0; i < graph[current].size(); i++) {
            int next = graph[current].get(i);
            //还没有访问
            if(color[next] == 0)
            {
                //涂色
                color[next] = color[current] == 1? -1: 1;
                //访问邻接顶点
                if(!dfs(graph, color, next))return false;
            }else {
                //颜色冲突
                if(color[next] == color[current])return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        PossibleBipartition possibleBipartition = new PossibleBipartition();
        int[][] dislikes =  {{1,2},{1,3},{2,3}};
        int N = 3;
        System.out.println(possibleBipartition.possibleBipartition(N, dislikes));
    }
}
