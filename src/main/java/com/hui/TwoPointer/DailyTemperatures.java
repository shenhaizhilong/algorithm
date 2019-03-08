package com.hui.TwoPointer;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/5 19:35
 */
public class DailyTemperatures {


    /**
     *
     * 739. Daily Temperatures
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a list of daily temperatures, produce a list that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.
     *
     * For example, given the list temperatures = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].
     *
     * Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
     *
     * @param temperatures
     * @return
     */
    public static int[] dailyTemperatures(int[] temperatures) {

        int[] res = new int[temperatures.length];
        res[temperatures.length -1] = 0;
        int slow = 0;
        int fast = 1;

        while (slow < temperatures.length -1)
        {
            while (fast < temperatures.length && temperatures[fast] <= temperatures[slow])
            {
                fast++;
            }

            if (fast == temperatures.length)
            {
                res[slow] = 0;
                slow++;
            }else {
                res[slow] = fast - slow;
                slow++;
            }
            fast = slow +1;
        }

        return res;

    }


    public static int[] dailyTemperatures2(int[] temperatures) {
        int[] stack = new int[temperatures.length];
        int top = -1;
        int[] result = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            while (top > -1 && temperatures[i] > temperatures[stack[top]]) {
                int index = stack[top--];
                result[index] = i - index;
            }
            stack[++top] = i;
        }
        return result;
    }

    public static void main(String[] args) {

//        System.out.println(Arrays.toString(dailyTemperatures(new int[]{1})));
//        System.out.println(Arrays.toString(dailyTemperatures(new int[]{1,2})));
        System.out.println(Arrays.toString(dailyTemperatures2(new int[]{73,74,75,71,69,72,76,73})));
    }
}
