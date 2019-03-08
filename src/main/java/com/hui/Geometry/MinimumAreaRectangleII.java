package com.hui.Geometry;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/26 20:56
 *
 *
 *963. Minimum Area Rectangle II
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a set of points in the xy-plane, determine the minimum area of any rectangle formed from these points, with sides not necessarily parallel to the x and y axes.
 *
 * If there isn't any rectangle, return 0.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: [[1,2],[2,1],[1,0],[0,1]]
 * Output: 2.00000
 * Explanation: The minimum area rectangle occurs at [1,2],[2,1],[1,0],[0,1], with an area of 2.
 * Example 2:
 *
 *
 *
 * Input: [[0,1],[2,1],[1,1],[1,0],[2,0]]
 * Output: 1.00000
 * Explanation: The minimum area rectangle occurs at [1,0],[1,1],[2,1],[2,0], with an area of 1.
 * Example 3:
 *
 *
 *
 * Input: [[0,3],[1,2],[3,1],[1,3],[2,1]]
 * Output: 0
 * Explanation: There is no possible rectangle to form from these points.
 * Example 4:
 *
 *
 *
 * Input: [[3,1],[1,1],[0,1],[2,1],[3,3],[3,2],[0,2],[2,3]]
 * Output: 2.00000
 * Explanation: The minimum area rectangle occurs at [2,1],[2,3],[3,3],[3,1], with an area of 2.
 *
 *
 * Note:
 *
 * 1 <= points.length <= 50
 * 0 <= points[i][0] <= 40000
 * 0 <= points[i][1] <= 40000
 * All points are distinct.
 * Answers within 10^-5 of the actual value will be accepted as correct.
 *
 */
public class MinimumAreaRectangleII {

    public double minAreaFreeRect(int[][] points) {
        Set<String> set = new HashSet<>();
        for(int[] p:points)
        {
            set.add(p[0] +"," + p[1]);
        }

        double minArea = Double.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                for (int k = j +1; k < points.length; k++) {
                    if(i == j || i == k)continue;
                    int[] p1 = points[i];
                    int[] p2 = points[j];
                    int[] p3 = points[k];
                    // p2 - p1 = a, p3 - p1 = b; a.b = |a||b|cos90 == 0
                    if((p2[0] - p1[0])*(p3[0] - p1[0]) + (p2[1] - p1[1])*(p3[1] - p1[1]) != 0)continue;

                    // x4 = x3 + (x2 - x1)
                    //y4 = y3 + (y2 - y1)
                    int x4 = p3[0] + p2[0] - p1[0];
                    int y4 = p3[1] + p2[1] - p1[1];
                    if(!set.contains(x4 + "," + y4))continue;
                    minArea = Math.min(minArea, square(p1,p2, p3));
                }
            }
        }
        return minArea == Double.MAX_VALUE ? 0: minArea;
    }

    private double square(int[] p1, int[] p2, int[] p3)
    {
        double width = distance(p1,p2);
        double length = distance(p1, p3);
        return Math.sqrt(width*length);
    }

    // get distance^2, use double to avoid overflow
    private double distance(int[] p1, int[] p2)
    {
        return 1.0D*(p2[0] - p1[0])*(p2[0] - p1[0]) + (p2[1] - p1[1])*(p2[1] - p1[1]);
    }

    public static void main(String[] args) {

        int[][] points = {{3,1},{1,1},{0,1},{2,1},{3,3},{3,2},{0,2},{2,3}};
        MinimumAreaRectangleII minimumAreaRectangleII = new MinimumAreaRectangleII();
        System.out.println(minimumAreaRectangleII.minAreaFreeRect(points));

        int[][] points2 ={{1,2},{2,1},{1,0},{0,1}};
        System.out.println(minimumAreaRectangleII.minAreaFreeRect(points2));

    }

}
