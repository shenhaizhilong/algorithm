package com.hui.GraphA;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/20 12:53
 *
 * 210 /course-schedule-ii/
 *There are a total of n courses you have to take, labeled from 0 to n-1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
 *
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
 *
 * Example 1:
 *
 * Input: 2, [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
 *              course 0. So the correct course order is [0,1] .
 * Example 2:
 *
 * Input: 4, [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,1,2,3] or [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
 *              courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 *              So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
 * Note:
 *
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 *
 */
public class CourseScheduleII {

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        return bfs(numCourses, prerequisites);
    }

    private int[] bfs(int numCourses, int[][] prerequisites)
    {
        int[] ans = new int[numCourses];
        int[] degree = new int[numCourses];
        int topNum = 0;
        ArrayList[] graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList();
        }

        for (int i = 0; i < prerequisites.length; i++) {
            degree[prerequisites[i][0]]++; // calc in degree
            graph[prerequisites[i][1]].add(prerequisites[i][0]); // add edge
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < degree.length; i++) {
            if(degree[i] == 0)
            {
                queue.add(i);
            }
        }

        while (!queue.isEmpty())
        {
            int course = queue.poll();
            ans[topNum++] = course;
            for (int i = 0; i < graph[course].size(); i++) {
                int adjacentCourse = (int)graph[course].get(i);
                if(--degree[adjacentCourse] == 0)
                {
                    queue.add(adjacentCourse);
                }
            }
        }

        return numCourses == topNum ? ans: new int[0];

    }
}
