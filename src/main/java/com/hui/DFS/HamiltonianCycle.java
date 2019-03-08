package com.hui.DFS;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/16 11:28
 *
 * Hamiltonian Cycle
 *
 */
public class HamiltonianCycle {
    private int[][] graph;
    private int[] path;

    private int N;
    private boolean dfs(int pos)
    {
       if(pos == N)
       {
           // whether there is an edge from the last vertex and the first vertex
           return graph[path[pos -1]][path[0]] == 1;
       }

        for (int v = 1; v < N; v++) {
            if(isSafe(v, pos))
            {
                path[pos] = v;
                if(dfs(pos +1))return true;
                // adding vertex doesn't lead to a solution
                path[pos] = -1;
            }
        }

        return false;
    }

    // check whether we can add vertex v to path
    private boolean isSafe(int v, int pos)
    {
        if(graph[path[pos -1]][v] == 0)return false;
        // already in path
        for (int i = 0; i < pos; i++) {
            if(path[i] == v)
                return false;
        }
        return true;
    }

    public int hamCycle(int[][] graph)
    {
         this.N = graph.length;
        this.graph = graph;
        path = new int[N];
        Arrays.fill(path, -1);
        path[0] = 0;
        if(dfs(1) == false){
            System.out.println("no hamiltonian cycle");
            return 0;
        }
        printPath();
        return 1;
    }

    private void printPath()
    {
        for (int i = 0; i < N; i++) {
            System.out.print(path[i] + "->");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        int[][] graph1 = {
                {0, 1, 0, 1, 0},
                {1, 0, 1, 1, 1},
                {0, 1, 0, 0, 1},
                {1, 1, 0, 0, 1},
                {0, 1, 1, 1, 0},
        };
        HamiltonianCycle hamiltonianCycle = new HamiltonianCycle();
        System.out.println(hamiltonianCycle.hamCycle(graph1));
    }
}
