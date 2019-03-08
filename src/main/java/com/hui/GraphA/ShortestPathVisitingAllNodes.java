package com.hui.GraphA;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/14 17:18
 *
 *847. Shortest Path Visiting All Nodes
 * DescriptionHintsSubmissionsDiscussSolution
 * An undirected, connected graph of N nodes (labeled 0, 1, 2, ..., N-1) is given as graph.
 *
 * graph.length = N, and j != i is in the list graph[i] exactly once, if and only if nodes i and j are connected.
 *
 * Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.
 *
 *
 *
 * Example 1:
 *
 * Input: [[1,2,3],[0],[0],[0]]
 * Output: 4
 * Explanation: One possible path is [1,0,2,0,3]
 * Example 2:
 *
 * Input: [[1],[0,2,4],[1,3,4],[2],[1,2]]
 * Output: 4
 * Explanation: One possible path is [0,1,4,2,3]
 *
 *
 * Note:
 *
 * 1 <= graph.length <= 12
 * 0 <= graph[i].length < graph.length
 *
 */
public class ShortestPathVisitingAllNodes {

    public int shortestPathLength(int[][] graph) {
        int N = graph.length;
        int finalState = (1 << N) -1;
        Set<State> seen = new HashSet<>();
        Deque<State> queue = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            State curr = new State(i, 1<< i, 0);
            queue.addLast(curr);  // {node i, state}
            seen.add(curr);
        }

        while (!queue.isEmpty())
        {
            State curr = queue.pollFirst();
            int vertex = curr.vertex;
            int state = curr.state;
            if(state == finalState)return curr.cost;
            // adjacent vertex
            for(int next:graph[vertex])
            {
                // visit adjacent vertex, set state[next] bit to 1
                State newState = new State(next, (state | 1 << next), curr.cost +1);
                if(!seen.contains(newState))
                {
                    queue.addLast(newState);
                    seen.add(newState);
                }
            }
        }


        return -1;
    }

    private static class State
    {
        private int state;
        private int vertex;
        private int cost;
        public State(int vertex, int state, int cost)
        {
            this.vertex = vertex;
            this.state = state;
            this.cost = cost;

        }

        @Override
        public int hashCode() {
            return vertex*1231 + state*1237;
        }

        @Override
        public boolean equals(Object obj) {
             if(!(obj instanceof  State))return false;
            State other = (State)obj;
            return other.cost == cost && other.state == state && other.vertex == vertex;
        }
    }

    public static void main(String[] args) {

        int[][] graph = {{1,2,3},{0},{0},{0}};
        ShortestPathVisitingAllNodes shortestPathVisitingAllNodes = new ShortestPathVisitingAllNodes();
        System.out.println(shortestPathVisitingAllNodes.shortestPathLength(graph));
        int[][] graph2 = {{6,7},{6},{6},{5,6},{6},{3},{2,0,3,4,1},{0}};
        System.out.println(shortestPathVisitingAllNodes.shortestPathLength(graph2));
    }
}
