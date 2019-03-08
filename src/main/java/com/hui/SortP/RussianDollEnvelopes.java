package com.hui.SortP;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/28 16:17
 *
 *354. Russian Doll Envelopes
 * DescriptionHintsSubmissionsDiscussSolution
 * You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.
 *
 * What is the maximum number of envelopes can you Russian doll? (put one inside other)
 *
 * Note:
 * Rotation is not allowed.
 *
 * Example:
 *
 * Input: [[5,4],[6,4],[6,7],[2,3]]
 * Output: 3
 * Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 *
 */
public class RussianDollEnvelopes {

    public int maxEnvelopes(int[][] envelopes) {

        if(envelopes == null || envelopes.length < 1)return 0;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0])return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });
        
        int N = envelopes.length;
        int[] dp = new int[N];
        dp[0] = 1;
        int ans = 1;
        for (int i = 1; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if(envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1])
                {
                    dp[i] = Math.max(dp[i], dp[j] +1);
                }
            }
            ans = Math.max(dp[i], ans);
        }
        return ans;
    }

    public int maxEnvelopes2(int[][] envelopes) {

        if(envelopes == null || envelopes.length < 1)return 0;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0])return o2[1] - o1[1];  // to avoid such case [2,3], [5,4], [6,4], [6,7]; we need
                // o1[0] ascending order and o[1] descending order
                return o1[0] - o2[0];
            }
        });

        int N = envelopes.length;
        int[] dp = new int[N];
        int size = 0;
        // same with 300. Longest Increasing Subsequence
        for (int i = 0; i < envelopes.length; i++) {
            int idx = Arrays.binarySearch(dp, 0, size, envelopes[i][1]);
            if(idx < 0)
            {
                idx = -(idx +1);
            }
            dp[idx] = envelopes[i][1];
            if(idx == size)
            {
                size++;
            }
        }

        return size;

    }
}
