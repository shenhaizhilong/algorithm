package com.hui.algorithm;

import java.util.*;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/9 11:06
 *
 *There is a brick wall in front of you. The wall is rectangular and has several rows of bricks. The bricks have the same height but different width. You want to draw a vertical line from the top to the bottom and cross the least bricks.
 *
 * The brick wall is represented by a list of rows. Each row is a list of integers representing the width of each brick in this row from left to right.
 *
 * If your line go through the edge of a brick, then the brick is not considered as crossed. You need to find out how to draw the line to cross the least bricks and return the number of crossed bricks.
 *
 * You cannot draw a line just along one of the two vertical edges of the wall, in which case the line will obviously cross no bricks.
 *
 *
 *
 * Example:
 *
 * Input: [[1,2,2,1],
 *         [3,1,2],
 *         [1,3,2],
 *         [2,4],
 *         [3,1,2],
 *         [1,3,1,1]]
 *
 * Output: 2
 *
 * Explanation:
 *
 *
 *
 * Note:
 *
 * The width sum of bricks in different rows are the same and won't exceed INT_MAX.
 * The number of bricks in each row is in range [1,10,000]. The height of wall is in range [1,10,000]. Total number of bricks of the wall won't exceed 20,000.
 *
 *
 */
public class BrickWall {

    public int leastBricks(List<List<Integer>> wall) {

        Map<Integer,Integer> counter = new HashMap<>();
        int rowSum = -1;
        int[] maxCount = {0,0};  // count and preSum
        for(List<Integer> row: wall)
        {
            int sum = 0;
            for(int i:row)
            {
                sum += i;
                int count = counter.getOrDefault(sum,0) +1;
                counter.put(sum, count);
                if(rowSum != sum && count > maxCount[0])
                {
                    maxCount[0] = count;
                    maxCount[1] = sum;
                }

            }
            if(rowSum == -1)rowSum = sum;
            if(maxCount[1] == rowSum)
            {
                maxCount[0] = 0;
                maxCount[1] = 0;
            }
        }

        return wall.size() - maxCount[0];

    }

    public int leastBricks2(List<List<Integer>> wall) {

        Map<Integer,Integer> counter = new HashMap<>();
        int maxCount = 0;
        for(List<Integer> row: wall)
        {
            int sum = 0;
            for (int i = 0; i < row.size() -1; i++) {

                sum += row.get(i);
                int count = counter.getOrDefault(sum,0) +1;
                counter.put(sum, count);
                if( count > maxCount)
                {
                    maxCount = count;
                }
            }
        }

        return wall.size() - maxCount;

    }


    public static void main(String[] args) {
        BrickWall brickWall = new BrickWall();
        List<List<Integer>> lists = new ArrayList<>();
//        lists.add(Arrays.asList(1,2,2,1));
//        lists.add(Arrays.asList(3,1,2));
//        lists.add(Arrays.asList(1,3,2));
//        lists.add(Arrays.asList(2,4));
//        lists.add(Arrays.asList(3,1,2));
//        lists.add(Arrays.asList(1,3,1,1));
        lists.add(Arrays.asList(79,12,208,1));
        System.out.println(brickWall.leastBricks2(lists));
    }
}
