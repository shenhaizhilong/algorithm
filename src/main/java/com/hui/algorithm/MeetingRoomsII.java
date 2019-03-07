package com.hui.algorithm;

import java.util.TreeMap;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/6 20:17
 *
 *
 * Leetcode – Meeting Rooms II (Java)
 * Tags: Algorithm, Facebook, LeetCode
 * Meeting Rooms II
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
 *
 * For example, Given [[0, 30],[5, 10],[15, 20]], return 2.
 *
 * This is a follow up question for the Meeting Rooms one.
 *
 *
 */
public class MeetingRoomsII {

    // compute the largest number of  concurrent on-going event at any time.

    public int minMeetingRooms(int[][] intervals) {
        TreeMap<Integer,Integer> counter = new TreeMap<>();
        if(intervals == null || intervals.length == 0)return 0;
        for(int[] interval:intervals)
        {
            counter.put(interval[0], counter.getOrDefault(interval[0],0) +1);
            counter.put(interval[1], counter.getOrDefault(interval[1],0) -1);
        }

        int ans = 0;
        int count = 0;
        for(int v:counter.values())
        {
            count += v;
            ans = Math.max(ans, count);
        }
        return ans;
    }

    public static void main(String[] args) {
        MeetingRoomsII meetingRoomsII = new MeetingRoomsII();
        System.out.println(meetingRoomsII.minMeetingRooms(new int[][]{{0,30},{5,10},{15,20}}));
    }
}
