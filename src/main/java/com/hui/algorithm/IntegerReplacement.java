package com.hui.algorithm;

import java.util.HashMap;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/12 19:10
 */
public class IntegerReplacement {

    public int integerReplacement(int n) {

        if(n == 1)return 0;
        if((n & 0x01) == 1)
        {
            return 2 + Math.min(integerReplacement(n/2), integerReplacement(n/2 + 1));
        }else {
            return 1+integerReplacement(n/2);
        }

    }


    // cache the results, so we don't need to re-compute it.
    private HashMap<Integer, Integer> memo = new HashMap<>();
    public int integerReplacement2(int n) {

        if(n == 1)return 0;
        if(!memo.containsKey(n))
        {
            if((n & 0x01) == 1)
            {
                int v = 2 + Math.min(integerReplacement2(n>>1), integerReplacement2((n>>1) + 1));
                memo.put(n,v) ;
            }else {
                int v =  1+integerReplacement2(n>>1);
                memo.put(n,v) ;
            }
        }
       return memo.get(n);

    }

    public int integerReplacement3(int n) {
        int count = 0;
        while (n != 1)
        {
            if( (n&0x01) == 0)
            {
                n >>= 1;
            }else if(n == 3 || Integer.bitCount(n +1) > Integer.bitCount(n-1))
            {
                --n;
            }else {
                ++n;
            }
            count++;
        }
        return count;
    }


    /**
     *
     * https://leetcode.com/problems/integer-replacement/discuss/87920/A-couple-of-Java-solutions-with-explanations
     *
     *
     * So the logic is:
     *
     * If n is even, halve it.
     * If n=3 or n-1 has less 1's than n+1, decrement n.
     * Otherwise, increment n.
     *
     *
     *  Indeed, if a number ends with 01, then certainly decrementing is the way to go. Otherwise, if it ends with 11,
     *  then certainly incrementing is at least as good as
     *  decrementing (*011 -> *010 / *100) or even better (if there are three or more 1's). This leads to the following solution:
     * @param n
     * @return
     */
    public int integerReplacement4(int n) {
        int count = 0;
        while (n != 1)
        {
            if((n & 0x01) != 0){
                if(n == 3 || ((n >>1) & 1) == 0)
                {
                    n--;
                }else {
                    n++;
                }
                count++;
            }
            // can't change it to n >>= 1; because n++ may overflow.
            n >>>= 1;
            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        IntegerReplacement integerReplacement = new IntegerReplacement();
        System.out.println(integerReplacement.integerReplacement(8));
        System.out.println(integerReplacement.integerReplacement(7));
        System.out.println(integerReplacement.integerReplacement(15));

        System.out.println(integerReplacement.integerReplacement2(8));
        System.out.println(integerReplacement.integerReplacement2(7));
        System.out.println(integerReplacement.integerReplacement2(15));


        System.out.println(integerReplacement.integerReplacement3(8));
        System.out.println(integerReplacement.integerReplacement3(7));
        System.out.println(integerReplacement.integerReplacement3(15));

        System.out.println(integerReplacement.integerReplacement4(8));
        System.out.println(integerReplacement.integerReplacement4(7));
        System.out.println(integerReplacement.integerReplacement4(15));
        System.out.println(integerReplacement.integerReplacement4(Integer.MAX_VALUE));
    }
}
