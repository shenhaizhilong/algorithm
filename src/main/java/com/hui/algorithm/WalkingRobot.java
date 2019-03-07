package com.hui.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/10 11:15
 *
 *
 *  Robot Room Cleaner
 *
 *
 * Given a robot cleaner in a room modeled as a grid.
 *
 * Each cell in the grid can be empty or blocked.
 *
 * The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.
 *
 * When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.
 *
 * Design an algorithm to clean the entire room using only the 4 given APIs shown below.
 *
 * interface Robot {
 *   // returns true if next cell is open and robot moves into the cell.
 *   // returns false if next cell is obstacle and robot stays on the current cell.
 *   boolean move();
 *
 *   // Robot will stay on the same cell after calling turnLeft/turnRight.
 *   // Each turn will be 90 degrees.
 *   void turnLeft();
 *   void turnRight();
 *
 *   // Clean the current cell.
 *   void clean();
 * }
 * Example:
 *
 * Input:
 * room = [
 *   [1,1,1,1,1,0,1,1],
 *   [1,1,1,1,1,0,1,1],
 *   [1,0,1,1,1,1,1,1],
 *   [0,0,0,1,0,0,0,0],
 *   [1,1,1,1,1,1,1,1]
 * ],
 * row = 1,
 * col = 3
 *
 * Explanation:
 * All grids in the room are marked by either 0 or 1.
 * 0 means the cell is blocked, while 1 means the cell is accessible.
 * The robot initially starts at the position of row=1, col=3.
 * From the top left corner, its position is one row below and three columns right.
 * Notes:
 *
 * The input is only given to initialize the room and the robot's position internally. You must solve this problem "blindfolded". In other words, you must control the robot using only the mentioned 4 APIs, without knowing the room layout and the initial robot's position.
 * The robot's initial position will always be in an accessible cell.
 * The initial direction of the robot will be facing up.
 * All accessible cells are connected, which means the all cells marked as 1 will be accessible by the robot.
 * Assume all four edges of the grid are all surrounded by wall.
 *
 */
public class WalkingRobot {

    interface Robot {
        // returns true if next cell is open and robot moves into the cell.
        // returns false if next cell is obstacle and robot stays on the current cell.
        boolean move();

        // Robot will stay on the same cell after calling turnLeft/turnRight.
        // Each turn will be 90 degrees.
        void turnLeft();
        void turnRight();

        // Clean the current cell.
        void clean();
    }



    private static final int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};



    private static class MyRobot implements Robot
    {
        private int dir;
        private int R,C;
        private int x,y;
        private int[][] room;
        private static final int Visited = -1; // if visited, mark it as visited
        private static final int Block = 0; // this point is a block
        public MyRobot(int[][] room, int dir, int sx, int sy)
        {
            this.dir = dir;
            this.x = sx;
            this.y = sy;
            this.room = room;
            this.R = room.length;
            this.C = room[0].length;
        }
        @Override
        public void clean() {
            this.room[x][y] = Visited;
        }

        @Override
        public boolean move() {
            int xx = dirs[dir][0] + x;
            int yy = dirs[dir][1] + y;
            if(xx < 0 || xx > R -1 || yy < 0 || yy > C -1 || room[xx][yy] == Block)return false;

            // move forward one step in this direction
            this.x = xx;
            this.y = yy;

            return true;
        }

        @Override
        public void turnLeft() {
            dir = (dir + 3) %4;
        }

        @Override
        public void turnRight() {
            dir = (dir +1) %4;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int[][] getRoom() {
            return room;
        }

        public void setDir(int dir) {
            this.dir = dir;
        }
    }

    public void cleanRoom(MyRobot robot) {
        Set<String> visited = new HashSet<>();
        dfs(robot, 0, robot.getX(), robot.getY(), visited);
    }

    private void dfs(MyRobot robot, int dir, int x, int y, Set<String> visited)
    {
        // visit this point
        robot.clean();
       // System.out.println("*************");
       // Matrix.print(robot.getRoom());
        visited.add(x +"," + y);
        for (int i = 0; i < 4; i++) {
            int curr = (dir + i) %4;  // new direction
            robot.setDir(curr);
            int xx = dirs[curr][0] + x;   // new position
            int yy = dirs[curr][1] + y;
            if(!visited.contains(xx + "," + yy) && robot.move())
            {
                dfs(robot,curr, xx, yy, visited);
                // back to original place

                // turn 180'
                robot.turnRight();
                robot.turnRight();
                robot.move();
                robot.turnLeft();
                robot.turnLeft();
            }
            robot.turnRight();
        }

    }

    public static void main(String[] args) {

        int[][] room = {
                {1,1,1,1,1,0,1,1},
                {1,1,1,1,1,0,1,1},
                {1,0,1,1,1,1,1,1},
                {0,0,0,1,0,0,0,0},
                {1,1,1,1,1,1,1,1}
        };
        MyRobot myRobot = new MyRobot(room, 0,0,0);
        WalkingRobot walkingRobot = new WalkingRobot();
        walkingRobot.cleanRoom(myRobot);
    }


}
