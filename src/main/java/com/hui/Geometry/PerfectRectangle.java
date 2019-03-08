package com.hui.Geometry;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/27 15:39
 *
 *391. Perfect Rectangle
 * DescriptionHintsSubmissionsDiscussSolution
 * Given N axis-aligned rectangles where N > 0, determine if they all together form an exact cover of a rectangular region.
 *
 * Each rectangle is represented as a bottom-left point and a top-right point. For example, a unit square is represented as [1,1,2,2]. (coordinate of bottom-left point is (1, 1) and top-right point is (2, 2)).
 *
 *
 * Example 1:
 *
 * rectangles = [
 *   [1,1,3,3],
 *   [3,1,4,2],
 *   [3,2,4,4],
 *   [1,3,2,4],
 *   [2,3,3,4]
 * ]
 *
 * Return true. All 5 rectangles together form an exact cover of a rectangular region.
 *
 *
 *
 *
 * Example 2:
 *
 * rectangles = [
 *   [1,1,2,3],
 *   [1,3,2,4],
 *   [3,1,4,2],
 *   [3,2,4,4]
 * ]
 *
 * Return false. Because there is a gap between the two rectangular regions.
 *
 *
 *
 *
 * Example 3:
 *
 * rectangles = [
 *   [1,1,3,3],
 *   [3,1,4,2],
 *   [1,3,2,4],
 *   [3,2,4,4]
 * ]
 *
 * Return false. Because there is a gap in the top center.
 *
 *
 *
 *
 * Example 4:
 *
 * rectangles = [
 *   [1,1,3,3],
 *   [3,1,4,2],
 *   [1,3,2,4],
 *   [2,2,4,4]
 * ]
 *
 * Return false. Because two of the rectangles overlap with each other.
 *
 */
public class PerfectRectangle {

    public boolean isRectangleCover(int[][] rectangles) {
        if(rectangles == null || rectangles.length == 0 || rectangles[0].length == 0)return false;
        int area = 0;

        Set<Point> seen = new HashSet<>();
        int x1 = Integer.MAX_VALUE;
        int y1 = Integer.MAX_VALUE;
        int x2 = Integer.MIN_VALUE;
        int y2 = Integer.MIN_VALUE;
        for(int[] rect: rectangles)
        {
            area += (rect[2] - rect[0])*(rect[3] - rect[1]);
            x1 = Math.min(x1, rect[0]);
            y1 = Math.min(y1, rect[1]);
            x2 = Math.max(x2, rect[2]);
            y2 = Math.max(y2, rect[3]);

            Point point0 = new Point(rect[0], rect[1]);
            Point point1 = new Point(rect[2], rect[1]);
            Point point2 = new Point(rect[2], rect[3]);
            Point point3 = new Point(rect[0], rect[3]);
            if(!seen.add(point0))seen.remove(point0);
            if(!seen.add(point1))seen.remove(point1);
            if(!seen.add(point2))seen.remove(point2);
            if(!seen.add(point3))seen.remove(point3);

        }
        if(seen.size() != 4 ||
                !seen.contains(new Point(x1,y1)) ||
                !seen.contains(new Point(x2,y1)) ||
                !seen.contains(new Point(x2,y2)) ||
                !seen.contains(new Point(x1, y2)))return false;

        return area == (x2 - x1)*(y2 - y1);
    }

    private static class Point{
        int x,y;
        public Point(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return 1231*x + 1237*y;
        }

        @Override
        public boolean equals(Object obj) {
            if( !(obj instanceof Point))return false;
            Point other = (Point)obj;
            return other.x == x && other.y == y;
        }
    }


}
