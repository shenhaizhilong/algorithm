package com.hui.algorithm;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/10 17:29
 *
 *954. Array of Doubled Pairs
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an array of integers A with even length, return true if and only if it is possible to reorder it such that A[2 * i + 1] = 2 * A[2 * i] for every 0 <= i < len(A) / 2.
 *
 *
 *
 * Example 1:
 *
 * Input: [3,1,3,6]
 * Output: false
 * Example 2:
 *
 * Input: [2,1,2,6]
 * Output: false
 * Example 3:
 *
 * Input: [4,-2,2,-4]
 * Output: true
 * Explanation: We can take two groups, [-2,-4] and [2,4] to form [-2,-4,2,4] or [2,4,-2,-4].
 * Example 4:
 *
 * Input: [1,2,4,16,8,4]
 * Output: false
 *
 *
 * Note:
 *
 * 0 <= A.length <= 30000
 * A.length is even
 * -100000 <= A[i] <= 100000
 *
 */
public class ArrayofDoubledPairs {

    public boolean canReorderDoubled(int[] A) {
        if(A == null || A.length == 0)return true;
        long sum = 0;
        Map<Integer,Integer> counter = new TreeMap<>();
        for(int a:A)
        {
            sum += a;
            counter.put(a, counter.getOrDefault(a,0) +1);
        }
        if(sum % 3 != 0)return false;   // if there is a Array A, where A[0], 2*A[0], A[2], 2*A[2]... so the sum % 3 == 0
        for(int key: counter.keySet())
        {
            int countA = counter.get(key);
            if(countA == 0)continue;
            int want = key > 0 ? key*2: key/2;  // key,2*key or key,key/2
            int countB = counter.getOrDefault(want,0);  // need counter
            if(countB < countA)return false;
            counter.put(key,0);
            counter.put(want, countB - countA);
        }

        return true;

    }

    public static void main(String[] args) {

        ArrayofDoubledPairs arrayofDoubledPairs = new ArrayofDoubledPairs();
        System.out.println(arrayofDoubledPairs.canReorderDoubled(new int[]{4,-2,2,-4}));
        System.out.println(arrayofDoubledPairs.canReorderDoubled(new int[]{4,-3,2,6}));
    }
}
