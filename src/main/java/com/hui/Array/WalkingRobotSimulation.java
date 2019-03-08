package com.hui.Array;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/2 12:30
 */
public class WalkingRobotSimulation {


    /**
     *
     * 874. Walking Robot Simulation
     * DescriptionHintsSubmissionsDiscussSolution
     * A robot on an infinite grid starts at point (0, 0) and faces north.  The robot can receive one of three possible types of commands:
     *
     * -2: turn left 90 degrees
     * -1: turn right 90 degrees
     * 1 <= x <= 9: move forward x units
     * Some of the grid squares are obstacles.
     *
     * The i-th obstacle is at grid point (obstacles[i][0], obstacles[i][1])
     *
     * If the robot would try to move onto them, the robot stays on the previous grid square instead (but still continues following the rest of the route.)
     *
     * Return the square of the maximum Euclidean distance that the robot will be from the origin.
     *
     *
     *
     * Example 1:
     *
     * Input: commands = [4,-1,3], obstacles = []
     * Output: 25
     * Explanation: robot will go to (3, 4)
     * Example 2:
     *
     * Input: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
     * Output: 65
     * Explanation: robot will be stuck at (1, 4) before turning left and going to (1, 8)
     *
     *
     * Note:
     *
     * 0 <= commands.length <= 10000
     * 0 <= obstacles.length <= 10000
     * -30000 <= obstacle[i][0] <= 30000
     * -30000 <= obstacle[i][1] <= 30000
     * The answer is guaranteed to be less than 2 ^ 31.
     *
     * @param commands
     * @param obstacles
     * @return
     */
    public int robotSim(int[] commands, int[][] obstacles) {

        int maxDis = 0;
        Set<String> obstacleSet = new HashSet<>();
        for(int[] obstacle:obstacles)
        {
            obstacleSet.add(obstacle[0] + "," + obstacle[1]);
        }

        int dir = 2;
        int[] pos = {0,0};
        for(int cmd: commands)
        {
            if(cmd  < 0)
            {
                dir = (cmd == -1) ? (dir + 1)%4: (dir +3)%4;
                //dir = turn(dir, cmd);
                continue;
            }

            while (cmd-- > 0)
            {
                // new position
                int x = dirs[dir][0] + pos[0];
                int y = dirs[dir][1] + pos[1];
                if(obstacleSet.contains(x + "," + y))continue;
                pos[0] = x;
                pos[1] = y;
                maxDis = Math.max(maxDis, x*x + y*y);
            }


        }
        return maxDis;
    }

    private static final int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
    private int turn(int d, int direction)
    {
        // turn right
        if(direction == -1)
        {
            return (d + 1)%4;
        }
        // turn left
        return (d +3)%4;
    }

//    private static final int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
//    private int[] turn(int[] dir, int direction)
//    {
//
//        // turn right
//        if(direction == -1)
//        {
//            if(dir[0] == 0 && dir[1] == 1)return dirs[2];
//            else if(dir[0] == 1 && dir[1] == 0)return dirs[1];
//            else if(dir[0] == 0 && dir[1] == -1)return dirs[3];
//            return dirs[0];
//
//        }
//        // turn left
//
//        if(dir[0] == 0 && dir[1] == 1)return dirs[3];
//        else if(dir[0] == 1 && dir[1] == 0)return dirs[0];
//        else if(dir[0] == 0 && dir[1] == -1)return dirs[2];
//        return dirs[1];
//
//    }

    public static void main(String[] args) {
        WalkingRobotSimulation walkingRobotSimulation = new WalkingRobotSimulation();
        System.out.println(walkingRobotSimulation.robotSim(new int[]{4,-1,3},new int[][]{}));
        System.out.println(walkingRobotSimulation.robotSim(new int[]{4,-1,4,-2,4},new int[][]{{2,4}}));
    }
}
