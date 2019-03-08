package com.hui.Math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/11 12:40
 */
public class Triangle {


    /**
     *
     *
     * 120. Triangle
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
     *
     * For example, given the following triangle
     *
     * [
     *      [2],
     *     [3,4],
     *    [6,5,7],
     *   [4,1,8,3]
     * ]
     * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
     *
     * Note:
     *
     * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
     *
     *
     * minPath[k][i] = min( minPath[k+1][i], minPath[k+1][i+1]) + triangle[k][i];
     * minPath[i] = min(minPath(i),minPath(i+1)) + triangle(i) (kth row);
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {

        if(triangle == null|| triangle.size() == 0)return 0;
        int k = triangle.size();
        int[] minPath = new int[k];
        for (int i = 0; i < k ; i++) {
            minPath[i] = triangle.get(k-1).get(i);
        }
        for (int kth = k -2; kth >=0; kth--) {
            List<Integer> list = triangle.get(kth);
            for (int i = 0; i <= kth; i++) {
                minPath[i] = Math.min(minPath[i],minPath[i+1]) + list.get(i);
            }
        }
        return minPath[0];
    }

    public static void main(String[] args) {

        Triangle triangle = new Triangle();
        List<List<Integer>> lists = new ArrayList<>();
        lists.add(Arrays.asList(2));
        lists.add(Arrays.asList(3,4));
        lists.add(Arrays.asList(6,5,7));
        lists.add(Arrays.asList(4,1,8,3));
        System.out.println(triangle.minimumTotal(lists));
    }
}
