package com.hui.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/25 23:52
 *
 *
 * 956. Tallest Billboard
 * DescriptionHintsSubmissionsDiscussSolution
 * You are installing a billboard and want it to have the largest height.  The billboard will have two steel supports, one on each side.  Each steel support must be an equal height.
 *
 * You have a collection of rods which can be welded together.  For example, if you have rods of lengths 1, 2, and 3, you can weld them together to make a support of length 6.
 *
 * Return the largest possible height of your billboard installation.  If you cannot support the billboard, return 0.
 *
 *
 *
 * Example 1:
 *
 * Input: [1,2,3,6]
 * Output: 6
 * Explanation: We have two disjoint subsets {1,2,3} and {6}, which have the same sum = 6.
 * Example 2:
 *
 * Input: [1,2,3,4,5,6]
 * Output: 10
 * Explanation: We have two disjoint subsets {2,3,5} and {4,6}, which have the same sum = 10.
 * Example 3:
 *
 * Input: [1,2]
 * Output: 0
 * Explanation: The billboard cannot be supported, so we return 0.
 *
 *
 * Note:
 *
 * 0 <= rods.length <= 20
 * 1 <= rods[i] <= 1000
 * The sum of rods is at most 5000.
 *
 */
public class TallestBillboard {

    public int tallestBillboard(int[] rods) {

        if(rods.length == 2)return rods[0] == rods[1] ? rods[0]:0;
        int sum = 0;
        for(int r:rods) sum += r;
        int half = sum/2;
        // key is the diff == sum(left rods) - sum(right rods)
        // value is the left billboard largest length
        Map<Integer,Integer> dp = new HashMap<>();
        // init diff == 0, left largest length == 0
        dp.put(0,0);
        for (int i = 0; i < rods.length; i++) {
            Map<Integer,Integer> curr = new HashMap<>(dp);
            for(int diff:dp.keySet())
            {
                // add it to left billBoard
                if(diff + rods[i] <= half)
                {
                    curr.put(diff + rods[i],
                            Math.max(curr.getOrDefault(diff + rods[i], 0), dp.get(diff) + rods[i]));
                }

                // add it to right bill board
                if(diff - rods[i] >= - half)
                {
                    curr.put(diff - rods[i], Math.max(curr.getOrDefault(diff - rods[i],0), dp.get(diff)));

                }


            }
            dp = curr;
        }

        return dp.get(0);
    }


    public static void main(String[] args) {
        TallestBillboard tallestBillboard = new TallestBillboard();
        System.out.println(tallestBillboard.tallestBillboard(new int[]{1,2,3,6}));
        System.out.println(tallestBillboard.tallestBillboard(new int[]{1,2,3,4,5,6}));
        System.out.println(tallestBillboard.tallestBillboard(new int[]{1,2}));

    }
}
