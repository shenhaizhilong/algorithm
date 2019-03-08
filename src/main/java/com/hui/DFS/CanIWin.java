package com.hui.DFS;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/5 14:28
 *
 *
 * 464. Can I Win
 * DescriptionHintsSubmissionsDiscussSolution
 * In the "100 game," two players take turns adding, to a running total, any integer from 1..10. The player who first causes the running total to reach or exceed 100 wins.
 *
 * What if we change the game so that players cannot re-use integers?
 *
 * For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement until they reach a total >= 100.
 *
 * Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first player to move can force a win, assuming both players play optimally.
 *
 * You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300.
 *
 * Example
 *
 * Input:
 * maxChoosableInteger = 10
 * desiredTotal = 11
 *
 * Output:
 * false
 *
 * Explanation:
 * No matter which integer the first player choose, the first player will lose.
 * The first player can choose an integer from 1 up to 10.
 * If the first player choose 1, the second player can only choose integers from 2 up to 10.
 * The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
 * Same with other integers chosen by the first player, the second player will always win.
 *
 *
 *
 * https://leetcode.com/problems/can-i-win/discuss/95277/Java-solution-using-HashMap-with-detailed-explanation
 *
 *
 */
public class CanIWin {

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if(maxChoosableInteger >= desiredTotal)return true;  // the first player can win
        if(maxChoosableInteger + 1 >= desiredTotal)return false; // the first player can't win
        int S = maxChoosableInteger*(maxChoosableInteger +1)/2; // sum of all numbers
        if(S < desiredTotal)return false;  // no one can reach to desiredTotal
        if(S == desiredTotal)return (maxChoosableInteger & 1) == 1;
        HashMap<Integer, Boolean> map = new HashMap<>();
        boolean[] used = new boolean[maxChoosableInteger + 1];// cache which number is used.
        return dfs(desiredTotal, used, map);

    }


    private boolean dfs(int desiredTotal, boolean[] used, Map<Integer, Boolean> map)
    {
        if(desiredTotal <=0)return false;  // previous player's sum already >= desiredTotal, previous player win
        int key = hash(used);
        if(!map.containsKey(key))
        {
            // try every un-chosen number as next step
            for (int i = 1; i < used.length; i++) {
                if(!used[i])
                {
                    used[i] = true;
                    // check whether this lead to a win
                    if(!dfs(desiredTotal - i, used, map))  // chose used[i] can't win, so didn't chose[i] can win, the second player can't win
                    {
                        map.put(key, true);
                        used[i] = false;
                        return true;
                    }
                    used[i] = false;
                }
            }

            map.put(key, false);
        }

        return map.get(key);
    }



    /**
     *
     * calc the state of the uesd array.
     *
     *  false => no use, true => used
     *  boolean[]  used is {false, false, true, true, false}, then hash(used) = 00110
     *  max length of used array is 20 + 1, avoid using index 0
     * @param used
     * @return
     */
    private int hash(boolean[] used)
    {
        int ans = 0;
        for(boolean b: used)
        {
            ans <<=1;
            if(b){
                ans |= 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {

        CanIWin canIWin = new CanIWin();
        System.out.println(canIWin.canIWin(10, 11));
        System.out.println(canIWin.canIWin(10, 20));
    }
}
