package com.hui.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/30 10:21
 */
public class DistributeCandies {

    /**
     *
     *
     * 575. Distribute Candies
     * DescriptionHintsSubmissionsDiscussSolution
     * Given an integer array with even length, where different numbers in this array represent different kinds of candies. Each number means one candy of the corresponding kind. You need to distribute these candies equally in number to brother and sister. Return the maximum number of kinds of candies the sister could gain.
     * Example 1:
     * Input: candies = [1,1,2,2,3,3]
     * Output: 3
     * Explanation:
     * There are three different kinds of candies (1, 2 and 3), and two candies for each kind.
     * Optimal distribution: The sister has candies [1,2,3] and the brother has candies [1,2,3], too.
     * The sister has three different kinds of candies.
     * Example 2:
     * Input: candies = [1,1,2,3]
     * Output: 2
     * Explanation: For example, the sister has candies [2,3] and the brother has candies [1,1].
     * The sister has two different kinds of candies, the brother has only one kind of candies.
     * Note:
     *
     * The length of the given array is in range [2, 10,000], and will be even.
     * The number in given array is in range [-100,000, 100,000].
     *
     *
     * beats 60%
     * @param candies
     * @return
     */
    public int distributeCandies(int[] candies) {
        int n = candies.length/2;
        Set<Integer> set = new HashSet<>(n);
        for(int i: candies)
        {
            set.add(i);
            if(set.size() >= n)
                return n;
        }

        return set.size();
    }

    /**
     * beats 98%
     * @param candies
     * @return
     */
    public int distrbuteCandies2(int[] candies)
    {
        int n = candies.length/2;
        boolean[] set = new boolean[200001];
        int count = 0;
        for (int i = 0; i < candies.length; i++) {
            int index = candies[i] + 100000;
            if(!(set[index]))
            {
                count++;
                set[index] = true;
                if(count >=n)return n;
            }
        }

        return count;
    }

}
