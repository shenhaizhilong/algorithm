package com.hui.Random;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * 497. Random Point in Non-overlapping Rectangles
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a list of non-overlapping axis-aligned rectangles rects, write a function pick which randomly and uniformily picks an integer point in the space covered by the rectangles.
 *
 * Note:
 *
 * An integer point is a point that has integer coordinates.
 * A point on the perimeter of a rectangle is included in the space covered by the rectangles.
 * ith rectangle = rects[i] = [x1,y1,x2,y2], where [x1, y1] are the integer coordinates of the bottom-left corner, and [x2, y2] are the integer coordinates of the top-right corner.
 * length and width of each rectangle does not exceed 2000.
 * 1 <= rects.length <= 100
 * pick return a point as an array of integer coordinates [p_x, p_y]
 * pick is called at most 10000 times.
 * Example 1:
 *
 * Input:
 * ["Solution","pick","pick","pick"]
 * [[[[1,1,5,5]]],[],[],[]]
 * Output:
 * [null,[4,1],[4,1],[3,3]]
 * Example 2:
 *
 * Input:
 * ["Solution","pick","pick","pick","pick","pick"]
 * [[[[-2,-2,-1,-1],[1,0,3,0]]],[],[],[],[],[]]
 * Output:
 * [null,[-1,-2],[2,0],[-2,-1],[3,0],[-2,-2]]
 * Explanation of Input Syntax:
 *
 * The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument, the array of rectangles rects. pick has no arguments. Arguments are always wrapped with a list, even if there aren't any.
 * @author: shenhaizhilong
 * @date: 2018/10/5 9:43
 */
public class RandomPointinNonoverlappingRectangles {

    private int[][] rects;
    private int[] pointsNumSum;  // all rects points Number sum;
    Random random;
    public RandomPointinNonoverlappingRectangles(int[][] rects) {
        int len = rects.length;
        this.rects = rects;
        random =  new Random();
        pointsNumSum = new int[len];
        pointsNumSum[0] = (rects[0][2] - rects[0][0] + 1)*(rects[0][3] - rects[0][1] + 1); // the number of points in one rect
        for (int i = 1; i <len ; i++) {
            int[] rect = rects[i];
            int points = (rect[2] - rect[0] + 1)*(rect[3] - rect[1] + 1);  // the number of points in one rect
            pointsNumSum[i] = pointsNumSum[i -1] + points;
        }

    }


    /**
     *
     * Use accumulated the number of points to get idx.
     *    pointsSum[] = {2,5,3,4} => pointsSum[] = {2,7,10,14}
     *    then get random val random.nextInt(14)+1, idx is in range [1,14]
     *
     *    idx in [1,2] return 0
     *    idx in [3,7] return 1
     *    idx in [8,10] return 2
     *    idx in [11,14] return 3
     * @return
     */
    public int[] pick() {

        // all rects points Number sum;
        int pointsSum = pointsNumSum[pointsNumSum.length -1];
        // based on
        int target = random.nextInt(pointsSum) + 1;  // get a random int in [1, pointsSum];
        // random find a rect, then random find the point in this rect.
        int id = Arrays.binarySearch(pointsNumSum, target);
        id = id >=0 ? id: -id -1;
        int[] rect = rects[id];
        return pickOnePointInRect(rect);



    }


    // find one random point in a Rect
    private int[] pickOnePointInRect(int[] rect)
    {
        int left = rect[0];
        int bottom = rect[1];
        int right = rect[2];
        int top = rect[3];
        return new int[]{left + random.nextInt(right - left + 1), bottom + random.nextInt(top - bottom +1)};
    }

    public int[] pick2() {

        // all rects points Number sum;
        int pointsSum = pointsNumSum[pointsNumSum.length -1];
        // based on
        int target = random.nextInt(pointsSum) + 1;  // get a random int in [1, pointsSum];
        // random find a rect, then random find the point in this rect.
        int id = Arrays.binarySearch(pointsNumSum, target);
        id = id >=0 ? id: -id -1;
        int[] rect = rects[id];
        int left = rect[0], right = rect[2], bottom = rect[1];
        int xPoints = right - left + 1;
        int randPointSum = pointsNumSum[id] - target;
        int x = left + randPointSum%xPoints;
        int y = bottom + randPointSum/xPoints;
        return new int[]{x,y};



    }

}
