package com.hui.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/2 17:07
 */
public class NumberofBoomerangs {

    /**
     *
     * 447. Number of Boomerangs
     * DescriptionHintsSubmissionsDiscussSolution
     * Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k) such that the distance between i and j equals the distance between i and k (the order of the tuple matters).
     *
     * Find the number of boomerangs. You may assume that n will be at most 500 and coordinates of points are all in the range [-10000, 10000] (inclusive).
     *
     * Example:
     * Input:
     * [[0,0],[1,0],[2,0]]
     *
     * Output:
     * 2
     *
     * Explanation:
     * The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]
     *
     * @param points
     * @return
     */
    public int numberOfBoomerangs(int[][] points) {
        Map<Integer, Integer> map = new HashMap<>(points.length);
        int res = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length ; j++) {
                if(i == j)continue;
                int d = distance(points[i], points[j]);
                map.put(d, map.getOrDefault(d,0) + 1);
            }
            for(int v: map.values())
            {
                res += v*(v -1);
            }
            map.clear();
        }
        return res;
    }

    private int distance(int[] p, int[] q)
    {
        int x = p[0] - q[0];
        int y = p[1] - q[1];
        return x*x + y*y;
    }
}
