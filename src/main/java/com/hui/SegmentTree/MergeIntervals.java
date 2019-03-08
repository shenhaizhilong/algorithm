package com.hui.SegmentTree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/29 10:02
 *
 *
 * 56. Merge Intervals
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * Example 1:
 *
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 *
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considerred overlapping.
 *
 */
public class MergeIntervals {

    public List<Interval> merge(List<Interval> intervals) {

        if(intervals == null || intervals.size() < 2)return intervals;
        intervals.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if(o1.start == o2.start)return 0;
                return o1.start - o2.start;
            }
        });
        List<Interval> results = new ArrayList<>();
        Interval prev = intervals.get(0);
        Interval curr = null;
        for (int i = 1; i < intervals.size(); i++) {
            curr = intervals.get(i);
            if(prev.end >= curr.start)
            {
                prev.end = Math.max(prev.end, curr.end);
                prev.start = Math.min(prev.start, curr.start);
            }else {
                results.add(prev);
                prev = curr;
            }
        }

        // add last one.
        results.add(prev);
        return results;
    }


     public class Interval {
      int start;
      int end;
      public Interval() { start = 0; end = 0; }
      public Interval(int s, int e) { start = s; end = e; }
  }

    public static void main(String[] args) {



    }
}
