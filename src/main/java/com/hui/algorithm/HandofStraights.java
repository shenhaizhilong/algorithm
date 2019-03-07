package com.hui.algorithm;

import java.util.*;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/3 16:34
 */
public class HandofStraights {

    /**
     *846. Hand of Straights
     * DescriptionHintsSubmissionsDiscussSolution
     * Alice has a hand of cards, given as an array of integers.
     *
     * Now she wants to rearrange the cards into groups so that each group is size W, and consists of W consecutive cards.
     *
     * Return true if and only if she can.
     *
     *
     *
     * Example 1:
     *
     * Input: hand = [1,2,3,6,2,3,4,7,8], W = 3
     * Output: true
     * Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8].
     * Example 2:
     *
     * Input: hand = [1,2,3,4,5], W = 4
     * Output: false
     * Explanation: Alice's hand can't be rearranged into groups of 4.
     *
     *
     * Note:
     *
     * 1 <= hand.length <= 10000
     * 0 <= hand[i] <= 10^9
     * 1 <= W <= hand.length
     *
     * @param hand
     * @param W
     * @return
     */
    public static boolean isNStraightHand(int[] hand, int W) {
        TreeMap<Integer,Integer> map = new TreeMap<>();
        for(int i: hand)
        {
            map.put(i,map.getOrDefault(i,0) + 1);
        }

        while (map.size() > 0)
        {
            int v1 = map.firstKey();
            for (int i = v1; i < v1 + W ; i++) {
                if(!map.containsKey(i))return false;
                int count = map.get(i);
                if(count == 1){
                    map.remove(i);
                }else {
                    map.replace(i,count -1);
                }

            }
        }
        return true;

    }

    /**
     *
     * 别人的解
     * @param hand
     * @param W
     * @return
     */

    public static boolean isNStraightHand2(int[] hand, int W) {

        if(hand == null || hand.length == 0) return W == 0;
        if(W == 1)return true;
        if(hand.length % W != 0)return false;
        int n = hand.length;
        int H = n/W;

        int[][] buckets = new int[W][H];
        int[] bucketSize = new int[W];

        for(int card : hand)
        {
            int bucketId = card % W;
            int indexInBucket = bucketSize[bucketId]++;

            if(indexInBucket >= H)
            {
                return false;
            }

            buckets[bucketId][indexInBucket] = card;
        }

        for(int i = 0; i < W; i++)
        {
            Arrays.sort(buckets[i]);
        }

        for(int j = 0; j < H; j++)
        {
            for(int i = 1; i < W; i++)
            {
                int prev = buckets[i-1][j], cur = buckets[i][j];

                if(prev + 1 != cur && prev-cur != W-1)
                {
                    return false;
                }
            }
        }

        return true;

    }


    public static void main(String[] args) {
        int[] hand = {1,2,3,6,2,3,4,7,8};
        int[] hand2 = {1,2,3,4,5};
        System.out.println(isNStraightHand(hand,3));
        System.out.println(isNStraightHand(hand2,4));
        int[] hand3 = {1};
        System.out.println(isNStraightHand(hand3,1));
        int[] hand4 = {1,2,3,4,5,6};
        System.out.println(isNStraightHand(hand4,2));
        int[] hand5 = {2,1};
        System.out.println(isNStraightHand(hand5,2));

    }
}
