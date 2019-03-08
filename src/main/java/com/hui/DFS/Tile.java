package com.hui.DFS;

import com.hui.Array.Matrix;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/18 17:19
 *
 *
 */
public class Tile {

    private int oneCount;
    private int totalCount;
    // 题意：
    //https://acm.ecnu.edu.cn/problem/3297/
    //有一长度为 N(N≤30) 的地板，给定三种不同瓷砖：一种长度为1，一种长度为2，另一种长度为3，数目不限。要求将地板铺满，且任意两个相邻的瓷砖长度均不等。
    //求一共有多少种铺法？在所有的铺设方法中，一共用了长度为1的瓷砖多少块？
    public int[] count(int N)
    {
        this.totalCount = 0;
        this.oneCount = 0;
        dfs(N, 0, 0);
        return new int[]{totalCount, oneCount};
    }

    private void dfs(int N, int pre, int usedOne)
    {
        if(N == 0)
        {
            totalCount++;
            oneCount += usedOne;
            return;
        }else {

            // three different kind type tile
            for (int i = 1; i < 4; i++) {
                if(pre == i || N < i)continue;
                if(i == 1)
                {
                    dfs(N - 1, 1, usedOne + 1);
                }else {
                    dfs(N - i, i, usedOne);
                }
            }
        }
    }


    // 在一个3 X N的长方形方格中，铺满1X2的骨牌（骨牌个数不限制），给定N，求方案数（图一 -1-1为N=2的所有方案），所以N=2时方案数为3。
    // f[n][0] means 3*n + 0
    // f[n][1] means 3*n +1
    // f[n][2] means 3*n +2;
    // https://www.geeksforgeeks.org/tiling-with-dominoes/ 图画的有问题但是答案时对的
    // http://cppblog.com/menjitianya/archive/2015/10/23/212084.html
    // O(n) space
    public  int count3(int N)
    {
        // if N is odd, then can't tile with 1*2
        if((N & 1)== 1)return 0;

        int[][] f = new int[N +1][3];
        f[0][0] = 1;
        f[1][1] = 1;
        f[0][2] = 1;
        for (int i = 2; i <= N; i++) {
            f[i][0] = f[i -2][0] + f[i -1][1] + f[i -2][2];
            f[i][1] = f[i -1][2];
            f[i][2] = f[i][0] + f[i -1][1];
        }

       // Matrix.print(f);
        return f[N][0];

    }

    //we can use O(1) space
    public  int count3_2(int N)
    {
        // if N is odd, then can't tile with 1*2
        if((N & 1)== 1)return 0;
        int p0 = 1;
        int p2 = 1;
        for (int i = 2; i <= N; i +=2) {
          p0 = p0 + 2*p2;
          p2 = p2 + p0;
        }

       return p0;

    }

    // 在一个2 X N的长方形方格中，铺满1X2 或者1X1 的骨牌（骨牌个数不限制），给定N，求方案数
    // f[n,0] = f[n-1,0] + f[n - 2,2] + f(n -1,1)
    // f[n,1] = f[n -1,0] + f[n -1,2] + f[n-1,1]
    // f[n,2] = f[n,0] + f[n,1]
    // O(n) space
    public int count2_n(int N)
    {
       int[][] fn = new int[N +1][3];
       fn[0][0] = 1;
       fn[0][1] = 1;
       fn[0][2] = 2;
       fn[1][0] = 2;
       //fn[2][0] = 7;
       fn[1][1] = 3;
       fn[1][2] = 5;
        for (int i = 2; i <= N; i++) {
            fn[i][0] = fn[i -1][0]  + fn[i -1][1] + fn[i -2][2];
            fn[i][1] = fn[i -1][0] + fn[i -1][1] + fn[i -1][2];
            fn[i][2] = fn[i][0] + fn[i][1];
        }

        Matrix.print(fn);
        return fn[N][0];


    }


    //O(1) space
    public int count2_n2(int N)
    {
        int f2 = 2;  // prepre row[2]
        int f10 = 2; // pre row[0]
        int f11 = 3; // pre row[1]
        int f12 = 5; // pre row[2]
        for (int i = 2; i <= N; i++) {
            int currF10 = f10 + f11 + f2;    // curr row[0]
            int currF11 = f10 + f11 + f12;   // curr row[1]
            int currF12 = currF10 + currF11;  // curr row[2]
            f10 = currF10;  // update pre row[0]
            f11 = currF11;  // update pre row[1]
            f2 = f12;   // update pre pre row[2]
            f12 = currF12;  // update pre row[2]
        }


        return f10;


    }

    //有一个N*M(N<=5,M<=1000)的棋盘，现在有1*2及2*1的小木块无数个，要盖满整个棋盘，有多少种方式？
    // 答案只需要mod1,000,000,007即可。
    //
    //例如：对于一个2*2的棋盘，有两种方法，一种是使用2个1*2的，一种是使用2个2*1的。
    public int countNM(int N, int M)
    {

        int AllState = (1 << N);
        // dp[i][state] 意思是第i -1 行对i 的影响是state 的方法数
        int[][] dp = new int[M +2][AllState];
        dp[1][0] = 1;
        // 枚举行
        for (int i = 1; i <= M; i++) {
            // 枚举状态
            for (int j = 0; j < AllState; j++) {
                if(dp[i][j] > 0)
                {
                    dfs(i,0,j, 0, N, dp );
                }
            }
        }
      //  Matrix.print(dp);
        return dp[M +1][0];
    }
    //https://blog.csdn.net/witnessai1/article/details/70234317
    // http://www.cppblog.com/menjitianya/archive/2014/06/23/207386.html
    private static final int Mod = 1_000_000_007;
    private void dfs(int row, int col, int state, int next, int C, int[][] dp)
    {
        if(col == C)
        {
            dp[row +1][next] += dp[row][state];
            dp[row +1][next] %= Mod;
            return;
        }

        // 如果当前row的state[col] (二进制)已经置为一则考虑下个位置
        if((state & (1 << col)) > 0)
        {
            dfs(row, col +1, state, next, C, dp);
        }

        // if state[col] is 0 then try to place a 2*1
        if((state & (1 << col)) == 0)
        {
            // next[col] = 1
            dfs(row, col +1, state, next | (1 << col), C, dp);
        }

        // if state[col] == 0 && state[col +1] == 0, place a 1*2 in row, then move to col +2 position
        if(col < C -1  && (state & (1 << col)) == 0 &&  (state & (1 <<(col +1))) == 0)
        {
            dfs(row, col +2, state, next, C, dp);
        }

        return;
    }



    public static void main(String[] args) {
        Tile tile = new Tile();
//        Matrix.print(tile.count(4));
//        for (int i = 2; i < 100; i +=2) {
//            System.out.println("i = " + i);
//            System.out.println(tile.count3(i));
//            System.out.println(tile.count3_2(i));
//        }
//
//        System.out.println("*******************");
//        System.out.println(tile.count2_n(6));
//        for (int i = 2; i < 10; i++) {
//            System.out.print(tile.count2_n2(i) +",");
//        }

//        for (int i = 1; i <5 ; i++) {
//            System.out.println(tile.count2_n(i));
//        }

        System.out.println(tile.countNM(4,4));

    }

}
