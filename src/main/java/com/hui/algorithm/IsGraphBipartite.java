package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/3 16:35
 *
 *785. Is Graph Bipartite?
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an undirected graph, return true if and only if it is bipartite.
 *
 * Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.
 *
 * The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.
 *
 * Example 1:
 * Input: [[1,3], [0,2], [1,3], [0,2]]
 * Output: true
 * Explanation:
 * The graph looks like this:
 * 0----1
 * |    |
 * |    |
 * 3----2
 * We can divide the vertices into two groups: {0, 2} and {1, 3}.
 * Example 2:
 * Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
 * Output: false
 * Explanation:
 * The graph looks like this:
 * 0----1
 * | \  |
 * |  \ |
 * 3----2
 * We cannot find a way to divide the set of nodes into two independent subsets.
 *
 *
 * Note:
 *
 * graph will have length in range [1, 100].
 * graph[i] will contain integers in range [0, graph.length - 1].
 * graph[i] will not contain i or duplicate values.
 * The graph is undirected: if any element j is in graph[i], then i will be in graph[j].
 *
 *
 *
 * same idea with 802. Find Eventual Safe States
 * value of color represents three states:
 * 0:have not been visited
 * 1:Red
 * 2:Black
 *
 */
public class IsGraphBipartite {

    public static final int UNKNOWN = 0;    // unknown, haven't been visited.
    public static final int Red = 1;        // Red color in set A
    public static final int Black   = 2;    // Black color in set B

    public boolean isBipartite(int[][] graph) {
        if(graph == null || graph.length == 0)return false;
        int[] color = new int[graph.length];

        // check all nodes.
        for (int i = 0; i < graph.length; i++) {
            if(!dfs(graph,i,UNKNOWN,color))return false;
        }
        return true;
    }


    private boolean dfs(int[][] graph, int idx,int lastColor, int[] color)
    {
        // already visited.
        if(color[idx] != UNKNOWN)return color[idx] != lastColor;
        color[idx] = lastColor == Red ? Black: Red; // reverse color with lastNode
        for(int adjacentNode: graph[idx])
        {
            // has same color with adjacent node
            if(!dfs(graph, adjacentNode, color[idx], color))return false;
        }
        return true;
    }

    public static void main(String[] args) {

        IsGraphBipartite isGraphBipartite = new IsGraphBipartite();
//        System.out.println(isGraphBipartite.isBipartite(new int[][]{
//                {},
//                {2,4,6},
//                {1,4,8,9},
//                {7,8},
//                {1,2,8,9},
//                {6,9},
//                {1,5,7,8,9},
//                {3,6,9},
//                {2,3,4,6,9},
//                {2,4,5,6,7,8}}));


        System.out.println(isGraphBipartite.isBipartite(new int[][]{
                {},
                {3},
                {},
                {1},
                {}}));
    }




}
