package com.hui.GraphA;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/25 13:39
 *
 * 882. Reachable Nodes In Subdivided Graph
 * DescriptionHintsSubmissionsDiscussSolution
 * Starting with an undirected graph (the "original graph") with nodes from 0 to N-1, subdivisions are made to some of the edges.
 *
 * The graph is given as follows: edges[k] is a list of integer pairs (i, j, n) such that (i, j) is an edge of the original graph,
 *
 * and n is the total number of new nodes on that edge.
 *
 * Then, the edge (i, j) is deleted from the original graph, n new nodes (x_1, x_2, ..., x_n) are added to the original graph,
 *
 * and n+1 new edges (i, x_1), (x_1, x_2), (x_2, x_3), ..., (x_{n-1}, x_n), (x_n, j) are added to the original graph.
 *
 * Now, you start at node 0 from the original graph, and in each move, you travel along one edge.
 *
 * Return how many nodes you can reach in at most M moves.
 *
 *
 *
 * Example 1:
 *
 * Input: edges = [[0,1,10],[0,2,1],[1,2,2]], M = 6, N = 3
 * Output: 13
 * Explanation:
 * The nodes that are reachable in the final graph after M = 6 moves are indicated below.
 *
 * Example 2:
 *
 * Input: edges = [[0,1,4],[1,2,6],[0,2,8],[1,3,1]], M = 10, N = 4
 * Output: 23
 *
 *
 * Note:
 *
 * 0 <= edges.length <= 10000
 * 0 <= edges[i][0] < edges[i][1] < N
 * There does not exist any i != j for which edges[i][0] == edges[j][0] and edges[i][1] == edges[j][1].
 * The original graph has no parallel edges.
 * 0 <= edges[i][2] <= 10000
 * 0 <= M <= 10^9
 * 1 <= N <= 3000
 * A reachable node is a node that can be travelled to using at most M moves starting from node 0.
 *
 *
 */
public class ReachableNodesInSubdividedGraph {

    public int reachableNodes(int[][] edges, int M, int N) {
        int[][] graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(graph[i], -1);  // init graph matrix, graph[i][j] == -1 , means they are not connected.
        }

        for(int[] edge: edges)
        {
            graph[edge[0]][edge[1]] = edge[2]; // the nodes from node[i] to node [j];
            graph[edge[1]][edge[0]] = edge[2]; // the nodes from node[j] to node [i];
        }
        PriorityQueue<int[]> heap = new PriorityQueue<>((int[] a, int[]b) -> b[1] - a[1]); // reverse order by remain moves from source node
        boolean[] visited = new boolean[N];
        int ans = 0;
        heap.offer(new int[]{0,M});
        while (!heap.isEmpty())
        {
            int[] curr = heap.poll();
            int currNode = curr[0];
            int remainMoves = curr[1];
            if(visited[currNode])continue;
            visited[currNode] = true;
            ans++;
            for (int i = 0; i < N; i++) {
                if(graph[currNode][i] >= 0)
                {
                    if(!visited[i] && remainMoves > graph[currNode][i])
                    {
                        // add the remain reachable nodes
                        heap.offer(new int[]{i,  remainMoves -graph[currNode][i] -1});
                    }
                    graph[i][currNode] -= Math.min(remainMoves, graph[currNode][i]);
                    // add the reachable nodes between node currNode and node i
                    ans += Math.min(remainMoves, graph[currNode][i]);

                }
            }

        }

        return ans;

    }


    public static void main(String[] args) {

    }
}
