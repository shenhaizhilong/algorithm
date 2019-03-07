package com.hui.algorithm;

/**
 *
 * 812. Largest Triangle Area
 * DescriptionHintsSubmissionsDiscussSolution
 * You have a list of points in the plane. Return the area of the largest triangle that can be formed by any 3 of the points.
 *
 * Example:
 * Input: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
 * Output: 2
 * Explanation:
 * The five points are show in the figure below. The red triangle is the largest.
 *
 * @author: shenhaizhilong
 * @date: 2018/8/21 19:33
 */
public class LargestTriangleArea {


    /**
     * https://en.wikipedia.org/wiki/Shoelace_formula
     * Shoelace formula
     * @param p
     * @param q
     * @param t
     * @return
     */
    private double area(int[]p, int[] q, int[] t)
    {
        return 0.5D*Math.abs(p[0]*q[1] + q[0]*t[1] + t[0]*p[1] - p[1]*q[0] - q[1]*t[0] - t[1]*p[0]);
    }

    public double largestTriangleArea(int[][] points) {
        double results = 0.0D;
        for (int i = 0; i < points.length; i++) {
            for (int j = i+1; j < points.length; j++) {
                for (int k = j +1; k <points.length ; k++) {
                    results = Math.max(results, area(points[i], points[j], points[k]));
                }
            }
        }

        return results;
    }

    public static void main(String[] args) {
      int[][]  points = {{0,0},{0,1},{1,0},{0,2},{2,0}};
      LargestTriangleArea largestTriangleArea = new LargestTriangleArea();
        System.out.println(largestTriangleArea.largestTriangleArea(points));

    }
}
