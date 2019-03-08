package com.hui.DynamicPrograming;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/3 15:23
 *84. Largest Rectangle in Histogram
 * DescriptionHintsSubmissionsDiscussSolution
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
 *
 *
 *
 *
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 *
 *
 *
 *
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 *
 *
 *
 * Example:
 *
 * Input: [2,1,5,6,2,3]
 * Output: 10
 *
 */
public class LargestRectangleinHistogram {

    public int largestRectangleArea(int[] heights) {

        if (heights == null || heights.length == 0) return 0;
        int N = heights.length;

        int ans = 0;
        int[] prevLess = new int[N];  // the prev element's index j where, heights[j] < heights[i]
        int[] nextLess = new int[N];  // the next element's index j where, heights[j] < heights[i]
        for (int i = 0; i < N; i++) {
            prevLess[i] = -1;  // if don't exist the prev less element, then we set it to -1
            nextLess[i] = N;   // if don't exist the next less element, then we set it to N
        }

        Deque<Integer> prevLessStack = new ArrayDeque<>();  // prev less stack
        Deque<Integer> nextLessStack = new ArrayDeque<>();  // next less stack
        for (int i = 0; i < N; i++) {
            while (!prevLessStack.isEmpty() && heights[prevLessStack.peekFirst().intValue()] > heights[i])
            {
                prevLessStack.pop();
            }

            prevLess[i] = prevLessStack.isEmpty() ? -1: prevLessStack.peekFirst().intValue();
            prevLessStack.push(i);

            while (!nextLessStack.isEmpty() && heights[nextLessStack.peekFirst().intValue()] > heights[i])
            {
                int idx = nextLessStack.pop();
                nextLess[idx] = i;
            }
            nextLessStack.push(i);
        }

        for (int i = 0; i < N; i++) {
            int area = heights[i]*(nextLess[i] - prevLess[i] -1);
            ans = Math.max(ans, area);
        }

        return ans;

    }

    public int largestRectangleArea2(int[] heights) {

        if (heights == null || heights.length == 0) return 0;
        int N = heights.length;

        int ans = 0;
        int[] prevLess = new int[N];  // the prev element's index j where heights[j] < heights[i]
        int[] nextLess = new int[N];  // the next element's index j where  heights[j] < heights[i]
        prevLess[0] = -1;
        nextLess[N -1] = N;

        for (int i = 1; i < N; i++) {
            int p = i -1;
            while (p >= 0 && heights[p] >= heights[i])
            {
                p = prevLess[p];
            }
            prevLess[i] = p;
        }

        for (int i = N -2; i >= 0; i--)
        {
            int p = i + 1;
            while (p < N && heights[p] >= heights[i])
            {
                p = nextLess[p];
            }

            nextLess[i] = p;
        }

        for (int i = 0; i < N; i++) {
            int area = heights[i]*(nextLess[i] - prevLess[i] -1);
            ans = Math.max(ans, area);
        }

        return ans;

    }


    public static void main(String[] args) {

        LargestRectangleinHistogram largestRectangleinHistogram = new LargestRectangleinHistogram();
        System.out.println(largestRectangleinHistogram.largestRectangleArea(new int[]{2,1,5,6,2,3}));

        System.out.println(largestRectangleinHistogram.largestRectangleArea2(new int[]{2,1,5,6,2,3}));

    }
}
