package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/29 14:54
 */
public class StudentAttendanceRecord {

    /**
     *
     * 551. Student Attendance Record I
     * DescriptionHintsSubmissionsDiscussSolution
     * You are given a string representing an attendance record for a student. The record only contains the following three characters:
     * 'A' : Absent.
     * 'L' : Late.
     * 'P' : Present.
     * A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).
     *
     * You need to return whether the student could be rewarded according to his attendance record.
     *
     * Example 1:
     * Input: "PPALLP"
     * Output: True
     * Example 2:
     * Input: "PPALLL"
     * Output: False
     *
     * @param s
     * @return
     */
    public static boolean checkRecord(String s) {
        if(s == null || s.length() == 0)return true;
        int[] counter = new int[26];
        char[] data = s.toCharArray();
        for (int i = 0; i < data.length; i++) {
            counter[data[i] - 'A']++;
            if(counter[0] >1)return false;
            if(i <= data.length -3 && data[i] == 'L' && data[i+1] == 'L' && data[i+2] == 'L')
            {
                return false;
            }
        }

        return true;
    }

    public static boolean checkRecord2(String s) {
        if(s == null || s.length() == 0)return true;
        int counter = 0;
        char[] data = s.toCharArray();
        for (int i = 0; i < data.length; i++) {
            if(data[i] == 'A')
            {
                counter++;
                if(counter >1)return false;

            }else if(data[i] == 'L')
            {
                if(i <= data.length -3 && data[i+1] == 'L' && data[i+2] == 'L')
                {
                    return false;
                }
            }

        }

        return true;
    }
    public static void main(String[] args) {
        System.out.println(checkRecord2("PPALALP"));
        System.out.println(checkRecord2("PPALLL"));
        System.out.println(checkRecord2("PLLPAL"));

    }
}
