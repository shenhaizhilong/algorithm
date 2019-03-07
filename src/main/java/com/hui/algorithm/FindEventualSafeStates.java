package com.hui.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/3 13:05
 *
 *
 * 802. Find Eventual Safe States
 * DescriptionHintsSubmissionsDiscussSolution
 * In a directed graph, we start at some node and every turn, walk along a directed edge of the graph.  If we reach a node that is terminal (that is, it has no outgoing directed edges), we stop.
 *
 * Now, say our starting node is eventually safe if and only if we must eventually walk to a terminal node.  More specifically, there exists a natural number K so that for any choice of where to walk, we must have stopped at a terminal node in less than K steps.
 *
 * Which nodes are eventually safe?  Return them as an array in sorted order.
 *
 * The directed graph has N nodes with labels 0, 1, ..., N-1, where N is the length of graph.  The graph is given in the following form: graph[i] is a list of labels j such that (i, j) is a directed edge of the graph.
 *
 * Example:
 * Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
 * Output: [2,4,5,6]
 * Here is a diagram of the above graph.
 *
 * Illustration of graph
 *
 * Note:
 *
 * graph will have length at most 10000.
 * The number of edges in the graph will not exceed 32000.
 * Each graph[i] will be a sorted list of different integers, chosen within the range [0, graph.length - 1].
 *
 *
 *
 * value of color represents three states:
 * 0:have not been visited
 * 1:unsafe
 * 2:safe
 *
 * For DFS,we need to do some optimization.When we travel a path,we mark the node with 1 which represents having been visited,
 *
 * and when we encounter a node which results in a cycle,we return false;
 * all node in the path stays 2 and it represents save
 * .And in the following traveling,whenever we encounter a node which points to a node marked with 1,
 * we know it will results in a cycle,so we can stop traveling.
 * On the contrary,when a node is safe,we can mark it with 2 and whenever we encounter a safe node,we know it will not results in a cycle.
 *
 *
 */
public class FindEventualSafeStates {

    public static final int UNKNOWN = 0;  // unknown.
    public static final int UNSAFE = 1;   // unsafe
    public static final int SAFE   = 2;   // safe

    public List<Integer> eventualSafeNodes(int[][] graph) {
        int N = graph.length;
        int[] states = new int[N];
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if(dfs(graph, i, states))ans.add(i);
        }
        return ans;
    }

    private boolean dfs(int[][] graph, int idx, int[] states)
    {
        // already visited.
        if(states[idx] != UNKNOWN)return states[idx] == SAFE;
        states[idx] = UNSAFE;
        for(int node: graph[idx])
        {
            // has cycle
            if(!dfs(graph, node, states))return false;
        }

        // no cycle
        states[idx] = SAFE;
        return true;
    }


    public static void main(String[] args) {
        int[][] graph = {{1,2},{2,3},{5},{0},{5},{},{}};
        FindEventualSafeStates findEventualSafeStates = new FindEventualSafeStates();
        System.out.println(findEventualSafeStates.eventualSafeNodes(graph));
    }



}
