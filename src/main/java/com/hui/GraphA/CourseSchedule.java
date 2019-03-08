package com.hui.GraphA;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/19 17:34
 *
 * 207. Course Schedule
 * DescriptionHintsSubmissionsDiscussSolution
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 *
 * Example 1:
 *
 * Input: 2, [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 *
 * Input: 2, [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0, and to take course 0 you should
 *              also have finished course 1. So it is impossible.
 * Note:
 *
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 *
 */
public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        return bfs(numCourses, prerequisites);
    }

    private boolean bfs(int numCourses, int[][] prerequisites)
    {
        ArrayList[] graph = new ArrayList[numCourses];
        int[] inDegree = new int[numCourses];
        Queue<Integer> queue = new ArrayDeque<>();
        int count = 0;
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList();
        }

        for (int i = 0; i < prerequisites.length; i++) {
            inDegree[prerequisites[i][0]]++;  // compute in- degree
            graph[prerequisites[i][1]].add(prerequisites[i][0]);  // adjacent table
        }

        // find all the vertex which it's indegree is zero.
        for (int i = 0; i < inDegree.length; i++) {
            if(inDegree[i] == 0)
            {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty())
        {
            // delete vertex course and all it's adjacent vertex's in-degree decrease one.
            // if one vertex's in-degree is zero, we add it to the queue.
            int course = queue.poll();
            count++;  // assign top number
            for (int i = 0; i < graph[course].size(); i++) {
                int w = (int)graph[course].get(i);
                if(--inDegree[w] == 0)
                {
                    queue.add(w);
                }

            }
        }

        return count == numCourses;
    }

    public static void main(String[] args) {
        int[][] prerequisites = {{3,0},{3,1},{3,4},{1,0},{4,1},{6,4},{6,3},{5,3},{5,6},{5,2},{2,0},{2,3}};
        CourseSchedule courseSchedule = new CourseSchedule();
        System.out.println(courseSchedule.canFinish(7, prerequisites));
    }
}
