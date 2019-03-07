package com.hui.algorithm;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/7 11:47
 *
 *435. Non-overlapping Intervals
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 *
 * Note:
 * You may assume the interval's end point is always bigger than its start point.
 * Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
 * Example 1:
 * Input: [ [1,2], [2,3], [3,4], [1,3] ]
 *
 * Output: 1
 *
 * Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
 * Example 2:
 * Input: [ [1,2], [1,2], [1,2] ]
 *
 * Output: 2
 *
 * Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
 * Example 3:
 * Input: [ [1,2], [2,3] ]
 *
 * Output: 0
 *
 * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 *
 */
public class NonoverlappingIntervals {

    public int eraseOverlapIntervals(Interval[] intervals) {
        if(intervals == null || intervals.length == 0)return 0;
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.end - o2.end;
            }
        });

        int count = 1;
        int N = intervals.length;
        int end = intervals[0].end;

        // greedy solution,  find the maximum number of intervals that are non-overlapping
        // https://en.wikipedia.org/wiki/Interval_scheduling#Interval_Scheduling_Maximization
        // Greedy polynomial solution
        //The following greedy algorithm does find the optimal solution:
        //
        //Select the interval, x, with the earliest finishing time.
        //Remove x, and all intervals intersecting x, from the set of candidate intervals.
        //Repeat until the set of candidate intervals is empty.
        //Whenever we select an interval at step 1, we may have to remove many intervals in step 2. However, all these intervals necessarily cross the finishing time of x, and thus they all cross each other (see figure). Hence, at most 1 of these intervals can be in the optimal solution. Hence, for every interval in the optimal solution, there is an interval in the greedy solution. This proves that the greedy algorithm indeed finds an optimal solution.
        //
        //A more formal explanation is given by a Charging argument.
        //
        //The greedy algorithm can be executed in time O(n log n), where n is the number of tasks, using a preprocessing step in which the tasks are sorted by their finishing times.

        for (int i = 1; i < N; i++) {
            if(intervals[i].start >= end)
            {
                count++;
                end = intervals[i].end;
            }
        }
        return N - count;
    }




    public class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }


}
