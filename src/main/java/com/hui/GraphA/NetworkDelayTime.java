package com.hui.GraphA;

import java.util.*;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/11 0:08
 *
 *743. Network Delay Time
 * DescriptionHintsSubmissionsDiscussSolution
 * There are N network nodes, labelled 1 to N.
 *
 * Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.
 *
 * Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.
 *
 * Note:
 * N will be in the range [1, 100].
 * K will be in the range [1, N].
 * The length of times will be in the range [1, 6000].
 * All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 1 <= w <= 100.
 *
 */
public class NetworkDelayTime {




    //relax algorithm
    public int BellFord(int[][] times, int N, int K) {
        int[] distance = new int[N +1];
        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[K] = 0;
        for (int i = 1; i <= N; i++) {
            for(int[] time: times)
            {
                int u = time[0];
                int v = time[1];
                int w = time[2];
                if(distance[u] != Integer.MAX_VALUE && distance[v] > distance[u] + w)
                {
                    distance[v] = distance[u] + w;
                }
            }
        }

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            ans = Math.max(ans, distance[i]);
        }
        return ans == Integer.MAX_VALUE ? -1: ans;

    }

    public int networkDelayTime(int[][] times, int N, int K) {

        Map<Integer,Map<Integer,Integer>> map = new HashMap<>();
        for(int[] time:times)
        {
            int u = time[0];
            int v = time[1];
            int w = time[2];
            if(!map.containsKey(u))
            {
                map.put(u, new HashMap<>());
            }

            // put the min distance for node (u,v)
            Map<Integer,Integer> neighbour = map.get(u);
            int w2 = neighbour.getOrDefault(v,Integer.MAX_VALUE);
            neighbour.put(v,Math.min(w2,w));


        }

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });   // {distance, nodeN}

        // recording the min distance from node n to node k
        int[] distance = new int[N +1];
        Arrays.fill(distance,Integer.MAX_VALUE);

        int ans = 0;
        int topNum = 0;
        // the distance from  node k to node k  is zero
        distance[K] = 0;
        boolean[] visited = new boolean[N+1];
        minHeap.offer(new int[]{0,K});
        while (!minHeap.isEmpty())
        {
            int[] curr = minHeap.poll();
            int node = curr[1];
            if(visited[node])continue;

            // visit curr node
            visited[node] = true;
            int dist = curr[0];
            ans = dist;
            topNum++;
            if(!map.containsKey(node))continue;
            for (int next:map.get(node).keySet())
            {
                int nextDis = map.get(node).get(next);
                if(!visited[next] && nextDis + dist < distance[next])
                {
                    distance[next]= dist + nextDis;
                    minHeap.offer(new int[]{distance[next], next});
                }
            }


        }

        return topNum == N ? ans: -1;
    }

}
