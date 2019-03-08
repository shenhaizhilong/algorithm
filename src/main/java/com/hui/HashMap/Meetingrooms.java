package com.hui.HashMap;

import java.util.TreeMap;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/6 20:26
 *
 *Leetcode – Meeting rooms solution in Java
 *
 * Meeting Rooms
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.
 *
 * For example, Given [[0, 30],[5, 10],[15, 20]], return false.
 *
 */
public class Meetingrooms {

    public boolean canAttendMeetings(int[][] intervals) {

        if(intervals == null || intervals.length == 0)return false;
        // compute the largest number of  concurrent on-going event at any time.
        TreeMap<Integer,Integer> counter = new TreeMap<>();
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
            if(ans > 1)return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Meetingrooms meetingrooms = new Meetingrooms();
        System.out.println(meetingrooms.canAttendMeetings(new int[][]{{0,30},{5,10},{15,20}}));
    }
}
