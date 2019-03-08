package com.hui.SortP;

import java.util.*;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/5 10:14
 */
public class QueueReconstructionbyHeight {


    /**
     *
     * 406. Queue Reconstruction by Height
     * DescriptionHintsSubmissionsDiscussSolution
     * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.
     *
     * Note:
     * The number of people is less than 1,100.
     *
     *
     * Example
     *
     * Input:
     * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
     *
     * Output:
     * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
     *
     * @param people
     * @return
     */
    public static int[][] reconstructQueue(int[][] people) {
       // Arrays.sort(people,(int[] p1,int[] p2) ->( p1[0] == p2[0] ? ( p1[1] - p2[1]):(p2[0] - p1[0])));
        //leetcode 上效率高于上面的lambda 表达式
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int diff = o2[0] - o1[0];
                if(diff == 0)
                    return o1[1] - o2[1];
                else
                    return diff;
            }
        });
        List<int[]> res = new ArrayList<>();
        for(int[] p: people)
        {
            res.add(p[1],p);
        }
        return res.toArray(new int[people.length][2]);
    }

    public static void main(String[] args) {

        int[][] res = reconstructQueue(new int[][]{{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}});
        for(int[] r:res)
        {
            System.out.println(Arrays.toString(r));
        }
    }
}
