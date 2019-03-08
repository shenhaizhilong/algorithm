package com.hui.BFS;
import java.util.*;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/8 16:55
 *
 *定义一个二维数组N*M（其中2<=N<=10;2<=M<=10），如5 × 5数组下所示：
 *
 * int maze[5][5] = {
 *
 * 0, 1, 0, 0, 0,
 *
 * 0, 1, 0, 1, 0,
 *
 * 0, 0, 0, 0, 0,
 *
 * 0, 1, 1, 1, 0,
 *
 * 0, 0, 0, 1, 0,
 *
 * };
 *
 * 它表示一个迷宫，其中的1表示墙壁，0表示可以走的路，只能横着走或竖着走，不能斜着走，要求编程序找出从左上角到右下角的最短路线。入口点为[0,0],既第一空格是可以走的路。
 *
 * Input
 *
 * 一个N × M的二维数组，表示一个迷宫。数据保证有唯一解,不考虑有多解的情况，即迷宫只有一条通道。
 *
 * Output
 *
 * 左上角到右下角的最短路径，格式如样例所示。
 *
 * Sample Input
 *
 * 0 1 0 0 0
 *
 * 0 1 0 1 0
 *
 * 0 0 0 0 0
 *
 * 0 1 1 1 0
 *
 * 0 0 0 1 0
 *
 * Sample Output
 *
 * (0, 0)
 *
 * (1, 0)
 *
 * (2, 0)
 *
 * (2, 1)
 *
 * (2, 2)
 *
 * (2, 3)
 *
 * (2, 4)
 *
 * (3, 4)
 *
 * (4, 4)
 *
 * 输入描述:
 *
 * 输入两个整数，分别表示二位数组的行数，列数。再输入相应的数组，其中的1表示墙壁，0表示可以走的路。数据保证有唯一解,不考虑有多解的情况，即迷宫只有一条通道。
 *
 * 输出描述:
 *
 * 左上角到右下角的最短路径，格式如样例所示。
 *
 * 示例1
 *
 * 输入
 *
 * 5 5
 *
 * 0 1 0 0 0
 *
 * 0 1 0 1 0
 *
 * 0 0 0 0 0
 *
 * 0 1 1 1 0
 *
 * 0 0 0 1 0
 *
 * 输出
 *
 * (0,0)
 *
 * (1,0)
 *
 * (2,0)
 *
 * (2,1)
 *
 * (2,2)
 *
 * (2,3)
 *
 * (2,4)
 *
 * (3,4)
 *
 * (4,4)
 *
 */
public class Maze {

    private static final int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
    private int R,C;
    public List<Node> shortestPath(int[][] maze)
    {
        R = maze.length;
        C = maze[0].length;
        List<Node> ans = new ArrayList<>();
        boolean[][] visited = new boolean[R][C];
        bfs(maze,0,0,visited, ans);
        return ans;
    }

    private class Node{
        int x;
        int y;
        Node pre;

        public Node(int x,int y)
        {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x +", " + y + ")";
        }
    }


    public int bfs(int[][] maze, int x,int y,boolean[][] visited, List<Node> list)
    {

        Deque<Node> queue = new ArrayDeque<>();
        queue.addLast(new Node(x,y));
        visited[x][y] = true;
        while (!queue.isEmpty())
        {
            Node curr = queue.pollFirst();
            for(int[] dir: dirs)
            {
                int xx = curr.x + dir[0];
                int yy = curr.y + dir[1];
                if(xx >= 0 && xx < R && yy >= 0 && yy < C && maze[xx][yy] == 0 && !visited[xx][yy])
                {
                    visited[xx][yy] = true;
                    Node next = new Node(xx,yy);
                    next.pre = curr;
                    if(xx == R -1 && yy == C -1)
                    {
                        curr = next;
                        while (curr != null)
                        {
                            list.add(0, curr);
                            curr = curr.pre;
                        }
                        return 1;
                    }


                    queue.addLast(next);

                }
            }
        }


        // no answer
        return -1;

    }



    public static void main(String[] args) {

        Maze maze = new Maze();
       List<Node> path =  maze.shortestPath(new int[][]{
                {0,1,0,0,0},
                {0,1,0,1,0},
                {0,0,0,0,0},
                {0,1,1,1,0},
                {0,0,1,0,0}
        });

       for(Node p:path)
       {
           System.out.println(p);
       }
    }

}
