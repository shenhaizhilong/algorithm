package com.hui.UnionFindD;

import com.hui.Array.Matrix;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/1 16:44
 *
 *
 *685. Redundant Connection II
 * DescriptionHintsSubmissionsDiscussSolution
 * In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.
 *
 * The given input is a directed graph that started as a rooted tree with N nodes (with distinct values 1, 2, ..., N), with one additional directed edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.
 *
 * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] that represents a directed edge connecting nodes u and v, where u is a parent of child v.
 *
 * Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array.
 *
 * Example 1:
 * Input: [[1,2], [1,3], [2,3]]
 * Output: [2,3]
 * Explanation: The given directed graph will be like this:
 *   1
 *  / \
 * v   v
 * 2-->3
 * Example 2:
 * Input: [[1,2], [2,3], [3,4], [4,1], [1,5]]
 * Output: [4,1]
 * Explanation: The given directed graph will be like this:
 * 5 <- 1 -> 2
 *      ^    |
 *      |    v
 *      4 <- 3
 * Note:
 * The size of the input 2D-array will be between 3 and 1000.
 * Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.
 *
 *
 *
 * https://leetcode.com/problems/redundant-connection-ii/discuss/141897/3ms-Union-Find-with-Explanations
 *
 *hought
 *
 * There are 3 cases for Redundant Connection:
 * case 1: two-parent problem such that an error node is with two parents
 *
 *  1
 *  / \
 * v   v
 * 2-->3     remove the second parentEdge of the node with two parents
 * case 2: cyclic problem such that there is a cycle in the graph
 *
 *  1
 *  / ^
 * v   \
 * 2-->3     remove the edge that forms the cycle
 * case 3: two-parent and cyclic problem
 *
 *  1
 *  / ^
 * v   \
 * 2-->3 <- 4     remove [2, 3] (to explain)
 * Explanation for case 3:
 * We do union only if it is not the second parentEdge. Why?
 * We assume we always remove the second parentEdge. If there is still cycle remained - that means we made the wrong choice, that is, we should remove the first parentEdge instead.
 *
 * If [[1, 2], [2, 3], [4, 3], [3, 1]], [2, 3] comes before [4, 3], we remove [4,3], then we union [1, 2], [2, 3], [3, 1], there is still cycle -- so we should remove [2, 3].
 * If [[1, 2], [4, 3], [2, 3], [3, 1]], [4, 3] comes before [2, 3], we remove [2, 3], then we union [1, 2], [4, 3], [3, 1], there is not cycle -- so we should remove [2, 3].
 *
 */
public class RedundantConnectionII {

    public int[] findRedundantDirectedConnection(int[][] edges) {
        int N = edges.length;
        int twoParentsEdge = -1;
        int makeCycleEdge = -1;
        UnionFind unionFind = new UnionFind(N +1);

        int[] parents = new int[N +1];
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            // have two parents
            if(parents[v] != 0)
            {
                twoParentsEdge = i;
                break;
            }else {
                parents[v] = u;
            }
        }

        for (int i = 0; i < edges.length; i++) {
            if(i == twoParentsEdge)continue;
            int u = edges[i][0];
            int v = edges[i][1];
            if(!unionFind.union(u,v))
            {
                makeCycleEdge = i;
                break;
            }
        }

        // have cycle and twoParents edge, delete first twoParents edge.
        if(makeCycleEdge != -1 && twoParentsEdge != -1)
        {
            int v = edges[twoParentsEdge][1];
            int u = parents[v];
            return new int[]{u,v};
        }

        // no two parents edge, just have cycle edge
        if(makeCycleEdge != -1)
        {
            return edges[makeCycleEdge];
        }

        // no cycle edge, just have two parent edge.
        if(twoParentsEdge != -1)
        {
            return edges[twoParentsEdge];
        }


        // no Redundant Connection
        return new int[]{0,0};

    }





    public static void main(String[] args) {

        RedundantConnectionII redundantConnection = new RedundantConnectionII();
        Matrix.print(redundantConnection.findRedundantDirectedConnection(new int[][]{{1,2},{1,3},{2,3}}));
        Matrix.print(redundantConnection.findRedundantDirectedConnection(new int[][]{{1,2},{2,3},{3,4},{4,1},{1,5}}));
        Matrix.print(redundantConnection.findRedundantDirectedConnection(new int[][]{{2,1},{1,4},{3,1},{4,2}}));
    }

}
