package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/29 9:16
 */
public class PoorPigs {


    /**
     *458. Poor Pigs
     * DescriptionHintsSubmissionsDiscussSolution
     * There are 1000 buckets, one and only one of them contains poison, the rest are filled with water. They all look the same. If a pig drinks that poison it will die within 15 minutes. What is the minimum amount of pigs you need to figure out which bucket contains the poison within one hour.
     *
     * Answer this question, and write an algorithm for the follow-up general case.
     *
     * Follow-up:
     *
     * If there are n buckets and a pig drinking poison will die within m minutes, how many pigs (x) you need to figure out the "poison" bucket within p minutes? There is exact one bucket with poison.
     *
     *
     *
     * https://leetcode.com/problems/poor-pigs/discuss/94305/1-line-solution-with-detailed-problem-clarification-math-proof-please-read-if-you-really-want-to-know-what-this-problem-means
     *https://leetcode.com/problems/poor-pigs/discuss/94266/Another-explanation-and-solution
     *
     * @param buckets
     * @param minutesToDie
     * @param minutesToTest
     * @return
     */
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {

         int t = minutesToTest/minutesToDie;
         return (int) Math.ceil(Math.log(buckets)/Math.log(t +1));
    }

    public static void main(String[] args) {
        PoorPigs poorPigs = new PoorPigs();
        System.out.println(poorPigs.poorPigs(1000, 15,60));
    }
}
