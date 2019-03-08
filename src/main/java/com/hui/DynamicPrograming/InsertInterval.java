package com.hui.DynamicPrograming;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/29 10:25
 *
 *
 * 57. Insert Interval
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 *
 * You may assume that the intervals were initially sorted according to their start times.
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * Example 2:
 *
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 *
 */
public class InsertInterval {

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        int start = newInterval.start;
        int end = newInterval.end;
        List<Interval> left = new ArrayList<>();
        List<Interval> right = new ArrayList<>();

        for (int i = 0; i < intervals.size(); i++) {
            Interval curr = intervals.get(i);
            if(curr.end < start)
            {
                left.add(curr);
            }else if(curr.start > end)
            {
                right.add(curr);
            }else {
                start = Math.min(start, curr.start);
                end = Math.max(end, curr.end);
            }
        }

        left.add(new Interval(start, end));
        left.addAll(right);
        return left;
    }


    public class Interval {
        int start;
        int end;
        public Interval() { start = 0; end = 0; }
        public Interval(int s, int e) { start = s; end = e; }
    }
}
