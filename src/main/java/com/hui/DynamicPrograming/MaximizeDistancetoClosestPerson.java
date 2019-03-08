package com.hui.DynamicPrograming;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/4 16:34
 */
public class MaximizeDistancetoClosestPerson {


    /**
     *
     * 849. Maximize Distance to Closest Person
     * DescriptionHintsSubmissionsDiscussSolution
     * In a row of seats, 1 represents a person sitting in that seat, and 0 represents that the seat is empty.
     *
     * There is at least one empty seat, and at least one person sitting.
     *
     * Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.
     *
     * Return that maximum distance to closest person.
     *
     * Example 1:
     *
     * Input: [1,0,0,0,1,0,1]
     * Output: 2
     * Explanation:
     * If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.
     * If Alex sits in any other open seat, the closest person has distance 1.
     * Thus, the maximum distance to the closest person is 2.
     * Example 2:
     *
     * Input: [1,0,0,0]
     * Output: 3
     * Explanation:
     * If Alex sits in the last seat, the closest person is 3 seats away.
     * This is the maximum distance possible, so the answer is 3.
     * Note:
     *
     * 1 <= seats.length <= 20000
     * seats contains only 0s or 1s, at least one 0, and at least one 1.
     *
     * @param seats
     * @return
     */
    public static int maxDistToClosest(int[] seats) {
        if(seats.length < 2)
            return 0;
        if(seats.length == 2)return 1;


        int slow = nextOne(seats,0);
        int fast = 0;
        int maxDistance = slow;
        if(slow == seats.length -1)
            return slow;


        while (fast < seats.length)
        {

            fast = nextOne(seats,slow + 1);
            if(fast == seats.length)
            {
                maxDistance = Math.max(maxDistance, fast - slow -1);
            }else {
                maxDistance = Math.max(maxDistance, (fast + slow)/2 - slow);
            }
            slow = fast;
        }

        return maxDistance;
    }

    private static int nextOne(int[] seats, int start)
    {
        int i = start;
        for (; i <seats.length ; i++) {
            if(seats[i] == 1)
                break;
        }
        return i;
    }

    public static void main(String[] args) {

       // System.out.println(maxDistToClosest(new int[]{1,0}));
       // System.out.println(maxDistToClosest(new int[]{0,1}));
        System.out.println(maxDistToClosest(new int[]{0,1,1}));
       // System.out.println(maxDistToClosest(new int[]{0,0,1}));
       // System.out.println(maxDistToClosest(new int[]{1,0,1}));
       // System.out.println(maxDistToClosest(new int[]{1,0,0,1}));
       // System.out.println(maxDistToClosest(new int[]{1,0,0,0,1}));
        System.out.println(maxDistToClosest(new int[]{1,0,0,0}));
        System.out.println(maxDistToClosest(new int[]{1,0,0,0,1,0,1}));
    }
}
