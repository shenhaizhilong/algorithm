package com.hui.algorithm;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/5 21:53
 */
public class Heaters {

    /**
     *475. Heaters
     * DescriptionHintsSubmissionsDiscussSolution
     * Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.
     *
     * Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so that all houses could be covered by those heaters.
     *
     * So, your input will be the positions of houses and heaters seperately, and your expected output will be the minimum radius standard of heaters.
     *
     * Note:
     * Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
     * Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
     * As long as a house is in the heaters' warm radius range, it can be warmed.
     * All the heaters follow your radius standard and the warm radius will the same.
     * Example 1:
     * Input: [1,2,3],[2]
     * Output: 1
     * Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
     * Example 2:
     * Input: [1,2,3,4],[1,4]
     * Output: 1
     * Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses ca
     *
     * @param houses
     * @param heaters
     * @return
     */
    public int findRadius(int[] houses, int[] heaters) {

        Arrays.sort(heaters);
        int res = Integer.MIN_VALUE;
        for(int h: houses)
        {
            int nearestHeaterIndex = Arrays.binarySearch(heaters,h);
            if(nearestHeaterIndex < 0)
            {
                nearestHeaterIndex = -(nearestHeaterIndex + 1);
            }
            int dist1 = nearestHeaterIndex -1 >=0 ? h - heaters[nearestHeaterIndex -1] : Integer.MAX_VALUE;
            int dist2 = nearestHeaterIndex < heaters.length ? heaters[nearestHeaterIndex] - h: Integer.MAX_VALUE;
            res = Math.max(res, Math.min(dist1,dist2));
        }

        return res;
    }


    public static void main(String[] args) {
        Heaters heaters = new Heaters();
        System.out.println(heaters.findRadius(new int[]{1,2,3,4},new int[]{1,4}));
    }
}
