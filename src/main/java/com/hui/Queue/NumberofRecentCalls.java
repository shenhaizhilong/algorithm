package com.hui.Queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/4 22:12
 *933. Number of Recent Calls
 * DescriptionHintsSubmissionsDiscussSolution
 * Write a class RecentCounter to count recent requests.
 *
 * It has only one method: ping(int t), where t represents some time in milliseconds.
 *
 * Return the number of pings that have been made from 3000 milliseconds ago until now.
 *
 * Any ping with time in [t - 3000, t] will count, including the current ping.
 *
 * It is guaranteed that every call to ping uses a strictly larger value of t than before.
 *
 *
 *
 * Example 1:
 *
 * Input: inputs = ["RecentCounter","ping","ping","ping","ping"], inputs = [[],[1],[100],[3001],[3002]]
 * Output: [null,1,2,3,3]
 *
 *
 * Note:
 *
 * Each test case will have at most 10000 calls to ping.
 * Each test case will call ping with strictly increasing values of t.
 * Each call to ping will have 1 <= t <= 10^9.
 *
 */
public class NumberofRecentCalls {

    Deque<Integer> queue;
    public NumberofRecentCalls() {
        queue = new ArrayDeque<>();
    }

    public int ping(int t) {

       while (!queue.isEmpty() && queue.peekFirst() < t - 3000)
       {
           queue.pollFirst();
       }

       queue.addLast(t);
       return queue.size();
    }

    public static void main(String[] args) {

        NumberofRecentCalls recentCalls = new NumberofRecentCalls();
        int[] arr = {1,100,3001,3002};
        for (int i = 0; i < arr.length; i++) {
            System.out.println(recentCalls.ping(arr[i]));
        }
    }
}
