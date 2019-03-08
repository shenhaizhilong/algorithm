package com.hui.DFS;

import com.hui.Array.Matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/14 15:43
 *
 *
 * 834. Sum of Distances in Tree
 * DescriptionHintsSubmissionsDiscussSolution
 * An undirected, connected tree with N nodes labelled 0...N-1 and N-1 edges are given.
 *
 * The ith edge connects nodes edges[i][0] and edges[i][1] together.
 *
 * Return a list ans, where ans[i] is the sum of the distances between node i and all other nodes.
 *
 * Example 1:
 *
 * Input: N = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
 * Output: [8,12,6,10,10,10]
 * Explanation:
 * Here is a diagram of the given tree:
 *   0
 *  / \
 * 1   2
 *    /|\
 *   3 4 5
 * We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
 * equals 1 + 1 + 2 + 2 + 2 = 8.  Hence, answer[0] = 8, and so on.
 * Note: 1 <= N <= 10000
 *
 *
 * ---------
 * https://leetcode.com/problems/sum-of-distances-in-tree/discuss/130583/C++JavaPython-Pre-order-and-Post-order-DFS-O(N)
 * Intuition:
 * What if given a tree, with a certain root 0?
 * In O(N) we can find sum of distances in tree from root and all other nodes.
 * Now for all N nodes?
 * Of course, we can do it N times and solve it in O(N^2).
 * C++ and Java may get accepted luckly, but it's not what we want.
 *
 * When we move our root from one node to its connected node, one part of nodes get closer, one the other part get further.
 * If we know exactly hom many nodes in both parts, we can solve this problem.
 *
 * With one single traversal in tree, we should get enough information for it and don't need to do it again and again.
 *
 * Explanation:
 * 0. Let's solve it with node 0 as root.
 *
 * Initial an array of hashset tree, tree[i] contains all connected nodes to i.
 * Initial an array count, count[i] counts all nodes in the subtree i.
 * Initial an array of res, res[i] counts sum of distance in subtree i.
 *
 * Post order dfs traversal, update count and res:
 * count[root] = sum(count[i]) + 1
 * res[root] = sum(res[i]) + sum(count[i])
 *
 * Pre order dfs traversal, update res:
 * When we move our root from parent to its child i, count[i] points get 1 closer to root, n - count[i] nodes get 1 futhur to root.
 * res[i] = res[root] - count[i] + N - count[i]
 *
 * return res, done.
 *
 * Time Complexity:
 * dfs: O(N)
 * dfs2: O(N)
 *
 *
 */
public class SumofDistancesinTree {

    // converted to Ntree
    private int[] res;   // the sum of distance in subtree i
    private int[] count; // the number of nodes in subtree i
    private List<List<Integer>> tree; // all connected nodes to i
    private int N;  // the counts of nodes

    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        res = new int[N];
        count = new int[N];
        tree = new ArrayList<>(N);
        this.N = N;
        for (int i = 0; i < N; i++) {
            tree.add(new ArrayList<>());
        }
        for(int[] edge: edges)
        {
            tree.get(edge[0]).add(edge[1]);
            tree.get(edge[1]).add(edge[0]);
        }

        // first post order dfs, collect count[i] and res[i]
        // second pre order dfs, update res[child] based on res[parent]
        dfs(0, -1);
        dfs2(0, -1);
        return res;

    }

    // bottom to top sum, post Order
    private void dfs(int root, int parent)
    {
        for(int child: tree.get(root))
        {
            // avoid cycle
            if(parent != child)
            {
                dfs(child, root);
                count[root] += count[child];
                res[root] += res[child] + count[child];
            }
        }
        count[root]++;
    }


    // top to bottom update, prevOrder
    private void dfs2(int root, int parent)
    {

        for(int child: tree.get(root))
        {
            // avoid cycle
            if(parent != child)
            {
                res[child] = res[root] - count[child] + N - count[child];
                dfs2(child, root);
            }
        }
    }


    public static void main(String[] args) {

        int[][] edges = {{0,1},{0,2},{2,3},{2,4},{2,5}};
        int N = 6;
        SumofDistancesinTree sumofDistancesinTree = new SumofDistancesinTree();
        Matrix.print(sumofDistancesinTree.sumOfDistancesInTree(N, edges));

    }
}
