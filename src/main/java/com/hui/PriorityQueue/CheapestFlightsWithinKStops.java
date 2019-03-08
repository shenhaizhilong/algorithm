package com.hui.PriorityQueue;

import java.util.*;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/13 17:45
 *
 *787. Cheapest Flights Within K Stops
 * DescriptionHintsSubmissionsDiscussSolution
 * There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.
 *
 * Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.
 *
 * Example 1:
 * Input:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 1
 * Output: 200
 * Explanation:
 * The graph looks like this:
 *
 *
 * The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
 * Example 2:
 * Input:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 0
 * Output: 500
 * Explanation:
 * The graph looks like this:
 *
 *
 * The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
 * Note:
 *
 * The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
 * The size of flights will be in range [0, n * (n - 1) / 2].
 * The format of each flight will be (src, dst, price).
 * The price of each flight will be in the range [1, 10000].
 * k is in the range of [0, n - 1].
 * There will not be any duplicated flights or self cycles.
 *
 */
public class CheapestFlightsWithinKStops {

    public int findCheapestPrice(int N, int[][] flights, int src, int dst, int K) {
        Map<Integer,Map<Integer,Integer>> map = new HashMap<>();
        for(int[] flight:flights)
        {
            int u = flight[0];
            int v = flight[1];
            int w = flight[2];  // weight ==> cost
            if(!map.containsKey(u))
            {
                map.put(u, new HashMap<>());
            }
            // put the min distance for node (u,v)
            Map<Integer,Integer> neighbour = map.get(u);
            neighbour.put(v,w);
        }

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });   // {cost, nodeN, edgesNumber/stops}

        minHeap.offer(new int[]{0,src,K +1});
        while (!minHeap.isEmpty())
        {
            int[] curr = minHeap.poll();
            int cost = curr[0];
            int city = curr[1];
            int stops = curr[2];
            if(city == dst)return cost;

            if(stops > 0)
            {
                Map<Integer,Integer> adjMap = map.getOrDefault(city, new HashMap<>());
                for(int next: adjMap.keySet())
                {
                    minHeap.offer(new int[]{cost + adjMap.get(next), next, stops -1});
                }
            }
        }

        return -1;

    }


    // https://leetcode.com/problems/cheapest-flights-within-k-stops/discuss/115596/c++-8-line-bellman-ford
    //   dynamic programming solution. As you see, this solution is very similar to bellman-ford algorithm Actually,
    // bellman-ford algorithm is a space-optimized(from 2D to 1D) version of this code. Hope this code will provide you with a deeper insight of bellman-ford algorithm

    public int findCheapestPrice2(int N, int[][] flights, int src, int dst, int K) {

        // dp[i][j] means the shortest distance from src to node j with the number of edges between them at most i
        int[][] dp = new int[K +2][N +1];
        for(int[] row:dp)
        {
            Arrays.fill(row,Integer.MAX_VALUE);
        }
        for (int i = 0; i <= K +1; i++) {
            dp[i][src] = 0;
        }

        for (int i = 1; i <= K +1; i++) {
            for(int[] flight: flights)
            {
                int u = flight[0];
                int v = flight[1];
                int price = flight[2]; // the distance from u to v
                if(dp[i -1][u] != Integer.MAX_VALUE)
                {
                    dp[i][v] = Math.min(dp[i][v], dp[i -1][u] + price);
                }
            }
        }

        return dp[K +1][dst] == Integer.MAX_VALUE ? -1: dp[K +1][dst];
    }

    public int findCheapestPrice3(int N, int[][] flights, int src, int dst, int K) {
        int[] cost = new int[N +1];

        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[src] = 0;
        for (int i = 0; i <= K; i++) {
            int[] curr = Arrays.copyOf(cost,cost.length);
            for(int[] flight: flights)
            {
                int u = flight[0];
                int v = flight[1];
                int price = flight[2];
                if(cost[u] != Integer.MAX_VALUE)
                {
                    curr[v] = Math.min(curr[v], cost[u] + price);
                }
            }
            cost = curr;
        }

        return cost[dst] == Integer.MAX_VALUE ? -1: cost[dst];
    }
}
