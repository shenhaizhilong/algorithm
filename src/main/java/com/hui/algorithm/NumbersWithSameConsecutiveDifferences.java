package com.hui.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/30 15:26
 *
 *967. Numbers With Same Consecutive Differences
 * Medium
 *
 * 3
 *
 * 4
 *
 * Favorite
 *
 * Share
 * Return all non-negative integers of length N such that the absolute difference between every two consecutive digits is K.
 *
 * Note that every number in the answer must not have leading zeros except for the number 0 itself. For example, 01 has one leading zero and is invalid, but 0 is valid.
 *
 * You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: N = 3, K = 7
 * Output: [181,292,707,818,929]
 * Explanation: Note that 070 is not a valid number, because it has leading zeroes.
 * Example 2:
 *
 * Input: N = 2, K = 1
 * Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
 *
 *
 * Note:
 *
 * 1 <= N <= 9
 * 0 <= K <= 9
 *
 */
public class NumbersWithSameConsecutiveDifferences {

    public int[] numsSameConsecDiff(int N, int K) {
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i <= 9; i++) {
            dfs(i, i, 1, N,K, ans);
        }
        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }
        return res;
    }

    // pre means pre digit ; len is the length of the num
    private void dfs(int num, int pre, int len, int N, int K, List<Integer> list)
    {
        if(len > N)return;
        if(N == len){
            list.add(num);
            return;
        }
        if(num == 0 && pre == 0)return;
        if(pre + K < 10)
        {
            dfs(num*10 + pre + K, pre + K, len +1, N, K, list);
        }
        if(pre >= K && K != 0)
        {
            dfs(num*10 + pre - K, pre -K, len +1, N, K, list);
        }

    }

    public static void main(String[] args) {
        NumbersWithSameConsecutiveDifferences numbersWithSameConsecutiveDifferences = new NumbersWithSameConsecutiveDifferences();
        Matrix.print(numbersWithSameConsecutiveDifferences.numsSameConsecDiff(3,7));
        Matrix.print(numbersWithSameConsecutiveDifferences.numsSameConsecDiff(2,1));
        Matrix.print(numbersWithSameConsecutiveDifferences.numsSameConsecDiff(1,0));
    }
}
