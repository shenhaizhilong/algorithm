package com.hui.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/8 22:34
 *
 *
 * 797. All Paths From Source to Target
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1, and return them in any order.
 *
 * The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j for which the edge (i, j) exists.
 *
 * Example:
 * Input: [[1,2], [3], [3], []]
 * Output: [[0,1,3],[0,2,3]]
 * Explanation: The graph looks like this:
 * 0--->1
 * |    |
 * v    v
 * 2--->3
 * There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
 * Note:
 *
 * The number of nodes in the graph will be in the range [2, 15].
 * You can print different paths in any order, but you should keep the order of nodes inside one path.
 */
public class AllPathsFromSourcetoTarget {

    private List<List<Integer>> allPath;
    private int N;
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            allPath = new ArrayList<>();
            if(graph == null || graph.length == 0 || graph[0].length == 0)return allPath;
            N = graph.length;
            boolean[] visited = new boolean[N];
            List<Integer> path = new ArrayList<>();
            path.add(0);
            dfs(graph, 0, visited, path );
            return allPath;
    }

    private void dfs(int[][] graph, int node, boolean[] visited, List<Integer> path)
    {
        path.add(node);
        if(node == N -1)
        {
            allPath.add(new ArrayList<>(path));
            path.remove(path.size() -1);
            return;
        }
        if(visited[node])return;  // don't repeat yourself.
        visited[node] = true;
        for(int n:graph[node])
        {
            dfs(graph, n, visited, path);
        }
        path.remove(path.size() -1);
        visited[node] = false;

    }
}
