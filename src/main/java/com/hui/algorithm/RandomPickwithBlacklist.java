package com.hui.algorithm;

import java.util.*;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/6 11:26
 */
public class RandomPickwithBlacklist {


    /**
     *
     * 710. Random Pick with Blacklist
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a blacklist B containing unique integers from [0, N), write a function to return a uniform random integer from [0, N) which is NOT in B.
     *
     * Optimize it such that it minimizes the call to system’s Math.random().
     *
     * Note:
     *
     * 1 <= N <= 1000000000
     * 0 <= B.length < min(100000, N)
     * [0, N) does NOT include N. See interval notation.
     * Example 1:
     *
     * Input:
     * ["Solution","pick","pick","pick"]
     * [[1,[]],[],[],[]]
     * Output: [null,0,0,0]
     * Example 2:
     *
     * Input:
     * ["Solution","pick","pick","pick"]
     * [[2,[]],[],[],[]]
     * Output: [null,1,1,1]
     * Example 3:
     *
     * Input:
     * ["Solution","pick","pick","pick"]
     * [[3,[1]],[],[],[]]
     * Output: [null,0,0,2]
     * Example 4:
     *
     * Input:
     * ["Solution","pick","pick","pick"]
     * [[4,[2]],[],[],[]]
     * Output: [null,1,3,1]
     * Explanation of Input Syntax:
     *
     * The input is two lists: the subroutines called and their arguments. Solution's constructor has two arguments, N and the blacklist B. pick has no arguments. Arguments are always wrapped with a list, even if there aren't any.
     *
     *
     *
     * https://leetcode.com/problems/random-pick-with-blacklist/discuss/144624/Java-O(B)-O(1)-HashMap
     *
     * @param N
     * @param blacklist
     */
    public RandomPickwithBlacklist(int N, int[] blacklist) {
        BlackList = new HashMap<>();
        for(int i:blacklist)
        {
            BlackList.put(i, -1);  // O(B)
        }
        random = new Random();
        this.UP = N - BlackList.size();
        for (int i = 0; i < blacklist.length; i++) {
            if(blacklist[i] < this.UP)  // re-mapping
            {
                while (BlackList.containsKey(N -1))
                    N--;
                BlackList.put(blacklist[i], N -1);
                N--;
            }
        }
    }

    private Map<Integer, Integer> BlackList;
    Random random;
    private  int UP;  // up bound

    public int pick() {
      int p = random.nextInt(this.UP);
      if(BlackList.containsKey(p))
          return BlackList.get(p);
      return p;
    }

}
