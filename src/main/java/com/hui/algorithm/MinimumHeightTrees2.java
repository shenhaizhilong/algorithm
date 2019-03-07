package com.hui.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/16 18:06
 *
 *
 * https://leetcode.com/problems/minimum-height-trees/discuss/76055/Share-some-thoughts
 *
 *
 */
public class MinimumHeightTrees2 {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> ans = new ArrayList<>();
        if(n == 1)
        {
            ans.add(0);
            return ans;
        }
        List<Set<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new HashSet<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if(graph.get(i).size() == 1)leaves.add(i);
        }

        while (n > 2)
        {
            n -= leaves.size();
            List<Integer> newLeaves = new ArrayList<>();
            for(int leaf:leaves)
            {
                int next = graph.get(leaf).iterator().next();
                graph.get(next).remove(leaf);
                if(graph.get(next).size() == 1)newLeaves.add(next);
            }
            leaves = newLeaves;
        }
        return leaves;

    }
}
