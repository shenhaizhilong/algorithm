package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/13 0:12
 *
 *
 * 135. Candy
 * DescriptionHintsSubmissionsDiscussSolution
 * There are N children standing in a line. Each child is assigned a rating value.
 *
 * You are giving candies to these children subjected to the following requirements:
 *
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 *
 * Example 1:
 *
 * Input: [1,0,2]
 * Output: 5
 * Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 * Example 2:
 *
 * Input: [1,2,2]
 * Output: 4
 * Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
 *              The third child gets 1 candy because it satisfies the above two conditions.
 *
 *
 */
public class Candy {

    public int candy(int[] ratings) {
        if(ratings == null || ratings.length == 0)return 0;
        int N = ratings.length;
        int[] maxFromLeft = new int[N];
        int[] maxFromRight = new int[N];
        maxFromLeft[0] = 1;
        maxFromRight[N-1] = 1;
        for (int i = 1; i < N; i++) {
            maxFromLeft[i] = ratings[i] > ratings[i-1] ? maxFromLeft[i-1] +1: 1;
        }

        for (int i = N-2; i >=0; i--) {
            maxFromRight[i] = ratings[i] > ratings[i+1] ? maxFromRight[i+1] +1:1;
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans += Math.max(maxFromLeft[i], maxFromRight[i]);
        }
        return ans;
    }

    public int candy2(int[] ratings) {
        if(ratings == null || ratings.length == 0)return 0;
        int N = ratings.length;
        int[] maxFromRight = new int[N];
        maxFromRight[N-1] = 1;
        for (int i = N-2; i >=0; i--) {
            maxFromRight[i] = ratings[i] > ratings[i+1] ? maxFromRight[i+1] +1:1;
        }
        int  maxFromLeft = 1;
        int ans = Math.max(maxFromLeft, maxFromRight[0]);
        for (int i = 1; i < N; i++) {
            maxFromLeft = ratings[i] > ratings[i-1] ? maxFromLeft +1: 1;
            ans += Math.max(maxFromLeft, maxFromRight[i]);
        }
        return ans;
    }


    public int candy3(int[] ratings) {
        if(ratings == null || ratings.length == 0)return 0;
        int sum = 1;
        int start = 0;
        int end = 0;
        int N = ratings.length;
        while (end < N-1)
        {
            // find the peak
            while (end < N -1 && ratings[end +1] > ratings[end])end++;
            int left = end - start;// the left length from left bottom to peak
            int peakIdx = end;

            // find the bottom
            while (end < N-1 && ratings[end+1] < ratings[end])end++;
            int right = end - peakIdx; // the right length from peak to right bottom
            int peak = Math.max(left, right) +1;
            sum += peak + (1+left)*left/2 + (1+right)*right/2 - 1;// since left bottom add twice, this round and last round, so minus one
            // skip the items with the same value, increase end and results add 1
            while (end < N -1 && ratings[end+1] == ratings[end])
            {
                end++;
                sum++;
            }
            start = end;

        }


        return sum;

    }



    public static void main(String[] args) {
        Candy candy = new Candy();
        System.out.println(candy.candy(new int[]{1,2,2}));
        System.out.println(candy.candy(new int[]{1,0,2}));
        System.out.println(candy.candy(new int[]{1,2,1,2}));
        System.out.println(candy.candy(new int[]{3,2,4,0,5,1,7}));
        System.out.println(candy.candy(new int[]{1,3,2,2,1}));
        System.out.println("*******************************");
        System.out.println(candy.candy2(new int[]{1,2,2}));
        System.out.println(candy.candy2(new int[]{1,0,2}));
        System.out.println(candy.candy2(new int[]{1,2,1,2}));
        System.out.println(candy.candy2(new int[]{3,2,4,0,5,1,7}));
        System.out.println(candy.candy2(new int[]{1,3,2,2,1}));

        System.out.println("*******************************");
        System.out.println(candy.candy3(new int[]{1,2,2}));
        System.out.println(candy.candy3(new int[]{1,0,2}));
        System.out.println(candy.candy3(new int[]{1,2,1,2}));
        System.out.println(candy.candy3(new int[]{3,2,4,0,5,1,7}));
        System.out.println(candy.candy3(new int[]{1,3,2,2,1}));

    }

}
