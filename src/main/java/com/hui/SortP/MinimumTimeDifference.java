package com.hui.SortP;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/4 11:11
 */
public class MinimumTimeDifference {


    /**
     *
     * 539. Minimum Time Difference
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a list of 24-hour clock time points in "Hour:Minutes" format, find the minimum minutes difference between any two time points in the list.
     * Example 1:
     * Input: ["23:59","00:00"]
     * Output: 1
     * Note:
     * The number of time points in the given list is at least 2 and won't exceed 20000.
     * The input time is legal and ranges from 00:00 to 23:59.
     *
     * @param timePoints
     * @return
     */
    public int findMinDifference(List<String> timePoints) {

        Collections.sort(timePoints);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < timePoints.size(); i++) {
            int a = convertToMinutes(timePoints.get(i));
            int b = convertToMinutes(timePoints.get(i -1));
            min = Math.min(min, a - b);
        }

        //compare first and last, one day have 1440 minutes.
        int diffLast =Math.abs(convertToMinutes(timePoints.get(timePoints.size() -1)) - convertToMinutes(timePoints.get(0)) - 1440) ;

        return Math.min(min, diffLast);

    }


    // convert the time point to minutes [0, 1439]
    private int convertToMinutes(String timePoint)
    {
        int r = 0;
        r += (timePoint.charAt(0) - '0')*10*60;
        r += (timePoint.charAt(1) - '0')*60;
        r += (timePoint.charAt(3) - '0')*10;
        r += (timePoint.charAt(4) - '0');
        return r;

    }

    public int findMinDifference2(List<String> timePoints) {
        if(timePoints == null || timePoints.size() < 2)
            return 0;

        // one day have 1440 minutes
        int len = 1440;
        boolean[] minutes = new boolean[len];
        for (int i = 0; i < timePoints.size(); i++) {
            int index = convertToMinutes(timePoints.get(i));
            if(minutes[index])return 0;  // same time points have  same minutes, so if we have seen this minutes, then return 0
            minutes[index] = true;
        }

        int min = Integer.MAX_VALUE;
        int prev = -1; //   minutes[prev] = true,  the max prev before index i;
        int first = -1;
        int last = 0;
        for (int i = 0; i < len; i++) {
            if(minutes[i])
            {
                if(prev != -1)
                {
                    min = Math.min(min, i - prev);
                }
               if (first == -1)
               {
                   first = i;
               }
                last = i;
                prev = i;

            }
        }
        return Math.min(min, first - last + len);

    }


    public int findMinDifference3(List<String> timePoints) {
        if(timePoints == null || timePoints.size() < 2)
            return 0;

        // one day have 1440 minutes
        int len = 1440;
        boolean[] minutes = new boolean[len];
        int first = len;
        int last = 0;
        for (int i = 0; i < timePoints.size(); i++) {
            int index = convertToMinutes(timePoints.get(i));
            if(minutes[index])return 0;  // same time points have  same minutes, so if we have seen this minutes, then return 0
            minutes[index] = true;
            if(first > index)first = index;
            if(last < index)last = index;

        }

        int min = Integer.MAX_VALUE;
        int prev = first; //   minutes[prev] = true,  the max prev before index i;

        int curr = first + 1;
        while (curr <= last) {
            if(minutes[curr])
            {
                min = Math.min(min, curr - prev);
                prev = curr;
            }
            curr++;
        }
        return Math.min(min, first - last + len);

    }
    public static void main(String[] args) {
        MinimumTimeDifference minimumTimeDifference = new MinimumTimeDifference();
        System.out.println(minimumTimeDifference.findMinDifference(Arrays.asList("23:59","00:00")));
        System.out.println(minimumTimeDifference.findMinDifference(Arrays.asList("12:12","00:13")));
        System.out.println(minimumTimeDifference.findMinDifference2(Arrays.asList("23:59","00:00")));
        System.out.println(minimumTimeDifference.findMinDifference2(Arrays.asList("12:12","00:13")));
        System.out.println(minimumTimeDifference.findMinDifference3(Arrays.asList("23:59","00:00")));
        System.out.println(minimumTimeDifference.findMinDifference3(Arrays.asList("12:12","00:13")));
    }
}
