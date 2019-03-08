package com.hui.PreSum;

import java.util.*;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/8 12:27
 *
 *
 *862. Shortest Subarray with Sum at Least K
 * DescriptionHintsSubmissionsDiscussSolution
 * Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.
 *
 * If there is no non-empty subarray with sum at least K, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: A = [1], K = 1
 * Output: 1
 * Example 2:
 *
 * Input: A = [1,2], K = 4
 * Output: -1
 * Example 3:
 *
 * Input: A = [2,-1,2], K = 3
 * Output: 3
 *
 *
 * Note:
 *
 * 1 <= A.length <= 50000
 * -10 ^ 5 <= A[i] <= 10 ^ 5
 * 1 <= K <= 10 ^ 9
 *
 */
public class ShortestSubarraywithSumatLeastK {

    public int shortestSubarray(int[] A, int K) {

        int N = A.length;
        int[] prefixSum = new int[N + 1];
        for (int i = 0; i < N; i++) {
            prefixSum[i +1] = prefixSum[i] + A[i];
        }

        int ans =  N + 1;
        for (int i = 1; i < prefixSum.length; i++) {
            for (int j = i -1; j >= i - ans && j >=0; j--) {
                if(prefixSum[i] - prefixSum[j] >= K)
                {
                    ans = Math.min(ans, i - j);
                    break;
                }
            }
        }

        return ans < N + 1 ? ans: -1;

    }

    public int shortestSubarray2(int[] A, int K) {

        int N = A.length;
        int[] prefixSum = new int[N + 1];
        for (int i = 0; i < N; i++) {
            prefixSum[i +1] = prefixSum[i] + A[i];
        }

        int ans =  N + 1;
        Deque<Integer> increasingQueue = new ArrayDeque<>();
        for (int i = 0; i < prefixSum.length; i++) {

            // find the largest x with prefixSum[x] <= prefixSum[y] - K;
            // since k > 1, so prefixSum[y] > prefixSum[x]
            // delete bigger or equal than prefixSUm[i]
            while (!increasingQueue.isEmpty() && prefixSum[i] <= prefixSum[increasingQueue.peekLast()])
            {
                increasingQueue.pollLast();
            }

            while (!increasingQueue.isEmpty() && prefixSum[i] >= prefixSum[increasingQueue.peekFirst()] + K)
            {
                ans = Math.min(ans, i - increasingQueue.pollFirst());  // delete the min in queue.
            }

            increasingQueue.addLast(i);

        }

        return ans < N + 1 ? ans: -1;

    }


    public static void main(String[] args) {


        ShortestSubarraywithSumatLeastK sumatLeastK = new ShortestSubarraywithSumatLeastK();
        System.out.println(sumatLeastK.shortestSubarray(new int[]{1,-2,3,4,0,8,-7}, 6));
        System.out.println(sumatLeastK.shortestSubarray(new int[]{1,-2,3,4,0,8,-7}, 12));

       // System.out.println(sumatLeastK.shortestSubarray2(new int[]{1,-2,3,4,0,8,-7}, 6));
        System.out.println(sumatLeastK.shortestSubarray2(new int[]{0,1,5,6,10}, 12));
        System.out.println(sumatLeastK.shortestSubarray2(new int[]{1,-2,3,4,0,8,-7}, 12));

    }

}
