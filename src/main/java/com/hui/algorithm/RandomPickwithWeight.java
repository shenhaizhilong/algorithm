package com.hui.algorithm;

import java.util.Arrays;
import java.util.Random;
import java.util.TreeMap;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/4 21:26
 */
public class RandomPickwithWeight {

    /**
     *
     *
     * 528. Random Pick with Weight
     * DescriptionHintsSubmissionsDiscussSolution
     * Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex which randomly picks an index in proportion to its weight.
     *
     * Note:
     *
     * 1 <= w.length <= 10000
     * 1 <= w[i] <= 10^5
     * pickIndex will be called at most 10000 times.
     * Example 1:
     *
     * Input:
     * ["Solution","pickIndex"]
     * [[[1]],[]]
     * Output: [null,0]
     * Example 2:
     *
     * Input:
     * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
     * [[[1,3]],[],[],[],[],[]]
     * Output: [null,0,1,1,1,0]
     * Explanation of Input Syntax:
     *
     * The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument, the array w. pickIndex has no arguments. Arguments are always wrapped with a list, even if there aren't any.
     *
     * @param w
     */
    public RandomPickwithWeight(int[] w) {

        random = new Random();
        mapCycle = new TreeMap<>();
        size = 0;
        for (int i = 0; i < w.length; i++) {
            size += w[i];
            mapCycle.put(size, i);
        }
    }

    TreeMap<Integer,Integer> mapCycle ;
    int size;


    public int pickIndex() {
        int key = mapCycle.higherKey(random.nextInt(size));
        return mapCycle.get(key);
    }


    int[] wSum;
    Random random;
    public void RandomWeight(int[] w) {

        for (int i = 1; i < w.length; i++) {
            w[i] = w[i-1]+w[i];
        }
        wSum = w;
        random = new Random();
    }


    /**
     *
     * https://leetcode.com/problems/random-pick-with-weight/discuss/154044/Java-accumulated-freq-sum-and-binary-search
     *
     * Use accumulated freq array to get idx.
     * w[] = {2,5,3,4} => wsum[] = {2,7,10,14}
     * then get random val random.nextInt(14)+1, idx is in range [1,14]
     *
     * idx in [1,2] return 0
     * idx in [3,7] return 1
     * idx in [8,10] return 2
     * idx in [11,14] return 3
     * then become LeetCode 35. Search Insert Position
     * Time: O(n) to init, O(logn) for one pick
     * Space: O(n)
     *
     *
     * @return
     */
    public int pickIndex2() {
       int len = wSum.length;
       int id = random.nextInt(wSum[len -1]) + 1;  // id in [1,wSum[len-1]] , wSum[len -1] is the total frequency
       int lo = 0;
       int hi = len -1;
       while (lo < hi)
       {
           int mid = (lo + hi)>>>1;
           if(wSum[mid] == id)
           {
               return mid;
           }else if(wSum[mid] > id)
           {
               hi = mid;  // result maybe is mid
           }else {
               lo = mid + 1;  // result can't be mid, mid must be in the right, so mid + 1
           }
       }
       return lo;
    }

    public int pickIndex3() {
        int len = wSum.length;
        int target = random.nextInt(wSum[len - 1]) + 1; // target in [1,wSum[len-1]] , wSum[len -1] is the total frequency
        int id = Arrays.binarySearch(wSum, target);
        return id >=0 ? id : -id -1;
    }
}
