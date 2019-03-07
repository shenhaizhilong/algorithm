package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/15 11:01
 */
public class BulbSwitcher {


    /**
     *
     * 319. Bulb Switcher
     * DescriptionHintsSubmissionsDiscussSolution
     * There are n bulbs that are initially off. You first turn on all the bulbs. Then, you turn off every second bulb. On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). For the i-th round, you toggle every i bulb. For the n-th round, you only toggle the last bulb. Find how many bulbs are on after n rounds.
     *
     * Example:
     *
     * Input: 3
     * Output: 1
     * Explanation:
     * At first, the three bulbs are [off, off, off].
     * After first round, the three bulbs are [on, on, on].
     * After second round, the three bulbs are [on, off, on].
     * After third round, the three bulbs are [on, off, off].
     *
     * So you should return 1, because there is only one bulb is on.
     * @param n
     * @return
     */
    public int bulbSwitch(int n) {
        int[] bulbs = new int[n+1];
        int ans = 0;
        // nth round
        for (int i = 1; i <=n ; i++) {
            //. For the i-th round, you toggle every i bulb
            for (int j = i; j  <=n ; j += i) {
                bulbs[j] = bulbs[j] ^ 1;
            }
        }

        for (int i = 1; i <= n; i++) {
            ans += bulbs[i];
        }

        return ans;
    }


    /**
     *
     *
     * https://leetcode.com/problems/bulb-switcher/discuss/77132/The-simplest-and-most-efficient-solution-well-explained
     *
     * Before we take a jump to the solution, let's first try to clear out what exactly the problem is talking about:
     *
     * every i-th distance you switch the bulb to the opposite state (from on to off, or from off to on); actually suppose the bulbs are labelled from 1 to n then the every second bulb will mean that 2, 4, 6, 8, ... all even numbers less than n; while every third bulb will be 3, 6, 9, 12, ... all multiples of 3 that is less than n and so on;
     * since the bulb will only have two different states - on or off, the result will be quite clear now; odd switching operations will result in bulb-on state (since original state is bulb-off) while even switching operations will give us bulb-off state;
     * Now the purpose here is clear searching for the odd-operation numbers:
     *
     * as for primes, they only have 1 and itself as their factors, even-operation numbers;
     * as for non-primes, normally they will have different pairs of factors like 12 whose factors are (1, 12), (3, 4), (2, 6) - 6 different factors, also even-operation numbers;
     * but among non-primes, there are some special numbers, perfect square numbers like 9 whose factors are (1, 9) and (3, 3) - odd-operation numbers, which means there will be only three different numbers that will affect the current bulb and result in bulb-on state!
     * So that's all we need to know to hack this problem now. But how to get the amount of squares that are less than n, quite simple. Sqrt(n) is the answer, since all square numbers that is less than n will be 1, 4, 9 ... n and their corresponding square roots will be 1, 2, 3,... sqrt(n).
     *
     * Space cost O(1)
     * Time cost O(1)
     *
     * @param n
     * @return
     */
    public int bulbSwitch2(int n) {
        return (int) Math.sqrt(n);
    }

    public static void main(String[] args) {
        BulbSwitcher bulbSwitcher = new BulbSwitcher();
        System.out.println( "i = " + 100 +" ," + bulbSwitcher.bulbSwitch(100));
//        for (int i = 1; i < 100; i++) {
//            System.out.println( "i = " + i +" ," + bulbSwitcher.bulbSwitch(i));
//
//        }

    }
}
