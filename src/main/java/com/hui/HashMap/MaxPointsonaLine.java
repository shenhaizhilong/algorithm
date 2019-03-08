package com.hui.HashMap;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/16 23:34
 *
 *
 * 149. Max Points on a Line
 * DescriptionHintsSubmissionsDiscussSolution
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 *
 * Example 1:
 *
 * Input: [[1,1],[2,2],[3,3]]
 * Output: 3
 * Explanation:
 * ^
 * |
 * |        o
 * |     o
 * |  o
 * +------------->
 * 0  1  2  3  4
 * Example 2:
 *
 * Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * Output: 4
 * Explanation:
 * ^
 * |
 * |  o
 * |     o        o
 * |        o
 * |  o        o
 * +------------------->
 * 0  1  2  3  4  5  6
 *
 */
public class MaxPointsonaLine {

    public int maxPoints(Point[] points) {

        Map<String,Integer> counter = new HashMap<>();
        int ans = 0;
        for (int i = 0; i < points.length -1; i++) {
            int overlap = 0;
            int max = 0;
            counter.clear();
            for (int j = i +1; j < points.length; j++) {
                int xDiff = points[i].x - points[j].x;
                int yDiff = points[i].y - points[j].y;
                if(xDiff == 0 && yDiff == 0)
                {
                    overlap++;
                    continue;
                }

                int factor = gcd(xDiff,yDiff);
                if(factor != 0)
                {
                    xDiff = xDiff/factor;
                    yDiff = yDiff/factor;
                }

                String key = xDiff +"#" + yDiff;
                counter.put(key, counter.getOrDefault(key,0) +1);
                max = Math.max(max, counter.get(key));

            }
            ans = Math.max(ans, max + overlap +1);

        }
        return ans;
    }



    private int gcd(int a, int b)
    {
        while (b != 0)
        {
            int temp = a % b;
            a = b;
            b = temp;
        }

        return a +b;
    }

    public static void main(String[] args) {
        MaxPointsonaLine maxPointsonaLine = new MaxPointsonaLine();
        System.out.println(maxPointsonaLine.gcd(-4,-2));
    }
}
